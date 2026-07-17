package com.cinema.ticketsystem.booking.service;

import com.cinema.ticketsystem.booking.dto.request.ReservationRequest;
import com.cinema.ticketsystem.booking.dto.response.ReservationResponse;
import com.cinema.ticketsystem.booking.entity.Reservation;
import com.cinema.ticketsystem.booking.entity.Seat;
import com.cinema.ticketsystem.booking.enums.ReservationStatus;
import com.cinema.ticketsystem.booking.enums.SeatType;
import com.cinema.ticketsystem.booking.repository.ReservationRepository;
import com.cinema.ticketsystem.booking.repository.SeatRepository;
import com.cinema.ticketsystem.catalog.repository.SessionRepository;
import com.cinema.ticketsystem.common.exception.BusinessRuleException;
import com.cinema.ticketsystem.common.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private static final BigDecimal BASE_PRICE = new BigDecimal("100.00");
    private static final BigDecimal VIP_SURCHARGE = new BigDecimal("50.00");

    private final ReservationRepository reservationRepository;
    private final SeatRepository seatRepository;
    private final SessionRepository sessionRepository;

    @Transactional
    public ReservationResponse createReservation(ReservationRequest request) {

        // Session gerçekten var mı kontrol et
        if (!sessionRepository.existsById(request.getSessionId())) {
            throw new ResourceNotFoundException("Seans bulunamadı: " + request.getSessionId());
        }

        // Koltuğu kilitli çek (aynı anda başka bir transaction bu koltuğu işleyemez, bekler)
        Seat seat = seatRepository.findByIdWithLock(request.getSeatId())
                .orElseThrow(() -> new ResourceNotFoundException("Koltuk bulunamadı: " + request.getSeatId()));

        // Bu koltuk bu seans için zaten aktif rezerve edilmiş mi?
        boolean alreadyReserved = reservationRepository.existsBySessionIdAndSeatIdAndStatus(
                request.getSessionId(), request.getSeatId(), ReservationStatus.ACTIVE);

        if (alreadyReserved) {
            throw new BusinessRuleException("Bu koltuk bu seans için zaten rezerve edilmiş!");
        }

        BigDecimal ticketPrice = calculatePrice(seat.getType());

        Reservation reservation = Reservation.builder()
                .sessionId(request.getSessionId())
                .seatId(request.getSeatId())
                .customerName(request.getCustomerName())
                .status(ReservationStatus.ACTIVE)
                .ticketPrice(ticketPrice)
                .build();

        Reservation saved = reservationRepository.save(reservation);

        return toResponse(saved);
    }

    @Transactional
    public ReservationResponse cancelReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ResourceNotFoundException("Rezervasyon bulunamadı: " + reservationId));

        if (reservation.getStatus() == ReservationStatus.CANCELLED) {
            throw new BusinessRuleException("Bu rezervasyon zaten iptal edilmiş.");
        }

        reservation.setStatus(ReservationStatus.CANCELLED);
        Reservation saved = reservationRepository.save(reservation);

        return toResponse(saved);
    }

    private BigDecimal calculatePrice(SeatType type) {
        if (type == SeatType.VIP) {
            return BASE_PRICE.add(VIP_SURCHARGE);
        }
        return BASE_PRICE;
    }

    private ReservationResponse toResponse(Reservation reservation) {
        return ReservationResponse.builder()
                .id(reservation.getId())
                .sessionId(reservation.getSessionId())
                .seatId(reservation.getSeatId())
                .customerName(reservation.getCustomerName())
                .status(reservation.getStatus())
                .ticketPrice(reservation.getTicketPrice())
                .build();
    }
}