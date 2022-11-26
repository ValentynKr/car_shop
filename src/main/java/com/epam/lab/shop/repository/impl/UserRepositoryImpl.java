package com.epam.lab.shop.repository.impl;

import com.epam.lab.shop.constant.Constant;
import com.epam.lab.shop.entity.Role;
import com.epam.lab.shop.entity.User;
import com.epam.lab.shop.repository.IDatabaseRepository;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class UserRepositoryImpl implements IDatabaseRepository<User> {

    private final Logger LOGGER = Logger.getLogger(UserRepositoryImpl.class.getName());

    @Override
    public List<User> getAll(Connection connection) {
        List<User> userList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(Constant.SELECT_ALL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                User user = getEntityFromResultSet(resultSet);
                userList.add(user);
            }
        } catch (SQLException sqlException) {
            LOGGER.severe(String.format(Constant.OPERATION_WITH_DATABASE_FAILED, Constant.GET_ALL_USERS_LABEL,
                    sqlException));
        }
        return userList;
    }

    @Override
    public User getById(int id, Connection connection) {
        User user = new User();
        try (PreparedStatement preparedStatement = connection.prepareStatement(Constant.SELECT_WHERE_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = getEntityFromResultSet(resultSet);
            }
            resultSet.close();
        } catch (SQLException sqlException) {
            LOGGER.severe(String.format(Constant.OPERATION_WITH_DATABASE_FAILED, Constant.GET_BY_ID_LABEL,
                    sqlException));
        }
        return user;
    }

    public String getUserAvatarName(int id, Connection connection) {
        String result = Constant.EMPTY;
        try (PreparedStatement preparedStatement = connection.prepareStatement(Constant.SELECT_AVATAR)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result = resultSet.getString(1);
            }
            resultSet.close();
        } catch (SQLException sqlException) {
            LOGGER.severe(String.format(Constant.OPERATION_WITH_DATABASE_FAILED, Constant.GET_BY_ID_LABEL,
                    sqlException));
        }
        return result;
    }

    public Optional<User> getByEmail(String email, Connection connection) {
        User user = new User();
        try (PreparedStatement preparedStatement = connection.prepareStatement(Constant.SELECT_WHERE_EMAIL)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.isBeforeFirst()) {
                while (resultSet.next()) {
                    user = getEntityFromResultSet(resultSet);
                }
            } else {
                user = null;
            }
        } catch (SQLException sqlException) {
            LOGGER.severe(String.format(Constant.OPERATION_WITH_DATABASE_FAILED, Constant.GET_USER_BY_EMAIL_LABEL,
                    sqlException));
        }
        return Optional.ofNullable(user);
    }

    @Override
    public User save(User user, Connection connection) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(Constant.INSERT, Statement.RETURN_GENERATED_KEYS)) {
            int indexOfAttribute = 1;
            preparedStatement.setInt(indexOfAttribute++, user.getId());
            indexOfAttribute = putEntityDataToPreparedStatement(user, preparedStatement, indexOfAttribute);
            preparedStatement.setString(indexOfAttribute, user.getRole().toString());
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            LOGGER.severe(String.format(Constant.OPERATION_WITH_DATABASE_FAILED, Constant.SAVE_LABEL, sqlException));
        }
        saveUserAvatar(user, connection);
        return user;
    }

    private void saveUserAvatar(User user, Connection connection) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(Constant.SAVE_AVATAR, Statement.RETURN_GENERATED_KEYS)) {
            int indexOfAttribute = 1;
            int userId = user.getId();
            preparedStatement.setInt(indexOfAttribute++, userId);
            preparedStatement.setString(indexOfAttribute, Constant.AVATAR + userId + Constant.JPG_FORMAT);
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            LOGGER.severe(String.format(Constant.OPERATION_WITH_DATABASE_FAILED, Constant.SAVE_LABEL, sqlException));
        }
    }

    @Override
    public User update(User user, Connection connection) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(Constant.UPDATE)) {
            int indexOfAttribute = 1;
            indexOfAttribute = putEntityDataToPreparedStatement(user, preparedStatement, indexOfAttribute);
            preparedStatement.setString(indexOfAttribute++, user.getRole().toString());
            preparedStatement.setInt(indexOfAttribute, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            LOGGER.severe(String.format(Constant.OPERATION_WITH_DATABASE_FAILED, Constant.UPDATE_LABEL, sqlException));
        }
        return user;
    }

    @Override
    public User delete(User user, Connection connection) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(Constant.DELETE)) {
            preparedStatement.setInt(1, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            LOGGER.severe(String.format(Constant.OPERATION_WITH_DATABASE_FAILED, Constant.DELETE_LABEL, sqlException));
        }
        deleteUserAvatar(user, connection);
        return user;
    }

    public void deleteUserAvatar(User user, Connection connection) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(Constant.DELETE_AVATAR)) {
            preparedStatement.setInt(1, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            LOGGER.severe(String.format(Constant.OPERATION_WITH_DATABASE_FAILED, Constant.DELETE_LABEL, sqlException));
        }
    }

    @Override
    public boolean existById(int id, Connection connection) {
        boolean isPresent;
        try (PreparedStatement preparedStatement = connection.prepareStatement(Constant.SELECT_WHERE_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            isPresent = resultSet.next();
            resultSet.close();
        } catch (SQLException sqlException) {
            LOGGER.severe(String.format(Constant.OPERATION_WITH_DATABASE_FAILED, Constant.EXISTS_BY_ID_LABEL,
                    sqlException));
            return false;
        }
        return isPresent;
    }

    private int putEntityDataToPreparedStatement(User user, PreparedStatement preparedStatement, int indexOfAttribute) throws SQLException {
        preparedStatement.setString(indexOfAttribute++, user.getFirstName());
        preparedStatement.setString(indexOfAttribute++, user.getSecondName());
        preparedStatement.setString(indexOfAttribute++, user.getEmail());
        preparedStatement.setString(indexOfAttribute++, user.getPhoneNumber());
        preparedStatement.setDate(indexOfAttribute++, new Date(user.getBirthDate().getTime()));
        preparedStatement.setString(indexOfAttribute++, user.getPassword());
        preparedStatement.setBoolean(indexOfAttribute++, user.isSubscription());
        preparedStatement.setInt(indexOfAttribute++, user.getLoginAttempts());
        preparedStatement.setTimestamp(indexOfAttribute++, user.getDateOfUnblocking());
        return indexOfAttribute;
    }

    private User getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return new User.Builder().withId(resultSet.getInt(1))
                .withFirstName(resultSet.getString(2))
                .withSecondName(resultSet.getString(3))
                .withEmail(resultSet.getString(4))
                .withPhoneNumber(resultSet.getString(5))
                .withBirthDate(resultSet.getDate(6))
                .withPassword(resultSet.getString(7))
                .withSubscription(resultSet.getBoolean(8))
                .withLoginAttempts(resultSet.getInt(9))
                .withDateOfUnblocking(resultSet.getTimestamp(10))
                .withRole(Role.valueOf(resultSet.getString(11)))
                .build();
    }
}