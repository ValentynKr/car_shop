package com.epam.lab.shop.repository.impl;

import com.epam.lab.shop.constant.Constant;
import com.epam.lab.shop.entity.BlackListRecord;
import com.epam.lab.shop.repository.IDatabaseRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class BlackListRepositoryImpl implements IDatabaseRepository<BlackListRecord> {

    private final Logger LOGGER = Logger.getLogger(BlackListRepositoryImpl.class.getName());

    @Override
    public List<BlackListRecord> getAll(Connection connection) {
        List<BlackListRecord> blackListRecords = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(Constant.SELECT_ALL_FROM_BLACK_LIST);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                BlackListRecord blackListRecord = new BlackListRecord();
                blackListRecord.setUserId(resultSet.getInt(1));
                blackListRecord.setCause(resultSet.getString(2));
                blackListRecord.setDateOfBlocking(resultSet.getTimestamp(3));
                blackListRecords.add(blackListRecord);
            }
        } catch (SQLException sqlException) {
            LOGGER.severe(String.format(Constant.OPERATION_WITH_DATABASE_FAILED,
                    Constant.LABEL_GET_ALL_FROM_BLACK_LIST, sqlException));
        }
        return blackListRecords;
    }

    @Override
    public BlackListRecord getById(int id, Connection connection) {
        BlackListRecord blackListRecord = new BlackListRecord();
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(Constant.SELECT_FROM_BLACK_LIST_WHERE_USER_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                blackListRecord.setUserId(resultSet.getInt(1));
                blackListRecord.setCause(resultSet.getString(2));
                blackListRecord.setDateOfBlocking(resultSet.getTimestamp(3));
            }
            resultSet.close();
        } catch (SQLException sqlException) {
            LOGGER.severe(String.format(Constant.OPERATION_WITH_DATABASE_FAILED,
                    Constant.LABEL_GET_BLACK_LIST_RECORD_BY_USER_ID, sqlException));
        }
        return blackListRecord;
    }

    @Override
    public BlackListRecord save(BlackListRecord blackListRecord, Connection connection) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(Constant.INSERT_INTO_BLACK_LIST, Statement.RETURN_GENERATED_KEYS)) {
            int indexOfAttribute = 1;
            preparedStatement.setInt(indexOfAttribute++, blackListRecord.getUserId());
            preparedStatement.setString(indexOfAttribute++, blackListRecord.getCause());
            preparedStatement.setTimestamp(indexOfAttribute, blackListRecord.getDateOfBlocking());
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            LOGGER.severe(String.format(Constant.OPERATION_WITH_DATABASE_FAILED, Constant.LABEL_SAVE_BLACK_LIST_RECORD,
                    sqlException));
        }
        return blackListRecord;
    }

    @Override
    public BlackListRecord update(BlackListRecord blackListRecord, Connection connection) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(Constant.UPDATE_BLACK_LIST_RECORD)) {
            int indexOfAttribute = 1;
            preparedStatement.setString(indexOfAttribute++, blackListRecord.getCause());
            preparedStatement.setTimestamp(indexOfAttribute++, blackListRecord.getDateOfBlocking());
            preparedStatement.setInt(indexOfAttribute, blackListRecord.getUserId());
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            LOGGER.severe(String.format(Constant.OPERATION_WITH_DATABASE_FAILED,
                    Constant.LABEL_UPDATE_BLACK_LIST_RECORD, sqlException));
        }
        return blackListRecord;
    }

    @Override
    public BlackListRecord delete(BlackListRecord blackListRecord, Connection connection) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(Constant.DELETE_RECORD_FROM_BLACK_LIST)) {
            preparedStatement.setInt(1, blackListRecord.getUserId());
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            LOGGER.severe(String.format(Constant.OPERATION_WITH_DATABASE_FAILED,
                    Constant.LABEL_DELETE_BLACK_LIST_RECORD, sqlException));
        }
        return blackListRecord;
    }

    @Override
    public boolean existById(int id, Connection connection) {
        boolean isPresent;
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(Constant.SELECT_FROM_BLACK_LIST_WHERE_USER_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            isPresent = resultSet.next();
            resultSet.close();
        } catch (SQLException sqlException) {
            LOGGER.severe(String.format(Constant.OPERATION_WITH_DATABASE_FAILED,
                    Constant.LABEL_CHECK_IN_BLACK_LIST_EXIST_BY_ID, sqlException));
            return false;
        }
        return isPresent;
    }
}