package com.freenow.dataaccessobject.custom;

import java.util.List;

import com.freenow.domainobject.CarDO;
import com.freenow.domainobject.DriverDO;
import com.freenow.domainobject.DriverFullDO;
import com.freenow.exception.FilterNotSetException;
import com.freenow.exception.InvalidParameterListException;

public interface DriverSearchRepositoryCustom
{

    List<DriverFullDO> searchDrivers(DriverDO filter, CarDO carFilter) throws FilterNotSetException, InvalidParameterListException;
}
