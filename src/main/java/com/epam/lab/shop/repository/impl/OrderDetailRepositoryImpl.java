package com.epam.lab.shop.repository.impl;

import com.epam.lab.shop.constant.Constant;
import com.epam.lab.shop.dto.OrderDetailDTO;
import com.epam.lab.shop.entity.OrderDetail;
import com.epam.lab.shop.repository.IDatabaseRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class OrderDetailRepositoryImpl implements IDatabaseRepository<OrderDetail> {

    private final Logger LOGGER = Logger.getLogger(OrderDetailRepositoryImpl.class.getName());

    @Override
    public List<OrderDetail> getAll(Connection connection) {
        List<OrderDetail> orderDetails = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(Constant.SELECT_ALL_FROM_ORDER_DETAIL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                OrderDetail orderDetail = getEntityFromResultSet(resultSet);
                orderDetails.add(orderDetail);
            }
        } catch (SQLException sqlException) {
            LOGGER.severe(String.format(Constant.OPERATION_WITH_DATABASE_FAILED, Constant.GET_ALL_ORDER_DETAILS,
                    sqlException));
        }
        return orderDetails;
    }

    public List<OrderDetail> getAllByOrderId(int orderId, Connection connection) {
        List<OrderDetail> orderDetails = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement
                (Constant.SELECT_FROM_ORDER_DETAIL_WHERE_ORDER_ID, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                orderDetails.add(getEntityFromResultSet(resultSet));
            }
            resultSet.close();
        } catch (SQLException sqlException) {
            LOGGER.severe(String.format(Constant.OPERATION_WITH_DATABASE_FAILED,
                    Constant.GET_ALL_ORDER_DETAILS_BY_ORDER_ID, sqlException));
        }
        return orderDetails;
    }

    public List<OrderDetailDTO> getAllByOrderIdDTO(int orderId, Connection connection) {
        List<OrderDetailDTO> orderDetailDTO = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement
                (Constant.SELECT_FROM_ORDER_DETAIL_WHERE_ORDER_ID_DTO, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                orderDetailDTO.add(getDTOFromResultSet(resultSet));
            }
            resultSet.close();
        } catch (SQLException sqlException) {
            LOGGER.severe(String.format(Constant.OPERATION_WITH_DATABASE_FAILED,
                    Constant.GET_ALL_ORDER_DETAILS_BY_ORDER_ID, sqlException));
        }
        return orderDetailDTO;
    }

    @Override
    public OrderDetail getById(int id, Connection connection) {
        OrderDetail orderDetail = new OrderDetail();
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(Constant.SELECT_FROM_ORDER_DETAIL_WHERE_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                orderDetail = getEntityFromResultSet(resultSet);
            }
            resultSet.close();
        } catch (SQLException sqlException) {
            LOGGER.severe(String.format(Constant.OPERATION_WITH_DATABASE_FAILED,
                    Constant.GET_ORDER_DETAIL_BY_ID, sqlException));
        }
        return orderDetail;
    }

    @Override
    public OrderDetail save(OrderDetail orderDetail, Connection connection) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(Constant.INSERT_INTO_ORDER_DETAIL, Statement.RETURN_GENERATED_KEYS)) {
            setPreparedStatementFromOrderDetail(preparedStatement, orderDetail);
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            LOGGER.severe(String.format(Constant.OPERATION_WITH_DATABASE_FAILED, Constant.SAVE_ORDER_DETAIL,
                    sqlException));
        }
        return orderDetail;
    }

    public boolean saveAll(List<OrderDetail> orderDetails, Connection connection) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(Constant.INSERT_INTO_ORDER_DETAIL,
                Statement.RETURN_GENERATED_KEYS)) {
            for (OrderDetail orderDetail : orderDetails) {
                setPreparedStatementFromOrderDetail(preparedStatement, orderDetail);
                preparedStatement.addBatch();
            }
            int[] results = preparedStatement.executeBatch();
            boolean resultStatus = Arrays.stream(results).filter(i -> i < 0).findAny().isPresent();
            return !resultStatus;
        } catch (SQLException sqlException) {
            LOGGER.severe(String.format(Constant.OPERATION_WITH_DATABASE_FAILED, Constant.SAVE_ALL_ORDER_DETAILS,
                    sqlException));
            return false;
        }
    }

    @Override
    public OrderDetail update(OrderDetail orderDetail, Connection connection) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(Constant.UPDATE_TO_ORDER_DETAIL)) {
            int indexOfAttribute = 1;
            preparedStatement.setDouble(indexOfAttribute++, orderDetail.getPricePerOne());
            preparedStatement.setInt(indexOfAttribute++, orderDetail.getQuantity());
            preparedStatement.setInt(indexOfAttribute++, orderDetail.getOrderId());
            preparedStatement.setInt(indexOfAttribute++, orderDetail.getCarId());
            preparedStatement.setInt(indexOfAttribute, orderDetail.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            LOGGER.severe(String.format(Constant.OPERATION_WITH_DATABASE_FAILED, Constant.UPDATE_ORDER_DETAIL,
                    sqlException));
        }
        return orderDetail;
    }

    @Override
    public OrderDetail delete(OrderDetail orderDetail, Connection connection) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(Constant.DELETE_FROM_ORDER_DETAIL)) {
            preparedStatement.setInt(1, orderDetail.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            LOGGER.severe(String.format(Constant.OPERATION_WITH_DATABASE_FAILED, Constant.DELETE_ORDER_DETAIL,
                    sqlException));
        }
        return orderDetail;
    }

    @Override
    public boolean existById(int id, Connection connection) {
        boolean isPresent;
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(Constant.SELECT_FROM_ORDER_DETAIL_WHERE_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            isPresent = resultSet.next();
            resultSet.close();
        } catch (SQLException sqlException) {
            LOGGER.severe(String.format(Constant.OPERATION_WITH_DATABASE_FAILED, Constant.EXIST_ORDER_DETAIL_BY_ID,
                    sqlException));
            return false;
        }
        return isPresent;
    }

    private OrderDetail getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return new OrderDetail(resultSet.getInt(1),
                resultSet.getFloat(2), resultSet.getInt(3),
                resultSet.getInt(4), resultSet.getInt(5));
    }

    private OrderDetailDTO getDTOFromResultSet(ResultSet resultSet) throws SQLException {
        return new OrderDetailDTO(resultSet.getInt(1),
                resultSet.getFloat(2), resultSet.getInt(3),
                resultSet.getInt(4), resultSet.getInt(5),
                resultSet.getString(6), resultSet.getString(7));
    }

    private void setPreparedStatementFromOrderDetail(PreparedStatement preparedStatement, OrderDetail orderDetail) throws SQLException {
        int indexOfAttribute = 1;
        preparedStatement.setInt(indexOfAttribute++, orderDetail.getId());
        preparedStatement.setDouble(indexOfAttribute++, orderDetail.getPricePerOne());
        preparedStatement.setInt(indexOfAttribute++, orderDetail.getQuantity());
        preparedStatement.setInt(indexOfAttribute++, orderDetail.getOrderId());
        preparedStatement.setInt(indexOfAttribute, orderDetail.getCarId());
    }
}