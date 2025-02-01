package kumo.api.api.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import kumo.api.api.Domain.Entity.ArtSchema;

public interface ArtRepository extends MongoRepository<ArtSchema, String> {
    List<ArtSchema> findByidArtist(String idArtist);
}
