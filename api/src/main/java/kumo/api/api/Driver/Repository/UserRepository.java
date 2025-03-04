package kumo.api.api.Driver.Repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import kumo.api.api.Entity.Model.ArtistSchema;

public interface UserRepository extends MongoRepository<ArtistSchema, String> {
    Optional<ArtistSchema> findByEmail(String email);
}
