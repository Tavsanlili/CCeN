package com.cinema.ticketsystem.booking.api;

import com.cinema.ticketsystem.booking.dto.response.SeatResponse;
import com.cinema.ticketsystem.common.dto.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Tag(name = "Seats", description = "Seans bazlı koltuk durumu API'si")
public interface SeatApi {

    @Operation(
            summary = "Seansa ait koltukları listele",
            description = "Belirtilen seansın salonundaki tüm koltukları, doluluk durumlarıyla birlikte getirir."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Başarılı"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Seans bulunamadı",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    ResponseEntity<List<SeatResponse>> getSeatsForSession(@PathVariable("sessionId") Long sessionId);
}