package com.javamcdt.model.dao;

import java.util.List;

public interface Dao<T> {
    // Create
    boolean save(T objet);
    boolean saveAll(List<T> objets);
    // Read
    T getById(int id); 
    List<T> getAll();
    // Update
    boolean update(T objet);
    // Delete
    boolean delete(int id);
}