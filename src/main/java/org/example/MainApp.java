package org.example;

import org.example.author.Author;
import org.example.books.Book;
import org.example.repository.BookManagementRepository;
import org.example.repository.impl.AuthorRepository;
import org.example.repository.impl.BookRepository;
import org.example.repository.impl.ReviewRepository;
import org.example.review.Review;

import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.Optional;

import static org.example.BaseConn.establishConn;

class MainApp {
    public static void main(String[] args) {

        try {
            BookManagementRepository<Author > authorRepository = new AuthorRepository(establishConn());
           // authorRepository.createTable();
          //  authorRepository.insert(new Author("Creanga", "Ion"));
            Optional<Author> optionalAuthor = authorRepository.getById(2);
            if(optionalAuthor.isPresent()) {
                Author author = optionalAuthor.get();
                author.setFirstName("Dandanache");
                authorRepository.updateById(author);
               // System.out.println(author);
            }

            authorRepository.showAll();

            BookManagementRepository<Book> bookRepository = new BookRepository(establishConn(), authorRepository);
         //   bookRepository.dropTableIfExist();
          //  bookRepository.createTable();
            Book book = new Book();
            book.setTitle("O scrisoare pierduta.");
            book.setDescription("O carte. ");
            book.setAuthor(optionalAuthor.get());

           // bookRepository.insert(book);

            Optional<Book>optionalBook = bookRepository.getById(1);
            if(optionalBook.isPresent()) {
                System.out.println(optionalBook.get());
            }
            optionalBook = bookRepository.getById(2);
            if(optionalBook.isPresent()) {
                System.out.println(optionalBook.get());
            }

            BookManagementRepository<Review> reviewRepository = new ReviewRepository(establishConn(), bookRepository);
          //  reviewRepository.createTable();
            Review review = new Review();
            review.setScore(5);
            review.setComment("Aceasta carte este magnifica! ");
            review.setBook(optionalBook.get());
          //  reviewRepository.insert(review);
            reviewRepository.deleteById(2);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}