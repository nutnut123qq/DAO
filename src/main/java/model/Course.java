package model;

import java.sql.Timestamp;
import java.util.Objects;

public class Course implements Comparable<Course> {

    private String id; // Changed from long to String
    private String name;
    private int rate;
    private float price;
    private String lectureId;
    private Timestamp timeCourse;
    private String description;
    private Timestamp createdDate;
    private Timestamp updatedDate;
    private String createdBy;
    private String updatedBy;
    private String idCategory;
    private String img; // New property for image link

    // Constructors
    public Course(String id, String name, int rate, float price, String lectureId, Timestamp timeCourse, String description, Timestamp createdDate, Timestamp updatedDate, String createdBy, String updatedBy, String idCategory, String img) {
        this.id = id;
        this.name = name;
        this.rate = rate;
        this.price = price;
        this.lectureId = lectureId;
        this.timeCourse = timeCourse;
        this.description = description;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.idCategory = idCategory;
        this.img = img; // Initialize the new property
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

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getLectureId() {
        return lectureId;
    }

    public void setLectureId(String lectureId) {
        this.lectureId = lectureId;
    }

    public Timestamp getTimeCourse() {
        return timeCourse;
    }

    public void setTimeCourse(Timestamp timeCourse) {
        this.timeCourse = timeCourse;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(String idCategory) {
        this.idCategory = idCategory;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    // equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(id, course.id) &&
                rate == course.rate &&
                Float.compare(course.price, price) == 0 &&
                Objects.equals(name, course.name) &&
                Objects.equals(lectureId, course.lectureId) &&
                Objects.equals(timeCourse, course.timeCourse) &&
                Objects.equals(description, course.description) &&
                Objects.equals(createdDate, course.createdDate) &&
                Objects.equals(updatedDate, course.updatedDate) &&
                Objects.equals(createdBy, course.createdBy) &&
                Objects.equals(updatedBy, course.updatedBy) &&
                Objects.equals(idCategory, course.idCategory) &&
                Objects.equals(img, course.img); // Include img in equality check
    }

    // hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(id, name, rate, price, lectureId, timeCourse, description, createdDate, updatedDate, createdBy, updatedBy, idCategory, img);
    }

    // compareTo method
    @Override
    public int compareTo(Course o) {
        // Compare by name
        return this.name.compareTo(o.name);
    }
}
