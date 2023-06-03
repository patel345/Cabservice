package com.cabservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Driver;
@Transactional
public interface DriverRepository extends JpaRepository<Driver,Integer> {
    com.cabservice.model.Driver save(com.cabservice.model.Driver driver);
}