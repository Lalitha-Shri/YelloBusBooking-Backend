package com.lalitha.busReservationBackend.service;


import com.lalitha.busReservationBackend.dto.JwtAuthResponse;
import com.lalitha.busReservationBackend.dto.LoginDto;
import com.lalitha.busReservationBackend.dto.RegisterDto;

public interface AuthService {
    String register(RegisterDto registerDto);


    JwtAuthResponse login(LoginDto loginDto);
}
