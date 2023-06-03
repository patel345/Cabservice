package com.cabservice.repository;

import com.cabservice.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Cache;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface CarRepository extends JpaRepository<Car,Integer> {
    List<Car> findBySeatingCapacityAndAvailableForBookingTrue(int seatingCapacity);

    List<Car> findByIdIn(List<Integer> carIds);
}
