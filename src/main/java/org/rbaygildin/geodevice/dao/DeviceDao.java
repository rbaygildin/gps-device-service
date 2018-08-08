package org.rbaygildin.geodevice.dao;

import org.rbaygildin.geodevice.domain.Device;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class DeviceDao {

    @PersistenceContext
    private EntityManager em;

    public Device save(Device device) {
        em.persist(device);
        return device;
    }

    public boolean existsById(Integer id) {
        return em.createQuery(
                "select case when count(d) > 0 " +
                        "then true else false end " +
                        "from Device d where d.id = ?1", Boolean.class
        )
                .setParameter(1, id)
                .getSingleResult();
    }

    public void deleteById(Integer id) {
        Device device = em.find(Device.class, id);
        em.detach(device);
    }
}
