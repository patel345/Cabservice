package com.cabservice.controller;

import com.cabservice.exception.InvalidSeatingCapacityException;
import com.cabservice.model.Car;
import com.cabservice.repository.CarRepository;
import com.cabservice.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.jws.WebParam;
import java.util.List;

@Controller
public class CarController {
   @Autowired
    private CarRepository carRepository;
    @Autowired
    private DriverRepository driverRepository;

    @RequestMapping("list-cars")
    public String listAllCars(ModelMap modelMap) {
        List<Car> cars=carRepository.findAll();
        modelMap.put("cars",cars);
        return "listCars";
    }

    @RequestMapping("list-available-cars")
    public String listAllAvailableCarsForBooking(@RequestParam int seatingCapacity, ModelMap modelMap) {
        List<Car> cars=carRepository.findBySeatingCapacityAndAvailableForBookingTrue(seatingCapacity);
        modelMap.put("cars",cars);
        return "listCarsAvailableForBooking";
    }

    @RequestMapping(value="add-car",method= RequestMethod.GET)
    public String showNewCarPage(Car car) {
        return "car";
    }

    //public String addNewTodo(@Valid Todo todo, ModelMap modelMap, BindingResult bindingResult) {
    @RequestMapping(value="add-car",method= RequestMethod.POST)
    public String addNewCar(Car car) throws Exception {
        int capacity=car.getSeatingCapacity();
        if(capacity!=3 && capacity!=4 && capacity!=7) {
            throw  new InvalidSeatingCapacityException("Allowed capacities are: {3,4,7}");
        }

        car.setAvailableForBooking(true);
        carRepository.save(car);
        return "redirect:list-cars";
    }

    //http://localhost:8080/delete-car?id=502
    @RequestMapping(value="delete-car")
    public String deleteCar(@RequestParam int id) {
        carRepository.deleteById(id);
        return "redirect:list-cars";
    }
}
