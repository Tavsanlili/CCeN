package com.cinema.ticketsystem.catalog.repository;

import com.cinema.ticketsystem.catalog.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessionRepository extends JpaRepository<Session, Long> {

    List<Session> findByMovieId(Long movieId);
}
