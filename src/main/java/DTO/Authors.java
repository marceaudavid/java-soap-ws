package DTO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement(name = "Author")
@XmlAccessorType(XmlAccessType.FIELD)
public class Authors {

    private ArrayList<Author> authors;

    public Authors() {
    }

    public Authors(ArrayList<Author> authors) {
        this.authors = authors;
    }

    public ArrayList<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(ArrayList<Author> authors) {
        this.authors = authors;
    }
}
