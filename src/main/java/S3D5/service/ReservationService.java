package S3D5.service;

import S3D5.entities.Event;
import S3D5.entities.Reservation;
import S3D5.entities.User;
import S3D5.exceptions.BadRequestEx;
import S3D5.exceptions.NotFoundEx;
import S3D5.exceptions.UnauthorizedEx;
import S3D5.payloads.NewReservationDTO;
import S3D5.payloads.ReservationUpdateDTO;
import S3D5.repositories.EventRepository;
import S3D5.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private EventRepository eventRepository;

    public Reservation createReservation(NewReservationDTO body, User user) {

        Event event = eventRepository.findById(body.eventId())
                .orElseThrow(() -> new NotFoundEx("Evento non trovato con id: " + body.eventId()));

        // verifica che ci siano ancora postri disponibili
        int existingReservationsCount = reservationRepository.countByEvent(event);

        if (existingReservationsCount >= event.getAvailableSeats()) {
            throw new BadRequestEx("Nessun posto disponibile per l'evento.");
        }

        boolean alreadyReserved = reservationRepository.existsByEventAndUser(event, user);
        if (alreadyReserved) {
            throw new BadRequestEx("Hai gia' una prenotazione per questo evento.");
        }


        Reservation reservation = new Reservation(event, user);

        Reservation savedReservation = this.reservationRepository.save(reservation);

        System.out.println("La prenotazione " + savedReservation + " e' stata salvata con successo!");

        return savedReservation;
    }

    public List<Event> findAllEvents() {
        return eventRepository.findAll();
    }

    public Reservation updateReservation(int reservationId, ReservationUpdateDTO reservationDTO, User currentUser) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new NotFoundEx("Prenotazione non trovata"));

        if (!(reservation.getUser().getId() == (currentUser.getId()))) {
            throw new UnauthorizedEx("Non hai l'autorizzazione per modificare questa prenotazione.");
        }

        Event event = eventRepository.findById(reservationDTO.eventId())
                .orElseThrow(() -> new NotFoundEx("Evento non trovato con id: " + reservationDTO.eventId()));

        reservation.setEvent(event);

        return reservationRepository.save(reservation);
    }


    public List<Reservation> findReservationsByUser(User user) {
        return reservationRepository.findByUser(user);
    }

    public void cancelReservation(int reservationId, User currentAuthenticatedUser) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new NotFoundEx("Prenotazione non trovata"));

        // per vedere sw la prenotazione appartiene all'utente autenticato
        if (reservation.getUser().getId() != currentAuthenticatedUser.getId()) {
            throw new UnauthorizedEx("Non hai l'autorizzazione per cancellare questa prenotazione.");
        }

        reservationRepository.delete(reservation);
    }

}
