package com.freenow.domainobject;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(
    name = "driver",
    uniqueConstraints = @UniqueConstraint(name = "uc_username", columnNames = {"username"}))
public class DriverFullDO extends DriverDO
{

    @OneToOne
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private CarDO car;

    public DriverFullDO()
    {
        super();
    }


    public DriverFullDO(String username, String password)
    {
        super(username, password);
    }


    public CarDO getCar()
    {
        return car;
    }


    public void setCar(CarDO car)
    {
        this.car = car;
    }

}
