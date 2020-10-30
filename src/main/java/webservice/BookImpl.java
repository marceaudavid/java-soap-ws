package webservice;

import DAO.BookDAO;
import DTO.Book;
import DTO.Books;

import javax.jws.WebService;
import java.sql.SQLException;
import java.util.ArrayList;

@WebService(endpointInterface = "webservice.IBook")
public class BookImpl implements IBook {
    private final BookDAO dao = new BookDAO();

    @Override
    public Books getBooks() {
        ArrayList<Book> results;
        Books books = null;
        try {
            results = dao.getAll();
            books = new Books(results);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public Book getBookById(String ISBN) {
        Book book = null;
        try {
            book = dao.getById(ISBN);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    @Override
    public String createBook(String ISBN, String title, Integer authorId) {
        int rows = 0;
        try {
            rows = dao.create(ISBN, title, authorId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows + " rows inserted";
    }

    @Override
    public String updateBook(String ISBN, String title, Integer authorId) {
        int rows = 0;
        try {
            rows = dao.update(ISBN, title, authorId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows + " rows updated";
    }

    @Override
    public String deleteBook(String ISBN) {
        int rows = 0;
        try {
            rows = dao.delete(ISBN);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows + " rows deleted";
    }
}