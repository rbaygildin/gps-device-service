package org.rbaygildin.geodevice.web.controller;

import org.rbaygildin.dto.DeviceId;
import org.rbaygildin.dto.NewDevice;
import org.rbaygildin.geodevice.mapper.DeviceMapper;
import org.rbaygildin.geodevice.service.DeviceService;
import org.rbaygildin.web.controller.DeviceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("device")
public class GeoDeviceController implements DeviceApi {

    private final DeviceService service;

    private final DeviceMapper mapper;

    public GeoDeviceController(DeviceService service, DeviceMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    @PostMapping
    public ResponseEntity<DeviceId> saveDevice(@Valid @RequestBody NewDevice newDevice) {
        return new ResponseEntity<>(
                mapper.deviceToDeviceId(
                        service.save(
                                mapper.newDeviceToDevice(newDevice)
                        )
                ),
                HttpStatus.OK
        );
    }

    @Override
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteDeviceById(@PathVariable Integer id) {
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
