package org.rbaygildin.geodevice.mapper;

import org.mapstruct.Mapper;
import org.rbaygildin.dto.DeviceId;
import org.rbaygildin.dto.NewDevice;
import org.rbaygildin.geodevice.domain.Device;

@Mapper(componentModel = "spring")
public interface DeviceMapper {

    /**
     * Map new device DTO to domain device
     * @param newDevice new device
     * @return domain device
     */
    Device newDeviceToDevice(NewDevice newDevice);

    /**
     * Map saved device to device ID
     * @param device device
     * @return device ID
     */
    default DeviceId deviceToDeviceId(Device device){
        return new DeviceId().id(device.getId());
    }
}