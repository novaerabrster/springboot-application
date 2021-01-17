package br.com.soulit.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.soulit.controller.mapper.CarMapper;
import br.com.soulit.controller.mapper.DriverMapper;
import br.com.soulit.datatransferobject.DriverDTO;
import br.com.soulit.datatransferobject.filter.SearchFilterDTO;
import br.com.soulit.domainobject.CarDO;
import br.com.soulit.domainobject.DriverFullDO;
import br.com.soulit.exception.CarAlreadyInUseException;
import br.com.soulit.exception.EntityNotFoundException;
import br.com.soulit.exception.FilterNotSetException;
import br.com.soulit.exception.InvalidParameterListException;
import br.com.soulit.service.car.CarService;
import br.com.soulit.service.driver.DriverService;

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
    public DriverDTO selectCar(@PathVariable long driverId, @PathVariable long carId) throws EntityNotFoundException, CarAlreadyInUseException
    {
        CarDO car = carService.find(carId);
        if (car.getDriver() != null)
            throw new CarAlreadyInUseException("This car is already i use :(");
        return DriverMapper.makeDriverDTO(driverService.selectCar(driverId, car));

    }


    @DeleteMapping("/{driverId}/car/remove")
    public void removeCar(@PathVariable long driverId) throws EntityNotFoundException
    {
        driverService.removeCar(driverId);
    }


    @PostMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<DriverDTO> searchDrivers(@Valid @RequestBody SearchFilterDTO filter)
        throws FilterNotSetException, InvalidParameterListException
    {
        List<DriverFullDO> driversList =
            driverService
                .searchDrivers(
                    CarMapper.makeCarDOFilter(filter.getCarFilter()),
                    DriverMapper.makeDriverDOFilter(filter.getDriverFilter()));

        return DriverMapper.makeDriverDTOList(driversList);
    }
}
