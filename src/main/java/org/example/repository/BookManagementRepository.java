package org.example.repository;

import java.sql.SQLException;
import java.util.Optional;

public interface BookManagementRepository <T>{

    void dropTableIfExist() throws SQLException;

    void createTable() throws SQLException;

    void showAll () throws SQLException;

   Optional<T> getById(int id) throws SQLException;

    void insert(T t) throws SQLException;

    void deleteById(Integer id) throws SQLException;

    void updateById( T t) throws SQLException;

}
