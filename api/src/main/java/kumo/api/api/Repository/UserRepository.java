package kumo.api.api.Repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import kumo.api.api.Domain.Entity.ArtistSchema;

public interface UserRepository extends MongoRepository<ArtistSchema, String> {
    Optional<ArtistSchema> findByEmail(String email);
}
