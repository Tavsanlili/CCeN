package com.cinema.ticketsystem.config;

import com.cinema.ticketsystem.booking.entity.Seat;
import com.cinema.ticketsystem.booking.enums.SeatType;
import com.cinema.ticketsystem.booking.repository.SeatRepository;
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
    private final SeatRepository seatRepository;

    private static final int ROWS = 8;
    private static final int SEATS_PER_ROW = 10;
    private static final int VIP_ROW_COUNT = 2; // ilk 2 sıra VIP olsun

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

        if (seatRepository.count() == 0) {
            seedSeatsForHall("Hall A");
            seedSeatsForHall("Hall B");
        }
    }

    private void seedSeatsForHall(String hallName) {
        for (int rowIndex = 0; rowIndex < ROWS; rowIndex++) {
            String rowLetter = String.valueOf((char) ('A' + rowIndex));
            SeatType type = rowIndex < VIP_ROW_COUNT ? SeatType.VIP : SeatType.STANDARD;

            for (int seatNumber = 1; seatNumber <= SEATS_PER_ROW; seatNumber++) {
                seatRepository.save(Seat.builder()
                        .hallName(hallName)
                        .rowLetter(rowLetter)
                        .seatNumber(seatNumber)
                        .type(type)
                        .build());
            }
        }
    }
}