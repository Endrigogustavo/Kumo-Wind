package kumo.api.api.Application.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kumo.api.api.Domain.Services.InteractionService;

@RestController
@RequestMapping("/interaction")
public class InteractionController {

    @Autowired
    private InteractionService interactionService;

    @PostMapping("/favoriteArt/{artId}")
    public ResponseEntity<?> addFavoriteArt(@CookieValue(name = "token", defaultValue = "null") String id, @PathVariable String artId){
        try {
            return ResponseEntity.ok().body(interactionService.addFavorite(id, artId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao salvar a imagem");
        }
    }

    @PostMapping("/likeArt/{artId}")
    public ResponseEntity<?> addLikeArt(@CookieValue(name = "token", defaultValue = "null") String id, @PathVariable String artId){
        try {
            return ResponseEntity.ok().body(interactionService.addLike(id, artId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao salvar a imagem");
        }
    }

    @GetMapping("/getMyLikes")
    public ResponseEntity<?> getMyLikes(@CookieValue(name = "token", defaultValue = "null") String id){
        try {
            return ResponseEntity.ok().body(interactionService.viewLikes(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao listar as curtidas " + e.getMessage());
        }
    }

    @GetMapping("/getMyArtLikes/{artId}")
    public ResponseEntity<?> getMyArtLikes(@PathVariable String artId){
        try {
            return ResponseEntity.ok().body(interactionService.viewMyArtLikes(artId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao listar as curtidas " + e.getMessage());
        }
    }

    @GetMapping("/getMyFavoriteArts")
    public ResponseEntity<?> getMyFavoriteArts(@CookieValue(name = "token", defaultValue = "null") String id){
        try {
            return ResponseEntity.ok().body(interactionService.viewLikes(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao listar as curtidas " + e.getMessage());
        }
    }
}
