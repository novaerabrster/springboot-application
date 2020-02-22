package com.freenow.service.car;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.freenow.dataaccessobject.CarRepository;
import com.freenow.domainobject.CarDO;
import com.freenow.domainvalue.car.Category;
import com.freenow.exception.ConstraintsViolationException;
import com.freenow.exception.EntityNotFoundException;

@Service
public class DefaultCarService implements CarService
{
    private static final Logger LOG = LoggerFactory.getLogger(DefaultCarService.class);

    private final CarRepository repository;

    public DefaultCarService(CarRepository repository)
    {
        super();
        this.repository = repository;
    }


    /**
     * Selects a car by id.
     *
     * @param carId
     * @return found car
     * @throws EntityNotFoundException if no car with the given id was found.
     */
    @Override
    public CarDO find(Long carId) throws EntityNotFoundException
    {
        return findCarChecked(carId);
    }


    /**
     * Selects a list of cars by manufacturer
     *
     * @param category
     * @return list of cars
     */
    @Override
    public List<CarDO> find(Category category)
    {
        return repository.findByCategoryAndDeleted(category, false);
    }


    /**
     *
     * Creates a car
     *
     * @param carDO
     * @return The new car with ID
     * @throws ConstraintsViolationException if there's another car with the same license plate
     */
    @Override
    public CarDO create(CarDO carDO) throws ConstraintsViolationException
    {
        CarDO car;
        try
        {
            car = repository.save(carDO);
        }
        catch (DataIntegrityViolationException e)
        {
            LOG.warn("ConstraintsViolationException while creating a car: {}", carDO, e);
            throw new ConstraintsViolationException(e.getMessage());
        }
        return car;
    }


    /**
     * Deletes an existing car by id.
     *
     * @param carId
     * @throws EntityNotFoundException if no car with the given id was found.
     */
    @Override
    @Transactional
    public void delete(Long carId) throws EntityNotFoundException
    {
        CarDO carDO = findCarChecked(carId);
        carDO.setDeleted(true);
        repository.save(carDO);
    }


    /**
     * Updates the category of a given car.
     *
     * @param carId
     * @param category
     * @throws EntityNotFoundException when no car with the given id is found
     */
    @Override
    @Transactional
    public void update(Long carId, Category category) throws EntityNotFoundException
    {
        CarDO carDO = findCarChecked(carId);
        carDO.setCategory(category);
        repository.save(carDO);
    }


    private CarDO findCarChecked(Long carId) throws EntityNotFoundException
    {
        return repository
            .findById(carId)
            .orElseThrow(() -> new EntityNotFoundException("Could not find entity with id: " + carId));
    }

}
