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

//Configurações do controller
@RestController
@RequestMapping("/interaction")
public class InteractionController {

    //Instancia da classe InteractionService
    @Autowired
    private InteractionService interactionService;

    //Rota de adicionar a arte como favoritada pelo artista com base no id da arte enviada pela url e o cookie do artista 
    @PostMapping("/favoriteArt/{artId}")
    public ResponseEntity<?> addFavoriteArt(@CookieValue(name = "token", defaultValue = "null") String id, @PathVariable String artId){
        try {
            //Retorna uma resposta da classe de add arte aos favoritos da service
            return ResponseEntity.ok().body(interactionService.addFavorite(id, artId));
        } catch (Exception e) {
            //Caso algo imprevisto 
            return ResponseEntity.badRequest().body("Erro ao salvar a imagem");
        }
    }

    //Rota de dar like em alguma arte enviando o id da arte pela url e o id do artista pelo cookie
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
