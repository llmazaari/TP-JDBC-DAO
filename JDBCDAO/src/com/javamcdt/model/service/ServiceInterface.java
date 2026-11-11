package com.javamcdt.model.service;

import java.util.List;

public interface ServiceInterface<T> {
    boolean save(T item);
    boolean saveAll(List<T> items);
    T getById(int id);
    List<T> getAll();
    boolean update(T item);
    boolean delete(int id);
}