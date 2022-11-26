package com.epam.lab.shop.service.impl;

import com.epam.lab.shop.database.TransactionManager;
import com.epam.lab.shop.entity.OrderDetail;
import com.epam.lab.shop.repository.impl.OrderDetailRepositoryImpl;
import com.epam.lab.shop.service.IDatabaseService;
import java.util.List;

public class OrderDetailServiceImpl implements IDatabaseService<OrderDetail> {

    private final OrderDetailRepositoryImpl orderDetailRepositoryImpl;
    private final TransactionManager transactionManager;

    public OrderDetailServiceImpl(OrderDetailRepositoryImpl orderDetailRepositoryImpl,
                                  TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
        this.orderDetailRepositoryImpl = orderDetailRepositoryImpl;
    }

    @Override
    public List<OrderDetail> getAll() {
        return (List<OrderDetail>) transactionManager
                .doInTransaction(orderDetailRepositoryImpl::getAll);
    }

    public List<OrderDetail> getAllByOrderId(int orderId) {
        return (List<OrderDetail>) transactionManager
                .doInTransaction((connection) -> orderDetailRepositoryImpl.getAllByOrderId(orderId, connection));
    }

    @Override
    public OrderDetail getById(int id) {
        return (OrderDetail) transactionManager
                .doInTransaction((connection) -> orderDetailRepositoryImpl.getById(id, connection));
    }

    @Override
    public OrderDetail save(OrderDetail orderDetail) {
        return (OrderDetail) transactionManager
                .doInTransaction((connection) -> orderDetailRepositoryImpl.save(orderDetail, connection));
    }

    public boolean saveAll(List<OrderDetail> orderDetails) {
        return (boolean) transactionManager
                .doInTransaction((connection) -> orderDetailRepositoryImpl.saveAll(orderDetails, connection));
    }

    @Override
    public OrderDetail update(OrderDetail orderDetail) {
        return (OrderDetail) transactionManager
                .doInTransaction((connection) -> orderDetailRepositoryImpl.update(orderDetail, connection));
    }

    @Override
    public OrderDetail delete(OrderDetail orderDetail) {
        return (OrderDetail) transactionManager
                .doInTransaction((connection) -> orderDetailRepositoryImpl.delete(orderDetail, connection));
    }

    @Override
    public boolean existById(int id) {
        return (boolean) transactionManager
                .doInTransaction((connection) -> orderDetailRepositoryImpl.existById(id, connection));
    }
}