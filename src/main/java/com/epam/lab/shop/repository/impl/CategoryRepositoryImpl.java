package com.epam.lab.shop.repository.impl;

import com.epam.lab.shop.constant.Constant;
import com.epam.lab.shop.entity.Category;
import com.epam.lab.shop.repository.IDatabaseRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class CategoryRepositoryImpl implements IDatabaseRepository<Category> {

    private final Logger LOGGER = Logger.getLogger(CategoryRepositoryImpl.class.getName());

    @Override
    public List<Category> getAll(Connection connection) {
        List<Category> categoryArrayList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(Constant.SELECT_ALL_FROM_CATEGORY);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Category category = getEntityFromResultSet(resultSet);
                categoryArrayList.add(category);
            }
        } catch (SQLException sqlException) {
            LOGGER.severe(String.format(Constant.OPERATION_WITH_DATABASE_FAILED, Constant.GET_ALL_CATEGORIES,
                    sqlException));
        }
        return categoryArrayList;
    }

    @Override
    public Category getById(int id, Connection connection) {
        Category category = new Category();
        try (PreparedStatement preparedStatement = connection.prepareStatement(Constant.SELECT_CATEGORY_WHERE_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                category = getEntityFromResultSet(resultSet);
            }
            resultSet.close();
        } catch (SQLException sqlException) {
            LOGGER.severe(String.format(Constant.OPERATION_WITH_DATABASE_FAILED, Constant.GET_CATEGORY_BY_ID,
                    sqlException));
        }
        return category;
    }

    @Override
    public Category save(Category category, Connection connection) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(Constant.INSERT_INTO_CATEGORY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, category.getName());
            if (preparedStatement.executeUpdate() > 0) {
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    category.setId(id);
                }
            }
        } catch (SQLException sqlException) {
            LOGGER.severe(String.format(Constant.OPERATION_WITH_DATABASE_FAILED, Constant.SAVE_CATEGORY,
                    sqlException));
        }
        return category;
    }

    @Override
    public Category update(Category category, Connection connection) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(Constant.UPDATE_CATEGORY)) {
            int indexOfAttribute = 1;
            preparedStatement.setString(indexOfAttribute++, category.getName());
            preparedStatement.setInt(indexOfAttribute, category.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            LOGGER.severe(String.format(Constant.OPERATION_WITH_DATABASE_FAILED, Constant.UPDATE_CATEGORY_BY_ID,
                    sqlException));
        }
        return category;
    }

    @Override
    public Category delete(Category category, Connection connection) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(Constant.DELETE_FROM_CATEGORY)) {
            preparedStatement.setInt(1, category.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            LOGGER.severe(String.format(Constant.OPERATION_WITH_DATABASE_FAILED, Constant.DELETE_CATEGORY,
                    sqlException));
        }
        return category;
    }

    @Override
    public boolean existById(int id, Connection connection) {
        boolean isPresent;
        try (PreparedStatement preparedStatement = connection.prepareStatement(Constant.SELECT_CATEGORY_WHERE_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            isPresent = resultSet.next();
            resultSet.close();
        } catch (SQLException sqlException) {
            LOGGER.severe(String.format(Constant.OPERATION_WITH_DATABASE_FAILED,
                    Constant.GET_KNOWN_WHETHER_CATEGORY_EXISTS, sqlException));
            return false;
        }
        return isPresent;
    }

    private Category getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        Category category = new Category();
        category.setId(resultSet.getInt(1));
        category.setName(resultSet.getString(2));
        return category;
    }
}