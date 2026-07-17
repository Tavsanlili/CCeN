package com.cinema.ticketsystem.booking.repository;

import com.cinema.ticketsystem.booking.entity.Reservation;
import com.cinema.ticketsystem.booking.enums.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    boolean existsBySessionIdAndSeatIdAndStatus(Long sessionId, Long seatId, ReservationStatus status);

    @Query("SELECT r.seatId FROM Reservation r WHERE r.sessionId = :sessionId AND r.status = :status")
    List<Long> findReservedSeatIdsBySessionIdAndStatus(
            @Param("sessionId") Long sessionId,
            @Param("status") ReservationStatus status);
}