package com.thoughtworks.parking_lot.controller;

import com.thoughtworks.parking_lot.model.ParkingLotOrder;
import com.thoughtworks.parking_lot.repository.ParkingLotOrderRepository;
import com.thoughtworks.parking_lot.service.ParkingLotOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class ParkingLotOrderController {
    @Autowired
    private ParkingLotOrderService parkingLotOrderService;

    @PostMapping("/parking-lot-orders")
    public ResponseEntity addParkingLotOrder(@RequestBody ParkingLotOrder parkingLotOrder){
        final ParkingLotOrder parkingLotOrderResult = parkingLotOrderService.parkCar(parkingLotOrder);
        if(parkingLotOrderResult!=null){
            return ResponseEntity.ok(parkingLotOrderResult);
        }
        return ResponseEntity.ok("停车场已经满");
    }

    @PutMapping("/parking-lot-orders/{id}")
    public ResponseEntity updateParkingLot(@PathVariable long id){
        ParkingLotOrder parkingLotOrder = parkingLotOrderService.fetchCar(id);
        return ResponseEntity.ok(parkingLotOrder);
    }

}
