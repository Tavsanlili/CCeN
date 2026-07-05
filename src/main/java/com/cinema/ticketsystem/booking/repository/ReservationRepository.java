package com.cinema.ticketsystem.booking.repository;

import com.cinema.ticketsystem.booking.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
