package com.child.service.Utility;


import ch.qos.logback.core.subst.Token;
import com.child.model.response.CredentialPayload;
import com.child.model.response.Tokens;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtils {

    private static final long JWT_TOKEN_VALIDITY = 120;
    public static String doGenerateToken(String subject) {
        return Jwts.builder()
                .setSubject(subject)
                .signWith(SignatureAlgorithm.HS256, SecretKeyConstant.SECRET_KEY_CONSTANT.name())
                .setExpiration(new Date(System.currentTimeMillis()+JWT_TOKEN_VALIDITY*60*100))
                .setIssuedAt(new Date()).compact();
    }

    public static Tokens generateToken(CredentialPayload cp) {
        String token = doGenerateToken(cp.getUsername());
        return new Tokens(token);
    }

    public static Claims validateToken(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(SecretKeyConstant.SECRET_KEY_CONSTANT.name()).parseClaimsJws(token).getBody();
            return claims;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getUserNameFromToken(String token){
        Claims claim = Jwts.parser()
                .setSigningKey(SecretKeyConstant.SECRET_KEY_CONSTANT.name())
                .parseClaimsJws(token)
                .getBody();

          String username =claim.getSubject();
          return username;

    }


}
