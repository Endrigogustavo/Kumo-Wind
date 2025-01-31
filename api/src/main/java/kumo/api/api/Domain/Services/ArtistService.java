package kumo.api.api.Domain.Services;

import java.util.List;
import java.util.Optional;

import kumo.api.api.Repository.UserRepository;
import lombok.SneakyThrows;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.JwtException;
import jakarta.validation.Valid;
import kumo.api.api.Application.Configs.Security.TokenService;
import kumo.api.api.Application.Dto.Request.UpdateUserDTO;
import kumo.api.api.Application.Dto.Response.UpdateResponseDTO;
import kumo.api.api.Domain.Entity.ArtistSchema;

@Service
public class ArtistService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private TokenService jwtConfig;

    @SneakyThrows
    public List<ArtistSchema> getAllArtist(){
            List<ArtistSchema> artists = repository.findAll();
            return artists;
    }

    @SneakyThrows
    public ArtistSchema findMyArtist(String token) {
            if (token == null || !jwtConfig.isTokenValid(token)) throw new JwtException("Token inválido ou ausente");

            String tokenId = jwtConfig.extractUserId(token);
            Optional<ArtistSchema> optionalArtist = repository.findById(tokenId);
            return optionalArtist.orElse(null);
    }

 
    @SneakyThrows
    public UpdateResponseDTO updateArtist(@Valid UpdateUserDTO artist, String token){
            Optional<ArtistSchema> artistEmailExist = repository.findByEmail(artist.getEmail());
            if(artistEmailExist.isPresent()) return null;

            String tokenId = jwtConfig.extractUserId(token);
            ArtistSchema artistToUpdate = repository.findById(tokenId).orElseThrow(() -> new IllegalArgumentException("Artista não encontrado"));;
           
            if (artist.getName() != null) artistToUpdate.setName(artist.getName());
            if (artist.getPhone() != null) artistToUpdate.setPhone(artist.getPhone());

            ArtistSchema updated = repository.save(artistToUpdate);
            return new UpdateResponseDTO(updated.getName(), updated.getEmail(), updated.getPhone());
    }

    @SneakyThrows
    public String deleteArtist(String token){
            if (token == "null") return "Erro ao deletar artista: token não encontrado.";
    
            if (jwtConfig.isTokenValid(token) == true ) {
                String tokenJWT = jwtConfig.extractUserId(token);
                repository.deleteById(tokenJWT);
                return "Artista deletado com sucesso!!!";
            }
            return "Artista deletado com sucesso!!!";
    }
}
