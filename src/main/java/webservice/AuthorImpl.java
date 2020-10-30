package webservice;

import DAO.AuthorDAO;
import DTO.Author;
import DTO.Authors;

import javax.jws.WebService;
import java.sql.SQLException;
import java.util.ArrayList;

@WebService(endpointInterface = "webservice.IAuthor")
public class AuthorImpl implements IAuthor {
    private final AuthorDAO dao = new AuthorDAO();

    @Override
    public Authors getAuthors() {
        ArrayList<Author> results;
        Authors authors = null;
        try {
            results = dao.getAll();
            authors = new Authors(results);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authors;
    }

    @Override
    public Author getAuthorById(Integer id) {
        Author author = null;
        try {
            author = dao.getById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return author;
    }

    @Override
    public String createAuthor(String firstname, String lastname) {
        int rows = 0;
        try {
            rows = dao.create(firstname, lastname);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows + " rows inserted";
    }

    @Override
    public String updateAuthor(Integer id, String firstname, String lastname) {
        int rows = 0;
        try {
            rows = dao.update(id, firstname, lastname);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows + " rows updated";
    }

    @Override
    public String deleteAuthor(Integer id) {
        int rows = 0;
        try {
            rows = dao.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows + " rows deleted";
    }
}
