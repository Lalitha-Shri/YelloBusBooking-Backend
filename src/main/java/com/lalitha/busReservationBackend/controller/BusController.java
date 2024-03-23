package com.lalitha.busReservationBackend.controller;

import com.lalitha.busReservationBackend.dto.BusDto;
import com.lalitha.busReservationBackend.entity.Bus;
import com.lalitha.busReservationBackend.repository.BusRepository;
import com.lalitha.busReservationBackend.service.BusService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/api/bus")
public class BusController  {
    @Autowired
    private BusService busService;
    @Autowired
    private BusRepository busRepository;
    //Postmapping is to add the new bus details to database
    @PostMapping
    public ResponseEntity<BusDto> saveBus(@RequestBody @Valid  BusDto busDto)
    {
        BusDto savedBus=busService.addBus(busDto);
        return new ResponseEntity<>(savedBus, HttpStatus.CREATED);
    }
    //Getmapping is to get all the bus details
    @GetMapping
    public ResponseEntity<List<BusDto>> getAllBus()
    {
        List<BusDto> allBus=busService.getAllBus();
        return new ResponseEntity<List<BusDto>>(allBus,HttpStatus.OK);
    }
    @GetMapping("/{busId}")//get bus details by id
    public ResponseEntity<BusDto> getBusId(@PathVariable("busId")Long busId)
    {
        BusDto getBus=busService.getBusById(busId);
        return new ResponseEntity<BusDto>(getBus,HttpStatus.OK);
    }
    @PutMapping("/{id}")//update bus details by id
    public ResponseEntity<BusDto> updateBus(@PathVariable("id") Long id, @RequestBody  @Valid BusDto busDto)
    {
        BusDto updatedBus=busService.updateBus(busDto,id);
        return new ResponseEntity<>(updatedBus,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public  String deleteBus(@PathVariable("id")Long busId)
    {
        busService.deleteBus(busId);
        return "Bus is deleted";
    }
//put mapping updates the available seats for every add passenger for bus booking
    @PutMapping("/updateAvailableTickets/{bus_id}/{number}")
    void updateAvailableTickets(@PathVariable("bus_id") Long bus_id,@PathVariable("number") int number) {
//        System.out.println(bus_id);
//        int number = 2;
        List<Bus> list = busRepository.findAll();
        System.out.println(number);

        for(Bus bus : list) {
            if(Objects.equals(bus.getId(), bus_id)) {
                System.out.println(bus.getAvailable_seats());
                bus.setAvailable_seats(bus.getAvailable_seats() - number);
                busRepository.save(bus);
                System.out.println(bus.getAvailable_seats());
            }
        }
    }
}
