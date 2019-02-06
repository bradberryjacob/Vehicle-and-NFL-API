package com.example.demo;

import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
//import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class VehicleDbo {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(Vehicle v){
        try {
            entityManager.persist(v);
        }catch(Exception e){

        }
    }
    public Vehicle getById(int id) {
        return entityManager.find(Vehicle.class, id);
    }

    public void updateVehicle(Vehicle v) {
        if(entityManager.contains(v))
            entityManager.merge(v);
        else
            create(v);
    }

    public void deleteVehicle(int id) {
        Vehicle x = entityManager.find(Vehicle.class, id);
        if(x != null)
            entityManager.remove(x);
    }
}
