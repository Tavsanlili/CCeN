package com.cinema.ticketsystem.booking.entity;

import com.cinema.ticketsystem.booking.enums.SeatType;
import com.cinema.ticketsystem.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "seats",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_seat_hall_row_number",
                columnNames = {"hall_name", "row_letter", "seat_number"}
        )
)
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Seat extends BaseEntity {

    @Column(nullable = false)
    private String hallName;

    @Column(nullable = false, length = 2)
    private String rowLetter;

    @Column(nullable = false)
    private Integer seatNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SeatType type = SeatType.STANDARD;
}