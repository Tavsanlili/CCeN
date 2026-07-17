package com.cinema.ticketsystem.booking.repository;

import com.cinema.ticketsystem.booking.entity.Reservation;
import com.cinema.ticketsystem.booking.enums.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    boolean existsBySessionIdAndSeatIdAndStatus(Long sessionId, Long seatId, ReservationStatus status);
}