package S3D5.controllers;

import S3D5.entities.Event;
import S3D5.entities.User;
import S3D5.payloads.NewEventDTO;
import S3D5.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/myevent")
    public ResponseEntity<Event> getEvent(@AuthenticationPrincipal User currentAuthenticatedUser) {
        Event event = eventService.findEventByOrganizer(currentAuthenticatedUser);
        return ResponseEntity.ok(event);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ORGANIZZATORE_DI_EVENTI')")
    public ResponseEntity<Event> createEvent(@AuthenticationPrincipal User currentAuthenticatedUser, @RequestBody NewEventDTO body) {
        Event newEvent = eventService.createEvent(body, currentAuthenticatedUser);
        return new ResponseEntity<>(newEvent, HttpStatus.CREATED);
    }

    @PutMapping("/eventId")
    @PreAuthorize("hasAuthority('ORGANIZZATORE_DI_EVENTI')")
    public ResponseEntity<Event> updateEvent(@AuthenticationPrincipal User currentAuthenticatedUser, @RequestBody NewEventDTO body) {
        Event updatedEvent = eventService.updateEventByOrganizer(currentAuthenticatedUser, body);
        return new ResponseEntity<>(updatedEvent, HttpStatus.OK);
    }

    @DeleteMapping("/{eventId}")
    @PreAuthorize("hasAuthority('ORGANIZZATORE_DI_EVENTI')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable int id) {
        this.eventService.findByIdAndDelete(id);
    }

}
