package kumo.api.api.Application.Controller;

import kumo.api.api.Repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestBody;
import kumo.api.api.Domain.Model.ArtistSchema;
import kumo.api.api.Domain.Services.ArtistService;

@RestController
@RequestMapping("/artists")
public class ArtistController {

    @Autowired
    private Repository repository;

    @Autowired
    private ArtistService service;

    @GetMapping
    public String getArtist() {
        return "Hello, artist!";
    }

    @PostMapping("/createArtist")
    public ResponseEntity<?> createArtist(@RequestBody ArtistSchema artist) {
        try {
            if(artist.getName() == null || artist.getEmail() == null || artist.getPhone() == null || artist.getPass() == null) {
                return ResponseEntity.badRequest().body("Erro ao criar artista: campos obrigatórios não preenchidos.");
            }else{
            service.createArtist(artist);
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
}

//https://youtube.com/playlist?list=PLA7e3zmT6XQUjrwAoOHvNu80Axuf-3jft&si=TWLtJJ9TxIBuZnmm
