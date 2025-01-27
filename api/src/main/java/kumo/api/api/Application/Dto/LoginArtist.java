package kumo.api.api.Application.Dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;
import kumo.api.api.Application.Configs.CookieConfig;
import kumo.api.api.Application.Configs.JWTConfig;
import kumo.api.api.Domain.Services.ArtistService;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/auth")
public class LoginArtist {
    

    private final AuthenticationManager authenticationManager;
    private final JWTConfig jwtConfig;

    @Autowired
    private CookieConfig cookieConfig;

    @Autowired
    private ArtistService service;

    @Autowired
    public LoginArtist(AuthenticationManager authenticationManager, JWTConfig jwtConfig) {
        this.authenticationManager = authenticationManager;
        this.jwtConfig = jwtConfig;
    }


   @PostMapping("/login")
   public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password, HttpServletResponse response) {
        try {
            service.loginArtist(email, password, response);
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            
            return ResponseEntity.ok("Login bem-sucedido" + authentication);

        } catch (Exception e) {
            return ResponseEntity.status(401).body("Credenciais inválidas");
        }
    }
}
