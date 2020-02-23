package com.freenow.service.driver;

import java.util.List;

import com.freenow.domainobject.CarDO;
import com.freenow.domainobject.DriverFullDO;
import com.freenow.domainvalue.OnlineStatus;
import com.freenow.exception.ConstraintsViolationException;
import com.freenow.exception.EntityNotFoundException;
import com.freenow.exception.FilterNotSetException;
import com.freenow.exception.InvalidParameterListException;

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
