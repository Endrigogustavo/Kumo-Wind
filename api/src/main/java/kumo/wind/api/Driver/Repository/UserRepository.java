package kumo.wind.api.Driver.Repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import kumo.wind.api.Entity.Model.ArtistSchema;

public interface UserRepository extends MongoRepository<ArtistSchema, String> {
    Optional<ArtistSchema> findByEmail(String email);
}
