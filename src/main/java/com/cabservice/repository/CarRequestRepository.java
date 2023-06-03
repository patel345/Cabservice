package com.cabservice.repository;

import com.cabservice.model.CarRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.net.CacheRequest;

@Transactional
public interface CarRequestRepository extends JpaRepository<CarRequest,Integer> {
}
