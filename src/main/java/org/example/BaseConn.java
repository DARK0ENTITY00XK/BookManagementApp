package org.example;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class BaseConn {

    private static final String username = "sdauserapp";
    private static final String password = "sdauserapp";
    private static final String database = "book_management";

    protected static Connection establishConn() throws SQLException {
        MysqlDataSource mysqlDataSource = new MysqlDataSource();

        mysqlDataSource.setURL(String.format("jdbc:mysql://localhost:3306/%s", database));
        mysqlDataSource.setUser(username);
        mysqlDataSource.setPassword(password);

        return mysqlDataSource.getConnection();
    }
}
