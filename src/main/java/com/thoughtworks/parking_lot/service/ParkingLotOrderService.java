package com.thoughtworks.parking_lot.service;

import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.model.ParkingLotOrder;
import com.thoughtworks.parking_lot.repository.ParkingLotOrderRepository;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ParkingLotOrderService {
    @Autowired
    private ParkingLotOrderRepository parkingLotOrderRepository;
    @Autowired
    private ParkingLotRepository parkingLotRepository;

    public ParkingLotOrder parkCar(ParkingLotOrder parkingLotOrder) {
        int currentCars = parkingLotOrderRepository.getAllCarCountByParkingLotId(parkingLotOrder.getParkingLot().getId());
        ParkingLot parkingLot = parkingLotRepository.findById(parkingLotOrder.getParkingLot().getId()).orElse(null);
        if (parkingLot != null && parkingLot.getCapacity() > currentCars) {
            parkingLotOrder.setCreateTime(new Date());
            parkingLotOrder.setOrderStatus(true);
            return parkingLotOrderRepository.save(parkingLotOrder);
        }
        return null;
    }

    public ParkingLotOrder fetchCar(long id) {
        ParkingLotOrder parkingLotOrder = parkingLotOrderRepository.findById(id).orElse(null);

        parkingLotOrder.setOrderStatus(false);
        parkingLotOrder.setEndTime(new Date());
        return parkingLotOrderRepository.save(parkingLotOrder);

    }
}
