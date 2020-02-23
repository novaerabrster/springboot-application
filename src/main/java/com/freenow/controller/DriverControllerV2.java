package com.freenow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.freenow.controller.mapper.DriverMapper;
import com.freenow.datatransferobject.DriverDTO;
import com.freenow.domainobject.CarDO;
import com.freenow.exception.EntityNotFoundException;
import com.freenow.service.car.CarService;
import com.freenow.service.driver.DriverService;

@RestController
@RequestMapping("v2/drivers")
public class DriverControllerV2 extends DriverController
{
    private final CarService carService;
    @Autowired
    public DriverControllerV2(DriverService driverService, CarService carService)
    {
        super(driverService);
        this.carService = carService;
    }


    @PatchMapping("/{driverId}/car/select/{carId}")
    public DriverDTO selectCar(@PathVariable long driverId, @PathVariable long carId) throws EntityNotFoundException
    {
        CarDO car = carService.find(carId);
        return DriverMapper.makeDriverDTO(driverService.selectCar(driverId, car));

    }


    @DeleteMapping("/{driverId}/car/remove")
    public void removeCar(@PathVariable long driverId) throws EntityNotFoundException
    {
        driverService.removeCar(driverId);
    }
}
