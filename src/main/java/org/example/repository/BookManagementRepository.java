package org.example.repository;

import java.sql.SQLException;

public interface BookManagementRepository<T> {

    void dropTableIfExists() throws SQLException;

    void createTable() throws SQLException;

    void showAll() throws SQLException;

    void insert(T t) throws SQLException;

    void deleteById(Integer id) throws SQLException;

    void updateById(T t) throws SQLException;

}
