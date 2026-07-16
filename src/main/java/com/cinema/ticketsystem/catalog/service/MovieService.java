package com.cinema.ticketsystem.catalog.service;

import com.cinema.ticketsystem.catalog.dto.response.MovieResponse;
import com.cinema.ticketsystem.catalog.entity.Movie;
import com.cinema.ticketsystem.catalog.repository.MovieRepository;
import com.cinema.ticketsystem.common.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    public List<MovieResponse> getAllMovies() {
        return movieRepository.findAll().stream()
                .map(MovieResponse::from)
                .toList();
    }

    public MovieResponse getMovieById(Long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> ResourceNotFoundException.of("Movie", id));

        return MovieResponse.from(movie);
    }
}