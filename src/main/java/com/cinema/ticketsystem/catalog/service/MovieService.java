package com.cinema.ticketsystem.catalog.service;

import com.cinema.ticketsystem.catalog.dto.request.MovieRequest;
import com.cinema.ticketsystem.catalog.dto.response.MovieResponse;
import com.cinema.ticketsystem.catalog.entity.Movie;
import com.cinema.ticketsystem.catalog.entity.enums.Genre;
import com.cinema.ticketsystem.catalog.repository.MovieRepository;
import com.cinema.ticketsystem.common.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    public MovieResponse createMovie(MovieRequest request) {
        Genre genre = parseGenre(request.getGenre());

        Movie movie = Movie.builder()
                .title(request.getTitle())
                .duration(request.getDuration())
                .genre(genre)
                .build();

        Movie saved = movieRepository.save(movie);
        return MovieResponse.from(saved);
    }

    private Genre parseGenre(String genreValue) {
        try {
            return Genre.valueOf(genreValue.toUpperCase());
        } catch (IllegalArgumentException e) {
            String validValues = Arrays.stream(Genre.values())
                    .map(Enum::name)
                    .collect(Collectors.joining(", "));
            throw new IllegalArgumentException(
                    "Geçersiz film türü: '" + genreValue + "'. Geçerli değerler: " + validValues
            );
        }
    }
}