package com.epam.lab.shop.service.impl;

import com.epam.lab.shop.database.TransactionManager;
import com.epam.lab.shop.dto.OrderDTO;
import com.epam.lab.shop.entity.Order;
import com.epam.lab.shop.repository.impl.OrderDetailRepositoryImpl;
import com.epam.lab.shop.repository.impl.OrderRepositoryImpl;
import com.epam.lab.shop.service.IDatabaseService;
import java.util.List;

public class OrderServiceImpl implements IDatabaseService<Order> {

    private final OrderRepositoryImpl orderRepositoryImpl;
    private final OrderDetailRepositoryImpl orderDetailRepositoryImpl;
    private final TransactionManager transactionManager;

    public OrderServiceImpl(OrderRepositoryImpl orderRepositoryImpl,
                            OrderDetailRepositoryImpl orderDetailRepositoryImpl,
                            TransactionManager transactionManager) {
        this.orderRepositoryImpl = orderRepositoryImpl;
        this.orderDetailRepositoryImpl = orderDetailRepositoryImpl;
        this.transactionManager = transactionManager;
    }

    @Override
    public List<Order> getAll() {
        return (List<Order>) transactionManager.doInTransaction((connection) -> {
            List<Order> orderList = orderRepositoryImpl.getAll(connection);
            for (Order order : orderList) {
                order.setOrderDetails(orderDetailRepositoryImpl.getAllByOrderId(order.getId(), connection));
            }
            return orderList;
        });
    }

    public List<Order> getAllByUserId(int userId) {
        return (List<Order>) transactionManager.doInTransaction((connection) -> {
            List<Order> orderList = orderRepositoryImpl.getAllByUserId(userId, connection);
            for (Order order : orderList) {
                order.setOrderDetails(orderDetailRepositoryImpl.getAllByOrderId(order.getId(), connection));
            }
            return orderList;
        });
    }

    public List<OrderDTO> getAllByUserIdDTO(int userId) {
        return (List<OrderDTO>) transactionManager.doInTransaction((connection) -> {
            List<OrderDTO> orderDTOList = orderRepositoryImpl.getAllByUserIdDTO(userId, connection);
            for (OrderDTO orderDTO : orderDTOList) {
                orderDTO.setOrderDetails(orderDetailRepositoryImpl.getAllByOrderIdDTO(orderDTO.getId(), connection));
            }
            return orderDTOList;
        });
    }

    @Override
    public Order getById(int id) {
        return (Order) transactionManager
                .doInTransaction((connection) -> orderRepositoryImpl.getById(id, connection));
    }

    @Override
    public Order save(Order order) {
        return (Order) transactionManager.doInTransaction((connection) -> {
            Order newOrder = orderRepositoryImpl.save(order, connection);
            orderDetailRepositoryImpl.saveAll(order.getOrderDetails(), connection);
            return newOrder;
        });
    }

    @Override
    public Order update(Order order) {
        return (Order) transactionManager
                .doInTransaction((connection) -> orderRepositoryImpl.update(order, connection));
    }

    @Override
    public Order delete(Order order) {
        return (Order) transactionManager
                .doInTransaction((connection) -> orderRepositoryImpl.delete(order, connection));
    }

    @Override
    public boolean existById(int id) {
        return (boolean) transactionManager
                .doInTransaction((connection) -> orderRepositoryImpl.existById(id, connection));
    }
}