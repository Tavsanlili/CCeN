package com.cinema.ticketsystem.booking.dto.response;

import com.cinema.ticketsystem.booking.enums.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationResponse {

    private Long id;
    private Long sessionId;
    private Long seatId;
    private String customerName;
    private ReservationStatus status;
    private BigDecimal ticketPrice;
}