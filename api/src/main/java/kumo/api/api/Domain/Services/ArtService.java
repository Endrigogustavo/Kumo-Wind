package kumo.api.api.Domain.Services;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;

import kumo.api.api.Application.Configs.Security.TokenService;
import kumo.api.api.Domain.Entity.ArtSchema;
import kumo.api.api.Domain.Entity.ArtistSchema;
import kumo.api.api.Repository.ArtRepository;
import kumo.api.api.Repository.UserRepository;


@Service
public class ArtService {

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository repository;

    @Autowired
    private ArtRepository artRepository;

    public String createArt(MultipartFile art, String title, String description, String token) throws IOException {
        String artPath = uploadImage(art);
        if(artPath == null) return null;

        String id = tokenService.extractUserId(token);
        ArtistSchema artist = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Artista não encontrado"));
        ArtSchema newArt = new ArtSchema();
        newArt.setArtist(artist.getName());
        newArt.setCreatedAt(new Date(System.currentTimeMillis()));
        newArt.setDescription(description);
        newArt.setIdArtist(artist.getId());
        newArt.setFilePath(artPath);
        newArt.setTitle(title);
        artRepository.save(newArt);
        return artPath;
    }

    public String uploadImage(MultipartFile file) throws IOException {
        Map<String, Object> options = new HashMap<>(); 
        Map<String, Object> uploadResult = cloudinary.uploader().upload(file.getBytes(), options);
        return (String) uploadResult.get("url");
    }

    public List<ArtSchema> getArtByArtist(String idArtist) {
        String id = tokenService.extractUserId(idArtist);
        return artRepository.findByidArtist(id);
    }

    public String deleteArt(String id) throws Exception{
        artRepository.deleteById(id);
        return "Art deletado com sucesso";
    }

    public void updateArt(){
        
    }
}
