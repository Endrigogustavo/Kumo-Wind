package kumo.api.api.Domain.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "art")
public class ArtSchema {
    @Id
    private String id;
    private String title;
    private String description;
    private String artist;
    private String image;
    private String IdArtist;
}
