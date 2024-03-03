package com.backend.EEA.common.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class JwtUtil {
    private static final String PREFIX = "Bearer ";
    public static String generateToken(String signingKey, String subject) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        JwtBuilder builder = Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS256, signingKey);

        return builder.compact();
    }

    public static Claims getSubject(HttpServletRequest httpServletRequest, String jwtTokenCookieName, String signingKey){
      //  String token = CookieUtil.getValue(httpServletRequest, jwtTokenCookieName);
         String token = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        token = token.replace(PREFIX, "");
        return Jwts.parser().setSigningKey(signingKey.getBytes()).parseClaimsJws(token).getBody();
    }
}