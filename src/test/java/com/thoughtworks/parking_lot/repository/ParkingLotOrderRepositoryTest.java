package com.thoughtworks.parking_lot.repository;

import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.model.ParkingLotOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ParkingLotOrderRepositoryTest {
    @Autowired
    private ParkingLotOrderRepository parkingLotOrderRepository;
    @Autowired
    private ParkingLotRepository parkingLotRepository;

    @Before
    public void setUp(){
        List<ParkingLotOrder> parkingLotOrders = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot("a lot", 5, "tiantang");
        parkingLot.setId(47);
        ParkingLot save = parkingLotRepository.save(parkingLot);

        parkingLotOrders.add(new ParkingLotOrder(1,save,"'A12345",null,null,true));
        parkingLotOrders.add(new ParkingLotOrder(2,save,"'A12343",null,null,true));
        parkingLotOrderRepository.saveAll(parkingLotOrders);

    }

    @Test
    public void should_return_cars_count_in_parking_lots_when_park_query_parking_lot_current_capacity(){
        int count = parkingLotOrderRepository.getAllCarCountByParkingLotId(2);
        assertEquals(2,count);
    }

}