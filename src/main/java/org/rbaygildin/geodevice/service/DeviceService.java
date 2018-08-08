package org.rbaygildin.geodevice.service;

import lombok.AllArgsConstructor;
import org.rbaygildin.geodevice.dao.DeviceDao;
import org.rbaygildin.geodevice.domain.Device;
import org.rbaygildin.geodevice.exception.DeviceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class DeviceService {

    private final DeviceDao dao;

    @Transactional
    public Device save(Device device) {
        return dao.save(device);
    }

    @Transactional
    public void deleteById(Integer id) {
        if (!existsById(id))
            throw new DeviceNotFoundException();
        dao.deleteById(id);
    }

    public boolean existsById(Integer id) {
        return dao.existsById(id);
    }
}
