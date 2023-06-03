package com.cabservice.controller;

import com.cabservice.dao.BookingDetailDao;
import com.cabservice.model.Booking;
import com.cabservice.model.Car;
import com.cabservice.repository.BookingRepository;
import com.cabservice.repository.CarRepository;
import org.hibernate.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@SessionAttributes({"userName","id"})
public class BookingController {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private CarRepository carRepository;
    private Car car;

    //http://localhost:8080/book-car?carId=503&username=cust1
    @GetMapping("book-car")
   @ResponseBody
    public BookingDetailDao bookCar(@RequestParam int carId, @RequestParam String username, ModelMap modelMap) {
        Car Car = carRepository.findById(carId).get();
        car.setAvailableForBooking(false);
        Booking newBooking=new Booking();
        newBooking.setCarId(carId);
        newBooking.setDriverId(car.getDriverId());
        newBooking.setStatus("booked");
        newBooking.setUsername(username);
        carRepository.save(car);
        bookingRepository.save(newBooking);
        String cancelCarUrl="localhost:8080/cancel-car?bookingId="+newBooking.getId();
        BookingDetailDao bookingDetailDao=new BookingDetailDao(newBooking,cancelCarUrl);
        return bookingDetailDao;
    }

   @RequestMapping("cancel-car")
    public ResponseEntity<Object> cancelCar(@RequestParam int bookingId, ModelMap modelMap) {
        Optional<Booking> bookingOptional=bookingRepository.findById(bookingId);

       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUsername = authentication.getName();
        // Booking doesn't exist or not the same user
        if(bookingOptional.isPresent() || !bookingOptional.get().getUsername().equals((loggedInUsername))) {
            return new ResponseEntity<>("<h1>BAD REQUEST</h1>", HttpStatus.BAD_REQUEST);
        }

        Booking booking=bookingOptional.get();
        Car car=carRepository.findById(booking.getCarId()).get();
        car.setAvailableForBooking(true);
        carRepository.save(car);
        bookingRepository.deleteById(bookingId);
        return new ResponseEntity<>("<h1>Booking Canceled Successfully</h1>", HttpStatus.OK);
    }
}
