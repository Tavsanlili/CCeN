package com.cinema.ticketsystem.catalog.entity;

import com.cinema.ticketsystem.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sessions")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(exclude = "movie") // lazy proxy'nin toString'de tetiklenmesini engeller
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Session extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    @Column(nullable = false, length = 50)
    private String hallName;

    @Column(nullable = false)
    private LocalDateTime startTime;
}