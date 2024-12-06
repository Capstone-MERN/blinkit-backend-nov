package com.CapStone.blinkitservice.configuration.jwt;

import com.CapStone.blinkitservice.auth.model.JwtAuthResponse;
import com.CapStone.blinkitservice.common.StringConstants;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.*;

@Component
public class JwtManager {

    private final String privateKey = StringConstants.PRIVATEKEY;

    public String generateToken(String email) {
        Map<String, Object> claims = new HashMap<>();                 // payload will be stored in this
        claims.put(StringConstants.EMAIL, email);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 1);                        // setting expiration time to 1 hour

        return Jwts.builder()
                .setIssuedAt(new Date())
                .setClaims(claims)
                .setExpiration(calendar.getTime())
                .signWith(SignatureAlgorithm.HS256, privateKey.getBytes(StandardCharsets.UTF_8))
                .compact();
    }

    public boolean validateToken(String token) {
        try {

            Jwts.parser()
                    .setSigningKey(privateKey.getBytes(StandardCharsets.UTF_8))
                    .parseClaimsJws(token)                             // to decode the token
                    .getBody();

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public JwtAuthResponse getUserInfo(String token) {
        try {
            Map<String, Object> claims = Jwts.parser()
                    .setSigningKey(privateKey.getBytes(StandardCharsets.UTF_8))
                    .parseClaimsJws(token)
                    .getBody();

            return new JwtAuthResponse(
                    claims.get(StringConstants.EMAIL).toString()
            );
        } catch (Exception e) {
            return null;
        }
    }
}