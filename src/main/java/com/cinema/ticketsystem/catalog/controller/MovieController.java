package com.cinema.ticketsystem.catalog.controller;

import com.cinema.ticketsystem.catalog.entity.Movie;
import com.cinema.ticketsystem.catalog.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cinema.ticketsystem.catalog.dto.request.MovieRequest;
import com.cinema.ticketsystem.catalog.dto.response.MovieResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movies") // Uç noktamızın ana adresi
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }
}   