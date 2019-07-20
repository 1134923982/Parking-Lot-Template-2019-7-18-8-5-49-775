package com.thoughtworks.parking_lot.repository;

import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.model.ParkingLotOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ParkingLotOrderRepository extends JpaRepository<ParkingLotOrder, Long> {
    @Query("SELECT  count(car_id) from ParkingLotOrder where parking_lot_id = 1 and order_status = true")
    int getAllCarCountByParkingLotId(long parkingLotId);
}
