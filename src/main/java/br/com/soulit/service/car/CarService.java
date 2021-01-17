package br.com.soulit.service.car;

import java.util.List;

import br.com.soulit.domainobject.CarDO;
import br.com.soulit.domainvalue.car.Category;
import br.com.soulit.exception.ConstraintsViolationException;
import br.com.soulit.exception.EntityNotFoundException;

public interface CarService
{

    CarDO find(Long carId) throws EntityNotFoundException;


    List<CarDO> find(Category category);


    void delete(Long carId) throws EntityNotFoundException;


    CarDO create(CarDO carDO) throws ConstraintsViolationException;


    void update(Long carId, Category category) throws EntityNotFoundException;

}
