package kumo.api.api.Domain.Interfaces;

import java.util.List;

import kumo.api.api.Application.Dto.Request.UpdateUserDTO;
import kumo.api.api.Application.Dto.Response.UpdateResponseDTO;
import kumo.api.api.Domain.Entity.ArtistSchema;

public interface ArtistInterface {
    List<ArtistSchema> getAllArtist();
    ArtistSchema findMyArtist(String token);
    UpdateResponseDTO updateArtist(UpdateUserDTO artist, String token);
    String deleteArtist(String token);
}
