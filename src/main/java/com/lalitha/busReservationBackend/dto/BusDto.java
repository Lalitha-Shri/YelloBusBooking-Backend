package com.lalitha.busReservationBackend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusDto {
    private Long Id;
    @NotEmpty
    private String busName;
    @NotEmpty
    private String pickupLocation;
    @NotEmpty
    private String dropLocation;
    @NotEmpty
    private int seats;
    @NotEmpty
     private  int available_seats;
    @NotEmpty
    private LocalDate departureDate;
    @NotEmpty
    private int price;
}
