package br.com.soulit.service.driver;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.soulit.dataaccessobject.DriverRepository;
import br.com.soulit.domainobject.CarDO;
import br.com.soulit.domainobject.DriverFullDO;
import br.com.soulit.domainvalue.GeoCoordinate;
import br.com.soulit.domainvalue.OnlineStatus;
import br.com.soulit.exception.ConstraintsViolationException;
import br.com.soulit.exception.EntityNotFoundException;
import br.com.soulit.exception.FilterNotSetException;
import br.com.soulit.exception.InvalidParameterListException;

/**
 * Service to encapsulate the link between DAO and controller and to have business logic for some driver specific things.
 * <p/>
 */
@Service
public class DefaultDriverService implements DriverService
{

    private static final Logger LOG = LoggerFactory.getLogger(DefaultDriverService.class);

    private final DriverRepository repository;

    public DefaultDriverService(DriverRepository repository)
    {
        super();
        this.repository = repository;
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
            driver = repository.save(DriverFullDO);
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
        driver = repository.save(driver);
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
        return repository.save(driver);
    }


    /**
     * Find all drivers by online state.
     *
     * @param onlineStatus
     */
    @Override
    public List<DriverFullDO> find(OnlineStatus onlineStatus)
    {
        return repository.findByOnlineStatus(onlineStatus);
    }


    /**
     *
     * Searches for drivers based on various filter possibilities.
     *
     * @param carFilter
     * @param driverFilter
     * @throws FilterNotSetException if not a single filter was set
     * @throws InvalidParameterListException if there was some kind of programming mistake
     *
     */
    @Override
    public List<DriverFullDO> searchDrivers(CarDO carFilter, DriverFullDO driverFilter) throws FilterNotSetException, InvalidParameterListException
    {
        return repository.searchDrivers(driverFilter, carFilter);
    }


    private DriverFullDO findDriverChecked(Long driverId) throws EntityNotFoundException
    {
        return repository
            .findById(driverId)
            .orElseThrow(() -> new EntityNotFoundException("Could not find entity with id: " + driverId));
    }

}
