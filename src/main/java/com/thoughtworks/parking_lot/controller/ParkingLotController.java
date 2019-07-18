package com.thoughtworks.parking_lot.controller;

import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.repository.ParkingPotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ParkingLotController {
    @Autowired
    private ParkingPotRepository parkingPotRepository;

    @PostMapping("/parking-lots")
    public ResponseEntity addParkingLot(@RequestBody ParkingLot parkingLot){
        ParkingLot resultParkingLot = parkingPotRepository.save(parkingLot);
        return ResponseEntity.ok(resultParkingLot);
    }

    @DeleteMapping("/parking-lots/{id}")
    public ResponseEntity deleteParkingLot(@PathVariable long id){
        parkingPotRepository.deleteById(id);
        return ResponseEntity.ok(1);
    }
}
