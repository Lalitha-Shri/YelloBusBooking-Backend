package com.lalitha.busReservationBackend.repository;

import com.lalitha.busReservationBackend.entity.BusBooking;
import com.lalitha.busReservationBackend.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger,Long> {
    Passenger findByEmail(String email);

}
