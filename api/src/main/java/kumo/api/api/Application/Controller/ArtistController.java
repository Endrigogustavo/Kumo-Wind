package kumo.api.api.Application.Controller;

import kumo.api.api.Repository.Repository;
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

import kumo.api.api.Application.Configs.SecurityConfig;
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
    private SecurityConfig security;

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
                security.CreateCookies(response, artist.getId());
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
            if(repository.findAll().isEmpty()){
                return null;
            }else{
                return ResponseEntity.ok(repository.findAll());
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
            }else{
                return ResponseEntity.ok(repository.findById(token).get());
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
                service.updateArtist(artist, token);
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
    public ResponseEntity<?> updateEmail(){
        return null;
    }

    @DeleteMapping("/deleteArtist")
    public ResponseEntity<?> deleteArtist(){
        return null;
    }
}

//https://youtube.com/playlist?list=PLA7e3zmT6XQUjrwAoOHvNu80Axuf-3jft&si=TWLtJJ9TxIBuZnmm
