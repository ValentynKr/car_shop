package com.epam.lab.shop.repository.impl;

import com.epam.lab.shop.constant.Constant;
import com.epam.lab.shop.entity.Car;
import com.epam.lab.shop.repository.IDatabaseRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class CarRepositoryImpl implements IDatabaseRepository<Car> {

    private final Logger LOGGER = Logger.getLogger(CarRepositoryImpl.class.getName());
    private int numberOfRecords;

    public int getNumberOfRecords() {
        return numberOfRecords;
    }

    @Override
    public List<Car> getAll(Connection connection) {
        List<Car> carArrayList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(Constant.SELECT_FROM_CARS);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Car car = getEntityFromResultSet(resultSet);
                carArrayList.add(car);
            }
        } catch (SQLException sqlException) {
            LOGGER.severe(String.format(Constant.OPERATION_WITH_DATABASE_FAILED, Constant.GET_ALL_CARS, sqlException));
        }
        return carArrayList;
    }

    public List<Car> getFiltered(String sqlQuery, Connection connection) {
        List<Car> carArrayList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Car car = getEntityFromResultSet(resultSet);
                carArrayList.add(car);
            }
            resultSet.close();
            resultSet = preparedStatement.executeQuery(Constant.SELECT_FOUND_ROWS);
            if (resultSet.next()) {
                numberOfRecords = resultSet.getInt(1);
            }
            resultSet.close();
        } catch (SQLException sqlException) {
            LOGGER.severe(String.format(Constant.OPERATION_WITH_DATABASE_FAILED, Constant.GET_ALL_CARS, sqlException));
        }
        return carArrayList;
    }

    @Override
    public Car getById(int id, Connection connection) {
        Car car = new Car();
        try (PreparedStatement preparedStatement = connection.prepareStatement(Constant.SELECT_FROM_CARS_WHERE_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                car = getEntityFromResultSet(resultSet);
            }
            resultSet.close();
        } catch (SQLException sqlException) {
            LOGGER.severe(String.format(Constant.OPERATION_WITH_DATABASE_FAILED, Constant.GET_CAR_BY_ID, sqlException));
        }
        return car;
    }

    @Override
    public Car save(Car car, Connection connection) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(Constant.INSERT_INTO_CARS_VALUES, Statement.RETURN_GENERATED_KEYS)) {
            int indexOfAttribute = 1;
            preparedStatement.setInt(indexOfAttribute++, car.getId());
            indexOfAttribute = putValuesToPreparedStatement(car, preparedStatement, indexOfAttribute);
            preparedStatement.setString(indexOfAttribute, car.getImageName());
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            LOGGER.severe(String.format(Constant.OPERATION_WITH_DATABASE_FAILED, Constant.SAVE_CAR, sqlException));
        }
        return car;
    }

    private int putValuesToPreparedStatement(Car car, PreparedStatement preparedStatement, int indexOfAttribute) throws SQLException {
        preparedStatement.setString(indexOfAttribute++, car.getName());
        preparedStatement.setFloat(indexOfAttribute++, car.getPrice());
        preparedStatement.setInt(indexOfAttribute++, car.getMileage());
        preparedStatement.setFloat(indexOfAttribute++, car.getEngineVolume());
        preparedStatement.setInt(indexOfAttribute++, car.getCategory().getId());
        preparedStatement.setInt(indexOfAttribute++, car.getManufacturer().getId());
        preparedStatement.setInt(indexOfAttribute++, car.getProductionYear());
        return indexOfAttribute;
    }

    @Override
    public Car update(Car car, Connection connection) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(Constant.UPDATE_CAR)) {
            int indexOfAttribute = 1;
            indexOfAttribute = putValuesToPreparedStatement(car, preparedStatement, indexOfAttribute);
            preparedStatement.setString(indexOfAttribute++, car.getImageName());
            preparedStatement.setInt(indexOfAttribute, car.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            LOGGER.severe(String.format(Constant.OPERATION_WITH_DATABASE_FAILED, Constant.UPDATE_CAR_LABEL,
                    sqlException));
        }
        return car;
    }

    @Override
    public Car delete(Car car, Connection connection) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(Constant.DELETE_CAR_WHERE_ID)) {
            preparedStatement.setInt(1, car.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            LOGGER.severe(String.format(Constant.OPERATION_WITH_DATABASE_FAILED, Constant.DELETE_CAR, sqlException));
        }
        return car;
    }

    @Override
    public boolean existById(int id, Connection connection) {
        boolean isPresent;
        try (PreparedStatement preparedStatement = connection.prepareStatement(Constant.SELECT_CAR_WHERE_EXISTS)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            isPresent = resultSet.next();
            resultSet.close();
        } catch (SQLException sqlException) {
            LOGGER.severe(String.format(Constant.OPERATION_WITH_DATABASE_FAILED, Constant.GET_KNOWN_WHETHER_CAR_EXISTS,
                    sqlException));
            return false;
        }
        return isPresent;
    }

    private Car getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return new Car.Builder().withId(resultSet.getInt(1))
                .withName(resultSet.getString(2))
                .withPrice(resultSet.getFloat(3))
                .withMileage(resultSet.getInt(4))
                .withEngineVolume(resultSet.getFloat(5))
                .withCategory(resultSet.getInt(6), resultSet.getString(7))
                .withManufacturer(resultSet.getInt(8), resultSet.getString(9))
                .withProductionYear(resultSet.getInt(10))
                .withImageName(resultSet.getString(11))
                .build();
    }
}