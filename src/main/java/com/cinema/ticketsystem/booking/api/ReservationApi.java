package com.cinema.ticketsystem.booking.api;

import com.cinema.ticketsystem.booking.dto.request.ReservationRequest;
import com.cinema.ticketsystem.booking.dto.response.ReservationResponse;
import com.cinema.ticketsystem.common.dto.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Reservations", description = "Bilet rezervasyon yönetimi API'si")
public interface ReservationApi {

    @Operation(
            summary = "Yeni rezervasyon oluştur",
            description = "Belirtilen seans ve koltuk için yeni bir rezervasyon (bilet) oluşturur."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rezervasyon başarıyla oluşturuldu"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Seans veya koltuk bulunamadı",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Koltuk zaten rezerve edilmiş",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    ResponseEntity<ReservationResponse> createReservation(@Valid @RequestBody ReservationRequest request);

    @Operation(
            summary = "Rezervasyonu iptal et",
            description = "ID değeri verilen rezervasyonu iptal eder (status CANCELLED olur)."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rezervasyon başarıyla iptal edildi"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Rezervasyon bulunamadı",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Rezervasyon zaten iptal edilmiş",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    ResponseEntity<ReservationResponse> cancelReservation(@PathVariable("id") Long id);
}