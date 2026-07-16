package com.cinema.ticketsystem.catalog.repository;

import com.cinema.ticketsystem.catalog.entity.Movie;
import com.cinema.ticketsystem.catalog.entity.enums.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("""
            SELECT m FROM Movie m
            WHERE (:title IS NULL OR LOWER(m.title) LIKE LOWER(CONCAT('%', :title, '%')))
            AND (:genre IS NULL OR m.genre = :genre)
            """)
    List<Movie> search(@Param("title") String title, @Param("genre") Genre genre);
}