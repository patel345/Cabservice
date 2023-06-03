package com.cabservice.repository;

import com.cabservice.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.awt.print.Book;
import java.util.List;

@Transactional
public interface BookingRepository extends JpaRepository<Booking,Integer> {
    List<Booking> findByUsername(String username);
    void deleteByUsername(String username);
}
