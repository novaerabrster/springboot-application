package com.freenow.service.driver;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.freenow.dataaccessobject.DriverRepository;
import com.freenow.domainobject.CarDO;
import com.freenow.domainobject.DriverFullDO;
import com.freenow.domainvalue.GeoCoordinate;
import com.freenow.domainvalue.OnlineStatus;
import com.freenow.exception.ConstraintsViolationException;
import com.freenow.exception.EntityNotFoundException;

/**
 * Service to encapsulate the link between DAO and controller and to have business logic for some driver specific things.
 * <p/>
 */
@Service
public class DefaultDriverService implements DriverService
{

    private static final Logger LOG = LoggerFactory.getLogger(DefaultDriverService.class);

    private final DriverRepository driverRepository;


    public DefaultDriverService(DriverRepository driverRepository)
    {
        super();
        this.driverRepository = driverRepository;
    }


    /**
     * Selects a driver by id.
     *
     * @param driverId
     * @return found driver
     * @throws EntityNotFoundException if no driver with the given id was found.
     */
    @Override
    public DriverFullDO find(Long driverId) throws EntityNotFoundException
    {
        return findDriverChecked(driverId);
    }


    /**
     * Creates a new driver.
     *
     * @param DriverFullDO
     * @return
     * @throws ConstraintsViolationException if a driver already exists with the given username, ... .
     */
    @Override
    public DriverFullDO create(DriverFullDO DriverFullDO) throws ConstraintsViolationException
    {
        DriverFullDO driver;
        try
        {
            driver = driverRepository.save(DriverFullDO);
        }
        catch (DataIntegrityViolationException e)
        {
            LOG.warn("ConstraintsViolationException while creating a driver: {}", DriverFullDO, e);
            throw new ConstraintsViolationException(e.getMessage());
        }
        return driver;
    }


    /**
     * Deletes an existing driver by id.
     *
     * @param driverId
     * @throws EntityNotFoundException if no driver with the given id was found.
     */
    @Override
    @Transactional
    public void delete(Long driverId) throws EntityNotFoundException
    {
        DriverFullDO DriverFullDO = findDriverChecked(driverId);
        DriverFullDO.setDeleted(true);
    }


    /**
     * Update the location for a driver.
     *
     * @param driverId
     * @param longitude
     * @param latitude
     * @throws EntityNotFoundException
     */
    @Override
    @Transactional
    public void updateLocation(long driverId, double longitude, double latitude) throws EntityNotFoundException
    {
        DriverFullDO DriverFullDO = findDriverChecked(driverId);
        DriverFullDO.setCoordinate(new GeoCoordinate(latitude, longitude));
    }


    /**
     * Associates a given car to a driver.
     *
     * @param driverId
     * @param car
     * @return
     * @throws EntityNotFoundException if no driver with the given id was found.
     */
    @Override
    @Transactional
    public DriverFullDO selectCar(long driverId, CarDO car) throws EntityNotFoundException
    {
        DriverFullDO driver = findDriverChecked(driverId);
        driver.setCar(car);
        driver = driverRepository.save(driver);
        return driver;

    }


    /**
     *
     * Unassigns the car from a given driver
     *
     * @param driverId
     * @return
     * @throws EntityNotFoundException if no driver with the given id was found.
     */
    @Override
    @Transactional
    public DriverFullDO removeCar(long driverId) throws EntityNotFoundException
    {
        DriverFullDO driver = findDriverChecked(driverId);
        driver.setCar(null);
        return driverRepository.save(driver);
    }
    /**
     * Find all drivers by online state.
     *
     * @param onlineStatus
     */
    @Override
    public List<DriverFullDO> find(OnlineStatus onlineStatus)
    {
        return driverRepository.findByOnlineStatus(onlineStatus);
    }


    private DriverFullDO findDriverChecked(Long driverId) throws EntityNotFoundException
    {
        return driverRepository.findById(driverId)
            .orElseThrow(() -> new EntityNotFoundException("Could not find entity with id: " + driverId));
    }

}
