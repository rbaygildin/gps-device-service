package org.rbaygildin.geodevice.dao;

import org.hibernate.Hibernate;
import org.rbaygildin.geodevice.domain.Device;
import org.rbaygildin.geodevice.domain.GpsData;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.List;

@Repository
public class GpsDataDao {

    @PersistenceContext
    private EntityManager em;

    public GpsData save(Integer id, GpsData gpsData) {
        Device device = em.find(Device.class, id);
        gpsData.setDevice(device);
        device.getGpsDataList().add(gpsData);
        em.persist(device);
        return gpsData;
    }

    public List<GpsData> findDevicePositions(@Valid Integer id) {
        Device device = em.find(Device.class, id);
        return device.getGpsDataList();
    }
}