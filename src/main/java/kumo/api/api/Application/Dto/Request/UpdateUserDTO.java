package kumo.api.api.Application.Dto.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateUserDTO {
    @NotBlank(message = "Name cannot be empty.")
    @Size(min = 2, message = "Name must be at least 8 characters long.")
    private String name;

    @NotBlank(message = "Email cannot be empty.")
    @Size(min = 8, message = "Email must be at least 8 characters long.")
    @Email(message = "Invalid email format.")
    private String email;

    @NotBlank(message = "Phone number cannot be empty.")
    @Size(min = 8, message = "Phone number must be at least 8 characters long.")
    private String phone;
}
