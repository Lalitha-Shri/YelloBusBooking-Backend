// Importing package module to code module
package com.lalitha.busReservationBackend.service;
import com.lalitha.busReservationBackend.dto.BusBookingDto;
import com.lalitha.busReservationBackend.dto.PassengerDto;
import com.lalitha.busReservationBackend.entity.BusBooking;
import com.lalitha.busReservationBackend.entity.Passenger;
import com.lalitha.busReservationBackend.exception.ResourceNotFoundException;
import com.lalitha.busReservationBackend.repository.PassengerRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
//class
public class PassengerServiceImpl implements PassengerService{
    private ModelMapper modelMapper;
    private PassengerRepository passengerRepository;
    @Override//get the dto request convert to entity and save in database using repository object
    public PassengerDto addPassenger(PassengerDto passengerDto) {
        Passenger passenger=modelMapper.map(passengerDto, Passenger.class);
        Passenger savePassenger=passengerRepository.save(passenger);
        return modelMapper.map(savePassenger, PassengerDto.class);
    }

    @Override//returns dto from Db by searching using id
    public PassengerDto getPassengerById(Long id) {
        Passenger passenger = passengerRepository.findById(id).get();
        return modelMapper.map(passenger,PassengerDto.class);
    }

    @Override//returns list of dto from Db
    public List<PassengerDto> getAllPassenger() {
        List<Passenger> passengerList= passengerRepository.findAll();
        return passengerList.stream().map(passenger->modelMapper.map(passenger,PassengerDto.class)).collect(Collectors.toList());
    }

    @Override
    public PassengerDto updatePassenger(PassengerDto passengerDto, Long id) {
        return null;
    }

    @Override
    public void deletePassenger(Long id) {//delete passenger by id
        Passenger passenger=passengerRepository.findById(id).get();
        passengerRepository.delete(passenger);

    }

    @Override
    public PassengerDto findByEmail(String email) {//find passenger by email from Db
        Passenger passenger=passengerRepository.findByEmail(email);
        return modelMapper.map(passenger,PassengerDto.class);
    }

    @Override
    public PassengerDto cancelTicket(Long id) {// make change in db the cancel status as true
        Passenger passenger=passengerRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("User","id",id));
        if(!passenger.cancelStat) {
            passenger.setCancelStat(Boolean.TRUE);
        }
        Passenger updatedPassenger=passengerRepository.save(passenger);
        return modelMapper.map(updatedPassenger,PassengerDto.class);
    }

}
