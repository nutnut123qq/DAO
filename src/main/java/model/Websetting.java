package model;

import java.sql.Timestamp;
import java.util.Objects;

public class Websetting implements Comparable<Websetting> {

    private String id;
    private String content;
    private long updatedBy;
    private Timestamp updatedDate;
    private Timestamp createdDate;
    private long createdBy;
    private String status;
    private String type;

    // Constructors
    public Websetting() {
    }

    public Websetting(String id, String content, long updatedBy, Timestamp updatedDate, Timestamp createdDate, long createdBy, String status, String type) {
        this.id = id;
        this.content = content;
        this.updatedBy = updatedBy;
        this.updatedDate = updatedDate;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.status = status;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
        Websetting that = (Websetting) o;
        return Objects.equals(id, that.id)
                && updatedBy == that.updatedBy
                && createdBy == that.createdBy
                && Objects.equals(content, that.content)
                && Objects.equals(updatedDate, that.updatedDate)
                && Objects.equals(createdDate, that.createdDate)
                && Objects.equals(status, that.status)
                && Objects.equals(type, that.type);
    }

    // hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(id, content, updatedBy, updatedDate, createdDate, createdBy, status, type);
    }

    // compareTo method
    @Override
    public int compareTo(Websetting o) {
        // Compare by createdDate timestamp
        return this.createdDate.compareTo(o.createdDate);
    }
}
