package S3D5.payloads;

import S3D5.enums.Role;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record NewUserDTO(

        @NotEmpty(message = "Lo username e' obbligatorio!")
        @Size(min = 5, max = 15, message = "Lo username deve essere compreso tra 5 e 15 caratteri")
        String username,

        @NotEmpty(message = "La password e' obbligatoria!")
        @Size(min = 5, max = 25, message = "La password deve essere compreso tra 5 e 25 caratteri")
        String password,

        @NotNull(message = "Il ruolo e' obbligatorio!")
        Role role )
{
}
