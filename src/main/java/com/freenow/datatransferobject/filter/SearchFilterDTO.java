package com.freenow.datatransferobject.filter;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchFilterDTO
{
    @JsonProperty(required = false)
    private DriverFilterDTO driverFilter;
    @JsonProperty(required = false)
    private CarFilterDTO carFilter;

    protected SearchFilterDTO(DriverFilterDTO driverFilter, CarFilterDTO carFilter)
    {
        super();
        this.driverFilter = driverFilter;
        this.carFilter = carFilter;
    }

    public DriverFilterDTO getDriverFilter()
    {
        return driverFilter;
    }


    public CarFilterDTO getCarFilter()
    {
        return carFilter;
    }

}
