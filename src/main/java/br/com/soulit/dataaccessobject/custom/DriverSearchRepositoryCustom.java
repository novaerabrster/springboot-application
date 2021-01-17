package br.com.soulit.dataaccessobject.custom;

import java.util.List;

import br.com.soulit.domainobject.CarDO;
import br.com.soulit.domainobject.DriverDO;
import br.com.soulit.domainobject.DriverFullDO;
import br.com.soulit.exception.FilterNotSetException;
import br.com.soulit.exception.InvalidParameterListException;

public interface DriverSearchRepositoryCustom
{

    List<DriverFullDO> searchDrivers(DriverDO filter, CarDO carFilter) throws FilterNotSetException, InvalidParameterListException;
}
