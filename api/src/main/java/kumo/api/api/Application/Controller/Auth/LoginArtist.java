package kumo.api.api.Application.Controller.Auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kumo.api.api.Application.Configs.Security.CookieConfig;
import kumo.api.api.Application.Configs.Security.JWTConfig;
import kumo.api.api.Domain.Services.ArtistService;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/auth")
public class LoginArtist {

    private final AuthenticationManager authenticationManager;
    private final JWTConfig jwtConfig;

    @Autowired
    private ArtistService service;

    @Autowired
    private CookieConfig cookieConfig;

    @Autowired
    public LoginArtist(AuthenticationManager authenticationManager, JWTConfig jwtConfig) {
        this.authenticationManager = authenticationManager;
        this.jwtConfig = jwtConfig;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password,
            HttpServletResponse response) {
        try {
            service.loginArtist(email, password, response);
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            return ResponseEntity.ok("Login bem-sucedido" + authentication);

        } catch (Exception e) {
            return ResponseEntity.status(401).body("Credenciais inválidas");
        }
    }

    @GetMapping("/user-auth")
    public Authentication getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalStateException("No user is currently authenticated.");
        }
        return authentication;
    }

    @GetMapping("/user-details")
    public Object getUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalStateException("No user is currently authenticated.");
        }
        return authentication.getPrincipal();
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        try {
            cookieConfig.DeleteCookies(response);
            SecurityContextHolder.getContext().setAuthentication(null);
            SecurityContextHolder.clearContext();
             HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
            return "User logged out successfully.";
        } catch (Exception e) {
            return "Error in logout" + e.getMessage();
        }
    }
}
