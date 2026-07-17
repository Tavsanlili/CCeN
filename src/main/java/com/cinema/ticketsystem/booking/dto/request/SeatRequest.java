package com.cinema.ticketsystem.booking.dto.request;

import com.cinema.ticketsystem.booking.enums.SeatType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeatRequest {
    @NotNull(message = "sessionId boş olamaz")
    private String hallName;

    @NotNull(message = "seatId boş olamaz")
    private String rowLetter;

    @NotNull(message = "seatNumber boş olamaz")
    @Positive(message = "seatNumber pozitif bir sayı olmalı")
    private Integer seatNumber;

    @NotNull(message = "type boş olamaz")
    private SeatType type;
}
