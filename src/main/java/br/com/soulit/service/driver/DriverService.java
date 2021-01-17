package br.com.soulit.service.driver;

import java.util.List;

import br.com.soulit.domainobject.CarDO;
import br.com.soulit.domainobject.DriverFullDO;
import br.com.soulit.domainvalue.OnlineStatus;
import br.com.soulit.exception.ConstraintsViolationException;
import br.com.soulit.exception.EntityNotFoundException;
import br.com.soulit.exception.FilterNotSetException;
import br.com.soulit.exception.InvalidParameterListException;

public interface DriverService
{

    DriverFullDO find(Long driverId) throws EntityNotFoundException;


    void delete(Long driverId) throws EntityNotFoundException;


    void updateLocation(long driverId, double longitude, double latitude) throws EntityNotFoundException;


    List<DriverFullDO> find(OnlineStatus onlineStatus);


    DriverFullDO selectCar(long driverId, CarDO car) throws EntityNotFoundException;


    DriverFullDO removeCar(long driverId) throws EntityNotFoundException;


    DriverFullDO create(DriverFullDO DriverFullDO) throws ConstraintsViolationException;


    List<DriverFullDO> searchDrivers(CarDO carFilter, DriverFullDO driverFilter) throws FilterNotSetException, InvalidParameterListException;

}
