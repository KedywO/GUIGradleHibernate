package gui;

import javax.persistence.*;
import javax.persistence.Column;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "city")
    private String city;
    @Column(name = "mail")
    private String mail;
    @Column(name = "orders")
    private int orders=0;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "id")
    private int id;

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\''

                 +
                ", password='" + password + '\'' +
                ", city='" + city + '\'' +
                ", mail='" + mail + '\'' +
                ", id=" + id +
                '}';
    }

    public String getPassword() {
        return password;
    }

    public String getCity() {
        return city;
    }

    public String getMail() {
        return mail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getOrders() {
        return orders;
    }

    public void setOrders(int orders) {
        this.orders = orders;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Id
    public int getId() {
        return id;
    }
}
