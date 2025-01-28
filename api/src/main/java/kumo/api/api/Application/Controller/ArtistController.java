package kumo.api.api.Application.Controller;

import kumo.api.api.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
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
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;

import kumo.api.api.Application.Configs.Security.CookieConfig;
import kumo.api.api.Application.Dto.Request.CreateRequestDTO;
import kumo.api.api.Application.Dto.Request.UpdateUserDTO;
import kumo.api.api.Application.Dto.Response.CreateResponseDTO;
import kumo.api.api.Application.Dto.Response.UpdateResponseDTO;
import kumo.api.api.Domain.Entity.ArtistSchema;
import kumo.api.api.Domain.Services.ArtistService;

@RestController
@RequestMapping("/artists")
public class ArtistController {

    @Autowired
    private UserRepository repository;

    @Autowired
    private ArtistService service;

    @Autowired
    private CookieConfig security;


    @GetMapping
    public String getArtist() {
        return "Hello, artist!";
    }

    @PostMapping("/create")
    public ResponseEntity<?> createArtist(@RequestBody CreateRequestDTO artist, HttpServletResponse response) {
        try {
            CreateResponseDTO responseCreate = service.createArtist(artist, response);
            return ResponseEntity.ok().body(responseCreate);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao criar cookies: " + e.getMessage());
        }
    }

    @GetMapping("/allArtists")
    public ResponseEntity<?> getAllArtists() {
        try {
            return ResponseEntity.ok(service.getAllArtist());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao buscar artistas: " + e.getMessage());
        }
    }

    @GetMapping("/artistById/{id}")
    public ArtistSchema getByIdArtist(@PathVariable String id) {
        return repository.findById(id).get();
    }

    @GetMapping("/findMyArtist")
    public ResponseEntity<?> findMyArtist(@CookieValue(value = "token", defaultValue = "null") String token) {
        try {
            return ResponseEntity.ok(service.findMyArtist(token));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao buscar artista: " + e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateArtist(@RequestBody @Valid UpdateUserDTO artist,
            @CookieValue(value = "token", defaultValue = "null") String token) {
        try {
            UpdateResponseDTO result = service.updateArtist(artist, token);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao atualizar artista: " + e.getMessage());
        }
    }

    @PatchMapping("/updateEmail")
    public ResponseEntity<?> updateEmail(@RequestBody String Email,
            @CookieValue(value = "token", defaultValue = "null") String token) {
        try {
            return ResponseEntity.ok(service.UpdateEmailArtist(Email, token));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao atualizar o email: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteArtist(@CookieValue(value = "token", defaultValue = "null") String token) {
        try {
            return ResponseEntity.ok(service.deleteArtist(token));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao deletar artista: " + e.getMessage());
        }
    }

}
