package com.epam.lab.shop.service;

import java.util.List;

public interface IDatabaseService<T> {

    List<T> getAll();

    T getById(int id);

    T save(T t);

    T update(T t);

    T delete(T t);

    boolean existById(int id);

}
