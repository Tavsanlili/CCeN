package com.cinema.ticketsystem.catalog.service;

import com.cinema.ticketsystem.catalog.dto.response.SessionResponse;
import com.cinema.ticketsystem.catalog.entity.Session;
import com.cinema.ticketsystem.catalog.repository.MovieRepository;
import com.cinema.ticketsystem.catalog.repository.SessionRepository;
import com.cinema.ticketsystem.common.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final SessionRepository sessionRepository;
    private final MovieRepository movieRepository;

    public List<SessionResponse> getAllSessions() {
        return sessionRepository.findAllWithMovie().stream()
                .map(SessionResponse::from)
                .toList();
    }

    public SessionResponse getSessionById(Long id) {
        Session session = sessionRepository.findByIdWithMovie(id)
                .orElseThrow(() -> ResourceNotFoundException.of("Session", id));

        return SessionResponse.from(session);
    }

    public List<SessionResponse> getSessionsByMovieId(Long movieId) {
        if (!movieRepository.existsById(movieId)) {
            throw ResourceNotFoundException.of("Movie", movieId);
        }

        return sessionRepository.findByMovieIdWithMovie(movieId).stream()
                .map(SessionResponse::from)
                .toList();
    }
}