package com.cinema.ticketsystem.catalog.controller;

import com.cinema.ticketsystem.catalog.dto.response.SessionResponse;
import com.cinema.ticketsystem.catalog.service.SessionService;
import com.cinema.ticketsystem.common.dto.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sessions")
@RequiredArgsConstructor
@Tag(name = "Sessions", description = "Sinema seans yönetimi API'si")
public class SessionController {

    private final SessionService sessionService;

    @Operation(
            summary = "Tüm seansları listele",
            description = "Sistemdeki tüm sinema seanslarını ilişkili film adıyla birlikte getirir."
    )
    @ApiResponse(responseCode = "200", description = "Başarılı")
    @GetMapping
    public ResponseEntity<List<SessionResponse>> getAllSessions() {
        return ResponseEntity.ok(sessionService.getAllSessions());
    }

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
    public ResponseEntity<SessionResponse> getSessionById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(sessionService.getSessionById(id));
    }
}