package com.cinema.ticketsystem.booking.controller;

import com.cinema.ticketsystem.booking.api.ReservationApi;
import com.cinema.ticketsystem.booking.dto.request.ReservationRequest;
import com.cinema.ticketsystem.booking.dto.response.ReservationResponse;
import com.cinema.ticketsystem.booking.service.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reservations")
@RequiredArgsConstructor
public class ReservationController implements ReservationApi {

    private final ReservationService reservationService;

    @Override
    @PostMapping
    public ResponseEntity<ReservationResponse> createReservation(@Valid @RequestBody ReservationRequest request) {
        return ResponseEntity.ok(reservationService.createReservation(request));
    }

    @Override
    @PatchMapping("/{id}/cancel")
    public ResponseEntity<ReservationResponse> cancelReservation(@PathVariable("id") Long id) {
        return ResponseEntity.ok(reservationService.cancelReservation(id));
    }
}