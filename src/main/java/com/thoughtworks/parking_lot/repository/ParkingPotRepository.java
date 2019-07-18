package com.thoughtworks.parking_lot.repository;

import com.thoughtworks.parking_lot.model.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingPotRepository extends JpaRepository<ParkingLot, Long> {
}
