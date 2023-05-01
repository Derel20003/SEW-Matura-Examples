package at.htl.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Book extends PanacheEntityBase {

    @EmbeddedId
    public BookId id;

    public String genre;
    public int price;

}
