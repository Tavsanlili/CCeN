package com.cinema.ticketsystem.config;

import com.cinema.ticketsystem.catalog.entity.Movie;
import com.cinema.ticketsystem.catalog.entity.Session;
import com.cinema.ticketsystem.catalog.entity.enums.Genre;
import com.cinema.ticketsystem.catalog.repository.MovieRepository;
import com.cinema.ticketsystem.catalog.repository.SessionRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final MovieRepository movieRepository;
    private final SessionRepository sessionRepository;

    @Override
    public void run(String... args) {
        if (movieRepository.count() > 0 || sessionRepository.count() > 0) {
            return;
        }

        Movie inception = movieRepository.save(Movie.builder()
                .title("Inception")
                .duration(148)
                .genre(Genre.SCIFI)
                .build());

        Movie interstellar = movieRepository.save(Movie.builder()
                .title("Interstellar")
                .duration(169)
                .genre(Genre.ADVENTURE)
                .build());

        sessionRepository.save(Session.builder()
                .movie(inception)
                .hallName("Hall A")
                .startTime(LocalDateTime.now().plusDays(1).withHour(19).withMinute(30).withSecond(0).withNano(0))
                .build());

        sessionRepository.save(Session.builder()
                .movie(interstellar)
                .hallName("Hall B")
                .startTime(LocalDateTime.now().plusDays(1).withHour(21).withMinute(0).withSecond(0).withNano(0))
                .build());
    }
}