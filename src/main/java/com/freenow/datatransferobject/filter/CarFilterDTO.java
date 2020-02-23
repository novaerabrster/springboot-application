package com.freenow.datatransferobject.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.freenow.domainvalue.car.Category;
import com.freenow.domainvalue.car.Color;
import com.freenow.domainvalue.car.EngineType;
import com.freenow.domainvalue.car.Manufacturer;

public class CarFilterDTO
{
    @JsonProperty(required = false)
    private Long id;
    @JsonProperty(required = false)
    private String licensePlate;
    @JsonProperty(required = false)
    private Integer seatCount;
    @JsonProperty(required = false)
    private Boolean convertible;
    @JsonProperty(required = false)
    private Color color;
    @JsonProperty(required = false)
    private Long rating;
    @JsonProperty(required = false)
    private Manufacturer manufacturer;
    @JsonProperty(required = false)
    private EngineType engineType;
    @JsonProperty(required = false)
    private Integer modelYear;
    @JsonProperty(required = false)
    private Category category;

    public static class CarFilterDTOBuilder
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

        public CarFilterDTOBuilder setId(Long id)
        {
            this.id = id;
            return this;
        }


        public CarFilterDTOBuilder setLicensePlate(String licensePlate)
        {
            this.licensePlate = licensePlate;
            return this;
        }


        public CarFilterDTOBuilder setSeatCount(Integer seatCount)
        {
            this.seatCount = seatCount;
            return this;
        }


        public CarFilterDTOBuilder setConvertible(Boolean convertible)
        {
            this.convertible = convertible;
            return this;
        }


        public CarFilterDTOBuilder setColor(Color color)
        {
            this.color = color;
            return this;
        }


        public CarFilterDTOBuilder setRating(Long rating)
        {
            this.rating = rating;
            return this;
        }


        public CarFilterDTOBuilder setManufacturer(Manufacturer manufacturer)
        {
            this.manufacturer = manufacturer;
            return this;
        }


        public CarFilterDTOBuilder setEngineType(EngineType engineType)
        {
            this.engineType = engineType;
            return this;
        }


        public CarFilterDTOBuilder setModelYear(Integer modelYear)
        {
            this.modelYear = modelYear;
            return this;
        }


        public CarFilterDTOBuilder setCategory(Category category)
        {
            this.category = category;
            return this;
        }


        public CarFilterDTO build()
        {
            return new CarFilterDTO(
                id, licensePlate, seatCount, convertible,
                color, rating, manufacturer, engineType, modelYear, category);
        }
    }

    private CarFilterDTO(
        Long id, String licensePlate, Integer seatCount,
        Boolean convertible, Color color, Long rating, Manufacturer manufacturer,
        EngineType engineType, Integer modelYear, Category category)
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
