package com.cinema.ticketsystem.catalog.repository;

import com.cinema.ticketsystem.catalog.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, Long> {

    @Query("SELECT s FROM Session s JOIN FETCH s.movie")
    List<Session> findAllWithMovie();

    @Query("SELECT s FROM Session s JOIN FETCH s.movie WHERE s.id = :id")
    Optional<Session> findByIdWithMovie(@Param("id") Long id);

    @Query("SELECT s FROM Session s JOIN FETCH s.movie WHERE s.movie.id = :movieId")
    List<Session> findByMovieIdWithMovie(@Param("movieId") Long movieId);
}