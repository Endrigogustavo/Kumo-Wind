package kumo.wind.api.Adapter.Controller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kumo.wind.api.Adapter.Dto.Request.UpdateArtRequestDTO;
import kumo.wind.api.Adapter.Dto.Response.CreateArtResponseDTO;
import kumo.wind.api.Adapter.Dto.Response.UpdateArtResponseDTO;
import kumo.wind.api.Entity.Model.ArtSchema;
import kumo.wind.api.UseCase.Services.ArtService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/art")
@RequiredArgsConstructor
public class ArtController {

    private final ArtService artService;

    @PostMapping("/create")
    public ResponseEntity<?> createArt(
            @RequestParam("file") MultipartFile art,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @CookieValue(name = "token", defaultValue = "null") String token) {
        try {
            String URL = this.artService.createArt(art, title, description, token);
            return ResponseEntity.status(HttpStatus.CREATED).body(new CreateArtResponseDTO(title, description, URL));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao salvar a arte: " + e.getMessage());
        }
    }

    @GetMapping("/getArts")
    public ResponseEntity<?> getArtByArtist(@CookieValue(value = "token", defaultValue = "null") String token) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(artService.getArtByArtist(token));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao buscar artes: " + e.getMessage());
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            String imageUrl = artService.uploadImage(file);
            return ResponseEntity.ok(imageUrl);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Erro ao fazer upload da imagem.");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteArt(@PathVariable String id) throws Exception {
            String response = artService.deleteArt(id);
            return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateArt(@PathVariable String id, UpdateArtRequestDTO art, MultipartFile file) throws Exception {
        ArtSchema response = artService.updateArt(art, file, id);
        return ResponseEntity.ok().body(new UpdateArtResponseDTO(response.getTitle(), response.getDescription()));
    }

    @GetMapping("/getAllArts")
    public ResponseEntity<?> getAllArts() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(artService.getAllArts());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao buscar artes: " + e.getMessage());
        }
    }
}
