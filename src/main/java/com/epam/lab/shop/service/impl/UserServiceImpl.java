package com.epam.lab.shop.service.impl;

import com.epam.lab.shop.database.TransactionManager;
import com.epam.lab.shop.entity.User;
import com.epam.lab.shop.repository.impl.UserRepositoryImpl;
import com.epam.lab.shop.service.IDatabaseService;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements IDatabaseService<User> {

    private final UserRepositoryImpl userRepositoryImpl;
    private final TransactionManager transactionManager;

    public UserServiceImpl(UserRepositoryImpl userRepositoryImpl, TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
        this.userRepositoryImpl = userRepositoryImpl;
    }

    @Override
    public List<User> getAll() {
        return (List<User>) transactionManager
                .doInTransaction(userRepositoryImpl::getAll);
    }

    @Override
    public User getById(int id) {
        return (User) transactionManager
                .doInTransaction((connection) -> userRepositoryImpl.getById(id, connection));
    }

    @Override
    public User save(User user) {
        return (User) transactionManager
                .doInTransaction((connection) -> userRepositoryImpl.save(user, connection));
    }

    @Override
    public User update(User user) {
        return (User) transactionManager
                .doInTransaction((connection) -> userRepositoryImpl.update(user, connection));
    }

    @Override
    public User delete(User user) {
        return (User) transactionManager
                .doInTransaction((connection) -> userRepositoryImpl.delete(user, connection));
    }

    @Override
    public boolean existById(int id) {
        return (boolean) transactionManager
                .doInTransaction((connection) -> userRepositoryImpl.existById(id, connection));
    }

    public Optional<User> getByEmail(String email) {
        return (Optional<User>) transactionManager
                .doInTransaction((connection) -> userRepositoryImpl.getByEmail(email, connection));
    }

    public String getUserAvatarName(int id) {
        return (String) transactionManager
                .doInTransaction((connection) -> userRepositoryImpl.getUserAvatarName(id, connection));
    }
}