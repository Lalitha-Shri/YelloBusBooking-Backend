// Importing package module to code module
package com.lalitha.busReservationBackend.service;

import com.lalitha.busReservationBackend.dto.BusBookingDto;
import com.lalitha.busReservationBackend.dto.BusDto;
import com.lalitha.busReservationBackend.entity.Bus;
import com.lalitha.busReservationBackend.entity.BusBooking;
import com.lalitha.busReservationBackend.entity.Passenger;
import com.lalitha.busReservationBackend.repository.BookingRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
// Annotation
@Service
@AllArgsConstructor
// Class
public class BusBookingImpl implements BusBookingService {
    private ModelMapper modelMapper;
    private BookingRepository bookingRepository;
    @Override//get the dto request convert to entity and save in database using repository object
    public BusBookingDto add(BusBookingDto busBooking) {
        BusBooking bus=modelMapper.map(busBooking, BusBooking.class);
        BusBooking savedBus=bookingRepository.save(bus);
        return modelMapper.map(savedBus, BusBookingDto.class);

    }

    @Override
    public BusBookingDto getById(Long id) {
        return null;
    }


    @Override//use findall method to get all the list from Db
    public List<BusBookingDto> getAll() {//returns list of dto from Db
        List<BusBooking> busBookingList= bookingRepository.findAll();
        return busBookingList.stream().map(bus->modelMapper.map(bus, BusBookingDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<BusBookingDto> getByPassenger(Passenger passenger) {
        return null;
    }

    @Override
    public BusBookingDto getByPassengerId(Long id) {
        BusBooking busBooking=bookingRepository.findByPassenger_id(id);
        return modelMapper.map(busBooking,BusBookingDto.class);
    }


//    @Override
//    public List<BusBookingDto> getByBus(Bus bus) {
//        return null;
//    }

}
