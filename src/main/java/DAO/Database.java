package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private static Database instance;
    private Connection connection;

    private Database() {
        try {
            this.connection = DriverManager.getConnection("jdbc:sqlite:database.db");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static Database getInstance() throws SQLException {
        if (instance == null) {
            instance = new Database();
        } else if (instance.getConnection().isClosed()) {
            instance = new Database();
        }
        return instance;
    }

    public Connection getConnection() {
        return this.connection;
    }

    public void populate() {
        try {
            String[] populate = {
                    "drop table if exists author;",
                    "drop table if exists book;",
                    "create table author (firstname text not null, lastname text not null);",
                    "create table book (isbn text primary key, title text not null, author_id integer not null, foreign key (author_id) references author(rowid));",
                    "insert into author (firstname, lastname) values ('Andrzej', 'Sapkowski');",
                    "insert into author (firstname, lastname) values ('Bernard', 'Cornwell');",
                    "insert into book (ISBN, title, author_id) values ('978-0-575-08244-1', 'The Last Wish', 1);",
                    "insert into book (ISBN, title, author_id) values ('978-1-4732-1153-7', 'Sword of Destiny', 1);",
                    "insert into book (ISBN, title, author_id) values ('978-0-575-08484-1', 'Blood of Elves', 1);",
                    "insert into book (ISBN, title, author_id) values ('0-00-714990-5', 'The Last Kingdom', 2);",
                    "insert into book (ISBN, title, author_id) values ('0-00-714992-1', 'The Pale Horseman', 2);",
                    "insert into book (ISBN, title, author_id) values ('0-00-721968-7', 'The Lords of the North', 2);",
            };
            Statement statement = this.connection.createStatement();
            for (String sql : populate) {
                statement.execute(sql);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
