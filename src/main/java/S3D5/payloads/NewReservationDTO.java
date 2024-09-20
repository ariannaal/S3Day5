package S3D5.payloads;

import jakarta.validation.constraints.NotNull;


public record NewReservationDTO(

        @NotNull(message = "L'id dell'evento e' obbligatorio!")
        Integer eventId,

        @NotNull(message = "L'id dell'utente e' obbligatorio!")
        Integer userId

) {}
