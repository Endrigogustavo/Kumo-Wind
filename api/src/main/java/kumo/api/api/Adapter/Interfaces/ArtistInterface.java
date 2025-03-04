package kumo.api.api.Adapter.Interfaces;

import java.util.List;

import kumo.api.api.Adapter.Dto.Request.UpdateUserDTO;
import kumo.api.api.Adapter.Dto.Response.UpdateResponseDTO;
import kumo.api.api.Entity.Model.ArtistSchema;

public interface ArtistInterface {
    List<ArtistSchema> getAllArtist();
    ArtistSchema findMyArtist(String token);
    UpdateResponseDTO updateArtist(UpdateUserDTO artist, String token);
    String deleteArtist(String token);
}
