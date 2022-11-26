package com.epam.lab.shop.service.impl;

import com.epam.lab.shop.database.TransactionManager;
import com.epam.lab.shop.entity.Manufacturer;
import com.epam.lab.shop.repository.impl.ManufacturerRepositoryImpl;
import com.epam.lab.shop.service.IDatabaseService;
import java.util.List;

public class ManufacturerServiceImpl implements IDatabaseService<Manufacturer> {

    private final ManufacturerRepositoryImpl manufacturerRepositoryImpl;
    private final TransactionManager transactionManager;

    public ManufacturerServiceImpl(ManufacturerRepositoryImpl manufacturerRepositoryImpl,
                                   TransactionManager transactionManager) {
        this.manufacturerRepositoryImpl = manufacturerRepositoryImpl;
        this.transactionManager = transactionManager;
    }

    @Override
    public List<Manufacturer> getAll() {
        return (List<Manufacturer>) transactionManager
                .doInTransaction(manufacturerRepositoryImpl::getAll);
    }

    @Override
    public Manufacturer getById(int id) {
        return (Manufacturer) transactionManager
                .doInTransaction((connection) -> manufacturerRepositoryImpl.getById(id, connection));
    }

    @Override
    public Manufacturer save(Manufacturer manufacturer) {
        return (Manufacturer) transactionManager
                .doInTransaction((connection) -> manufacturerRepositoryImpl.save(manufacturer, connection));
    }

    @Override
    public Manufacturer update(Manufacturer manufacturer) {
        return (Manufacturer) transactionManager
                .doInTransaction((connection) -> manufacturerRepositoryImpl.update(manufacturer, connection));
    }

    @Override
    public Manufacturer delete(Manufacturer manufacturer) {
        return (Manufacturer) transactionManager
                .doInTransaction((connection) -> manufacturerRepositoryImpl.delete(manufacturer, connection));
    }

    @Override
    public boolean existById(int id) {
        return (boolean) transactionManager
                .doInTransaction((connection) -> manufacturerRepositoryImpl.existById(id, connection));
    }
}