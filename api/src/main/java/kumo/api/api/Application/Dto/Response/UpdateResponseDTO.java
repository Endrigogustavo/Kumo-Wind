package kumo.api.api.Application.Dto.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UpdateResponseDTO {
    private String name;
    private String email;
    private String phone;
}
