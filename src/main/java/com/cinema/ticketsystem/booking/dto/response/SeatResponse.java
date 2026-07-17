package com.cinema.ticketsystem.booking.dto.response;

import com.cinema.ticketsystem.booking.enums.SeatType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeatResponse {
    private Long id;
    private String hallName;
    private String rowLetter;
    private Integer seatNumber;
    private SeatType type;


    private Boolean available;

}
