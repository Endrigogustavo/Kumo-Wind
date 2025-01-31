package kumo.api.api.Domain.Services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kumo.api.api.Application.Configs.Security.TokenService;
import kumo.api.api.Application.Dto.Request.CreateArtRequestDTO;
import kumo.api.api.Application.Dto.Response.CreateArtResponseDTO;
import kumo.api.api.Domain.Entity.ArtSchema;
import kumo.api.api.Domain.Entity.ArtistSchema;
import kumo.api.api.Domain.Interfaces.ArtInterface;
import kumo.api.api.Repository.ArtRepository;
import kumo.api.api.Repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
public class ArtService {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository repository;

    @Autowired
    private ArtRepository artRepository;

    @Value("${PATH_IMG}")
    private String UPLOAD_DIR;

    public String createArt(MultipartFile art, String title, String description, String token) {
        String artPath = uploadArt(art);
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

    public String uploadArt(MultipartFile art) {
        try {
            if (art.isEmpty()) return null;
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) Files.createDirectories(uploadPath);

            String fileName = UUID.randomUUID() + "_" + art.getOriginalFilename();
            Path filePath = uploadPath.resolve(fileName);

            art.transferTo(filePath.toFile());
            System.out.println("Arquivo salvo com sucesso: " + filePath);

            return "/uploads/" + fileName;
        } catch (IOException e) {
            System.err.println("Erro ao criar diretório de upload: " + e.getMessage());
            return null;
        }
    }

    public List<ArtSchema> getArtByArtist(String idArtist) {
        String id = tokenService.extractUserId(idArtist);
        return artRepository.findByidArtist(id);
    }
}
