package kumo.api.api.Application.Controller;

import java.io.IOException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiOperation;
import kumo.api.api.Application.Dto.Request.CreateArtRequestDTO;
import kumo.api.api.Application.Dto.Request.UpdateArtRequestDTO;
import kumo.api.api.Application.Dto.Response.CreateArtResponseDTO;
import kumo.api.api.Application.Dto.Response.GetArtsArtistsResponseDTO;
import kumo.api.api.Application.Dto.Response.UpdateArtResponseDTO;
import kumo.api.api.Domain.Entity.ArtSchema;
import kumo.api.api.Domain.Services.ArtService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/art")
@RequiredArgsConstructor
public class ArtController {

    private final ArtService artService;

    @ApiOperation(value = "Faz o upload de uma imagem", consumes = "multipart/form-data")
   
    @PostMapping("/create")
    public ResponseEntity<?> createArt(
        @RequestParam("file") MultipartFile art,
        @RequestParam("title") String title, 
        @RequestParam("description") String description,
        @CookieValue(name = "token", defaultValue = "null") String token){
            try {
                String URL = this.artService.createArt(art, title, description, token);
                return ResponseEntity.ok().body(new CreateArtResponseDTO(title, description, URL));
            } catch (Exception e) {
                return null;
            }
    }

    @GetMapping("/getArts/")
    public List<ArtSchema> getArtByArtist(@CookieValue(value = "token", defaultValue = "null") String token) {
        return artService.getArtByArtist(token);
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

    @DeleteMapping("/delete/{artId}")
    public ResponseEntity<?> deleteArt(@PathVariable String id) throws Exception{
        String response = artService.deleteArt(id);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateArt(UpdateArtRequestDTO art) throws Exception{
        ArtSchema response = artService.updateArt(art);
        return ResponseEntity.ok().body(new UpdateArtResponseDTO(response.getTitle(), response.getDescription()));
    }

    @PatchMapping("/updateImg/{imageId}")
    public ResponseEntity<?> updateImgArt(@PathVariable String id, MultipartFile file) throws Exception{
        return ResponseEntity.ok().body(artService.updateArtIMG(file, id));
    }
}
