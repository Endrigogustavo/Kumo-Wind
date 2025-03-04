package kumo.wind.api.Adapter.Interfaces;

import java.util.List;

import kumo.wind.api.Adapter.Dto.Request.UpdateUserDTO;
import kumo.wind.api.Adapter.Dto.Response.UpdateResponseDTO;
import kumo.wind.api.Entity.Model.ArtistSchema;

public interface ArtistInterface {
    List<ArtistSchema> getAllArtist();
    ArtistSchema findMyArtist(String token);
    UpdateResponseDTO updateArtist(UpdateUserDTO artist, String token);
    String deleteArtist(String token);
}
