package com.cinema.ticketsystem.catalog.controller;

import com.cinema.ticketsystem.catalog.api.SessionApi;
import com.cinema.ticketsystem.catalog.dto.response.SessionResponse;
import com.cinema.ticketsystem.catalog.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sessions")
@RequiredArgsConstructor
public class SessionController implements SessionApi {

    private final SessionService sessionService;

    @Override
    @GetMapping
    public ResponseEntity<List<SessionResponse>> getAllSessions() {
        return ResponseEntity.ok(sessionService.getAllSessions());
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<SessionResponse> getSessionById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(sessionService.getSessionById(id));
    }
}