package kumo.api.api.Domain.Entity;

import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "likes")
public class LikesSchema {
    @Id
    private String id;
    private String userId;
    private String artId;
    private Date createdAt;
}
