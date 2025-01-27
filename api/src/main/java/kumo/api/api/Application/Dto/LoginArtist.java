package kumo.api.api.Application.Dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import kumo.api.api.Application.Configs.JWTConfig;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/login")
public class LoginArtist {
    

    private final AuthenticationManager authenticationManager;
    private final JWTConfig jwtConfig;

    @Autowired
    public LoginArtist(AuthenticationManager authenticationManager, JWTConfig jwtConfig) {
        this.authenticationManager = authenticationManager;
        this.jwtConfig = jwtConfig;
    }


   @PostMapping("/login")
    public String login(@RequestBody LoginArtistRequest artist) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(artist.getEmail(), artist.getPassword());
            
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            
            SecurityContextHolder.getContext().setAuthentication(authentication);
            
           
            return "Logado com sucesso"; 
        } catch (BadCredentialsException ex) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciais inválidas", ex);
        }
    }
}
