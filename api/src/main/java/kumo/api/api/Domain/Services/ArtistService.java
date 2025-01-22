package kumo.api.api.Domain.Services;

import java.util.Date;

import kumo.api.api.Repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kumo.api.api.Domain.Model.ArtistSchema;

@Service
public class ArtistService {

    @Autowired
    private Repository repository;

    public ArtistSchema createArtist(ArtistSchema artist){
            artist.setCreatedAt(new Date(System.currentTimeMillis()));
            artist.setRole("artist");
            repository.save(artist);
            return artist;
    }
}
