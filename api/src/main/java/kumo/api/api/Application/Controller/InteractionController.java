package kumo.api.api.Application.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/interaction")
public class InteractionController {
    
    @PostMapping("/favoriteArt/{artId}")
    public ResponseEntity<?> addFavoriteArt(@CookieValue(name = "token", defaultValue = "null") String id, @PathVariable String artId){
        return null;
    }
}
