package org.example;

import org.example.author.Author;
import org.example.repository.BookManagementRepository;
import org.example.repository.impl.AuthorRepository;

import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.Optional;

import static org.example.BaseConn.establishConn;

class MainApp {
    public static void main(String[] args) {

        try {
            BookManagementRepository<Author > authorRepository = new AuthorRepository(establishConn());
           // authorRepository.createTable();
            authorRepository.insert(new Author("Creanga", "Ion"));
            Optional<Author> optionalAuthor = authorRepository.getById(1);
            if(optionalAuthor.isPresent()) {
                Author author = optionalAuthor.get();
                System.out.println(author);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}