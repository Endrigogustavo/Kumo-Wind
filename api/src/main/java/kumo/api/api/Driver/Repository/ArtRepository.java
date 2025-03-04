package kumo.api.api.Driver.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import kumo.api.api.Entity.Model.ArtSchema;

public interface ArtRepository extends MongoRepository<ArtSchema, String> {
    List<ArtSchema> findByidArtist(String idArtist);
}
