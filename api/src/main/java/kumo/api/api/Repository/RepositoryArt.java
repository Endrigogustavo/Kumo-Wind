package kumo.api.api.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import kumo.api.api.Domain.Entity.ArtSchema;

public interface RepositoryArt extends MongoRepository<ArtSchema, String> {
    
}
