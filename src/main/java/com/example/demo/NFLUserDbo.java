package com.example.demo;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class NFLUserDbo {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(NFLUser n){
        System.out.println(n.getId());
        System.out.println(n.getFavTeam());
        entityManager.persist(n);
    }

    //public int numOfEntries() {
        //entityManager.createQuery("SELECT COUNT * FROM nfl");

    //}
    public NFLUser getById(int id) {
        return entityManager.find(NFLUser.class, id);
    }

    public Boolean isPresent(int id){ return entityManager.contains(entityManager.find(NFLUser.class, id)); }

    public void updateVehicle(NFLUser v) {
        if(entityManager.contains(v)) {
            v.setId(100);
            create(v);
        }
        else
            create(v);
    }

    public void deleteVehicle(int id) {
       NFLUser x = entityManager.find(NFLUser.class, id);
        if(x != null)
            entityManager.remove(x);
    }

}
