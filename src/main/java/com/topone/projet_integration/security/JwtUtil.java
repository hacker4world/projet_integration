package com.topone.projet_integration.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;

@Component
public class JwtUtil {
    private final String key = "fq0aYMYCm06/5Da7RJFJhDDTv7hcbI0FNnxoCSm7BGPxJ25SgJZypoPsYDs7e2IcGQXEjdaorcCHdqMAxvuJLnNdl2PMsLwtGERV16xonloBWk8n4/taZh3pfCr4UMTmsEqwqbABrpb0OEXkuFF1T+ILb7VsDy+3O4D2Mut5NIc8iRnGRKR992HVGcpZ/ZY/8yXh3X4v7iHnKPPPNoZwMbbxV3jDCtZ50r0s8LRvFuFw7vCORhvEYxPGkrmLTQcq8wqOJg8gbv2LAwl5fWeeo5YCtp2KyR9kNqojK2QHJJ5uhXWTohAEE+I3ugRfUaHIiViG7vIY/S0DaOXTDouHTT7F06L6UtmJyTL8PkzWqGI=";

    public String createToken(HashMap<String, String> claims, String email) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }

    public String extractEmail(String token) {
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token, String email) {
        return email.equals(extractEmail(token));
    }

}
