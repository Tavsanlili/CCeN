package com.cinema.ticketsystem.booking.service;

import com.cinema.ticketsystem.booking.dto.response.SeatResponse;
import com.cinema.ticketsystem.booking.entity.Seat;
import com.cinema.ticketsystem.booking.enums.ReservationStatus;
import com.cinema.ticketsystem.booking.repository.ReservationRepository;
import com.cinema.ticketsystem.booking.repository.SeatRepository;
import com.cinema.ticketsystem.catalog.entity.Session;
import com.cinema.ticketsystem.catalog.repository.SessionRepository;
import com.cinema.ticketsystem.common.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SeatService {

    private final SeatRepository seatRepository;
    private final SessionRepository sessionRepository;
    private final ReservationRepository reservationRepository;

    public List<SeatResponse> getSeatsForSession(Long sessionId) {

        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new ResourceNotFoundException("Seans bulunamadı: " + sessionId));

        List<Seat> seats = seatRepository.findByHallNameOrderByRowLetterAscSeatNumberAsc(session.getHallName());

        Set<Long> reservedSeatIds = Set.copyOf(
                reservationRepository.findReservedSeatIdsBySessionIdAndStatus(sessionId, ReservationStatus.ACTIVE)
        );

        return seats.stream()
                .map(seat -> toResponse(seat, reservedSeatIds))
                .toList();
    }

    private SeatResponse toResponse(Seat seat, Set<Long> reservedSeatIds) {
        boolean isReserved = reservedSeatIds.contains(seat.getId());

        return SeatResponse.builder()
                .id(seat.getId())
                .hallName(seat.getHallName())
                .rowLetter(seat.getRowLetter())
                .seatNumber(seat.getSeatNumber())
                .type(seat.getType())
                .available(!isReserved)
                .build();
    }
}