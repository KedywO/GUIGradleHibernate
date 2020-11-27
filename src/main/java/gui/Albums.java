package gui;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Albums {
    @Id
    private long albumId;
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

    public long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(long albumId) {
        this.albumId = albumId;
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

    @Override
    public String toString() {
        return "Albums{" +
                "albumId=" + albumId +
                ", albumName='" + albumName + '\'' +
                ", albumAuthor='" + albumAuthor + '\'' +
                ", status='" + status + '\'' +
                ", outYear=" + outYear +
                ", albumDescription='" + albumDescription + '\'' +
                '}';
    }
}
