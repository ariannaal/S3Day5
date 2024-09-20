package S3D5.controllers;

import S3D5.entities.Reservation;
import S3D5.entities.User;
import S3D5.payloads.NewReservationDTO;
import S3D5.payloads.UserLoginDTO;
import S3D5.payloads.UserLoginResponseDTO;
import S3D5.service.AuthService;
import S3D5.service.ReservationService;
import S3D5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private AuthService authService;

    @Autowired
    private ReservationService reservationService;

    @PostMapping
    @PreAuthorize("hasAuthority('UTENTE_NORMALE')")
    public ResponseEntity<Reservation> createReservation(
            @AuthenticationPrincipal User currentAuthenticatedUser,
            @RequestBody @Validated NewReservationDTO reservationDTO,
            BindingResult validationResult) {

        Reservation newReservation = reservationService.createReservation(reservationDTO, currentAuthenticatedUser);
        return new ResponseEntity<>(newReservation, HttpStatus.CREATED);
    }

}
