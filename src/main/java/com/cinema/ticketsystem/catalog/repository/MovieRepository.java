package com.cinema.ticketsystem.catalog.repository;

import com.cinema.ticketsystem.catalog.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
