package DTO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Author")
@XmlAccessorType(XmlAccessType.FIELD)
public class Book {

    private String ISBN;

    private String title;

    private Author author;

    public Book() {
    }

    public Book(String ISBN, String title, Author author) {
        this.ISBN = ISBN;
        this.title = title;
        if (author != null) {
            this.author = author;
        }
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
