package S3D5.payloads;

import jakarta.validation.constraints.NotNull;

public record ReservationUpdateDTO(

        @NotNull(message = "L'ID dell'evento e' obbligatorio!")
        Integer eventId

) {
}
