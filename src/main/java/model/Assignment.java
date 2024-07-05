package model;

import java.sql.Timestamp;
import java.util.Objects;

public class Assignment implements Comparable<Assignment> {

    private String id;
    private long idCourse;
    private String title;
    private String description;
    private Timestamp deadline;
    private String fileURL;
    private long updatedBy;
    private Timestamp updatedDate;
    private Timestamp createdDate;
    private long createdBy;

    // Constructor
    public Assignment(String id, long idCourse, String title, String description, Timestamp deadline, String fileURL, long updatedBy, Timestamp updatedDate, Timestamp createdDate, long createdBy) {
        this.id = id;
        this.idCourse = idCourse;
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.fileURL = fileURL;
        this.updatedBy = updatedBy;
        this.updatedDate = updatedDate;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(long idCourse) {
        this.idCourse = idCourse;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getDeadline() {
        return deadline;
    }

    public void setDeadline(Timestamp deadline) {
        this.deadline = deadline;
    }

    public String getFileURL() {
        return fileURL;
    }

    public void setFileURL(String fileURL) {
        this.fileURL = fileURL;
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Assignment that = (Assignment) o;
        return idCourse == that.idCourse &&
                updatedBy == that.updatedBy &&
                createdBy == that.createdBy &&
                Objects.equals(id, that.id) &&
                Objects.equals(title, that.title) &&
                Objects.equals(description, that.description) &&
                Objects.equals(deadline, that.deadline) &&
                Objects.equals(fileURL, that.fileURL) &&
                Objects.equals(updatedDate, that.updatedDate) &&
                Objects.equals(createdDate, that.createdDate);
    }

    // hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(id, idCourse, title, description, deadline, fileURL, updatedBy, updatedDate, createdDate, createdBy);
    }

    // compareTo method
    @Override
    public int compareTo(Assignment o) {
        // Compare by deadline
        return this.deadline.compareTo(o.deadline);
    }
}
