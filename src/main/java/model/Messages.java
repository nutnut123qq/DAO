package model;

import java.sql.Timestamp;
import java.util.Objects;

public class Messages implements Comparable<Messages> {

    private String id;
    private String subject;
    private String email;
    private String description;
    private long updatedBy;
    private Timestamp updatedDate;
    private Timestamp createdDate;
    private long createdBy;
    private String message;

    // Constructor
    public Messages(String id, String subject, String email, String description, long updatedBy, Timestamp updatedDate, Timestamp createdDate, long createdBy, String message) {
        this.id = id;
        this.subject = subject;
        this.email = email;
        this.description = description;
        this.updatedBy = updatedBy;
        this.updatedDate = updatedDate;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.message = message;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
        Messages messages = (Messages) o;
        return Objects.equals(id, messages.id)
                && updatedBy == messages.updatedBy
                && createdBy == messages.createdBy
                && Objects.equals(subject, messages.subject)
                && Objects.equals(email, messages.email)
                && Objects.equals(description, messages.description)
                && Objects.equals(updatedDate, messages.updatedDate)
                && Objects.equals(createdDate, messages.createdDate)
                && Objects.equals(message, messages.message);
    }

    // hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(id, subject, email, description, updatedBy, updatedDate, createdDate, createdBy, message);
    }

    // compareTo method
    @Override
    public int compareTo(Messages o) {
        // Compare by createdDate timestamp
        return this.createdDate.compareTo(o.createdDate);
    }
}
