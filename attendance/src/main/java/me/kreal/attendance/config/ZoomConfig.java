package me.kreal.attendance.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Base64;

@Configuration
@PropertySource("classpath:application.properties")
public class ZoomConfig {

    @Value("${oauth2.zoom.clientId}")
    private String clientId;

    @Value("${oauth2.zoom.clientSecret}")
    private String clientSecret;

    @Value("${oauth2.zoom.redirectUrl}")
    private String redirectUrl;

    @Value("${oauth2.zoom.authorizeUrl}")
    private String authorizeUrl;

    @Value("${oauth2.zoom.tokenUrl}")
    private String tokenUrl;

    public String getGeneratedAuthorizeUrl() {
        return this.authorizeUrl + "?response_type=code&client_id=" + this.clientId + "&redirect_uri=" + this.redirectUrl;
    }

    public String getTokenUrl() {
        return this.tokenUrl;
    }

    public String getRedirectUrl() {
        return this.redirectUrl;
    }

    public String getBase64Encoded() {
        return "Basic " + Base64.getEncoder().encodeToString((this.clientId + ":" + this.clientSecret).getBytes());
    }

}
