package com.cabservice.dao;

import javax.persistence.Cache;
import java.util.List;

public class DriverUseCarsDao {
    private int driverId;
    private String driverName;
    private List<Cache> usedCars;

    public DriverUseCarsDao() {}

    public DriverUseCarsDao(int driverId, String driverName, List<Cache> usedCars) {
        this.driverId = driverId;
        this.driverName = driverName;
        this.usedCars = usedCars;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public List<Cache> getUsedCars() {
        return usedCars;
    }

    public void setUsedCars(List<Cache> usedCars) {
        this.usedCars = usedCars;
    }

    @Override
    public String toString() {
        return "DriverUseCarsDao{" +
                "driverId=" + driverId +
                ", driverName='" + driverName + '\'' +
                ", usedCars=" + usedCars +
                '}';
    }
}
