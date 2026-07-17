package com.cinema.ticketsystem.booking.controller;

import com.cinema.ticketsystem.booking.api.SeatApi;
import com.cinema.ticketsystem.booking.dto.response.SeatResponse;
import com.cinema.ticketsystem.booking.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sessions/{sessionId}/seats")
@RequiredArgsConstructor
public class SeatController implements SeatApi {

    private final SeatService seatService;

    @Override
    @GetMapping
    public ResponseEntity<List<SeatResponse>> getSeatsForSession(@PathVariable("sessionId") Long sessionId) {
        return ResponseEntity.ok(seatService.getSeatsForSession(sessionId));
    }
}