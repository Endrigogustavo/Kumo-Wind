package kumo.wind.api.Adapter.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import kumo.wind.api.Adapter.Dto.Request.UpdateUserDTO;
import kumo.wind.api.Adapter.Dto.Response.GetMyUserInfoResponseDTO;
import kumo.wind.api.Adapter.Dto.Response.UpdateResponseDTO;
import kumo.wind.api.Driver.Repository.UserRepository;
import kumo.wind.api.Entity.Model.ArtistSchema;
import kumo.wind.api.UseCase.Services.ArtistService;

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
            return ResponseEntity.status(HttpStatus.OK).body(artists);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao buscar artistas: " + e.getMessage());
        }
    }

    @GetMapping("/artistById/{id}")
    public ResponseEntity<?> getByIdArtist(@PathVariable String id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(repository.findById(id).get());
        } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao buscar artista: " + e.getMessage());
        } 
    }

    @GetMapping("/findMyArtist")
    public ResponseEntity<?> findMyArtist(@CookieValue(value = "token", defaultValue = "null") String token) {
        try {
            ArtistSchema artis = service.findMyArtist(token); 
            return ResponseEntity.status(HttpStatus.OK).body(new GetMyUserInfoResponseDTO(artis.getName(), artis.getEmail(), artis.getPhone()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao buscar artista: " + e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateArtist(@RequestBody @Valid UpdateUserDTO artist,
            @CookieValue(value = "token", defaultValue = "null") String token) {
        try {
            UpdateResponseDTO result = service.updateArtist(artist, token);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao atualizar artista: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteArtist(@CookieValue(value = "token", defaultValue = "null") String token) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.deleteArtist(token));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao deletar artista: " + e.getMessage());
        }
    }
}
