// Importing package module to code module
package com.lalitha.busReservationBackend.service;

import com.lalitha.busReservationBackend.dto.BusDto;
import com.lalitha.busReservationBackend.entity.Bus;
import com.lalitha.busReservationBackend.repository.BusRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@AllArgsConstructor
@Service
//class
public class BusServiceImpl implements BusService {
    private ModelMapper modelMapper;
    private BusRepository busRepository;

    @Override//get the dto request convert to entity and save in database using repository object
    public BusDto addBus(BusDto busDto) {
        Bus bus=modelMapper.map(busDto, Bus.class);
        Bus savedBus=busRepository.save(bus);
        return modelMapper.map(savedBus,BusDto.class);

    }

    @Override//find bus by id from Db
    public BusDto getBusById(Long id) {
        Bus bus = busRepository.findById(id).get();
        return modelMapper.map(bus,BusDto.class);
    }
    //use findall method to get all the list from Db
    @Override//returns list of dto from Db
    public List<BusDto> getAllBus() {
        List<Bus> busList= busRepository.findAll();
        return busList.stream().map(bus->modelMapper.map(bus,BusDto.class)).collect(Collectors.toList());

    }
//update in Db using save method after setting it to the field
    @Override
    public BusDto updateBus(BusDto busDto, Long id) {
        Bus bus= busRepository.findById(id).get();
        bus.setBusName(busDto.getBusName());
        bus.setPickupLocation(busDto.getPickupLocation());
        bus.setDropLocation(busDto.getDropLocation());
        bus.setPrice(busDto.getPrice());
        bus.setSeats(busDto.getSeats());
        bus.setAvailable_seats(busDto.getAvailable_seats());
        bus.setDepartureDate(busDto.getDepartureDate());
        Bus updatedBus=busRepository.save(bus);
        return modelMapper.map(updatedBus,BusDto.class);
    }

    @Override//delete in Db the bus that equals the sent id
    public void deleteBus(Long id) {
        Bus bus=busRepository.findById(id).get();
        busRepository.delete(bus);

    }
}
