package kumo.api.api.Domain.Entity;


import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "artistsTest")
public class ArtistSchema {
    @Id
    private String id;

    @NotNull(message = "Nome é obrigatório.")
    @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres.")
    private String name;

    @NotNull(message = "Email é obrigatório.")
    @Email(message = "O email deve ser válido.")
    private String email;

    private String phone;

    @NotNull(message = "Senha é obrigatória.")
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres.")
    private String password;

    private String role;

    private Date createdAt;

    public ArtistSchema() {
    }

    public ArtistSchema(String name, String email, String phone, String password, String role) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.role = role;
    }

}
