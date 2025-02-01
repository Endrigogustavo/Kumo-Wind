package kumo.api.api.Domain.Services.Auth;

import java.util.Date;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.HttpServletResponse;
import kumo.api.api.Application.Configs.Security.CookieConfig;
import kumo.api.api.Application.Configs.Security.TokenService;
import kumo.api.api.Application.Dto.Request.CreateRequestDTO;
import kumo.api.api.Application.Dto.Request.LoginRequestDTO;
import kumo.api.api.Domain.Entity.ArtistSchema;
import kumo.api.api.Repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthArtistService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final CookieConfig cookie;

    public String loginArtist(LoginRequestDTO body, HttpServletResponse response) throws JwtException {
        try {
            ArtistSchema user = this.repository.findByEmail(body.email())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (passwordEncoder.matches(body.password(), user.getPassword())) {
            String tokenId = tokenService.generateTokenId(user.getId());
            cookie.CreateCookies(response, tokenId);
            String token = this.tokenService.generateToken(user);
            return token;
        }
        } catch (Exception e) {
            return "Erro ao fazer login: " +e.getMessage();
        }
        return null;
    }

    public String registerArtist(CreateRequestDTO body, HttpServletResponse response) {
        Optional<ArtistSchema> user = this.repository.findByEmail(body.getEmail());
        try {
            if (user.isEmpty()) {
                ArtistSchema newUser = new ArtistSchema();
                newUser.setPassword(passwordEncoder.encode(body.getPassword()));
                newUser.setEmail(body.getEmail());
                newUser.setName(body.getName());
                newUser.setCreatedAt(new Date(System.currentTimeMillis()));
                newUser.setRole("artist");
                newUser.setPhone(body.getPhone());
                this.repository.save(newUser);
    
                String tokenId = tokenService.generateTokenId(newUser.getId());
                cookie.CreateCookies(response, tokenId);
    
                String token = this.tokenService.generateToken(newUser);
                return token;
            }
        } catch (Exception e) {
            return "Erro ao fazer o registro " +e.getMessage();
        }
        
        return null;
    }
}
