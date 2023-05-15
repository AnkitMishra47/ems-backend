package com.ems.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;

@Component
public class JwtUtils {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    private SecretKey jwtSecret = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    public SecretKey getJwtSecret() {
        return jwtSecret;
    }

    @Value("${jwt.expiration}")
    private int jwtExpirationMs;

    public String generateJwtToken(Authentication authentication) {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return this.generateToken(userDetails);
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(jwtSecret)
                .compact();
    }


    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            if (!isTokenExpired(authToken))
            {
                Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
                return true;
            }
        } catch (Exception e) {
            logger.error("JWT authentication error: {}", e.getMessage());
        }

        return false;
    }

    public  List<GrantedAuthority> getRolesFromJwtToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        List<String> roles = (List<String>) claims.get("roles");
        return roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    public Date getExpirationDateFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return claims.getExpiration();
    }

    public boolean isTokenExpired(String token) {
        Date expiration = getExpirationDateFromToken(token);
        logger.info("Date", expiration.toString());
        return expiration.before(new Date());
    }
}
