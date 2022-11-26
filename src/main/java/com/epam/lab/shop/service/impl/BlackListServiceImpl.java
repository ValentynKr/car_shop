package com.epam.lab.shop.service.impl;

import com.epam.lab.shop.database.TransactionManager;
import com.epam.lab.shop.entity.BlackListRecord;
import com.epam.lab.shop.repository.impl.BlackListRepositoryImpl;
import com.epam.lab.shop.service.IDatabaseService;
import java.util.List;

public class BlackListServiceImpl implements IDatabaseService<BlackListRecord> {

    private final BlackListRepositoryImpl blackListRepository;
    private final TransactionManager transactionManager;

    public BlackListServiceImpl(BlackListRepositoryImpl blackListRepository, TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
        this.blackListRepository = blackListRepository;
    }

    @Override
    public List<BlackListRecord> getAll() {
        return (List<BlackListRecord>) transactionManager
                .doInTransaction(blackListRepository::getAll);
    }

    @Override
    public BlackListRecord getById(int id) {
        return (BlackListRecord) transactionManager
                .doInTransaction((connection) -> blackListRepository.getById(id, connection));
    }

    @Override
    public BlackListRecord save(BlackListRecord blackListRecord) {
        return (BlackListRecord) transactionManager
                .doInTransaction((connection) -> blackListRepository.save(blackListRecord, connection));
    }

    @Override
    public BlackListRecord update(BlackListRecord blackListRecord) {
        return (BlackListRecord) transactionManager
                .doInTransaction((connection) -> blackListRepository.update(blackListRecord, connection));
    }

    @Override
    public BlackListRecord delete(BlackListRecord blackListRecord) {
        return (BlackListRecord) transactionManager
                .doInTransaction((connection) -> blackListRepository.delete(blackListRecord, connection));
    }

    @Override
    public boolean existById(int id) {
        return (boolean) transactionManager
                .doInTransaction((connection) -> blackListRepository.existById(id, connection));
    }
}
