package model;

import java.sql.Timestamp;
import java.util.Objects;

public class Comment implements Comparable<Comment> {

    private long id;
    private String name;
    private String email;
    private long commentId; // Foreign key referencing itself
    private String status;
    private int quantity;
    private String website;
    private String message;
    private Timestamp createdDate;
    private Timestamp updatedDate;
    private long createdBy;
    private long updatedBy;
    private long courseId; // Foreign key referencing tblCourse(id)

    // Constructor
    public Comment(long id, String name, String email, long commentId, String status, int quantity, String website, String message, Timestamp createdDate, Timestamp updatedDate, long createdBy, long updatedBy, long courseId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.commentId = commentId;
        this.status = status;
        this.quantity = quantity;
        this.website = website;
        this.message = message;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.courseId = courseId;
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    // equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return id == comment.id &&
                commentId == comment.commentId &&
                quantity == comment.quantity &&
                createdBy == comment.createdBy &&
                updatedBy == comment.updatedBy &&
                courseId == comment.courseId &&
                Objects.equals(name, comment.name) &&
                Objects.equals(email, comment.email) &&
                Objects.equals(status, comment.status) &&
                Objects.equals(website, comment.website) &&
                Objects.equals(message, comment.message) &&
                Objects.equals(createdDate, comment.createdDate) &&
                Objects.equals(updatedDate, comment.updatedDate);
    }

    // hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, commentId, status, quantity, website, message, createdDate, updatedDate, createdBy, updatedBy, courseId);
    }

    // compareTo method
    @Override
    public int compareTo(Comment o) {
        // Compare by id
        return Long.compare(this.id, o.id);
    }
}
