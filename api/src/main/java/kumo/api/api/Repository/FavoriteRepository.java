package kumo.api.api.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import kumo.api.api.Domain.Entity.FavoriteSchema;

public interface FavoriteRepository extends MongoRepository<FavoriteSchema, String>{
    boolean existsByUserIdAndArtId(String userId, String artId);
    FavoriteSchema findByArtId(String artId); 
    List<FavoriteSchema> findByUserId(String userId);
} 
