package com.cinema.ticketsystem.catalog.controller;

import com.cinema.ticketsystem.catalog.api.MovieApi;
import com.cinema.ticketsystem.catalog.dto.response.MovieResponse;
import com.cinema.ticketsystem.catalog.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MovieController implements MovieApi {

    private final MovieService movieService;

    @Override
    public ResponseEntity<List<MovieResponse>> getAllMovies() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    @Override
    public ResponseEntity<MovieResponse> getMovieById(Long id) {
        return ResponseEntity.ok(movieService.getMovieById(id));
    }
}