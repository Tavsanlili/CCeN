package com.cinema.ticketsystem.catalog.dto.response;

import com.cinema.ticketsystem.catalog.entity.Session;
import java.time.LocalDateTime;

public record SessionResponse(
        Long id,
        Long movieId,
        String movieTitle,
        String hallName,
        LocalDateTime startTime
) {
    public static SessionResponse from(Session session) {
        return new SessionResponse(
                session.getId(),
                session.getMovie().getId(),
                session.getMovie().getTitle(),
                session.getHallName(),
                session.getStartTime()
        );
    }
}