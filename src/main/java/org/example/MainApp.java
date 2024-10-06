package org.example;

import org.example.author.Author;
import org.example.repository.BookManagementRepository;
import org.example.repository.impl.AuthorRepository;

import java.sql.SQLException;

class MainApp {
    public static void main(String[] args) {

        BookManagementRepository<Author> authorRepository = new AuthorRepository();
        try {
            authorRepository.insert(new Author("Creanga", "Ion"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}