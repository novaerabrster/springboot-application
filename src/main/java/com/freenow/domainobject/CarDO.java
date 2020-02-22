package com.freenow.domainobject;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.freenow.domainvalue.car.Category;
import com.freenow.domainvalue.car.Color;
import com.freenow.domainvalue.car.EngineType;
import com.freenow.domainvalue.car.Manufacturer;

@Entity
@Table(
    name = "car",
    uniqueConstraints = @UniqueConstraint(name = "uc_license_plate", columnNames = {"licensePlate"}))
public class CarDO
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateCreated = ZonedDateTime.now();

    @Column(nullable = false)
    @NotNull(message = "License Plate can not be null!")
    private String licensePlate;

    @Column(nullable = false)
    @NotNull(message = "Seat Count can not be null!")
    private Integer seatCount;

    @Column(nullable = false)
    private Boolean convertible = false;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull(message = "Color can not be null!")
    private Color color;

    @Column(nullable = false)
    private Long rating = 5l;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull(message = "Manufacturer can not be null!")
    private Manufacturer manufacturer;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull(message = "Engine Type can not be null!")
    private EngineType engineType;

    @Column(nullable = false)
    @NotNull(message = "Model Year can not be null!")
    private Integer modelYear;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull(message = "Model Year can not be null!")
    private Category category;

    @Column(nullable = false)
    private Boolean deleted = false;

    public CarDO(
        String licensePlate, Integer seatCount, Boolean convertible,
        Color color, Long rating, Manufacturer manufacturer,
        EngineType engineType, Integer modelYear, Category category)
    {
        super();
        this.licensePlate = licensePlate;
        this.seatCount = seatCount;
        this.convertible = convertible;
        this.color = color;
        this.rating = rating;
        this.manufacturer = manufacturer;
        this.engineType = engineType;
        this.modelYear = modelYear;
        this.category = category;
    }


    public CarDO()
    {
        super();
    }


    public Long getId()
    {
        return id;
    }


    public void setId(Long id)
    {
        this.id = id;
    }


    public ZonedDateTime getDateCreated()
    {
        return dateCreated;
    }


    public void setDateCreated(ZonedDateTime dateCreated)
    {
        this.dateCreated = dateCreated;
    }


    public String getLicensePlate()
    {
        return licensePlate;
    }


    public void setLicensePlate(String licensePlate)
    {
        this.licensePlate = licensePlate;
    }


    public Integer getSeatCount()
    {
        return seatCount;
    }


    public void setSeatCount(Integer seatCount)
    {
        this.seatCount = seatCount;
    }


    public Boolean isConvertible()
    {
        return convertible;
    }


    public void setConvertible(Boolean convertible)
    {
        this.convertible = convertible;
    }


    public Color getColor()
    {
        return color;
    }


    public void setColor(Color color)
    {
        this.color = color;
    }


    public Long getRating()
    {
        return rating;
    }


    public void setRating(Long rating)
    {
        this.rating = rating;
    }


    public Manufacturer getManufacturer()
    {
        return manufacturer;
    }


    public void setManufacturer(Manufacturer manufacturer)
    {
        this.manufacturer = manufacturer;
    }


    public EngineType getEngineType()
    {
        return engineType;
    }


    public void setEngineType(EngineType engineType)
    {
        this.engineType = engineType;
    }


    public Boolean getDeleted()
    {
        return deleted;
    }


    public void setDeleted(Boolean deleted)
    {
        this.deleted = deleted;
    }


    public Integer getModelYear()
    {
        return modelYear;
    }


    public void setModelYear(Integer modelYear)
    {
        this.modelYear = modelYear;
    }






    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }


    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CarDO other = (CarDO) obj;
        if (id == null)
        {
            if (other.id != null)
                return false;
        }
        else if (!id.equals(other.id))
            return false;
        return true;
    }


    public Category getCategory()
    {
        return category;
    }


    public void setCategory(Category category)
    {
        this.category = category;
    }


    @Override
    public String toString()
    {
        return "CarDO [id="
            + id + ", dateCreated=" + dateCreated + ", licensePlate=" + licensePlate + ", seatCount=" + seatCount + ", convertible=" + convertible + ", color=" + color
            + ", rating=" + rating + ", manufacturer=" + manufacturer + ", engineType=" + engineType + ", modelYear=" + modelYear + ", category=" + category + ", deleted="
            + deleted + "]";
    }

}
