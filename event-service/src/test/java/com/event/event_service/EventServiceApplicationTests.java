package com.event.event_service.service;

import com.event.event_service.Repository.EventRepository;
import com.event.event_service.model.Event;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EventServiceTest {
	private EventRepository eventRepository;
	private EventService eventService;

	@BeforeEach
	void setUp() {
		eventRepository = Mockito.mock(EventRepository.class);
		eventService = new EventService(eventRepository);
	}

	@Test
	void testCreateEvent() {
		Event event = new Event("Java Workshop", "Learn Spring Boot", LocalDate.now(), 60);
		when(eventRepository.save(event)).thenReturn(event);

		Event savedEvent = eventService.createEvent(event);

		assertNotNull(savedEvent);
		assertEquals("Java Workshop", savedEvent.getTitle());
		verify(eventRepository, times(1)).save(event);
	}

	@Test
	void testGetAllEvents() {
		Event e1 = new Event("Event1", "Desc1", LocalDate.now(), 30);
		Event e2 = new Event("Event2", "Desc2", LocalDate.now(), 40);
		when(eventRepository.findAll()).thenReturn(Arrays.asList(e1, e2));

		List<Event> events = eventService.getAllEvents();

		assertEquals(2, events.size());
	}
}
