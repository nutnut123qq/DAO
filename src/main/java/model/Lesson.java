package model;

import java.sql.Timestamp;
import java.util.Objects;

public class Lesson implements Comparable<Lesson> {

    private String id; // Chuyển từ long sang String
    private String nameLesson;
    private long courseId;
    private Timestamp createdDate;
    private Timestamp updatedDate;
    private long createdBy;
    private long updatedBy;
    private String content;
    private String lessonId; // Chuyển từ long sang String
    private java.sql.Time timeLesson; // Using java.sql.Time for time_lesson

    // Constructor
    public Lesson(String id, String nameLesson, long courseId, Timestamp createdDate, Timestamp updatedDate, long createdBy, long updatedBy, String content, String lessonId, java.sql.Time timeLesson) {
        this.id = id;
        this.nameLesson = nameLesson;
        this.courseId = courseId;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.content = content;
        this.lessonId = lessonId;
        this.timeLesson = timeLesson;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameLesson() {
        return nameLesson;
    }

    public void setNameLesson(String nameLesson) {
        this.nameLesson = nameLesson;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLessonId() {
        return lessonId;
    }

    public void setLessonId(String lessonId) {
        this.lessonId = lessonId;
    }

    public java.sql.Time getTimeLesson() {
        return timeLesson;
    }

    public void setTimeLesson(java.sql.Time timeLesson) {
        this.timeLesson = timeLesson;
    }

    // equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lesson lesson = (Lesson) o;
        return Objects.equals(id, lesson.id) &&
                courseId == lesson.courseId &&
                createdBy == lesson.createdBy &&
                updatedBy == lesson.updatedBy &&
                Objects.equals(nameLesson, lesson.nameLesson) &&
                Objects.equals(createdDate, lesson.createdDate) &&
                Objects.equals(updatedDate, lesson.updatedDate) &&
                Objects.equals(content, lesson.content) &&
                Objects.equals(lessonId, lesson.lessonId) &&
                Objects.equals(timeLesson, lesson.timeLesson);
    }

    // hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(id, nameLesson, courseId, createdDate, updatedDate, createdBy, updatedBy, content, lessonId, timeLesson);
    }

    // compareTo method
    @Override
    public int compareTo(Lesson o) {
        // Compare by id
        return this.id.compareTo(o.id);
    }
}
