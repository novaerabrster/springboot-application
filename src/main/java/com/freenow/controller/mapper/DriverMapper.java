package com.freenow.controller.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.freenow.datatransferobject.DriverDTO;
import com.freenow.datatransferobject.filter.DriverFilterDTO;
import com.freenow.domainobject.DriverFullDO;
import com.freenow.domainvalue.GeoCoordinate;

public class DriverMapper
{
    public static DriverFullDO makeDriverDO(DriverDTO driverDTO)
    {
        return new DriverFullDO(driverDTO.getUsername(), driverDTO.getPassword());
    }


    private DriverMapper()
    {
        super();
    }
    public static DriverFullDO makeDriverDOFilter(DriverFilterDTO filter)
    {
        if (filter == null)
            return null;
        DriverFullDO result = new DriverFullDO();
        result.clear();
        result.setId(filter.getId());
        result.setUsername(filter.getUsername());
        result.setOnlineStatus(filter.getOnlineStatus());
        return result;
    }


    public static DriverDTO makeDriverDTO(DriverFullDO driverDO)
    {
        DriverDTO.DriverDTOBuilder builder =
            DriverDTO
                .newBuilder()
                .setId(driverDO.getId())
                .setPassword(driverDO.getPassword())
                .setUsername(driverDO.getUsername());

        GeoCoordinate coordinate = driverDO.getCoordinate();
        if (coordinate != null)
        {
            builder.setCoordinate(coordinate);
        }

        if (driverDO.getCar() != null)
        {
            builder
                .setCar(
                    CarMapper
                        .makeCarDTO(((DriverFullDO) driverDO).getCar()));
        }
        return builder.createDriverDTO();
    }


    public static List<DriverDTO> makeDriverDTOList(Collection<DriverFullDO> drivers)
    {
        return drivers
            .stream()
            .map(DriverMapper::makeDriverDTO)
            .collect(Collectors.toList());
    }
}
