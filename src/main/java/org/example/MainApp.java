package org.example;

import org.example.author.Author;
import org.example.books.Book;
import org.example.repository.BookManagementRepository;
import org.example.repository.impl.AuthorRepository;
import org.example.repository.impl.BookRepository;

import java.sql.SQLException;
import java.util.Optional;

import static org.example.BaseConn.establishConn;

class MainApp {
    public static void main(String[] args) {

        try {
            BookManagementRepository<Author> authorRepository = new AuthorRepository(establishConn());
//            authorRepository.createTable();
//            authorRepository.insert(new Author("Caragiale", "Ion"));
            Optional<Author> optionalAuthor = authorRepository.getById(1);
            if (optionalAuthor.isPresent()) {
                Author author = optionalAuthor.get();
//                System.out.println(author);
            }

            authorRepository.showAll();
//            authorRepository.deleteById(4);

            BookManagementRepository<Book> bookRepository = new BookRepository(establishConn(), authorRepository);
//            bookRepository.dropTableIfExists();
//            bookRepository.createTable();
            Book book = new Book();
            book.setTitle("O scrisoare pierduta");
            book.setDescription("o carte traumatizanta");
            book.setAuthor(optionalAuthor.get());


//            bookRepository.insert(book);   // il comentam ca deja am facut insertul si daca dam run sa nu se insereze aceasi valoare de mai multe ori

//            Optional<Book> optionalBook = bookRepository.getById(1);
//            if (optionalBook.isPresent()) {
//                System.out.println(optionalBook.get());
//            }
//            optionalBook = bookRepository.getById(2);
//            if (optionalBook.isPresent()) {
//                System.out.println(optionalBook.get());
//            }
            bookRepository.showAll();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}