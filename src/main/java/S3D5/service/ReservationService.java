package S3D5.service;

import S3D5.entities.Event;
import S3D5.entities.Reservation;
import S3D5.entities.User;
import S3D5.exceptions.NotFoundEx;
import S3D5.payloads.NewEventDTO;
import S3D5.payloads.NewReservationDTO;
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

        Reservation reservation = new Reservation(event, user);

        Reservation savedReservation = this.reservationRepository.save(reservation);

        System.out.println("La prenotazione " + savedReservation + " e' stata salvata con successo!");

        return savedReservation;
    }

    public List<Event> findAllEvents() {
        return eventRepository.findAll();
    }

}
