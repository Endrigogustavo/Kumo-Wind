package kumo.api.api.Domain.Services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kumo.api.api.Application.Configs.Security.TokenService;
import kumo.api.api.Domain.Entity.FavoriteSchema;
import kumo.api.api.Domain.Entity.LikesSchema;
import kumo.api.api.Repository.FavoriteRepository;
import kumo.api.api.Repository.LikeRepository;

@Service
public class InteractionService {
    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private TokenService tokenService;

    public String addLike(String userId, String artId) throws Exception{
        String idToken = tokenService.extractUserId(userId);

        if(likeRepository.existsByUserIdAndArtId(idToken, artId)) return "Voçê já deu like nessa arte!!!";
        LikesSchema like = new LikesSchema();
        like.setUserId(idToken);
        like.setArtId(artId);
        like.setCreatedAt(new Date(System.currentTimeMillis()));
        likeRepository.save(like);
        return "Art curtida!!!";
    }

    public String addFavorite(String userId, String artId) throws Exception{
        String idToken = tokenService.extractUserId(userId);

        if(favoriteRepository.existsByUserIdAndArtId(idToken, artId)) return "Você já deu like nessa arte!!!";
        FavoriteSchema favorite = new FavoriteSchema();
        favorite.setUserId(idToken);
        favorite.setArtId(artId);
        favorite.setCreatedAt(new Date(System.currentTimeMillis()));
        favoriteRepository.save(favorite);
        return "Art Salva!!!";
    }

    public LikesSchema viewMyArtLikes(String artId){
        try {
            LikesSchema myLikes = likeRepository.findByArtId(artId);
            return myLikes;
        } catch (Exception e) {
            return null;
        }
    }

    public List<LikesSchema> viewLikes(String userId){
        try {
            String idToken = tokenService.extractUserId(userId);
            List<LikesSchema> myLikes = likeRepository.findByUserId(idToken);
            return myLikes;
        } catch (Exception e) {
            return null;
        }
    }

    public List<FavoriteSchema> viewMyFavoriteArts(String userId){
        try {
            String idToken = tokenService.extractUserId(userId);
            List<FavoriteSchema> myFavorites = favoriteRepository.findByUserId(idToken);
            return myFavorites;
        } catch (Exception e) {
            return null;
        }
    }
}
