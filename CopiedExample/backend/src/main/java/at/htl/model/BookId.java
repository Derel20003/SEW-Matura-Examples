package at.htl.model;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class BookId implements Serializable {

    private String author;
    private String name;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
