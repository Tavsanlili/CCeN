package com.cinema.ticketsystem.catalog.api;

import com.cinema.ticketsystem.catalog.dto.response.SessionResponse;
import com.cinema.ticketsystem.common.dto.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Tag(name = "Sessions", description = "Sinema seans yönetimi API'si")
public interface SessionApi {

    @Operation(
            summary = "Tüm seansları listele",
            description = "Sistemdeki tüm sinema seanslarını ilişkili film adıyla birlikte getirir."
    )
    @ApiResponse(responseCode = "200", description = "Başarılı")
    @GetMapping
    ResponseEntity<List<SessionResponse>> getAllSessions();

    @Operation(
            summary = "Seans detayını getir",
            description = "ID değeri verilen spesifik bir sinema seansının detaylarını getirir."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Seans başarıyla bulundu"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Seans bulunamadı",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @GetMapping("/{id}")
    ResponseEntity<SessionResponse> getSessionById(@PathVariable("id") Long id);
}