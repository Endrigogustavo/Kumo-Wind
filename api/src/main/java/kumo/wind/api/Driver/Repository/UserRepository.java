package kumo.wind.api.Driver.Repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import kumo.wind.api.Entity.Model.ArtistSchema;

@Repository
public interface UserRepository extends MongoRepository<ArtistSchema, String> {
    Optional<ArtistSchema> findByEmail(String email);
}
