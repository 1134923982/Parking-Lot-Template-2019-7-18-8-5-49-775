package com.thoughtworks.parking_lot.controller;

import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ParkingLotController {
    @Autowired
    private ParkingLotRepository parkingLotRepository;

    @PostMapping("/parking-lots")
    public ResponseEntity addParkingLot(@RequestBody ParkingLot parkingLot){
        ParkingLot resultParkingLot = parkingLotRepository.save(parkingLot);
        return ResponseEntity.ok(resultParkingLot);
    }

    @DeleteMapping("/parking-lots/{id}")
    public ResponseEntity deleteParkingLot(@PathVariable long id){
        parkingLotRepository.deleteById(id);
        return ResponseEntity.ok(1);
    }

    @GetMapping("/parking-lots")
    public ResponseEntity getParkingLots(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "15")int pageSize){
        Pageable pageable = PageRequest.of(page,pageSize, Sort.Direction.ASC,"id");
        Page<ParkingLot> allParkingLots = parkingLotRepository.findAll(pageable);

        return ResponseEntity.ok(allParkingLots.getContent());
    }

    @GetMapping("/parking-lots/{id}")
    public ResponseEntity getParkingLot(@PathVariable long id){
        ParkingLot parkingLot = parkingLotRepository.findById(id).orElse(null);
        return ResponseEntity.ok(parkingLot);
    }

    @PutMapping("/parking-lots/{id}")
    public ResponseEntity updateParkingLot(@PathVariable long id, @RequestBody ParkingLot parkingLot){
        parkingLot.setId(id);
        ParkingLot saveParkingLot = parkingLotRepository.save(parkingLot);
        return ResponseEntity.ok(saveParkingLot);
    }
}
