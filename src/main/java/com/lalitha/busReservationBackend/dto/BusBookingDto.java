package com.lalitha.busReservationBackend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.lalitha.busReservationBackend.entity.Bus;
import com.lalitha.busReservationBackend.entity.Passenger;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusBookingDto {
    private Long id;
    @NotEmpty
    private String bookingId;
    @NotEmpty
    private String bookingDate;
    @NotEmpty
    private Passenger passenger;
    @NotEmpty
    private String busName;
    @NotEmpty
    private int fee;
    @NotEmpty
    private LocalDate departureDate;
    //private Bus bus;
}
