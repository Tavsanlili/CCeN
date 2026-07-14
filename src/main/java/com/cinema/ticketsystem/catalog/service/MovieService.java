package com.cinema.ticketsystem.catalog.service;

import com.cinema.ticketsystem.catalog.entity.Movie;
import com.cinema.ticketsystem.catalog.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.cinema.ticketsystem.catalog.dto.request.MovieRequest;
import com.cinema.ticketsystem.catalog.dto.response.MovieResponse;  

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    // Veritabanındaki tüm filmleri listeleyen metot
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }
}