package me.kreal.attendance.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import me.kreal.attendance.DTO.ProfileDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Component
@PropertySource("classpath:application.properties")
public class JwtProvider {

    @Value("${security.jwt.token.key}")
    private String key;

    public String createToken(ProfileDTO profileDTO){
        //Claims is essentially a key-value pair, where the key is a string and the value is an object
        Claims claims = Jwts.claims().setSubject(profileDTO.getFullName());
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("read"));
        claims.put("authorities", authorities);
        claims.put("email", profileDTO.getEmail());
        claims.put("zoom_id", profileDTO.getId());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, key) // algorithm and key to sign the token
                .compact();
    }

    public Optional<AuthUserDetail> resolveToken(HttpServletRequest request){

        try {

            String token = Arrays.stream(request.getCookies())
                    .filter(c -> c.getName().equals("token"))
                    .map(Cookie::getValue).findFirst()
                    .orElse("");
            Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody(); // decode

            String username = claims.getSubject();
            List<LinkedHashMap<String, String>> permissions = (List<LinkedHashMap<String, String>>) claims.get("authorities");

            // convert the permission list to a list of GrantedAuthority
            List<GrantedAuthority> authorities = permissions.stream()
                    .map(p -> new SimpleGrantedAuthority(p.get("authority")))
                    .collect(Collectors.toList());

            //return a userDetail object with the permissions the user has
            return Optional.of(AuthUserDetail.builder()
                    .username(username)
                    .email((String) claims.get("email"))
                    .zoom_id((String) claims.get("zoom_id"))
                    .authorities(authorities)
                    .build());

        } catch (Exception e) {
            return Optional.empty();
        }

    }

}
