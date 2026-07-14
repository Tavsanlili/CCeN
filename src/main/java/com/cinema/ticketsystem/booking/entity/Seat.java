package com.cinema.ticketsystem.booking.entity;

import com.cinema.ticketsystem.booking.enums.SeatType;
import com.cinema.ticketsystem.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "seats")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Seat extends BaseEntity {

    // Koltuğun bulunduğu salonun adı (Katalog modülündeki seanslarla eşleşmesi için)
    @Column(nullable = false)
    private String hallName;

    // Koltuk sırası (Örn: "A", "B", "C")
    @Column(nullable = false, length = 2)
    private String rowLetter;

    // Koltuk numarası (Örn: 1, 2, 3)
    @Column(nullable = false)
    private Integer seatNumber;

    // Koltuğun tipi (Veritabanına "VIP" veya "STANDARD" olarak metin şeklinde yazılır)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SeatType type = SeatType.STANDARD;
}