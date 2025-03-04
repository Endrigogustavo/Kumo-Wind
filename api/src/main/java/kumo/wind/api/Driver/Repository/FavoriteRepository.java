package kumo.wind.api.Driver.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import kumo.wind.api.Entity.Model.FavoriteSchema;

public interface FavoriteRepository extends MongoRepository<FavoriteSchema, String>{
    boolean existsByUserIdAndArtId(String userId, String artId);
    FavoriteSchema findByArtId(String artId); 
    List<FavoriteSchema> findByUserId(String userId);
} 
