package kumo.api.api.Application.Controller;

import kumo.api.api.Repository.Repository;

import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestBody;

import kumo.api.api.Application.Configs.CookieConfig;
import kumo.api.api.Application.Configs.JWTConfig;
import kumo.api.api.Domain.Model.ArtistSchema;
import kumo.api.api.Domain.Services.ArtistService;

@RestController
@RequestMapping("/artists")
public class ArtistController {

    @Autowired
    private Repository repository;

    @Autowired
    private ArtistService service;

    @Autowired
    private CookieConfig security;

    @Autowired
    private JWTConfig jwtConfig;

    @GetMapping
    public String getArtist() {
        return "Hello, artist!";
    }

    @PostMapping("/createArtist")
    public ResponseEntity<?> createArtist(@RequestBody ArtistSchema artist, HttpServletResponse response) {
        try {
            if(artist.getName() == null || artist.getEmail() == null || artist.getPhone() == null || artist.getPass() == null) {
                return ResponseEntity.badRequest().body("Erro ao criar artista: campos obrigatórios não preenchidos.");
            }else{
            service.createArtist(artist);
            try {
                String token = jwtConfig.generateToken(artist.getId());
                security.CreateCookies(response, token);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Erro ao criar cookies: " + e.getMessage());
            }
            return new ResponseEntity<ArtistSchema>(artist, HttpStatus.CREATED);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao criar artista: " + e.getMessage());
        }      
    }

    @GetMapping("/allArtists")
    public ResponseEntity<?> getAllArtists() {
        try {
            ;
            if(service.getAllArtist().isEmpty()){
                return null;
            }else{
                return ResponseEntity.ok(service.getAllArtist());
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao buscar artistas: " + e.getMessage());
        }
        
    }

    @GetMapping("/artistById/{id}")
    public ArtistSchema getByIdArtist(@PathVariable String id){
        return repository.findById(id).get();
    }

    @GetMapping("/findMyArtist")
    public ResponseEntity<?> findMyArtist(@CookieValue(value = "token", defaultValue = "null") String token) {
        try {
            if(token == "null"){
                return ResponseEntity.badRequest().body("Erro ao buscar artista: token não encontrado.");
            }
            if(jwtConfig.isTokenValid(token) == true){
                String tokenJWT = jwtConfig.extractUserId(token);
                return ResponseEntity.ok(repository.findById(tokenJWT).get());
            }
            else{
                return ResponseEntity.badRequest().body("Erro ao buscar artista: token inválido.");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao buscar artista: " + e.getMessage());
        }
    }

    @PutMapping("/updateArtist")
    public ResponseEntity<?> updateArtist(@RequestBody ArtistSchema artist, @CookieValue(value = "token", defaultValue = "null") String token) {
        try {
            if(artist.getName() == null || artist.getEmail() == null) {
                return ResponseEntity.badRequest().body("Erro ao atualizar artista: campos obrigatórios não preenchidos.");
            }else{
                String tokenId = jwtConfig.extractUserId(token);
                service.updateArtist(artist, tokenId);
                return ResponseEntity.ok(artist);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao atualizar artista: " + e.getMessage());
        }
    }

    @PostMapping("/loginArtist")
    public ResponseEntity<?> loginArtist(@RequestBody ArtistSchema artist, HttpServletResponse response) {
        try {
            if (artist.getEmail() == null || artist.getPass() == null) {
                return ResponseEntity.badRequest().body("Erro ao logar artista: campos obrigatórios não preenchidos.");
            } else {
                boolean loginSuccessful = service.loginArtist(artist.getEmail(), artist.getPass(), response);
                if (loginSuccessful) {
                    return ResponseEntity.ok().body("Login realizado com sucesso.");
                } else {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Erro ao logar artista: credenciais inválidas.");
                }
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao logar artista: " + e.getMessage());
        }
    }
    

    @PostMapping("/logoutArtist")
    public ResponseEntity<?> logoutArtist(HttpServletResponse response){
        try {
            security.DeleteCookies(response);
            return ResponseEntity.ok("Logout realizado com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao realizar logout: " + e.getMessage());
        }
    }

    @PatchMapping("/updateEmail")
    public ResponseEntity<?> updateEmail(@RequestBody String Email, @CookieValue(value = "token", defaultValue = "null") String token){
        try {
            if(Email == null){
                return ResponseEntity.badRequest().body("Erro ao atualizar o email: campo obrigatório não preenchido.");
            }else{
                String tokenId = jwtConfig.extractUserId(token);
                service.UpdateEmailArtist(Email, tokenId);
                return ResponseEntity.ok("Email atualizado com sucesso.");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao atualizar o email: " + e.getMessage());
        }
    }

    @DeleteMapping("/deleteArtist")
    public ResponseEntity<?> deleteArtist(@CookieValue(value = "token", defaultValue = "null") String token){
        try {
            if(token == "null"){
                return ResponseEntity.badRequest().body("Erro ao deletar artista: token não encontrado.");
            }
            if(jwtConfig.isTokenValid(token) == true){
                String tokenJWT = jwtConfig.extractUserId(token);
                service.deleteArtist(tokenJWT);
                return ResponseEntity.ok("Artista deletado com sucesso.");
            }
            else{
                return ResponseEntity.badRequest().body("Erro ao deletar artista: token inválido.");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao deletar artista: " + e.getMessage());
        }
    }

}
