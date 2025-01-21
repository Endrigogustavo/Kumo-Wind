package kumo.api.api.Application.Controller;

import java.util.List;

import kumo.api.api.Repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestBody;
import kumo.api.api.Domain.Model.ArtistSchema;

@RestController
@RequestMapping("/artists")
public class ArtistController {

    @Autowired
    private Repository repository;

    @GetMapping
    public String getArtist() {
        return "Hello, artist!";
    }

    @PostMapping("/createArtist")
    public ArtistSchema createArtist(@RequestBody ArtistSchema artist) {
        return repository.save(artist);
    }

    @GetMapping("/allArtists")
    public List<ArtistSchema> getAllArtists() {
        return repository.findAll();
    }
}
