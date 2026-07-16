package com.cinema.ticketsystem.catalog.api;

import com.cinema.ticketsystem.catalog.dto.request.MovieRequest;
import com.cinema.ticketsystem.catalog.dto.response.MovieResponse;
import com.cinema.ticketsystem.catalog.dto.response.SessionResponse;
import com.cinema.ticketsystem.common.dto.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/movies")
@Tag(name = "Movies", description = "Sinema filmleri yönetim API'si")
public interface MovieApi {

    @Operation(summary = "Tüm filmleri listele", description = "Vizyondaki tüm filmleri getirir.")
    @ApiResponse(responseCode = "200", description = "Başarılı")
    @GetMapping
    ResponseEntity<List<MovieResponse>> getAllMovies();

    @Operation(summary = "Film detayını getir", description = "ID'si verilen filmin detaylarını getirir.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Başarılı"),
            @ApiResponse(responseCode = "404", description = "Film bulunamadı",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{id}")
    ResponseEntity<MovieResponse> getMovieById(@PathVariable("id") Long id);

    @Operation(summary = "Yeni film oluştur", description = "Verilen bilgilerle yeni bir film kaydı oluşturur.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Film başarıyla oluşturuldu"),
            @ApiResponse(responseCode = "400", description = "Geçersiz istek (validation hatası)",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping
    ResponseEntity<MovieResponse> createMovie(@Valid @RequestBody MovieRequest request);

    @Operation(
            summary = "Filme ait seansları getir",
            description = "Verilen film ID'sine ait tüm seansları listeler."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Başarılı"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Film bulunamadı",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @GetMapping("/{movieId}/sessions")
    ResponseEntity<List<SessionResponse>> getSessionsByMovieId(@PathVariable("movieId") Long movieId);
}