package com.cinema.ticketsystem.catalog.entity;

import com.cinema.ticketsystem.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sessions")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Session extends BaseEntity {


    @Column(nullable = false)
    private Long movieId;

    @Column(nullable = false, length = 50)
    private String hallName;

    @Column(nullable = false)
    private LocalDateTime startTime;
}