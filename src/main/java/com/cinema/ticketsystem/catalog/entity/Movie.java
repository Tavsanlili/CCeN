package com.cinema.ticketsystem.catalog.entity;

import com.cinema.ticketsystem.common.entity.BaseEntity;
import com.cinema.ticketsystem.catalog.entity.enums.Genre;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "movies")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movie extends BaseEntity {

    @Column(nullable = false, length = 150)
    private String title;

    @Column(nullable = false)
    private Integer duration;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private Genre genre;
}