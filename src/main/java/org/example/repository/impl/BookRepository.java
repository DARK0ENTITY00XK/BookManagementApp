package org.example.repository.impl;

import org.example.books.Book;
import org.example.repository.BookManagementRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

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
        String query = "SELECT * FROM book";

        this.preparedStatement = this.conn.prepareStatement(query);
        this.preparedStatement.executeUpdate();
    }

    @Override
    public Optional<Book> getById(int id) throws SQLException {
        return Optional.empty();
    }

    @Override
    public void insert(Book book) throws SQLException {
        String query = "INSERT INTO book"
                + ("(title, author_id, description)")
                + "VALUES (?,?,?)";

        this.preparedStatement = this.conn.prepareStatement(query);
        this.preparedStatement.setString(1, book.getTitle());
        this.preparedStatement.setInt(2, book.getAuthor().getId());
        this.preparedStatement.setString(3, book.getDescription());
        this.preparedStatement.executeUpdate();

    }

    @Override
    public void deleteById(Integer id) throws SQLException {

    }

    @Override
    public void updateById(Book book) throws SQLException {

    }
}
