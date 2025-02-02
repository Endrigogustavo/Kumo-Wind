package kumo.api.api.Application.Controller;

import kumo.api.api.Repository.UserRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;

import kumo.api.api.Application.Dto.Request.UpdateUserDTO;
import kumo.api.api.Application.Dto.Response.GetMyUserInfoResponseDTO;
import kumo.api.api.Application.Dto.Response.UpdateResponseDTO;
import kumo.api.api.Domain.Entity.ArtistSchema;
import kumo.api.api.Domain.Services.ArtistService;

@RestController
@RequestMapping("/artists")
@SecurityRequirement(name = "Bearer Authentication")
public class ArtistController {

    @Autowired
    private UserRepository repository;

    @Autowired
    private ArtistService service;

    @GetMapping
    public String getArtist() {
        return "Hello, artist!";
    }

    @GetMapping("/allArtists")
    public ResponseEntity<?> getAllArtists() {
        try {
            List<ArtistSchema> artists = service.getAllArtist();
            return ResponseEntity.ok().body(artists);
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
            ArtistSchema artis = service.findMyArtist(token); 
            return ResponseEntity.ok().body(new GetMyUserInfoResponseDTO(artis.getName(), artis.getEmail(), artis.getPhone()));
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

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteArtist(@CookieValue(value = "token", defaultValue = "null") String token) {
        try {
            return ResponseEntity.ok(service.deleteArtist(token));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao deletar artista: " + e.getMessage());
        }
    }
}
