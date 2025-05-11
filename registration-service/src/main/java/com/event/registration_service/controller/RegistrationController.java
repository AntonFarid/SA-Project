package com.event.registration_service.controller;

import com.event.registration_service.dto.EventDto;
import com.event.registration_service.model.Registration;
import com.event.registration_service.repository.RegistrationRepository;
import com.event.registration_service.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/registrations")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;
    @Autowired
    private RegistrationRepository registrationRepository;

    @PostMapping
    public Registration register(@RequestParam Long eventId, @RequestParam Long participantId) {
        return registrationService.register(eventId, participantId);
    }

    @GetMapping
    public List<Registration> getAll() {
        return registrationService.getAllRegistrations();
    }

    @GetMapping("/{id}")
    public Registration getById(@PathVariable Long id) {
        return registrationService.getRegistrationById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteRegistration(@PathVariable Long id) {
        registrationService.deleteRegistration(id);
    }
}
