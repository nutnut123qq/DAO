package model;

import java.sql.Timestamp;
import java.util.Objects;

public class Ad implements Comparable<Ad> {

    private long id;
    private String img;
    private String description;
    private String link;
    private int position;
    private String status;
    private Timestamp createdDate;
    private Timestamp updatedDate;
    private long createdBy;
    private long updateBy;
    private float width;
    private float height;

    // Constructor
    public Ad(long id, String img, String description, String link, int position, String status, Timestamp createdDate, Timestamp updatedDate, long createdBy, long updateBy, float width, float height) {
        this.id = id;
        this.img = img;
        this.description = description;
        this.link = link;
        this.position = position;
        this.status = status;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.createdBy = createdBy;
        this.updateBy = updateBy;
        this.width = width;
        this.height = height;
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Timestamp getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Timestamp updatedDate) {
        this.updatedDate = updatedDate;
    }

    public long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(long createdBy) {
        this.createdBy = createdBy;
    }

    public long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(long updateBy) {
        this.updateBy = updateBy;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    // equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ad ad = (Ad) o;
        return id == ad.id
                && position == ad.position
                && createdBy == ad.createdBy
                && updateBy == ad.updateBy
                && Float.compare(ad.width, width) == 0
                && Float.compare(ad.height, height) == 0
                && Objects.equals(img, ad.img)
                && Objects.equals(description, ad.description)
                && Objects.equals(link, ad.link)
                && Objects.equals(status, ad.status)
                && Objects.equals(createdDate, ad.createdDate)
                && Objects.equals(updatedDate, ad.updatedDate);
    }

    // hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(id, img, description, link, position, status, createdDate, updatedDate, createdBy, updateBy, width, height);
    }

    // compareTo method
    @Override
    public int compareTo(Ad o) {
        // Compare by id
        return Long.compare(this.id, o.id);
    }

}
