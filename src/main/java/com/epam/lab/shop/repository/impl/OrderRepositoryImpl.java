package com.epam.lab.shop.repository.impl;

import com.epam.lab.shop.constant.Constant;
import com.epam.lab.shop.dto.OrderDTO;
import com.epam.lab.shop.entity.DeliveryType;
import com.epam.lab.shop.entity.Order;
import com.epam.lab.shop.entity.OrderStatus;
import com.epam.lab.shop.entity.PaymentType;
import com.epam.lab.shop.repository.IDatabaseRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class OrderRepositoryImpl implements IDatabaseRepository<Order> {

    private final Logger LOGGER = Logger.getLogger(OrderRepositoryImpl.class.getName());

    @Override
    public List<Order> getAll(Connection connection) {
        List<Order> orders = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(Constant.SELECT_FROM_ORDER);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Order order = getEntityFromResultSet(resultSet);
                orders.add(order);
            }
        } catch (SQLException sqlException) {
            LOGGER.severe(String.format(Constant.OPERATION_WITH_DATABASE_FAILED, Constant.GET_ALL_ORDERS,
                    sqlException));
        }
        return orders;
    }

    public List<Order> getAllByUserId(int userId, Connection connection) {
        List<Order> orders = new ArrayList<>();
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(Constant.SELECT_FROM_ORDER_WHERE_USER_ID)) {
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order order = getEntityFromResultSet(resultSet);
                orders.add(order);
            }
        } catch (SQLException sqlException) {
            LOGGER.severe(String.format(Constant.OPERATION_WITH_DATABASE_FAILED, Constant.GET_ALL_ORDERS_BY_USER_ID,
                    sqlException));
        }
        return orders;
    }

    public List<OrderDTO> getAllByUserIdDTO(int userId, Connection connection) {
        List<OrderDTO> orderDTOS = new ArrayList<>();
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(Constant.SELECT_FROM_ORDER_WHERE_USER_ID)) {
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                OrderDTO orderDTO = getDTOFromResultSet(resultSet);
                orderDTOS.add(orderDTO);
            }
        } catch (SQLException sqlException) {
            LOGGER.severe(String.format(Constant.OPERATION_WITH_DATABASE_FAILED, Constant.GET_ALL_ORDERS_BY_USER_ID,
                    sqlException));
        }
        return orderDTOS;
    }

    @Override
    public Order getById(int id, Connection connection) {
        Order order = new Order();
        try (PreparedStatement preparedStatement = connection.prepareStatement(Constant.SELECT_FROM_ORDER_WHERE_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                order = getEntityFromResultSet(resultSet);
            }
            resultSet.close();
        } catch (SQLException sqlException) {
            LOGGER.severe(String.format(Constant.OPERATION_WITH_DATABASE_FAILED, Constant.GET_ORDER_BY_ID,
                    sqlException));
        }
        return order;
    }

    @Override
    public Order save(Order order, Connection connection) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(Constant.INSERT_INTO_ORDER,
                Statement.RETURN_GENERATED_KEYS)) {
            int indexOfAttribute = 1;
            preparedStatement.setInt(indexOfAttribute++, order.getId());
            indexOfAttribute = setPreparedStatementFromOrder(order, preparedStatement, indexOfAttribute);
            preparedStatement.setTimestamp(indexOfAttribute, order.getCreationDate());
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            LOGGER.severe(String.format(Constant.OPERATION_WITH_DATABASE_FAILED, Constant.SAVE_ORDER, sqlException));
        }
        return order;
    }

    @Override
    public Order update(Order order, Connection connection) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(Constant.UPDATE_ORDER_BY_ID)) {
            int indexOfAttribute = 1;
            indexOfAttribute = setPreparedStatementFromOrder(order, preparedStatement, indexOfAttribute);
            preparedStatement.setTimestamp(indexOfAttribute++, order.getCreationDate());
            preparedStatement.setInt(indexOfAttribute, order.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            LOGGER.severe(String.format(Constant.OPERATION_WITH_DATABASE_FAILED, Constant.UPDATE_ORDER, sqlException));
        }
        return order;
    }

    @Override
    public Order delete(Order order, Connection connection) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(Constant.DELETE_FROM_ORDER_WHERE_ID)) {
            preparedStatement.setInt(1, order.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            LOGGER.severe(String.format(Constant.OPERATION_WITH_DATABASE_FAILED, Constant.DELETE_ORDER, sqlException));
        }
        return order;
    }

    @Override
    public boolean existById(int id, Connection connection) {
        boolean isPresent;
        try (PreparedStatement preparedStatement = connection.prepareStatement(Constant.SELECT_FROM_ORDER_WHERE_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            isPresent = resultSet.next();
            resultSet.close();
        } catch (SQLException sqlException) {
            LOGGER.severe(String.format(Constant.OPERATION_WITH_DATABASE_FAILED, Constant.EXIST_ORDER_BY_ID,
                    sqlException));
            return false;
        }
        return isPresent;
    }

    private Order getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setId(resultSet.getInt(1));
        order.setOrderStatus(OrderStatus.valueOf(resultSet.getString(2)));
        order.setDetail(resultSet.getString(3));
        order.setPaymentType(PaymentType.valueOf(resultSet.getString(4)));
        order.setCardCredential(resultSet.getString(5));
        order.setDeliveryType(DeliveryType.valueOf(resultSet.getString(6)));
        order.setDeliveryAddress(resultSet.getString(7));
        order.setUserId(resultSet.getInt(8));
        order.setCreationDate(resultSet.getTimestamp(9));
        return order;
    }

    private OrderDTO getDTOFromResultSet(ResultSet resultSet) throws SQLException {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(resultSet.getInt(1));
        orderDTO.setOrderStatus(OrderStatus.valueOf(resultSet.getString(2)));
        orderDTO.setDetail(resultSet.getString(3));
        orderDTO.setPaymentType(PaymentType.valueOf(resultSet.getString(4)));
        orderDTO.setCardCredential(resultSet.getString(5));
        orderDTO.setDeliveryType(DeliveryType.valueOf(resultSet.getString(6)));
        orderDTO.setDeliveryAddress(resultSet.getString(7));
        orderDTO.setUserId(resultSet.getInt(8));
        orderDTO.setCreationDate(resultSet.getTimestamp(9));
        return orderDTO;
    }

    private int setPreparedStatementFromOrder(Order order, PreparedStatement preparedStatement, int indexOfAttribute) throws SQLException {
        preparedStatement.setString(indexOfAttribute++, order.getOrderStatus().toString());
        preparedStatement.setString(indexOfAttribute++, order.getDetail());
        preparedStatement.setString(indexOfAttribute++, order.getPaymentType().toString());
        preparedStatement.setString(indexOfAttribute++, order.getCardCredential());
        preparedStatement.setString(indexOfAttribute++, order.getDeliveryType().toString());
        preparedStatement.setString(indexOfAttribute++, order.getDeliveryAddress());
        preparedStatement.setInt(indexOfAttribute++, order.getUserId());
        return indexOfAttribute;
    }
}