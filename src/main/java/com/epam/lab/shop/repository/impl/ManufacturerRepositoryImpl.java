package com.epam.lab.shop.repository.impl;

import com.epam.lab.shop.constant.Constant;
import com.epam.lab.shop.entity.Manufacturer;
import com.epam.lab.shop.repository.IDatabaseRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ManufacturerRepositoryImpl implements IDatabaseRepository<Manufacturer> {

    private final Logger LOGGER = Logger.getLogger(ManufacturerRepositoryImpl.class.getName());

    @Override
    public List<Manufacturer> getAll(Connection connection) {
        List<Manufacturer> manufacturerArrayList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(Constant.SELECT_ALL_FROM_MANUFACTURER);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Manufacturer manufacturer = getEntityFromResultSet(resultSet);
                manufacturerArrayList.add(manufacturer);
            }
        } catch (SQLException sqlException) {
            LOGGER.severe(String.format(Constant.OPERATION_WITH_DATABASE_FAILED, Constant.GET_ALL_MANUFACTURERS,
                    sqlException));
        }
        return manufacturerArrayList;
    }

    @Override
    public Manufacturer getById(int id, Connection connection) {
        Manufacturer manufacturer = new Manufacturer();
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(Constant.SELECT_MANUFACTURER_WHERE_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                manufacturer = getEntityFromResultSet(resultSet);
            }
            resultSet.close();
        } catch (SQLException sqlException) {
            LOGGER.severe(String.format(Constant.OPERATION_WITH_DATABASE_FAILED, Constant.GET_MANUFACTURER_BY_ID,
                    sqlException));
        }
        return manufacturer;
    }

    @Override
    public Manufacturer save(Manufacturer manufacturer, Connection connection) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(Constant.INSERT_INTO_MANUFACTURER, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, manufacturer.getName());
            if (preparedStatement.executeUpdate() > 0) {
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    manufacturer.setId(id);
                }
            }
        } catch (SQLException sqlException) {
            LOGGER.severe(String.format(Constant.OPERATION_WITH_DATABASE_FAILED, Constant.SAVE_MANUFACTURER,
                    sqlException));
        }
        return manufacturer;
    }

    @Override
    public Manufacturer update(Manufacturer manufacturer, Connection connection) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(Constant.UPDATE_MANUFACTURER)) {
            int indexOfAttribute = 1;
            preparedStatement.setString(indexOfAttribute++, manufacturer.getName());
            preparedStatement.setInt(indexOfAttribute, manufacturer.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            LOGGER.severe(String.format(Constant.OPERATION_WITH_DATABASE_FAILED, Constant.UPDATE_MANUFACTURER_BY_ID,
                    sqlException));
        }
        return manufacturer;
    }

    @Override
    public Manufacturer delete(Manufacturer manufacturer, Connection connection) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(Constant.DELETE_FROM_MANUFACTURER)) {
            preparedStatement.setInt(1, manufacturer.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            LOGGER.severe(String.format(Constant.OPERATION_WITH_DATABASE_FAILED, Constant.DELETE_MANUFACTURER,
                    sqlException));
        }
        return manufacturer;
    }

    @Override
    public boolean existById(int id, Connection connection) {
        boolean isPresent;
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(Constant.SELECT_MANUFACTURER_WHERE_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            isPresent = resultSet.next();
            resultSet.close();
        } catch (SQLException sqlException) {
            LOGGER.severe(String.format(Constant.OPERATION_WITH_DATABASE_FAILED,
                    Constant.GET_KNOWN_WHETHER_MANUFACTURER_EXISTS, sqlException));
            return false;
        }
        return isPresent;
    }

    private Manufacturer getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setId(resultSet.getInt(1));
        manufacturer.setName(resultSet.getString(2));
        return manufacturer;
    }
}