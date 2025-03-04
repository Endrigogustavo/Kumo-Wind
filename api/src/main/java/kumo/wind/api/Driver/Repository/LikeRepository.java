package kumo.wind.api.Driver.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import kumo.wind.api.Entity.Model.LikesSchema;

public interface LikeRepository extends MongoRepository<LikesSchema, String>{
    boolean existsByUserIdAndArtId(String userId, String artId);
    LikesSchema findByArtId(String artId); 
    List<LikesSchema> findByUserId(String userId);
}
