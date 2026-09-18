package org.example.backend.security;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long expiration;

    private SecretKey getSignature(){
        byte[] keyBytes= Base64.getDecoder().decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(UserDetails userDetails){
        return Jwts.builder().subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration( new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignature())
                .compact();
    }

    public String extractUser(String token){
        return Jwts.parser().verifyWith(getSignature())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    private Date extractExpiration(String token){
        return Jwts.parser().verifyWith(getSignature())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration();
    }

    public boolean isExpiredToken(String token){
        return extractExpiration(token).before(new Date());
    }

    public boolean isValidToken(String token){
        return !isExpiredToken(token);
    }
}
