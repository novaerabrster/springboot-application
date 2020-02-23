package com.freenow.service.car;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.freenow.dataaccessobject.CarRepository;
import com.freenow.domainobject.CarDO;
import com.freenow.domainvalue.car.Category;
import com.freenow.domainvalue.car.Color;
import com.freenow.domainvalue.car.EngineType;
import com.freenow.domainvalue.car.Manufacturer;
import com.freenow.exception.ConstraintsViolationException;
import com.freenow.exception.EntityNotFoundException;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DefaultCarServiceTest
{

    @TestConfiguration
    static class DefaultCarServiceTestContextConfiguration
    {
        @Autowired
        private CarRepository repository;
        @Bean
        public CarService carService()
        {
            return new DefaultCarService(repository);
        }
    }

    //    @Before
    //    public void setUp()
    //    {
    //        Optional<CarDO> carNotDeleted =
    //            Optional.of(new CarDO(
    //                "ABC1234", 4, false, Color.BLACK,
    //            5l, Manufacturer.MERCEDEZ, EngineType.HYBRID, 2020, Category.REGULAR));
    //        carNotDeleted.get().setId(1l);
    //
    //        Optional<CarDO> carDeleted =
    //            Optional.of(new CarDO(
    //                "ZXC1234", 4, true, Color.BLACK,
    //                5l, Manufacturer.OPEL, EngineType.GAS, 2020, Category.LUXURY));
    //        carDeleted.get().setId(3l);
    //
    //        List<CarDO> carList = new ArrayList<CarDO>();
    //        carList.add(carNotDeleted.get());
    //
    //        Optional<CarDO> emptyCar = Optional.empty();
    //
    //        Mockito.when(repository.findById(1l)).thenReturn(carNotDeleted);
    //        Mockito.when(repository.findById(2l)).thenReturn(emptyCar);
    //        Mockito.when(repository.findByCategoryAndDeleted(Category.REGULAR, false)).thenReturn(carList);
    //
    //    }

    @Autowired
    private CarService service;

    @Test
    public void whenCarIsFound() throws EntityNotFoundException
    {

        CarDO car = service.find(1l);
        assertThat(car).isNotNull();
        assertThat(car.getId()).isEqualTo(1l);
        assertThat(car.getLicensePlate()).isEqualTo("ABC1234");
    }


    @Test
    public void whenCarIsNotFound()
    {
        assertThatThrownBy(() -> {
            service.find(999l);
        })
            .isInstanceOf(EntityNotFoundException.class);

    }


    @Test
    public void whenCarListIsFound()
    {
        List<CarDO> carList = service.find(Category.REGULAR);
        assertThat(carList).isNotNull();
        assertThat(carList).isNotEmpty();

    }


    @Test
    public void carCreationTest() throws ConstraintsViolationException
    {
        CarDO car = new CarDO("ABC1235", 4, false, Color.BLACK,
                5l, Manufacturer.MERCEDEZ, EngineType.HYBRID, 2020, Category.REGULAR);
        car = service.create(car);
        assertThat(car).isNotNull();
        assertThat(car.getId()).isNotNull();

    }


    @Test
    public void carCreationDuplicateLicensePlateTest()
    {
        CarDO car =
            new CarDO(
                "ABC1234", 4, false, Color.BLACK,
                5l, Manufacturer.MERCEDEZ, EngineType.HYBRID, 2020, Category.REGULAR);

        assertThatThrownBy(() -> {
            service.create(car);
        })
            .isInstanceOf(ConstraintsViolationException.class);

    }


    @Test
    public void updateCarTest() throws EntityNotFoundException
    {
        service.update(1l, Category.REGULAR);
        CarDO car = service.find(1l);
        assertThat(car.getCategory()).isEqualTo(Category.REGULAR);
    }


    @Test
    public void updateNonExistentCarTest() throws EntityNotFoundException
    {

        assertThatThrownBy(() -> {
            service.update(999l, Category.REGULAR);
        })
            .isInstanceOf(EntityNotFoundException.class);

    }


    @Test
    public void deleteCarTest() throws EntityNotFoundException
    {
        service.delete(5l);
        assertThatThrownBy(() -> {
            service.find(5l);
        })
            .isInstanceOf(EntityNotFoundException.class);
    }


    @Test
    public void deleteNonExistentCarTest() throws EntityNotFoundException
    {

        assertThatThrownBy(() -> {
            service.delete(999l);
        })
            .isInstanceOf(EntityNotFoundException.class);

    }

}
