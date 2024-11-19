package com.CapStone.BlinkIt_BackEnd.auth;

import com.CapStone.BlinkIt_BackEnd.common.Role;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtManager {

    private String privateKey = "accio_capstone_blinkit_clone";

    public String generateToken(String userId){
        Map<String, Object> claims = new HashMap<>();                   //payload will be stored in this
        claims.put("id", userId);
        //claims.put("role", String.valueOf(userRole));       ** role is removed currently

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 1);  // Set expiration time (e.g., 1 hour)

        return Jwts.builder()
                .setIssuedAt(new Date())
                .setClaims(claims)
                .setExpiration(calendar.getTime())
                .signWith(SignatureAlgorithm.HS256, privateKey)
                .compact();
    }

    public boolean validateToken(String token){
        try{
            Jwts.parser()
                    .setSigningKey(privateKey)
                    .parseClaimsJwt(token)            // to decode the token
                    .getBody();
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public UserAuthResponse getUserInfo(String token) {
        try {
            Map<String, Object> claims = Jwts.parser()
                    .setSigningKey(privateKey)
                    .parseClaimsJws(token)
                    .getBody();

            return new UserAuthResponse(
                    claims.get("id").toString()
                    //Role.valueOf(claims.get("role").toString())       ** role is removed currently
            );
        } catch (Exception e) {
            return null;
        }
    }
}
