package com.event.event_service.controller;

import com.event.event_service.Repository.EventRepository;
import com.event.event_service.model.Event;
import com.event.event_service.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;
    @Autowired
    private EventRepository eventRepository;

    @PostMapping
    public Event createEvent (@RequestBody Event event){
        return eventService.createEvent(event);
    }

    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/{id}")
    public Event getEventById(@PathVariable Long id){
        return eventService.getEventById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
    }

    @PutMapping("/{id}/reschedule")
    public Event rescheduleEvent(@PathVariable Long id, @RequestParam LocalDate newDate) {
        return eventService.rescheduleEvent(id, newDate);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody Event updatedEvent){
        Optional<Event> optionalEvent = eventRepository.findById(id);

        if(optionalEvent.isPresent()) {
            Event existngEvent = optionalEvent.get();

            existngEvent.setTitle(updatedEvent.getTitle());
            existngEvent.setDescription(updatedEvent.getDescription());
            existngEvent.setDate(updatedEvent.getDate());
            existngEvent.setTotalSeats(updatedEvent.getTotalSeats());
            existngEvent.setAvailableSeats(updatedEvent.getAvailableSeats());

            Event savedEvent = eventRepository.save(existngEvent);
            return ResponseEntity.ok(savedEvent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
