package at.htl.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Entity
public class Customer extends PanacheEntity {

    public String password;
    public String first_name;
    public String last_name;
    public Date birth_date;
    public String street;
    public Integer house_number;
    public String city, zip_code;
    public String email;
    public String tel_number;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonIgnore
    public List<Reservation> reservationList;

    public Customer() {
    }

    public Customer(String password, String first_name,
                    String last_name, Date birth_date,
                    String street, Integer house_number,
                    String city, String zip_code,
                    String email, String tel_number,
                    List<Reservation> reservationList) {
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.birth_date = birth_date;
        this.street = street;
        this.house_number = house_number;
        this.city = city;
        this.zip_code = zip_code;
        this.email = email;
        this.tel_number = tel_number;
        this.reservationList = reservationList;
    }
}
