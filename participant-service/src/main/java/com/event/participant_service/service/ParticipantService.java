package com.event.participant_service.service;

import com.event.participant_service.dto.EventDTO;
import com.event.participant_service.dto.ParticipantWithEventDTO;
import com.event.participant_service.model.Participant;
import com.event.participant_service.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class ParticipantService {

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private RestTemplate restTemplate;

    public List<ParticipantWithEventDTO> getAllParticipantsWithEventTitles() {
        List<Participant> participants = participantRepository.findAll();

        return participants.stream().map(p -> {
           String eventTitle = "UnKnown";
           try {
               EventDTO event = restTemplate.getForObject(
                       "http://localhost:8081/events/" + p.getEventId(),
                       EventDTO.class
               );
               if (event != null) {
                   eventTitle = event.getTitle();
               }
           } catch (Exception e) {
               System.out.println("Failed to fetch event title for eventId: " + p.getEventId());
           }

           return new ParticipantWithEventDTO(
                   p.getName(),
                   p.getEmail(),
                   p.getEventId(),
                   eventTitle
           );
        }).collect(Collectors.toList());
    }

    public Participant addParticipant(Participant participant){
        return participantRepository.save(participant);
    }

    public List<Participant> getAllParticipants() {
        return participantRepository.findAll();
    }

    public Participant registerToEvent(Long eventId, @org.jetbrains.annotations.NotNull Participant participant) {
        participant.setEventId(eventId);
        return participantRepository.save(participant);
    }

}
