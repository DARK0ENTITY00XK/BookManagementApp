package org.example.repository.impl;

import org.example.books.Book;
import org.example.repository.BookManagementRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookRepository implements BookManagementRepository<Book> {

    private final Connection conn;
    private PreparedStatement preparedStatement;

    public BookRepository(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void dropTableIfExists() throws SQLException {
        String query = "DROP TABLE IF EXISTS book";

        this.preparedStatement = this.conn.prepareStatement(query);
        this.preparedStatement.executeUpdate();
    }

    @Override
    public void createTable() throws SQLException {
        String query = "CREATE TABLE book ("
                + "book_id INTEGER AUTO_INCREMENT, "
                + "title VARCHAR(225), "
                + "author_id INTEGER, "
                + "description TEXT ,"
                + "PRIMARY KEY(book_id), "
                + "FOREIGN KEY (author_id) REFERENCES author(author_id)";

        this.preparedStatement = this.conn.prepareStatement(query);
        this.preparedStatement.executeUpdate();
    }

    @Override
    public void showAll() throws SQLException {

    }

    @Override
    public void insert(Book book) throws SQLException {

    }

    @Override
    public void deleteById(Integer id) throws SQLException {

    }

    @Override
    public void updateById(Book book) throws SQLException {

    }
}
