package kumo.wind.api.Adapter.Interfaces;

import java.util.List;

import kumo.wind.api.Entity.Model.ArtSchema;

public interface ArtInterface {
    ArtSchema createArt(ArtSchema art);
    List<ArtSchema> getArtMyUser(String token);
}
