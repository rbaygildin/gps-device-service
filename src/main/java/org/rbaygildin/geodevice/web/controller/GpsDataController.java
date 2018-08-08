package org.rbaygildin.geodevice.web.controller;

import lombok.AllArgsConstructor;
import org.rbaygildin.dto.GpsData;
import org.rbaygildin.dto.NewGpsData;
import org.rbaygildin.geodevice.mapper.GpsDataMapper;
import org.rbaygildin.geodevice.service.GpsDataService;
import org.rbaygildin.web.controller.GpsdataApi;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.util.List;

@RestController
@RequestMapping("gpsdata")
@AllArgsConstructor
public class GpsDataController implements GpsdataApi {

    private final GpsDataService service;
    private GpsDataMapper mapper;

    @Override
    @PostMapping
    public ResponseEntity<Void> saveDevicePosition(@Valid @RequestBody NewGpsData gpsData) {
        service.save(gpsData.getId(), mapper.newGpsDataToGpsData(gpsData));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<GpsData>> findDevicePositions(
            @Valid @Param("id") Integer id,
            @Valid @RequestParam("timestart") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime timestart,
            @Valid @RequestParam("timeend") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime timeend
    ) {
        return new ResponseEntity<>(
                mapper.domainGpsDataListToGpsDataList(
                        service.findDevicePositions(id, timestart, timeend)
                ),
                HttpStatus.OK
        );
    }
}
