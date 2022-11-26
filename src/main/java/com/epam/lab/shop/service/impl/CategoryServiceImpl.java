package com.epam.lab.shop.service.impl;

import com.epam.lab.shop.database.TransactionManager;
import com.epam.lab.shop.entity.Category;
import com.epam.lab.shop.repository.impl.CategoryRepositoryImpl;
import com.epam.lab.shop.service.IDatabaseService;
import java.util.List;

public class CategoryServiceImpl implements IDatabaseService<Category> {

    private final CategoryRepositoryImpl categoryRepositoryImpl;
    private final TransactionManager transactionManager;

    public CategoryServiceImpl(CategoryRepositoryImpl categoryRepositoryImpl, TransactionManager transactionManager) {
        this.categoryRepositoryImpl = categoryRepositoryImpl;
        this.transactionManager = transactionManager;
    }

    @Override
    public List<Category> getAll() {
        return (List<Category>) transactionManager
                .doInTransaction(categoryRepositoryImpl::getAll);
    }

    @Override
    public Category getById(int id) {
        return (Category) transactionManager
                .doInTransaction((connection) -> categoryRepositoryImpl.getById(id, connection));
    }

    @Override
    public Category save(Category category) {
        return (Category) transactionManager
                .doInTransaction((connection) -> categoryRepositoryImpl.save(category, connection));
    }

    @Override
    public Category update(Category category) {
        return (Category) transactionManager
                .doInTransaction((connection) -> categoryRepositoryImpl.update(category, connection));
    }

    @Override
    public Category delete(Category category) {
        return (Category) transactionManager
                .doInTransaction((connection) -> categoryRepositoryImpl.delete(category, connection));
    }

    @Override
    public boolean existById(int id) {
        return (boolean) transactionManager
                .doInTransaction((connection) -> categoryRepositoryImpl.existById(id, connection));
    }
}