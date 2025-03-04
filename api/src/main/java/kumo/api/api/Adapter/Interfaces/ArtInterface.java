package kumo.api.api.Adapter.Interfaces;

import java.util.List;

import kumo.api.api.Entity.Model.ArtSchema;

public interface ArtInterface {
    ArtSchema createArt(ArtSchema art);
    List<ArtSchema> getArtMyUser(String token);
}
