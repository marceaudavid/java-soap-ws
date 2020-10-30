package DTO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Author")
@XmlAccessorType(XmlAccessType.FIELD)
public class Author {

    private Integer id;

    private String firstname;

    private String lastname;

    private Books books;

    public Author() {
    }

    public Author(Integer id, String firstname, String lastname, Books books) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        if (books != null) {
            this.books = books;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Books getBooks() {
        return books;
    }

    public void setBooks(Books books) {
        this.books = books;
    }
}