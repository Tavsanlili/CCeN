package com.cinema.ticketsystem.catalog.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MovieResponse {
    private Long id;
    private String title;
    private Integer duration;
    private String genre;
}