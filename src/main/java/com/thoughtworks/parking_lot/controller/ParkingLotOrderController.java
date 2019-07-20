package com.thoughtworks.parking_lot.controller;

import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.model.ParkingLotOrder;
import com.thoughtworks.parking_lot.repository.ParkingLotOrderRepository;
import com.thoughtworks.parking_lot.repository.ParkingPotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class ParkingLotOrderController {
    @Autowired
    private ParkingLotOrderRepository parkingLotOrderRepository;

    @PostMapping("/parking-lot-orders")
    public ResponseEntity addParkingLot(@RequestBody ParkingLotOrder parkingLotOrder){
        parkingLotOrder.setCreateTime(new Date());
        parkingLotOrder.setOrderStatus(true);
        ParkingLotOrder saveParkingLotOrder = parkingLotOrderRepository.save(parkingLotOrder);
        return ResponseEntity.ok(saveParkingLotOrder);
    }

}
