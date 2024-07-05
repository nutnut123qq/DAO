package model;

import java.sql.Timestamp;
import java.util.Objects;

public class Category implements Comparable<Category> {

    private String id; // Changed from long to String
    private String name;
    private String description;
    private long updatedBy;
    private Timestamp updatedDate;
    private Timestamp createdDate;
    private long createdBy;
    private String status;
    private long idCourse;

    // Constructor
    public Category(String id, String name, String description, long updatedBy, Timestamp updatedDate, Timestamp createdDate, long createdBy, String status, long idCourse) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.updatedBy = updatedBy;
        this.updatedDate = updatedDate;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.status = status;
        this.idCourse = idCourse;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(long idCourse) {
        this.idCourse = idCourse;
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
        Category category = (Category) o;
        return updatedBy == category.updatedBy
                && createdBy == category.createdBy
                && idCourse == category.idCourse
                && Objects.equals(id, category.id)
                && Objects.equals(name, category.name)
                && Objects.equals(description, category.description)
                && Objects.equals(updatedDate, category.updatedDate)
                && Objects.equals(createdDate, category.createdDate)
                && Objects.equals(status, category.status);
    }

    // hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, updatedBy, updatedDate, createdDate, createdBy, status, idCourse);
    }

    // compareTo method
    @Override
    public int compareTo(Category o) {
        // Compare by name
        return this.name.compareTo(o.name);
    }
}
