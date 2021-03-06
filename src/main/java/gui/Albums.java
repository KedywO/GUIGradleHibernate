package gui;


import javafx.scene.control.Button;

import javax.persistence.*;

@Entity
public class Albums {
    @Id
    private int albumId;
    @Column (name = "name")
    private String albumName;
    @Column (name = "author")
    private String albumAuthor;
    @Column (name = "status")
    private String status;
    @Column (name = "outyear")
    private int outYear;
    @Column (name = "albumdesc")
    private String albumDescription;
    @Column (name = "albumImg")
    private String albumImg;
    @Column (name = "price")
    private int price;
    @Transient
    private int quantity=1;
    @Transient
    private Button addBtn, subBtn,delBtn;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getAlbumAuthor() {
        return albumAuthor;
    }

    public void setAlbumAuthor(String albumAuthor) {
        this.albumAuthor = albumAuthor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getOutYear() {
        return outYear;
    }

    public void setOutYear(int outYear) {
        this.outYear = outYear;
    }

    public String getAlbumDescription() {
        return albumDescription;
    }

    public void setAlbumDescription(String albumDescription) {
        this.albumDescription = albumDescription;
    }

    public String getAlbumImg() {
        return albumImg;
    }

    public void setAlbumImg(String albumImg) {
        this.albumImg = albumImg;
    }

    public Button getAddBtn() {
        return addBtn;
    }

    public void setAddBtn(Button addBtn) {
        this.addBtn = addBtn;
    }

    public Button getSubBtn() {
        return subBtn;
    }

    public void setSubBtn(Button subBtn) {
        this.subBtn = subBtn;
    }

    public Button getDelBtn() {
        return delBtn;
    }

    public void setDelBtn(Button delBtn) {
        this.delBtn = delBtn;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    @Override
    public String toString() {
        return "Albums{" +
                "albumId=" + albumId +
                ", albumName='" + albumName + '\'' +
                ", albumAuthor='" + albumAuthor + '\'' +
                ", status='" + status + '\'' +
                ", outYear=" + outYear +
                ", albumImg='" + albumImg + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
