package br.com.soulit.datatransferobject;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.soulit.domainvalue.car.Category;
import br.com.soulit.domainvalue.car.Color;
import br.com.soulit.domainvalue.car.EngineType;
import br.com.soulit.domainvalue.car.Manufacturer;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarDTO
{

    private Long id;

    @NotNull(message = "License Plate can not be null!")
    private String licensePlate;

    @NotNull(message = "Seat Count can not be null!")
    private Integer seatCount;

    private Boolean convertible;

    @NotNull(message = "Color can not be null!")
    private Color color;

    private Long rating;

    @NotNull(message = "Manufacturer can not be null!")
    private Manufacturer manufacturer;

    @NotNull(message = "Engine Type can not be null!")
    private EngineType engineType;

    @NotNull(message = "Model Year can not be null!")
    private Integer modelYear;

    @NotNull(message = "Model Year can not be null!")
    private Category category;

    public CarDTO(
        Long id, String licensePlate, Integer seatCount, Boolean convertible,
        Color color, Long rating, Manufacturer manufacturer, EngineType engineType,
        Integer modelYear, Category category)
    {
        super();
        this.id = id;
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


    public CarDTO()
    {
        super();
    }

    public static class CarDTOBuilder
    {
        private Long id;
        private String licensePlate;
        private Integer seatCount;
        private Boolean convertible;
        private Color color;
        private Long rating;
        private Manufacturer manufacturer;
        private EngineType engineType;
        private Integer modelYear;
        private Category category;

        public CarDTOBuilder setId(Long id)
        {
            this.id = id;
            return this;
        }


        public CarDTOBuilder setLicensePlate(String licensePlate)
        {
            this.licensePlate = licensePlate;
            return this;
        }


        public CarDTOBuilder setSeatCount(Integer seatCount)
        {
            this.seatCount = seatCount;
            return this;
        }


        public CarDTOBuilder setConvertible(Boolean convertible)
        {
            this.convertible = convertible;
            return this;
        }


        public CarDTOBuilder setColor(Color color)
        {
            this.color = color;
            return this;
        }


        public CarDTOBuilder setRating(Long rating)
        {
            this.rating = rating;
            return this;
        }


        public CarDTOBuilder setManufacturer(Manufacturer manufacturer)
        {
            this.manufacturer = manufacturer;
            return this;
        }


        public CarDTOBuilder setEngineType(EngineType engineType)
        {
            this.engineType = engineType;
            return this;
        }


        public CarDTOBuilder setModelYear(Integer modelYear)
        {
            this.modelYear = modelYear;
            return this;
        }


        public CarDTOBuilder setCategory(Category category)
        {
            this.category = category;
            return this;
        }


        public CarDTO createDriverDTO()
        {
            return new CarDTO(
                id, licensePlate, seatCount, convertible,
                color, rating, manufacturer, engineType, modelYear, category);
        }
    }


    public Long getId()
    {
        return id;
    }


    public String getLicensePlate()
    {
        return licensePlate;
    }


    public Integer getSeatCount()
    {
        return seatCount;
    }


    public Boolean isConvertible()
    {
        return convertible;
    }


    public Color getColor()
    {
        return color;
    }


    public Long getRating()
    {
        return rating;
    }


    public Manufacturer getManufacturer()
    {
        return manufacturer;
    }


    public EngineType getEngineType()
    {
        return engineType;
    }


    public Integer getModelYear()
    {
        return modelYear;
    }


    public Category getCategory()
    {
        return category;
    }

}
