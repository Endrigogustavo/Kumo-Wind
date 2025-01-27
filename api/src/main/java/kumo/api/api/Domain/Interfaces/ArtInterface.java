package kumo.api.api.Domain.Interfaces;

import java.util.List;

import kumo.api.api.Domain.Entity.ArtSchema;

public interface ArtInterface {
    ArtSchema createArt(ArtSchema art);
    List<ArtSchema> getArtMyUser(String token);
}
