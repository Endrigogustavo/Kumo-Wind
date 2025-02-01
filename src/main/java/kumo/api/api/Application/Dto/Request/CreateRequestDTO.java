package kumo.api.api.Application.Dto.Request;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateRequestDTO {

    @NotBlank(message = "name cannot be empty")
    @Size(min = 2, message = "name cannot be less than 2 characters")
    private String name;

    @NotBlank(message = "email cannot be empty")
    @Size(min = 10, message = "email cannot be less than 10 characters")
    @Email(message = "email with invalid format")
    @Nonnull
    private String email;

    @NotBlank(message = "password cannot be empty")
    @Size(min = 8, message = "email password be less than 8 characters")
    private String password;

    @NotBlank(message = "phone number cannot be empty")
    @Size(min = 10, message = "phone number password be less than 8 characters")
    private String phone;
}
