package br.com.soulit.domainobject;

import java.time.ZonedDateTime;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.com.soulit.domainvalue.GeoCoordinate;
import br.com.soulit.domainvalue.OnlineStatus;

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


    public DriverFullDO(Long id, ZonedDateTime dateCreated, String username, GeoCoordinate coordinate, ZonedDateTime dateCoordinateUpdated, OnlineStatus onlineStatus)
    {
        super(id, dateCreated, username, coordinate, dateCoordinateUpdated, onlineStatus);
    }


    public CarDO getCar()
    {
        return car;
    }


    public void setCar(CarDO car)
    {
        this.car = car;
    }


    @Override
    public void clear()
    {
        super.clear();
        this.car = null;
    }

}
