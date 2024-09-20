package S3D5.service;

import S3D5.entities.Event;
import S3D5.entities.User;
import S3D5.exceptions.NotFoundEx;
import S3D5.payloads.NewEventDTO;
import S3D5.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public Event createEvent(NewEventDTO body, User organizer) {

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

    public Event findById(int id) {
        return this.eventRepository.findById(id).orElseThrow(() -> new NotFoundEx(id));
    }

    public void findByIdAndDelete(int id) {
        Event found = this.findById(id);
        this.eventRepository.delete(found);
    }

    public Event findByIdAndUpdate(int id, Event newEvent) {

        Event found = this.findById(id);
        found.setTitle(newEvent.getTitle());
        found.setDescription(newEvent.getDescription());
        found.setPlace(newEvent.getPlace());
        found.setAvailableSeats(newEvent.getAvailableSeats());
        found.setUser(newEvent.getUser());
        return this.eventRepository.save(found);
    }

    public Event findEventByOrganizer(User organizer) {
        return eventRepository.findByUser(organizer)
                .orElseThrow(() -> new NotFoundEx("Nessun evento trovato per l'organizzatore."));
    }

    public Event updateEventByOrganizer(User organizer, NewEventDTO eventDTO) {
        Event event = findEventByOrganizer(organizer);
        event.setTitle(eventDTO.title());
        event.setDescription(eventDTO.description());
        event.setPlace(eventDTO.place());
        event.setAvailableSeats(eventDTO.availableSeats());
        event.setDate(eventDTO.date());

        return eventRepository.save(event);
    }

}
