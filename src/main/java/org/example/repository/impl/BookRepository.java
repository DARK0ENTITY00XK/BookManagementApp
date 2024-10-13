package org.example.repository.impl;

import org.example.author.Author;
import org.example.books.Book;
import org.example.repository.BookManagementRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class BookRepository implements BookManagementRepository<Book> {

    private final Connection conn;
    private PreparedStatement preparedStatement;
    private BookManagementRepository<Author> authorRepository;

    public BookRepository (Connection conn, BookManagementRepository<Author> authorRepository) {
        this.conn = conn;
        this.authorRepository = authorRepository;
    }


    @Override
    public void dropTableIfExist() throws SQLException {
        String query = "DROP TABLE IF EXISTS book ";

        this.preparedStatement = this.conn.prepareStatement(query);
        this.preparedStatement.executeUpdate();
    }

    @Override
    public void createTable() throws SQLException {
        String query = "CREATE TABLE book ("
                + "book_id  INTEGER AUTO_INCREMENT, "
                + "title VARCHAR(225), "
                + "author_id INTEGER, "
                + "description TEXT, "
                + "PRIMARY KEY(book_id), "
                + "FOREIGN KEY(author_id) REFERENCES author(author_id)"
                + ")";

        this.preparedStatement = this.conn.prepareStatement(query);
        this.preparedStatement.executeUpdate();
    }

    @Override
    public void showAll() throws SQLException {
        String query = "SELECT * FROM book ";

        this.preparedStatement = this.conn.prepareStatement(query);

        ResultSet rs = this.preparedStatement.executeQuery();
        while(rs.next()) {
            int book_id = rs.getInt("book_id");
            String title = rs.getString("title");
            int author_id = rs.getInt("author_id");
            String description = rs.getString("description");

            Book book = new Book();
            book.setId(book_id);
            book.setTitle(title);
            book.setDescription(description);

            Optional<Author> optionalAuthor = authorRepository.getById(author_id);
            if (optionalAuthor.isPresent()) {
                book.setAuthor(optionalAuthor.get());
            } else {
                System.out.println("Autorul nu a fost gasit");
            }

            System.out.println(book);

          //  System.out.println(book);
        }
    }

    @Override
    public Optional<Book> getById(int id) throws SQLException {

        String query = "SELECT * FROM book "
                + "WHERE book_id = ? ";

        this.preparedStatement = this.conn.prepareStatement(query);
        this.preparedStatement.setInt(1, id);

        ResultSet rs = preparedStatement.executeQuery();

        if (rs.next()) {
            int book_id = rs.getInt("book_id");
            String title = rs.getString("title");
            int author_id = rs.getInt("author_id");
            String description = rs.getString("description");

            Book book = new Book();
            book.setId(book_id);
            book.setTitle(title);
            book.setDescription(description);
            Optional<Author> optionalAuthor = this.authorRepository.getById(author_id);
            if(optionalAuthor.isPresent()) {
                book.setAuthor(optionalAuthor.get());
            }else {
                System.out.println("Autorul nu a fost gasit.");
            }

            // punem obiectul author in cutia optional
            return Optional.of(book);
        } else {
            System.out.println("Cartea nu a fost gasita ");
            return Optional.empty();
        }
    }

    @Override
    public void insert(Book book) throws SQLException {
        String query = "INSERT INTO book (title, author_id, description) VALUES (?, ?, ?)";


        this.preparedStatement = this.conn.prepareStatement(query);
        this.preparedStatement.setString(1, book.getTitle());
        this.preparedStatement.setInt(2, book.getAuthor().getId());
        this.preparedStatement.setString(3, book.getDescription());
        this.preparedStatement.executeUpdate();

    }

    @Override
    public void deleteById(Integer id) throws SQLException {
        String query = "DELETE FROM book"
                + "WHERE book_id = ? ";

        this.preparedStatement = this.conn.prepareStatement(query);
        this.preparedStatement.setInt(1, id);
        this.preparedStatement.executeUpdate();

    }

    @Override
    public void updateById(Book book) throws SQLException {
        String query = "UPDATE book"
                + "SET title = ?, author_id = ?, description = ?"
                + "WHERE book_id = ? ";

        this.preparedStatement = this.conn.prepareStatement(query);
        this.preparedStatement.setString(1, book.getTitle());
        this.preparedStatement.setInt(2, book.getAuthor().getId());
        this.preparedStatement.setString(3, book.getDescription());
        this.preparedStatement.setInt(4, book.getId());
        this.preparedStatement.executeUpdate();
    }
}
