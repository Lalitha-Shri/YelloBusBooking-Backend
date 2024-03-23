package com.lalitha.busReservationBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//dto for login form
public class LoginDto {
    private String username;
    private String password;

}
