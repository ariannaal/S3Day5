package S3D5.payloads;

import jakarta.validation.constraints.NotEmpty;


public record NewReservationDTO(

        @NotEmpty(message = "L'id dell'evento e' obbligatorio!")
        Integer eventId,

        @NotEmpty(message = "L'id dell'utente e' obbligatorio!")
        Integer userId

) {}
