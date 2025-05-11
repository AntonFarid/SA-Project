package com.event.participant_service.dto;

public class ParticipantWithEventDTO {
    private String name;
    private String email;
    private Long eventId;
    private String eventTitle;

    public ParticipantWithEventDTO() {
    }

    public ParticipantWithEventDTO(String name, String email, Long eventId, String eventTitle) {
        this.name = name;
        this.email = email;
        this.eventId = eventId;
        this.eventTitle = eventTitle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }
}

