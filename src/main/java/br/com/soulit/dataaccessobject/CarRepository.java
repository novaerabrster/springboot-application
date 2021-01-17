package br.com.soulit.dataaccessobject;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.com.soulit.domainobject.CarDO;
import br.com.soulit.domainvalue.car.Category;

public interface CarRepository extends CrudRepository<CarDO, Long>
{
    public List<CarDO> findByCategoryAndDeleted(Category category, boolean deleted);


    public Optional<CarDO> findByIdAndDeleted(long id, boolean deleted);

}
