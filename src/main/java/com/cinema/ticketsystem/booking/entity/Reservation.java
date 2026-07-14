package com.cinema.ticketsystem.booking.entity;

import com.cinema.ticketsystem.booking.enums.ReservationStatus;
import com.cinema.ticketsystem.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "reservations")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reservation extends BaseEntity {

    // Catalog modülündeki veriyi referans alıyoruz (Direct Entity Mapping YAPIYORUZ)
    @Column(nullable = false)
    private Long sessionId;

    // Hangi koltuk satıldı?
    @Column(nullable = false)
    private Long seatId;

    // Bilet kimin adına kesildi?
    @Column(nullable = false, length = 100)
    private String customerName;

    // Biletin anlık durumu (Veritabanına 0, 1 diye değil, ACTIVE, CANCELLED diye string olarak yazılır)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReservationStatus status = ReservationStatus.ACTIVE;
    
    // Bilet fiyatı (İndirimler veya farklı salonlar için fiyat değişebileceğinden o anki fiyatı buraya mühürleriz)
    @Column(nullable = false)
    private Double ticketPrice;
}