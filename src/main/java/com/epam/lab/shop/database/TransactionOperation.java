package com.epam.lab.shop.database;

import java.sql.Connection;

public interface TransactionOperation {

    Object operation(Connection connection);
}
