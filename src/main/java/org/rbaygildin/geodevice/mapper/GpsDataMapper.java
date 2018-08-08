package org.rbaygildin.geodevice.mapper;

import org.mapstruct.Mapper;
import org.rbaygildin.dto.NewGpsData;
import org.rbaygildin.geodevice.domain.GpsData;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GpsDataMapper {

    /**
     * Map new GPS data to domain GPS data
     * @param gpsData GPS data
     * @return domain GPS data
     */
    GpsData newGpsDataToGpsData(NewGpsData gpsData);

    /**
     * Map list of domain GPS data to list of DTO GPS data
     * @param devicePositions device positions
     * @return device positions
     */
    List<org.rbaygildin.dto.GpsData> domainGpsDataListToGpsDataList(List<GpsData> devicePositions);
}
