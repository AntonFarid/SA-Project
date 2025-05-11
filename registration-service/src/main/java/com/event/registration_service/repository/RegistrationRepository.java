package com.event.registration_service.repository;

import com.event.registration_service.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
List<Registration> findByEventId(Long eventId);
}
