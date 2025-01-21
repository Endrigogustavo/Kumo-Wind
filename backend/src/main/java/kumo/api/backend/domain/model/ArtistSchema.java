package kumo.api.backend.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class ArtistSchema {
    @Id
    private String id;
    private String nome;
    private String email;

    private static class InstanceHolder{
        public static final ArtistSchema INSTANCE = new ArtistSchema();
    }

    public ArtistSchema() {
    }

    public static ArtistSchema getInstance(){
        return InstanceHolder.INSTANCE;
    }

    public ArtistSchema(String id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }   
    
}
