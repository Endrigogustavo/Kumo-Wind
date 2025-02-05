package kumo.api.api.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import kumo.api.api.Domain.Entity.FavoriteSchema;

public interface FavoriteRepository extends MongoRepository<FavoriteSchema, String>{
    boolean existsByUserIdAndArtId(String userId, String artId);
} 
