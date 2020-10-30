package DAO;

import DTO.Author;
import DTO.Book;
import DTO.Books;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AuthorDAO {

    private Author buildAuthorWithBookList(Connection connection, ResultSet rs) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("select * from book where author_id = ?;");
        statement.setInt(1, rs.getInt("rowid"));
        ResultSet rs2 = statement.executeQuery();
        ArrayList<Book> books = new ArrayList<Book>();
        while (rs2.next()) {
            Book book = new Book(rs2.getString("isbn"), rs2.getString("title"), null);
            books.add(book);
        }
        return new Author(rs.getInt("rowid"), rs.getString("firstname"), rs.getString("lastname"), new Books(books));
    }

    public ArrayList<Author> getAll() throws SQLException {
        Connection connection = Database.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("select rowid, firstname, lastname from author;");
        ResultSet rs = statement.executeQuery();
        ArrayList<Author> authors = new ArrayList<Author>();
        while (rs.next()) {
            Author author = buildAuthorWithBookList(connection, rs);
            authors.add(author);
        }
        return authors;
    }

    public Author getById(Integer id) throws SQLException {
        Connection connection = Database.getInstance().getConnection();
        PreparedStatement statement = connection
                .prepareStatement("select rowid, firstname, lastname from author where author.rowid = ?;");
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();
        return buildAuthorWithBookList(connection, rs);
    }

    public int create(String firstname, String lastname) throws SQLException {
        Connection connection = Database.getInstance().getConnection();
        PreparedStatement statement = connection
                .prepareStatement("insert into author (firstname, lastname) values (?, ?);");
        statement.setString(1, firstname);
        statement.setString(2, lastname);
        return statement.executeUpdate();
    }

    public int update(Integer id, String firstname, String lastname) throws SQLException {
        Connection connection = Database.getInstance().getConnection();
        PreparedStatement statement = connection
                .prepareStatement("update author set firstname = ?, lastname = ? where author.rowid = ?;");
        statement.setString(1, firstname);
        statement.setString(2, lastname);
        statement.setInt(3, id);
        return statement.executeUpdate();
    }

    public int delete(Integer id) throws SQLException {
        Connection connection = Database.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("delete from author where author.rowid = ?;");
        statement.setInt(1, id);
        return statement.executeUpdate();
    }
}
