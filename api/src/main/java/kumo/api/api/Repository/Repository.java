package kumo.api.api.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import kumo.api.api.Domain.Model.ArtistSchema;

public interface Repository extends MongoRepository<ArtistSchema, String> {

}
