package com.epam.lab.shop.service.impl;

import com.epam.lab.shop.database.TransactionManager;
import com.epam.lab.shop.entity.Car;
import com.epam.lab.shop.repository.impl.CarRepositoryImpl;
import com.epam.lab.shop.service.IDatabaseService;
import java.util.List;

public class CarServiceImpl implements IDatabaseService<Car> {

    private final CarRepositoryImpl carRepositoryImpl;
    private final TransactionManager transactionManager;

    public CarServiceImpl(CarRepositoryImpl carRepositoryImpl, TransactionManager transactionManager) {
        this.carRepositoryImpl = carRepositoryImpl;
        this.transactionManager = transactionManager;
    }

    @Override
    public List<Car> getAll() {
        return (List<Car>) transactionManager
                .doInTransaction(carRepositoryImpl::getAll);
    }

    public List<Car> getFiltered(String sqlQuery) {
        return (List<Car>) transactionManager
                .doInTransaction((connection -> carRepositoryImpl.getFiltered(sqlQuery, connection)));
    }

    @Override
    public Car getById(int id) {
        return (Car) transactionManager
                .doInTransaction((connection) -> carRepositoryImpl.getById(id, connection));
    }

    @Override
    public Car save(Car car) {
        return (Car) transactionManager
                .doInTransaction((connection) -> carRepositoryImpl.save(car, connection));
    }

    @Override
    public Car update(Car car) {
        return (Car) transactionManager
                .doInTransaction((connection) -> carRepositoryImpl.update(car, connection));
    }

    @Override
    public Car delete(Car car) {
        return (Car) transactionManager
                .doInTransaction((connection) -> carRepositoryImpl.delete(car, connection));
    }

    @Override
    public boolean existById(int id) {
        return (boolean) transactionManager
                .doInTransaction((connection) -> carRepositoryImpl.existById(id, connection));
    }

    public int getNumberOfRecords() {
        return carRepositoryImpl.getNumberOfRecords();
    }
}