package model;

import java.sql.Timestamp;
import java.util.Objects;

public class Cart implements Comparable<Cart> {

    private long id;
    private long idUser;
    private long idCourse;
    private Timestamp addedAt;
    private long updatedBy;
    private Timestamp updatedDate;
    private Timestamp createdDate;
    private long createdBy;

    // Constructor
    public Cart(long id, long idUser, long idCourse, Timestamp addedAt, long updatedBy, Timestamp updatedDate, Timestamp createdDate, long createdBy) {
        this.id = id;
        this.idUser = idUser;
        this.idCourse = idCourse;
        this.addedAt = addedAt;
        this.updatedBy = updatedBy;
        this.updatedDate = updatedDate;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public long getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(long idCourse) {
        this.idCourse = idCourse;
    }

    public Timestamp getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(Timestamp addedAt) {
        this.addedAt = addedAt;
    }

    public long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Timestamp getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Timestamp updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(long createdBy) {
        this.createdBy = createdBy;
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
        Cart cart = (Cart) o;
        return id == cart.id
                && idUser == cart.idUser
                && idCourse == cart.idCourse
                && updatedBy == cart.updatedBy
                && createdBy == cart.createdBy
                && Objects.equals(addedAt, cart.addedAt)
                && Objects.equals(updatedDate, cart.updatedDate)
                && Objects.equals(createdDate, cart.createdDate);
    }

    // hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(id, idUser, idCourse, addedAt, updatedBy, updatedDate, createdDate, createdBy);
    }

    // compareTo method
    @Override
    public int compareTo(Cart o) {
        // Compare by addedAt timestamp
        return this.addedAt.compareTo(o.addedAt);
    }
}
