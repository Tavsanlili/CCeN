package com.cinema.ticketsystem.catalog.controller;

import com.cinema.ticketsystem.catalog.api.MovieApi;
 import com.cinema.ticketsystem.catalog.dto.request.MovieRequest;
import com.cinema.ticketsystem.catalog.dto.response.MovieResponse;
import com.cinema.ticketsystem.catalog.dto.response.SessionResponse;
import com.cinema.ticketsystem.catalog.entity.enums.Genre;
import com.cinema.ticketsystem.catalog.service.MovieService;
import com.cinema.ticketsystem.catalog.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MovieController implements MovieApi {

    private final MovieService movieService;
    private final SessionService sessionService;

    @Override
    public ResponseEntity<List<MovieResponse>> getAllMovies() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    @Override
    public ResponseEntity<List<MovieResponse>> searchMovies(String title, Genre genre) {
        return ResponseEntity.ok(movieService.searchMovies(title, genre));
    }

    @Override
    public ResponseEntity<MovieResponse> getMovieById(Long id) {
        return ResponseEntity.ok(movieService.getMovieById(id));
    }

    @Override
    public ResponseEntity<MovieResponse> createMovie(MovieRequest request) {
        MovieResponse response = movieService.createMovie(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public ResponseEntity<List<SessionResponse>> getSessionsByMovieId(Long movieId) {
        return ResponseEntity.ok(sessionService.getSessionsByMovieId(movieId));
    }
}