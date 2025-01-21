package kumo.api.backend.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import kumo.api.backend.repository.RepositoryMongo;

@RestController("/artist")
public class ArtistController {
    @Autowired
    private RepositoryMongo artistService;

    @GetMapping
    public String getArtist() {
        return "Hello, artist!";
    }
}
