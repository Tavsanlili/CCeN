package com.cinema.ticketsystem.catalog.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class MovieRequest {

    @NotBlank(message = "Film adı kesinlikle boş bırakılamaz!")
    private String title;

    @Positive(message = "Film süresi 0 veya eksi bir değer olamaz!")
    private Integer duration;

    @NotBlank(message = "Film türü boş bırakılamaz!")
    private String genre;
}