package com.epam.lab.shop.repository;

import java.sql.Connection;
import java.util.List;

public interface IDatabaseRepository<T> {

    List<T> getAll(Connection connection);

    T getById(int id, Connection connection);

    T save(T t, Connection connection);

    T update(T t, Connection connection);

    T delete(T t, Connection connection);

    boolean existById(int id, Connection connection);
}
