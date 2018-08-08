package org.rbaygildin.geodevice.service;

import lombok.AllArgsConstructor;
import org.rbaygildin.geodevice.dao.GpsDataDao;
import org.rbaygildin.geodevice.domain.GpsData;
import org.rbaygildin.geodevice.exception.DeviceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GpsDataService {

    private DeviceService deviceService;
    private GpsDataDao dao;

    @Transactional
    public void save(Integer id, GpsData gpsData) {
        if (!deviceService.existsById(id))
            throw new DeviceNotFoundException();
        dao.save(id, gpsData);
    }

    public List<GpsData> findDevicePositions(@Valid Integer id, OffsetDateTime timeStart, OffsetDateTime timeEnd) {
        return dao.findDevicePositions(id)
                .stream()
                .filter(
                        pos -> pos.getTime().isAfter(timeStart)
                                && pos.getTime().isBefore(timeEnd)
                )
                .collect(Collectors.toList());
    }
}
