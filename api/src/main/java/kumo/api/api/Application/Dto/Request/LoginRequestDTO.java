package kumo.api.api.Application.Dto.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequestDTO(
    @NotBlank(message = "Email cannot be empty.")
    @Email
    String email, 

    @NotBlank(message = "Password cannot be empty.")
    @Size(min = 8, message = "Password cannot be less than 2 characters.")
    @Size(max = 32, message = "Password cannot exceed 50 characters.")
    String password
    ) {
}
