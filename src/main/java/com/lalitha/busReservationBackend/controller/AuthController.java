package com.lalitha.busReservationBackend.controller;
import com.lalitha.busReservationBackend.dto.JwtAuthResponse;
import com.lalitha.busReservationBackend.dto.LoginDto;
import com.lalitha.busReservationBackend.dto.RegisterDto;
import com.lalitha.busReservationBackend.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
@AllArgsConstructor
@CrossOrigin
public class AuthController {
    AuthService authService;
    //controller endpoints for login and register form
    @PostMapping("/register")
    public String register(@RequestBody RegisterDto registerDto)
    {
       String response1=authService.register(registerDto);
       return response1;
    }
    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto)
    {
        JwtAuthResponse jwtAuthResponse=authService.login(loginDto);
        return new ResponseEntity<>(jwtAuthResponse,HttpStatus.OK);
    }
}
