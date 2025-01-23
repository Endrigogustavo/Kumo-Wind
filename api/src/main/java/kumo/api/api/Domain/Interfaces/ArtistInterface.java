package kumo.api.api.Domain.Interfaces;

import java.util.List;

import jakarta.servlet.http.HttpServletResponse;
import kumo.api.api.Domain.Model.ArtistSchema;

public interface ArtistInterface {
    ArtistSchema createArtist(ArtistSchema artist);
    List<ArtistSchema> getAllArtist();
    ArtistSchema updateArtist(ArtistSchema artist, String token);
    void deleteArtist(String token);
    boolean loginArtist(String email, String pass, HttpServletResponse response);
    ArtistSchema UpdateEmailArtist(String email, String token);
}
