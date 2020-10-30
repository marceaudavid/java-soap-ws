package DAO;

import DTO.Author;
import DTO.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookDAO {

    private Book buildBookWithAuthor(Connection connection, ResultSet rs) throws SQLException {
        PreparedStatement statement = connection
                .prepareStatement("select rowid, firstname, lastname from author where author.rowid = ?;");
        statement.setInt(1, rs.getInt("author_id"));
        ResultSet rs2 = statement.executeQuery();
        return new Book(rs.getString("isbn"), rs.getString("title"),
                new Author(rs2.getInt("rowid"), rs2.getString("firstname"), rs2.getString("lastname"), null));
    }

    public ArrayList<Book> getAll() throws SQLException {
        Connection connection = Database.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("select isbn, title, author_id from book;");
        ResultSet rs = statement.executeQuery();
        ArrayList<Book> books = new ArrayList<Book>();
        while (rs.next()) {
            Book book = buildBookWithAuthor(connection, rs);
            books.add(book);
        }
        return books;
    }

    public ArrayList<Book> getAllByAuthor(Integer id) throws SQLException {
        Connection connection = Database.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("select * from book where author_id = ?;");
        ResultSet rs = statement.executeQuery();
        statement.setInt(1, id);
        ArrayList<Book> books = new ArrayList<Book>();
        while (rs.next()) {
            Book book = new Book(rs.getString("isbn"), rs.getString("title"), null);
            books.add(book);
        }
        return books;
    }

    public Book getById(String id) throws SQLException {
        Connection connection = Database.getInstance().getConnection();
        PreparedStatement statement = connection
                .prepareStatement("select isbn, title, author_id from book where book.isbn = ?;");
        statement.setString(1, id);
        ResultSet rs = statement.executeQuery();
        return buildBookWithAuthor(connection, rs);
    }

    public int create(String ISBN, String title, Integer authorId) throws SQLException {
        Connection connection = Database.getInstance().getConnection();
        PreparedStatement statement = connection
                .prepareStatement("insert into book (isbn, title, author_id) values (?, ?, ?);");
        statement.setString(1, ISBN);
        statement.setString(2, title);
        statement.setInt(3, authorId);
        return statement.executeUpdate();
    }

    public int update(String ISBN, String title, Integer authorId) throws SQLException {
        Connection connection = Database.getInstance().getConnection();
        PreparedStatement statement = connection
                .prepareStatement("update book set title = ?, author_id = ? where isbn = ?;");
        statement.setString(1, title);
        statement.setInt(2, authorId);
        statement.setString(3, ISBN);
        return statement.executeUpdate();
    }

    public int delete(String ISBN) throws SQLException {
        Connection connection = Database.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("delete from book where isbn = ?;");
        statement.setString(1, ISBN);
        return statement.executeUpdate();
    }
}
