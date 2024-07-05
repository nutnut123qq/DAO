package model;

import java.sql.Timestamp;
import java.util.Objects;

public class Rating implements Comparable<Rating> {

    private long id;
    private String comment;
    private String website;
    private long updatedBy;
    private Timestamp updatedDate;
    private long ratedBy;
    private Timestamp ratedAt;
    private long idCourse;
    private byte numberStar;

    // Constructor
    public Rating(long id, String comment, String website, long updatedBy, Timestamp updatedDate, long ratedBy, Timestamp ratedAt, long idCourse, byte numberStar) {
        this.id = id;
        this.comment = comment;
        this.website = website;
        this.updatedBy = updatedBy;
        this.updatedDate = updatedDate;
        this.ratedBy = ratedBy;
        this.ratedAt = ratedAt;
        this.idCourse = idCourse;
        this.numberStar = numberStar;
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
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

    public long getRatedBy() {
        return ratedBy;
    }

    public void setRatedBy(long ratedBy) {
        this.ratedBy = ratedBy;
    }

    public Timestamp getRatedAt() {
        return ratedAt;
    }

    public void setRatedAt(Timestamp ratedAt) {
        this.ratedAt = ratedAt;
    }

    public long getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(long idCourse) {
        this.idCourse = idCourse;
    }

    public byte getNumberStar() {
        return numberStar;
    }

    public void setNumberStar(byte numberStar) {
        this.numberStar = numberStar;
    }
    
     // equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rating rating = (Rating) o;
        return id == rating.id &&
                updatedBy == rating.updatedBy &&
                ratedBy == rating.ratedBy &&
                idCourse == rating.idCourse &&
                numberStar == rating.numberStar &&
                Objects.equals(comment, rating.comment) &&
                Objects.equals(website, rating.website) &&
                Objects.equals(updatedDate, rating.updatedDate) &&
                Objects.equals(ratedAt, rating.ratedAt);
    }

    // hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(id, comment, website, updatedBy, updatedDate, ratedBy, ratedAt, idCourse, numberStar);
    }

    // compareTo method
    @Override
    public int compareTo(Rating o) {
        // Compare by ratedAt timestamp
        return this.ratedAt.compareTo(o.ratedAt);
    }
}
