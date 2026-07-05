package com.cinema.ticketsystem.booking.repository;

import com.cinema.ticketsystem.booking.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Long> {
}
