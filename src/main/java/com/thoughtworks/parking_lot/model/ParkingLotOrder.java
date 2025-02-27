package com.thoughtworks.parking_lot.model;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table
public class ParkingLotOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @NotNull
    private ParkingLot parkingLot;

    private String carId;
    private Date createTime;
    private Date endTime;
    @Column(columnDefinition = "boolean default true")
    private boolean orderStatus;

    public ParkingLotOrder() {
    }

    public ParkingLotOrder(long id,@NotNull ParkingLot parkingLot, String carId, Date createTime, Date endTime, boolean orderStatus) {
        this.id = id;
        this.parkingLot = parkingLot;
        this.carId = carId;
        this.createTime = createTime;
        this.endTime = endTime;
        this.orderStatus = orderStatus;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public boolean isOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(boolean orderStatus) {
        this.orderStatus = orderStatus;
    }
}
