package com.lalitha.busReservationBackend.repository;

import com.lalitha.busReservationBackend.dto.BusBookingDto;
import com.lalitha.busReservationBackend.entity.Bus;
import com.lalitha.busReservationBackend.entity.BusBooking;
import com.lalitha.busReservationBackend.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<BusBooking,Long> {
    //find passengerby id declaration
    BusBooking findByPassenger_id(Long id);
   // List<BusBookingDto> findByBus(Bus bus);


}
