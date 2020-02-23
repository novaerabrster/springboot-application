package com.freenow.controller.mapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.freenow.datatransferobject.CarDTO;
import com.freenow.datatransferobject.CarDTO.CarDTOBuilder;
import com.freenow.datatransferobject.filter.CarFilterDTO;
import com.freenow.domainobject.CarDO;

public class CarMapper
{
    public static CarDO makeCarDO(CarDTO carDTO)
    {
        if (carDTO == null)
            return null;

        return new CarDO(
            carDTO.getLicensePlate(), carDTO.getSeatCount(), carDTO.isConvertible(),
            carDTO.getColor(), carDTO.getRating(), carDTO.getManufacturer(), carDTO.getEngineType(),
            carDTO.getModelYear(), carDTO.getCategory());
    }

    public static CarDO makeCarDOFilter(CarFilterDTO filter)
    {
        if (filter == null)
            return null;

        CarDO result = new CarDO();
        result.clear();
        result.setId(filter.getId());
        result.setLicensePlate(filter.getLicensePlate());
        result.setSeatCount(filter.getSeatCount());
        result.setConvertible(filter.isConvertible());
        result.setColor(filter.getColor());
        result.setRating(filter.getRating());
        result.setManufacturer(filter.getManufacturer());
        result.setEngineType(filter.getEngineType());
        result.setModelYear(filter.getModelYear());
        result.setCategory(filter.getCategory());
        return result;
    }

    public static CarDTO makeCarDTO(CarDO carDO)
    {
        if (carDO == null)
            return null;
        CarDTO.CarDTOBuilder builder = new CarDTOBuilder();
        return builder
            .setId(carDO.getId())
            .setLicensePlate(carDO.getLicensePlate())
            .setSeatCount(carDO.getSeatCount())
            .setConvertible(carDO.isConvertible())
            .setColor(carDO.getColor())
            .setRating(carDO.getRating())
            .setManufacturer(carDO.getManufacturer())
            .setEngineType(carDO.getEngineType())
            .setModelYear(carDO.getModelYear())
            .setCategory(carDO.getCategory())
            .createDriverDTO();
    }


    public static List<CarDTO> makeCarDTOList(List<CarDO> carDOList)
    {
        if (carDOList == null || carDOList.isEmpty())
            return Collections.emptyList();

        return carDOList
            .stream()
            .map(CarMapper::makeCarDTO)
            .collect(Collectors.toList());
    }
}
