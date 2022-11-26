package com.epam.lab.shop.database;

import com.epam.lab.shop.constant.Constant;
import java.sql.Connection;
import java.util.logging.Logger;

public class TransactionManager {

    private final Logger LOGGER = Logger.getLogger(TransactionManager.class.getName());
    private final ConnectionManager connectionManager;

    public TransactionManager(ConnectionManager connectionManager) {
    this.connectionManager = connectionManager;
    }

    public Object doInTransaction(TransactionOperation transactionOperation) {
        Connection connection = connectionManager.getConnection();
        if (connection == null) {
            LOGGER.severe(Constant.NO_CONNECTION_TRANSACTION_NULL);
            return null;
        }
        Object result = null;
        try {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            result = transactionOperation.operation(connection);
            connection.commit();
        } catch (Exception exception) {
            connectionManager.rollback(connection);
            LOGGER.severe(Constant.EXCEPTION_THROWN_DURING_TRANSACTION + exception);
        } finally {
            connectionManager.closeConnection(connection);
        }
        return result;
    }
}