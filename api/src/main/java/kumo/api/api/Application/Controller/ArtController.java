package kumo.api.api.Application.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiOperation;
import kumo.api.api.Application.Dto.Request.CreateArtRequestDTO;
import kumo.api.api.Application.Dto.Response.CreateArtResponseDTO;
import kumo.api.api.Domain.Services.ArtService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/art")
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
}
