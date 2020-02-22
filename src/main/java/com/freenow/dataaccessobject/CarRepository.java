package com.freenow.dataaccessobject;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.freenow.domainobject.CarDO;
import com.freenow.domainvalue.car.Category;

public interface CarRepository extends CrudRepository<CarDO, Long>
{
    public List<CarDO> findByCategoryAndDeleted(Category category, boolean deleted);

}
