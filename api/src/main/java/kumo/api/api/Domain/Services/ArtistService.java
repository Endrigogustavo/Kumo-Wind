package kumo.api.api.Domain.Services;

import java.util.Date;
import java.util.List;

import kumo.api.api.Repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletResponse;
import kumo.api.api.Application.Configs.SecurityConfig;
import kumo.api.api.Domain.Interfaces.ArtistInterface;
import kumo.api.api.Domain.Model.ArtistSchema;

@Service
public class ArtistService implements ArtistInterface{

    @Autowired
    private Repository repository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private SecurityConfig securityConfig;

    public ArtistSchema createArtist(ArtistSchema artist){
            artist.setCreatedAt(new Date(System.currentTimeMillis()));
            artist.setPass(encoder.encode(artist.getPass()));
            artist.setRole("artist");
            repository.save(artist);
            return artist;
    }

    public List<ArtistSchema> getAllArtist(){
        try {
            return repository.findAll();
        } catch (Exception e) {
            return null;
        }
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
            return null;
        }
    }

    public void deleteArtist(String token){
        try {
            repository.deleteById(token);
        } catch (Exception e) {
            System.err.println("Erro ao deletar artista: " + e.getMessage());
        }
    }

    public boolean loginArtist(String email, String pass, HttpServletResponse response){
        try {
            ArtistSchema artist = repository.findByEmail(email).get();
            if(encoder.matches(pass, artist.getPass())){
                securityConfig.CreateCookies(response, artist.getId());
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
            repository.save(artist);
            return artist;
        } catch (Exception e) {
            return null;
        }
    }
}
