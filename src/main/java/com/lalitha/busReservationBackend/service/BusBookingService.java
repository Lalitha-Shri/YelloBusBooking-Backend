package com.lalitha.busReservationBackend.service;

import com.lalitha.busReservationBackend.dto.BusBookingDto;
import com.lalitha.busReservationBackend.entity.Bus;
import com.lalitha.busReservationBackend.entity.BusBooking;
import com.lalitha.busReservationBackend.entity.Passenger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BusBookingService {
    BusBookingDto add(BusBookingDto busBooking);
    BusBookingDto getById(Long id);
    List<BusBookingDto> getAll();
    List<BusBookingDto> getByPassenger(Passenger passenger);

    BusBookingDto getByPassengerId(Long id);


}
