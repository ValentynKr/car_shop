package com.epam.lab.shop.database;

import com.epam.lab.shop.constant.Constant;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

public class ConnectionManager {

    private final Logger LOGGER = Logger.getLogger(ConnectionManager.class.getName());
    private DataSource dataSource;

    public ConnectionManager() {
        try {
            Context initialContext = new InitialContext();
            Context context = (Context) initialContext.lookup(Constant.JAVA_COMP_ENV);
            dataSource = (DataSource) context.lookup(Constant.JDBC_TASK_12);
        } catch (NamingException exception) {
            LOGGER.severe(Constant.NAMING_EXCEPTION_INITIALIZATION + exception);
        }
    }

    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException sqlException) {
            LOGGER.severe(Constant.SQL_EXCEPTION_GET_CONNECTION + sqlException);
            return null;
        }
    }

    public void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException sqlException) {
            LOGGER.severe(Constant.SQL_EXCEPTION_CLOSE_CONNECTION + sqlException);
        }
    }

    public void rollback(Connection connection) {
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException sqlException) {
                LOGGER.severe(Constant.SQL_EXCEPTION_ROLLBACK_TRANSACTION + sqlException);
            }
        }
    }
}