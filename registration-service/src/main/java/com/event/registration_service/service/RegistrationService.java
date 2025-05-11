package com.event.registration_service.service;

import com.event.registration_service.dto.EventDto;
import com.event.registration_service.exception.RegistrationNotFoundException;
import com.event.registration_service.model.Registration;
import com.event.registration_service.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

@Service
public class RegistrationService {

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private RestTemplate restTemplate;

    private final String EVENT_SERVICE_URL = "http://localhost:8080/events/";

    public Registration register(Long eventId, Long participantId) {
        ResponseEntity<EventDto> response =     restTemplate.getForEntity(EVENT_SERVICE_URL + eventId, EventDto.class);
        EventDto eventDto = response.getBody();

        if (eventDto == null){
            throw new RuntimeException("Event not found");
        }

        if (eventDto.getAvailableSeats() <= 0){
            throw new RuntimeException("No available seats for this event.");
        }

        // 2. Decrement available seats
        eventDto.setAvailableSeats(eventDto.getAvailableSeats() - 1);

        // 3. Send updated event back to event_service
        restTemplate.put(EVENT_SERVICE_URL + eventId, eventDto);

        // 4. Save the registration
        Registration registration = new Registration(eventId, participantId, LocalDate.now());
        return registrationRepository.save(registration);
    }

    public List<Registration> getAllRegistrations() {
        return registrationRepository.findAll();
    }

    public Registration getRegistrationById(Long id) {
        return registrationRepository.findById(id).orElse(null);
    }

    public void deleteRegistration(Long id) {
        if (!registrationRepository.existsById(id)) {
            throw new RegistrationNotFoundException(id);
        }
        registrationRepository.deleteById(id);
    }

    // to track registered participants (matloob fe el project)
    public List<Registration> getRegistrationsByEventId(Long eventId) {
        return registrationRepository.findByEventId(eventId);
    }

}
