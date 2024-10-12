package org.example.repository.impl;

import org.example.books.Book;
import org.example.repository.BookManagementRepository;
import org.example.review.Review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ReviewRepository implements BookManagementRepository<Review> {

    private final Connection conn;
    private PreparedStatement preparedStatement;
    private BookManagementRepository<Book> bookRepository;

    public ReviewRepository(Connection conn, BookManagementRepository<Book> bookRepository) {
        this.conn = conn;
        this.bookRepository = bookRepository;
    }


    @Override
    public void dropTableIfExists() throws SQLException {
        String query = "DROP TABLE IF EXISTS review";

        this.preparedStatement = this.conn.prepareStatement(query);
        this.preparedStatement.executeUpdate();
    }

    @Override
    public void createTable() throws SQLException {
        String query = "CREATE TABLE review ("
                + "review_id INTEGER AUTO_INCREMENT, "
                + "book_id INTEGER, "
                + "score INTEGER, "
                + "comment TEXT, "
                + "PRIMARY KEY(review_id), "
                + "FOREIGN KEY (book_id) REFERENCES book(book_id)"
                + ");";

        this.preparedStatement = this.conn.prepareStatement(query);
        this.preparedStatement.executeUpdate();
    }

    @Override
    public void showAll() throws SQLException {
        String query = "SELECT * FROM review";

        this.preparedStatement = this.conn.prepareStatement(query);

        ResultSet rs = this.preparedStatement.executeQuery();
        while (rs.next()) {
            int review_id = rs.getInt("review_id");
            int book_id = rs.getInt("book_id");
            int score = rs.getInt("score");
            String comment = rs.getString("comment");

            Review review = new Review();
            review.setId(review_id);
            review.setScore(score);
            review.setComment(comment);
            Optional<Book> optionalBook = bookRepository.getById(book_id);
            if (optionalBook.isPresent()) {
                review.setBook(optionalBook.get());
            } else {
                System.out.println("Cartea nu a fost gasita");
            }
            System.out.println(review);
        }

    }

    @Override
    public Optional<Review> getById(int id) throws SQLException {
        String query = "SELECT * FROM review "
                + "WHERE review_id = ?";

        this.preparedStatement = this.conn.prepareStatement(query);
        this.preparedStatement.setInt(1, id);

        ResultSet rs = this.preparedStatement.executeQuery();
        while (rs.next()) {
            int review_id = rs.getInt("review_id");
            int book_id = rs.getInt("book_id");
            int score = rs.getInt("score");
            String comment = rs.getString("comment");

            Review review = new Review();
            review.setId(review_id);
            review.setScore(score);
            review.setComment(comment);
            Optional<Book> optionalBook = bookRepository.getById(book_id);
            if (optionalBook.isPresent()) {
                review.setBook(optionalBook.get());
            } else {
                System.out.println("Cartea nu a fost gasita");
                return Optional.empty();
            }
            return Optional.of(review);
        }
        return Optional.empty();
    }

    @Override
    public void insert(Review review) throws SQLException {

    }

    @Override
    public void deleteById(Integer id) throws SQLException {

    }

    @Override
    public void updateById(Review review) throws SQLException {

    }
}
