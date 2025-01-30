package kumo.api.api.Domain.Services;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import kumo.api.api.Repository.UserRepository;
import lombok.SneakyThrows;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.JwtException;
import jakarta.validation.Valid;
import kumo.api.api.Application.Configs.Security.CookieConfig;
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

    private static final Logger log = Logger.getLogger(ArtistService.class.getName());


    @SneakyThrows
    public List<ArtistSchema> getAllArtist(){
        try {
            if (repository.findAll().isEmpty() && repository.findAll() == null) {
                return null;
            } else {
                return repository.findAll();
            }
        } catch (Exception e) {
            return null;
        }
    }

    @SneakyThrows
    public ArtistSchema findMyArtist(String token) {
        try {
            if (token == null || !jwtConfig.isTokenValid(token)) {
                throw new JwtException("Token inválido ou ausente");
            }
            String tokenId = jwtConfig.extractUserId(token);
            Optional<ArtistSchema> optionalArtist = repository.findById(tokenId);
            return optionalArtist.orElse(null);
        } catch (JwtException e) {
            log.warning("Erro de validação do token" + e.getMessage());
        } catch (Exception e) {
            log.severe("Erro inesperado ao buscar o artista" + e.getMessage());
        }
        return null;
    }

 
    @SneakyThrows
    public UpdateResponseDTO updateArtist(@Valid UpdateUserDTO artist, String token){
        try {
            String tokenId = jwtConfig.extractUserId(token);
            
            ArtistSchema artistToUpdate = repository.findById(tokenId).orElseThrow(() -> new IllegalArgumentException("Artista não encontrado"));;
           
            if (artist.getName() != null) artistToUpdate.setName(artist.getName());
            if (artist.getEmail() != null) artistToUpdate.setEmail(artist.getEmail());
            if (artist.getPhone() != null) artistToUpdate.setPhone(artist.getPhone());

            ArtistSchema updated = repository.save(artistToUpdate);
            return new UpdateResponseDTO(updated.getName(), updated.getEmail(), updated.getPhone());
        } catch (Exception e) {
            System.err.println("Erro ao atualizar artista: " + e.getMessage());
            return null;
        }
    }

    @SneakyThrows
    public ResponseEntity<?> UpdateEmailArtist(String email, String token){
        try {
            if(email == null){
                return ResponseEntity.badRequest().body("Erro ao atualizar o email: campo obrigatório não preenchido.");
            }
            
            String tokenId = jwtConfig.extractUserId(token);

            ArtistSchema artist = repository.findById(tokenId).get();
            artist.setEmail(email);
            System.out.println("Get email: " + artist.getEmail());
            repository.save(artist);
            return ResponseEntity.ok().body(artist);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao atualizar o email " +e.getMessage());
        }
    }

    @SneakyThrows
    public ResponseEntity<?> deleteArtist(String token){
        try {
            if (token == "null") {
                return ResponseEntity.badRequest().body("Erro ao deletar artista: token não encontrado.");
            }
            if (jwtConfig.isTokenValid(token) == true ) {
                String tokenJWT = jwtConfig.extractUserId(token);
                repository.deleteById(tokenJWT);
                return ResponseEntity.ok().body("Artista deletado com sucesso!!!");
            }
            return ResponseEntity.ok().body("Artista deletado com sucesso!!!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao deletar o artista " + e.getMessage());
        }
    }

}
