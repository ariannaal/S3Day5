package S3D5.service;

import S3D5.entities.Event;
import S3D5.entities.User;
import S3D5.payloads.NewEventDTO;
import S3D5.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public Event save(NewEventDTO body, User organizer) {

        Event newEvent = new Event(
                body.title(),
                body.description(),
                body.place(),
                body.availableSeats(),
                body.date(),
                organizer
        );


        Event savedEvent = this.eventRepository.save(newEvent);

        System.out.println("L'evento " + savedEvent + " e' stato salvato con successo!");

        return savedEvent;
    }

    public List<Event> findAllEvents() {
        return eventRepository.findAll();
    }

}
