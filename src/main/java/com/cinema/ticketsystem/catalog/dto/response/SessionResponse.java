package com.cinema.ticketsystem.catalog.dto.response;

import java.time.LocalDateTime;

public record SessionResponse(
        Long id,
        Long movieId,
        String hallName,
        LocalDateTime startTime
) {
}
