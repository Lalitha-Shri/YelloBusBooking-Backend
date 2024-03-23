package com.lalitha.busReservationBackend.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassengerDto {
    private Long Id;
    @NotEmpty
    private  String passengerName;
    @NotEmpty
    private Long aadharId;
    @NotEmpty
    private Long contactNo;
    @NotEmpty
    private  String email;
    @NotEmpty
    private int age;
    public boolean cancelStat;

    private boolean journeyStat;

}
