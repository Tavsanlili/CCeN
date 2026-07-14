package com.cinema.ticketsystem.catalog.entity;

import com.cinema.ticketsystem.common.entity.BaseEntity;
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
    private Integer duration; // Dakika cinsinden süre

    @Column(nullable = false, length = 50)
    private String genre;
}