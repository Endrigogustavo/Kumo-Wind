package kumo.api.api.Application.Configs.Security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.context.annotation.Configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Configuration
public class JWTConfig {
    
      private final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String generateToken(String userId) {
        return Jwts.builder()
                   .setSubject(userId)
                   .setIssuedAt(new Date(System.currentTimeMillis()))
                   .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) 
                   .signWith(SECRET_KEY)
                   .compact();
    }

    public String extractUserId(String token) {
        Claims claims = Jwts.parserBuilder()
                            .setSigningKey(SECRET_KEY)
                            .build()
                            .parseClaimsJws(token)
                            .getBody();
        return claims.getSubject();
    }

    public boolean isTokenValid(String token) {
        return !Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getExpiration()
                    .before(new Date());
    }
}
