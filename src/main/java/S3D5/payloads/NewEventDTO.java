package S3D5.payloads;

import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.Date;

public record NewEventDTO(

        @NotEmpty(message = "Il titolo dell'evento e' obbligatorio!")
        String title,

        @NotEmpty(message = "La descrizione dell'evento e' obbligatoria!")
        String description,

        @NotEmpty(message = "Il luogo dell'evento e' obbligatorio!")
        String place,

        @NotNull(message = "Il numero di posti disponibili e' obbligatorio!")
        @Min(value = 1, message = "Il numero di posti disponibili deve essere maggiore di 1!")
        Integer availableSeats,

        @NotNull(message = "La data dell'evento e' obbligatoria!")
        LocalDate date

) {
}
