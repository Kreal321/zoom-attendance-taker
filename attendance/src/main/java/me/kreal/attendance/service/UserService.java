package me.kreal.attendance.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.kreal.attendance.DTO.ProfileDTO;
import me.kreal.attendance.DTO.ZoomTokenDTO;
import me.kreal.attendance.config.ZoomAPI;
import me.kreal.attendance.config.ZoomConfig;
import me.kreal.attendance.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class UserService {

    private final ZoomConfig zoomConfig;
    private final JwtProvider jwtProvider;

    protected RestTemplate restTemplate = new RestTemplate();

    @Autowired
    public UserService(ZoomConfig zoomConfig, JwtProvider jwtProvider) {
        this.zoomConfig = zoomConfig;
        this.jwtProvider = jwtProvider;
    }

    public String getAuthorizeUrl() {
        return this.zoomConfig.getGeneratedAuthorizeUrl();
    }

    private Optional<ZoomTokenDTO> getZoomTokenFromCode(String code) {
        HttpHeaders header = new HttpHeaders();
        header.set("Authorization", this.zoomConfig.getBase64Encoded());
        header.set("Content-Type", "application/x-www-form-urlencoded");

        MultiValueMap<String, String> bodyParamMap = new LinkedMultiValueMap<>();
        bodyParamMap.add("code", code);
        bodyParamMap.add("grant_type", "authorization_code");
        bodyParamMap.add("redirect_uri", this.zoomConfig.getRedirectUrl());

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(bodyParamMap, header);

        ResponseEntity<ZoomTokenDTO> result = this.restTemplate.postForEntity(this.zoomConfig.getTokenUrl(), requestEntity, ZoomTokenDTO.class);

        if (result.getStatusCode().equals(HttpStatus.OK)) {
            return Optional.ofNullable(result.getBody());
        }

        return Optional.empty();
    }

    private Optional<ProfileDTO> getUserProfile(String code) {

        Optional<ZoomTokenDTO> zoomTokenDTOOptional = this.getZoomTokenFromCode(code);

        if (!zoomTokenDTOOptional.isPresent()) return Optional.empty();

        ZoomAPI zoomAPI = new ZoomAPI(zoomTokenDTOOptional.get().getAccess_token());

        return Optional.ofNullable(zoomAPI.getProfile());
    }

    public Optional<String> issueToken(String code) {

        Optional<ProfileDTO> profileOptional = this.getUserProfile(code);

        if (!profileOptional.isPresent()) return Optional.empty();

        return Optional.ofNullable(jwtProvider.createToken(profileOptional.get()));
    }
}
