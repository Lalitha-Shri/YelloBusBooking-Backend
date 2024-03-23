package com.lalitha.busReservationBackend.controller;

import com.lalitha.busReservationBackend.dto.BusBookingDto;
import com.lalitha.busReservationBackend.dto.BusDto;
import com.lalitha.busReservationBackend.service.BusBookingService;
import com.lalitha.busReservationBackend.service.BusService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//this controller is to handle API endpoints of BusBookingDto
@RestController
@RequestMapping("api/bus/book")
@CrossOrigin
public class BusBookingController {
    @Autowired
    private BusBookingService busService;
    //Post Mapping is to call post endpoints that saves the request body
    @PostMapping
    public ResponseEntity<BusBookingDto> saveBooking(@RequestBody @Valid BusBookingDto busDto)
    {
        BusBookingDto savedBus=busService.add(busDto);
        return new ResponseEntity<>(savedBus, HttpStatus.CREATED);
    }
    //Getmapping is to get all the bus details
    @GetMapping
    public ResponseEntity<List<BusBookingDto>> getAllBus()
    {
        List<BusBookingDto> allBus=busService.getAll();
        return new ResponseEntity<List<BusBookingDto>>(allBus,HttpStatus.OK);
    }
    //gets the booking details by sending the passenger id as path variable to display in viewEtickets component of react
    @GetMapping("/passenger/{id}")
    public ResponseEntity<BusBookingDto> getbypassenger_id(@PathVariable("id") Long id)
    {
        BusBookingDto passenger=busService.getByPassengerId(id);
        return new ResponseEntity<BusBookingDto>(passenger,HttpStatus.OK);
    }


}
