package com.cinema.ticketsystem.catalog.dto.response;

import com.cinema.ticketsystem.catalog.entity.Movie;

public record MovieResponse(
        Long id,
        String title,
        Integer duration,
        String genre
) {
    public static MovieResponse from(Movie movie) {
        return new MovieResponse(
                movie.getId(),
                movie.getTitle(),
                movie.getDuration(),
                movie.getGenre().name()
        );
    }
}