package com.cinema.ticketsystem.catalog.service;

import com.cinema.ticketsystem.catalog.dto.response.SessionResponse;
import com.cinema.ticketsystem.catalog.entity.Movie;
import com.cinema.ticketsystem.catalog.entity.Session;
import com.cinema.ticketsystem.catalog.repository.MovieRepository;
import com.cinema.ticketsystem.catalog.repository.SessionRepository;
import com.cinema.ticketsystem.common.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final SessionRepository sessionRepository;
    private final MovieRepository movieRepository;

    public List<SessionResponse> getAllSessions() {
        List<Session> sessions = sessionRepository.findAll();
        return mapToResponsesWithBatchFetch(sessions);
    }

    public SessionResponse getSessionById(Long id) {
        Session session = sessionRepository.findById(id)
                .orElseThrow(() -> ResourceNotFoundException.of("Session", id));

        return toResponse(session);
    }

    public List<SessionResponse> getSessionsByMovieId(Long movieId) {
        if (!movieRepository.existsById(movieId)) {
            throw ResourceNotFoundException.of("Movie", movieId);
        }

        List<Session> sessions = sessionRepository.findByMovieId(movieId);
        return mapToResponsesWithBatchFetch(sessions);
    }


    private List<SessionResponse> mapToResponsesWithBatchFetch(List<Session> sessions) {
        if (sessions.isEmpty()) {
            return List.of();
        }

        Set<Long> movieIds = sessions.stream()
                .map(Session::getMovieId)
                .collect(Collectors.toSet());

        Map<Long, Movie> movieMap = movieRepository.findAllById(movieIds).stream()
                .collect(Collectors.toMap(Movie::getId, movie -> movie));

        return sessions.stream()
                .map(session -> {
                    Movie movie = movieMap.get(session.getMovieId());

                    if (movie == null) {
                        throw new ResourceNotFoundException("Veri bütünlüğü hatası: Film bulunamadı (id=" + session.getMovieId() + ")");
                    }

                    return new SessionResponse(
                            session.getId(),
                            session.getMovieId(),
                            session.getHallName(),
                            session.getStartTime()
                    );
                })
                .toList();
    }

    private SessionResponse toResponse(Session session) {
        Movie movie = movieRepository.findById(session.getMovieId())
                .orElseThrow(() -> ResourceNotFoundException.of("Movie", session.getMovieId()));

        return new SessionResponse(
                session.getId(),
                session.getMovieId(),
                session.getHallName(),
                session.getStartTime()
        );
    }
}