package com.cinema.ticketsystem.booking.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationRequest {

    @NotNull(message = "sessionId boş olamaz")
    private Long sessionId;

    @NotNull(message = "seatId boş olamaz")
    private Long seatId;

    @NotBlank(message = "customerName boş olamaz")
    private String customerName;
}