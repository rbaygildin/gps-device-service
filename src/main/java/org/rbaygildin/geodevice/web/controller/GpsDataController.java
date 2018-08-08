package org.rbaygildin.geodevice.web.controller;

import lombok.AllArgsConstructor;
import org.rbaygildin.dto.GpsData;
import org.rbaygildin.dto.NewGpsData;
import org.rbaygildin.geodevice.mapper.GpsDataMapper;
import org.rbaygildin.geodevice.service.GpsDataService;
import org.rbaygildin.web.controller.GpsdataApi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
public class GpsDataController implements GpsdataApi {

    private final GpsDataService service;
    private GpsDataMapper mapper;

    @Override
    public ResponseEntity<Void> saveDevicePosition(@Valid NewGpsData gpsData) {
        service.save(gpsData.getId(), mapper.newGpsDataToGpsData(gpsData));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<GpsData>> findDevicePositions(@Valid Integer id, @Valid OffsetDateTime timestart, @Valid OffsetDateTime timeend) {
        return new ResponseEntity<>(
                mapper.domainGpsDataListToGpsDataList(
                        service.findDevicePositions(id, timestart, timeend)
                ),
                HttpStatus.OK
        );
    }
}
