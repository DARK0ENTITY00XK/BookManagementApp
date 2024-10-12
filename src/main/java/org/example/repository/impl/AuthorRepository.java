package org.example.repository.impl;

import org.example.author.Author;
import org.example.repository.BookManagementRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class AuthorRepository implements BookManagementRepository<Author> {

    private final Connection conn;
    private PreparedStatement preparedStatement = null;

    public AuthorRepository(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void dropTableIfExists() throws SQLException {
        String query = "DROP TABLE IF EXISTS author";

        this.preparedStatement = this.conn.prepareStatement(query);
        this.preparedStatement.executeUpdate();
    }

    @Override
    public void createTable() throws SQLException {
        String query = "CREATE TABLE author ("
                + "author_id INTEGER AUTO_INCREMENT, "
                + "first_name VARCHAR(225), "
                + "last_name VARCHAR(225), "
                + "PRIMARY KEY(author_id)"
                + ");";

        this.preparedStatement = this.conn.prepareStatement(query);
        this.preparedStatement.executeUpdate();
    }

    @Override
    public void showAll() throws SQLException {
        String query = "SELECT * FROM author";

        this.preparedStatement = this.conn.prepareStatement(query);
        this.preparedStatement.executeUpdate();
    }

    @Override
    public Optional<Author> getById(int id) throws SQLException {
        String query = "SELECT * FROM author "
                + "WHERE author_id = ?";

        this.preparedStatement = this.conn.prepareStatement(query); // il preg pt a fi executat
        preparedStatement.setInt(1, id);

        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            int author_id = rs.getInt("author_id");
            String first_name = rs.getString("first_name");
            String last_name = rs.getString("last_name");

            Author author = new Author();
            author.setId(author_id);
            author.setFirstName(first_name);
            author.setLastName(last_name);

            // punem obiectul author in cutia optional
            return Optional.of(author);
        } else {
            System.out.println("Autorul nu a fost gasit");
            return Optional.empty();
        }
    }

    @Override
    public void insert(Author author) throws SQLException {
        String query = "INSERT INTO author"
                + ("(first_name, last_name)")
                + "VALUES (?, ?)";

        this.preparedStatement = this.conn.prepareStatement(query);
        this.preparedStatement.setString(1, author.getFirstName());
        this.preparedStatement.setString(2, author.getLastName());
        this.preparedStatement.executeUpdate();
    }

    @Override
    public void deleteById(Integer id) throws SQLException {
        String query = "DELETE FROM author"
                + "WHERE author_id = ?";

        this.preparedStatement = this.conn.prepareStatement(query);
        this.preparedStatement.setInt(1, id);
        this.preparedStatement.executeUpdate();
    }

    @Override
    public void updateById(Author author) throws SQLException {
        String query = "UPDATE author"
                + "SET first_name = ?, last_name = ?"
                + "WHERE author_id = ?";

        this.preparedStatement = this.conn.prepareStatement(query);
        this.preparedStatement.setString(1, author.getFirstName());
        this.preparedStatement.setString(2, author.getLastName());
        this.preparedStatement.setInt(3, author.getId());
    }
}
