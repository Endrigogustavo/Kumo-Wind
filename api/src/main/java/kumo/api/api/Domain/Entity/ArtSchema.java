package kumo.api.api.Domain.Entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "art")
public class ArtSchema {
    @Id
    private String id;

    @NotNull(message = "Titulo é obrigatório.")
    @Size(min = 2, max = 100, message = "O Titulo deve ter entre 2 e 100 caracteres.")
    private String title;

    @NotNull(message = "Descrição é obrigatório.")
    @Size(min = 2, max = 100, message = "O Descrição deve ter entre 2 e 100 caracteres.")
    private String description;

    @NotNull(message = "Nome do artista é obrigatório.")
    @Size(min = 2, max = 100, message = "O Nome do artista deve ter entre 2 e 100 caracteres.")
    private String artist;

    @NotNull(message = "URL é obrigatório.")
    @Size(min = 2, max = 200, message = "URL deve ter entre 2 e 200 caracteres.")
    private String filePath;

    @NotNull(message = "ID é obrigatório.")
    private String IdArtist;

    private Date createdAt;
}
