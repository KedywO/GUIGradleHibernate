package gui;


import javax.persistence.*;

@Entity
@Table (name = "orders")
public class Orders {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int orderId;
    @Column (name = "quantity")
    private int quantity;
    @Column (name = "albumid")
    private int albumId;
    @Column (name = "userid")
    private int userId;
    @Column (name = "price")
    private int price;
    @Transient
    private String albumName;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }
}
