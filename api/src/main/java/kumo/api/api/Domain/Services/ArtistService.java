package kumo.api.api.Domain.Services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import kumo.api.api.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.HttpServletResponse;
import kumo.api.api.Application.Configs.CookieConfig;
import kumo.api.api.Application.Configs.JWTConfig;
import kumo.api.api.Domain.Entity.ArtistSchema;
import kumo.api.api.Domain.Interfaces.ArtistInterface;

@Service
public class ArtistService implements ArtistInterface{

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private CookieConfig securityConfig;

    @Autowired
    private JWTConfig jwtConfig;

    private static final Logger log = Logger.getLogger(ArtistService.class.getName());


    public ResponseEntity<?> createArtist(ArtistSchema artist){
         if(artist.getName() == null || artist.getEmail() == null || artist.getPhone() == null || artist.getPass() == null) {
                return ResponseEntity.badRequest().body("Erro ao criar artista: campos obrigatórios não preenchidos.");
            }else{
                artist.setCreatedAt(new Date(System.currentTimeMillis()));
                artist.setPass(encoder.encode(artist.getPass()));
                artist.setRole("artist");
                repository.save(artist);
            return ResponseEntity.ok().body(artist);
            }
    }


    public List<ArtistSchema> getAllArtist(){
        try {
            return repository.findAll();
        } catch (Exception e) {
            return null;
        }
    }

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

 
    public ArtistSchema updateArtist(ArtistSchema artist, String token){
        try {
            ArtistSchema artistToUpdate = repository.findById(token).get();
            if(artist.getName() != null){
                artistToUpdate.setName(artist.getName());
            }
            if(artist.getEmail() != null){
                artistToUpdate.setEmail(artist.getEmail());
            }
            if(artist.getPhone() != null){
                artistToUpdate.setPhone(artist.getPhone());
            }

            repository.save(artistToUpdate);
            return artistToUpdate;
        } catch (Exception e) {
            System.err.println("Erro ao atualizar artista: " + e.getMessage());
            return null;
        }
    }

    public void deleteArtist(String token){
        try {
            String tokenId = jwtConfig.extractUserId(token);
            repository.deleteById(tokenId);
        } catch (Exception e) {
            System.err.println("Erro ao deletar artista: " + e.getMessage());
        }
    }

    public boolean loginArtist(String email, String pass, HttpServletResponse response){
        try {
            ArtistSchema artist = repository.findByEmail(email).get();
            if(encoder.matches(pass, artist.getPass())){
                String token = jwtConfig.generateToken(artist.getId());
                securityConfig.CreateCookies(response, token);
                System.out.println("Login realizado com sucesso!");
                return true;
            }else{
                System.err.println("Erro ao realizar login: senha incorreta.");
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public ArtistSchema UpdateEmailArtist(String email, String token){
        try {
            ArtistSchema artist = repository.findById(token).get();
            artist.setEmail(email);
            System.out.println("Get email: " + artist.getEmail());
            repository.save(artist);
            return artist;
        } catch (Exception e) {
            return null;
        }
    }
}
