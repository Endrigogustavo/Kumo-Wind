package kumo.api.backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import kumo.api.backend.domain.model.ArtistSchema;;

public interface RepositoryMongo extends MongoRepository<ArtistSchema, String> {
	
}
