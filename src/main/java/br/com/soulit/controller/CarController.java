package br.com.soulit.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.soulit.controller.mapper.CarMapper;
import br.com.soulit.datatransferobject.CarDTO;
import br.com.soulit.domainobject.CarDO;
import br.com.soulit.domainvalue.car.Category;
import br.com.soulit.exception.ConstraintsViolationException;
import br.com.soulit.exception.EntityNotFoundException;
import br.com.soulit.service.car.CarService;

/**
 * All operations regarding cars will be routed in this controller
 * @author guilhermepeluzzo
 *
 */
@RestController
@RequestMapping("v1/cars")
public class CarController
{

    private final CarService service;

    @Autowired
    public CarController(CarService service)
    {
        super();
        this.service = service;
    }


    @GetMapping("/{carId}")
    public CarDTO findCar(@PathVariable long carId) throws EntityNotFoundException
    {
        return CarMapper.makeCarDTO(service.find(carId));
    }


    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public CarDTO create(@Valid @RequestBody CarDTO carDTO) throws ConstraintsViolationException
    {
        CarDO carDO = CarMapper.makeCarDO(carDTO);
        return CarMapper.makeCarDTO(service.create(carDO));
    }


    @PatchMapping("/{carId}")
    public void updateCategory(@PathVariable long carId, @RequestParam Category category) throws EntityNotFoundException
    {
        service.update(carId, category);
    }


    @DeleteMapping("/{carId}")
    public void delete(@PathVariable long carId) throws EntityNotFoundException
    {
        service.delete(carId);
    }


    @GetMapping
    public List<CarDTO> findCars(@RequestParam Category category)
    {
        return CarMapper.makeCarDTOList(service.find(category));
    }

}
