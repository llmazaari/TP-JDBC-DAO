package com.javamcdt.model.service.Impl;

import com.javamcdt.domain.Personnel;
import com.javamcdt.model.dao.Dao;
import com.javamcdt.model.service.ServiceInterface;

import java.util.List;

public class PersonnelService implements ServiceInterface<Personnel> {

    // On déclare l'interface DAO
    private Dao<Personnel> personnelDao;

    // Constructeur pour injecter le DAO
    public PersonnelService(Dao<Personnel> personnelDao) {
        this.personnelDao = personnelDao;
    }

    @Override
    public boolean save(Personnel item) {
        if (item == null) return false;
        
        
        if (item.getMat() <= 0) return false; 
        
        if (item.getNom() == null || item.getNom().isEmpty()) return false;
        if (item.getSalaire() < 0) return false; 
        
        return personnelDao.save(item);
    }

    @Override
    public boolean saveAll(List<Personnel> items) {
        return personnelDao.saveAll(items);
    }

    @Override
    public Personnel getById(int id) {
        return personnelDao.getById(id);
    }

    @Override
    public List<Personnel> getAll() {
        return personnelDao.getAll();
    }

    @Override
    public boolean update(Personnel item) {
        return personnelDao.update(item);
    }

    @Override
    public boolean delete(int id) {
        return personnelDao.delete(id);
    }
}