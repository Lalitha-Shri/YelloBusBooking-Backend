package com.lalitha.busReservationBackend.controller;

import com.lalitha.busReservationBackend.dto.BusBookingDto;
import com.lalitha.busReservationBackend.dto.PassengerDto;
import com.lalitha.busReservationBackend.entity.Passenger;
import com.lalitha.busReservationBackend.repository.PassengerRepository;
import com.lalitha.busReservationBackend.service.PassengerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/api/passenger")
public class PassengerController {
    @Autowired
    private PassengerService passengerService;
    @Autowired
    private PassengerRepository passengerRepository;
    //Postmapping is to add the passenger details to database
    @PostMapping    //Postmapping is to add the new passenger details to database
    public ResponseEntity<PassengerDto> savePassenger(@RequestBody @Valid PassengerDto passengerDto)
    {
        PassengerDto savedPassenger=passengerService.addPassenger(passengerDto);
        return new ResponseEntity<>(savedPassenger, HttpStatus.CREATED);
    }
    //Getmapping is to get all the passengers details
    @GetMapping
    public ResponseEntity<List<PassengerDto>> getAllUser()
    {
        List<PassengerDto> allUser=passengerService.getAllPassenger();
        return new ResponseEntity<List<PassengerDto>>(allUser,HttpStatus.OK);
    }
    @GetMapping("/{passengerId}") //get passenger by id
    public ResponseEntity<PassengerDto> getUserId(@PathVariable("passengerId")Long userId)
    {
        PassengerDto getUser=passengerService.getPassengerById(userId);
        return new ResponseEntity<PassengerDto>(getUser,HttpStatus.OK);
    }
    @DeleteMapping("/{id}") //delete passenger by id
    public  String deleteUser(@PathVariable("id")Long userId)
    {
        passengerService.deletePassenger(userId);
        return "Passenger is deleted";
    }
    @PutMapping("/canceled/{pnr_details}") //update the cancel status by clicking cancel booking button in my booking component react
    public String updateEmployee(@PathVariable Long pnr_details) {
        Passenger cancel = passengerRepository.findById(pnr_details).get();
        System.out.println(pnr_details);

        System.out.println(cancel.getId());
        if (cancel.getId() == pnr_details) {
            cancel.setCancelStat(true);
            passengerRepository.save(cancel);
            System.out.println(cancel.isCancelStat());
            return cancel.getPassengerName() + " ticket is cancelled";

        }
        return null;

    }
    @GetMapping("email/{email}")
    public ResponseEntity<PassengerDto> getpassengerbyEmail(@PathVariable String email)
    {
        PassengerDto passengerDto=passengerService.findByEmail(email);
        return new ResponseEntity<PassengerDto>(passengerDto,HttpStatus.OK);
    }
    @PutMapping("cancelticket/{id}")
    public ResponseEntity<PassengerDto> cancelTicket(@PathVariable("id") Long id){
        PassengerDto updatedPassenger=passengerService.cancelTicket(id);
        return ResponseEntity.ok(updatedPassenger);

    }
}
