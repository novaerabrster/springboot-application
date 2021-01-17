package br.com.soulit.dataaccessobject.custom;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;

import br.com.soulit.domainobject.CarDO;
import br.com.soulit.domainobject.DriverDO;
import br.com.soulit.domainobject.DriverFullDO;
import br.com.soulit.exception.FilterNotSetException;
import br.com.soulit.exception.InvalidParameterListException;

public class DriverSearchRepositoryCustomImpl implements DriverSearchRepositoryCustom
{
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<DriverFullDO> searchDrivers(DriverDO driverFilter, CarDO carFilter) throws FilterNotSetException, InvalidParameterListException
    {
        if (driverFilter == null && carFilter == null)
        {
            throw new FilterNotSetException("No filter found");
        }

        StringBuilder sbQuery = new StringBuilder();
        List<Object> parameters = new ArrayList<Object>();
        sbQuery.append("select driver from DriverFullDO driver ");
        if (carFilter != null)
            sbQuery.append(" inner join driver.car car ");
        sbQuery.append(" where driver.deleted = false ");
        if (carFilter != null)
            sbQuery.append("   and car.deleted = false ");

        setDriverParameters(driverFilter, sbQuery, parameters);
        setCarParamenters(carFilter, sbQuery, parameters);
        Query query = em.createQuery(sbQuery.toString());
        setQueryParameters(parameters, query);
        return query.getResultList();
    }


    private void setDriverParameters(DriverDO driverFilter, StringBuilder sbQuery, List<Object> parameters)
    {
        if (driverFilter == null)
        {
            return;
        }
        if (driverFilter.getId() != null)
        {
            sbQuery.append(" and driver.id = :driverId ");
            parameters.add("driverId");
            parameters.add(driverFilter.getId());
        }

        if (StringUtils.isNotEmpty(driverFilter.getUsername()))
        {
            sbQuery.append(" and driver.username LIKE :username ");
            parameters.add("username");
            parameters.add("%" + driverFilter.getUsername() + "%");
        }

        if (driverFilter.getOnlineStatus() != null)
        {
            sbQuery.append(" and driver.onlineStatus = :onlineStatus ");
            parameters.add("onlineStatus");
            parameters.add(driverFilter.getOnlineStatus());
        }
    }


    private void setCarParamenters(CarDO carFilter, StringBuilder sbQuery, List<Object> parameters)
    {
        if (carFilter == null)
        {
            return;
        }
        if (carFilter.getId() != null)
        {
            sbQuery.append(" and car.id = :carId ");
            parameters.add("carId");
            parameters.add(carFilter.getId());
        }
        if (StringUtils.isNotEmpty(carFilter.getLicensePlate()))
        {
            sbQuery.append(" and car.licensePlate = :licensePlate ");
            parameters.add("licensePlate");
            parameters.add(carFilter.getLicensePlate());
        }
        if (carFilter.getSeatCount() != null)
        {
            sbQuery.append(" and car.seatCount = :seatCount ");
            parameters.add("seatCount");
            parameters.add(carFilter.getSeatCount());
        }
        if (carFilter.isConvertible() != null)
        {
            sbQuery.append(" and car.convertible = :convertible ");
            parameters.add("convertible");
            parameters.add(carFilter.isConvertible());
        }
        if (carFilter.getColor() != null)
        {
            sbQuery.append(" and car.color = :color ");
            parameters.add("color");
            parameters.add(carFilter.getColor());
        }

        if (carFilter.getRating() != null)
        {
            sbQuery.append(" and car.rating = :rating ");
            parameters.add("rating");
            parameters.add(carFilter.getRating());
        }
        if (carFilter.getManufacturer() != null)
        {
            sbQuery.append(" and car.manufacturer = :manufacturer ");
            parameters.add("manufacturer");
            parameters.add(carFilter.getManufacturer());
        }
        if (carFilter.getEngineType() != null)
        {
            sbQuery.append(" and car.engineType = :engineType ");
            parameters.add("engineType");
            parameters.add(carFilter.getEngineType());
        }
        if (carFilter.getModelYear() != null)
        {
            sbQuery.append(" and car.modelYear = :modelYear ");
            parameters.add("modelYear");
            parameters.add(carFilter.getModelYear());
        }
        if (carFilter.getCategory() != null)
        {
            sbQuery.append(" and car.category = :category ");
            parameters.add("category");
            parameters.add(carFilter.getCategory());
        }
    }


    private void setQueryParameters(List<Object> parameters, Query query) throws InvalidParameterListException
    {
        if (parameters == null || parameters.size() % 2 != 0)
        {
            throw new InvalidParameterListException("Invalid parameter list");
        }
        for (int i = 0; i < parameters.size(); i++)
        {
            query.setParameter((String) parameters.get(i), parameters.get(++i));
        }
    }

}
