package webservice;

import DAO.Database;

import javax.xml.ws.Endpoint;
import java.sql.SQLException;

public class WebService {
    public static void main(String[] args) throws SQLException {
        Database.getInstance().populate();
        Endpoint.publish("http://localhost:9999/ws/author", new AuthorImpl());
        Endpoint.publish("http://localhost:9999/ws/book", new BookImpl());
    }
}
