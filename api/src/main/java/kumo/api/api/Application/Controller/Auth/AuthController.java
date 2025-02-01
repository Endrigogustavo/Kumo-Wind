package kumo.api.api.Application.Controller.Auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import kumo.api.api.Application.Dto.Request.CreateRequestDTO;
import kumo.api.api.Application.Dto.Request.LoginRequestDTO;
import kumo.api.api.Application.Dto.Response.LoginAndCreateReponseDTO;
import kumo.api.api.Domain.Services.Auth.AuthArtistService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthArtistService auth;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequestDTO body, HttpServletResponse response)
    {
        try {
            String token = auth.loginArtist(body, response);
            return ResponseEntity.ok(new LoginAndCreateReponseDTO(body.email(), token));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid CreateRequestDTO body, HttpServletResponse response) {
        try {
            String token = auth.registerArtist(body, response);
            return ResponseEntity.ok(new LoginAndCreateReponseDTO(body.getName(), token));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@CookieValue(value = "token", defaultValue = "null") @Valid String token){
        return null;
    }
}