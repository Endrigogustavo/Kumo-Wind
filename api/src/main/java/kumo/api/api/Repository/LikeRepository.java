package kumo.api.api.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import kumo.api.api.Domain.Entity.LikesSchema;

@Repository
public interface LikeRepository extends MongoRepository<LikesSchema, String>{
    boolean existsByUserIdAndArtId(String userId, String artId);
}
