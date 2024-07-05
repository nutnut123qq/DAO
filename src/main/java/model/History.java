package model;

import java.sql.Timestamp;
import java.util.Objects;

public class History implements Comparable<History> {

    private long id;
    private String ipAddress;
    private Timestamp updatedDate;
    private Timestamp createdDate;
    private String type;
    private int mappingId;

    // Default constructor
    public History() {
    }

    // Parameterized constructor
    public History(long id, String ipAddress, Timestamp updatedDate, Timestamp createdDate, String type, int mappingId) {
        this.id = id;
        this.ipAddress = ipAddress;
        this.updatedDate = updatedDate;
        this.createdDate = createdDate;
        this.type = type;
        this.mappingId = mappingId;
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getMappingId() {
        return mappingId;
    }

    public void setMappingId(int mappingId) {
        this.mappingId = mappingId;
    }

    @Override
    public String toString() {
        return "TblHistory{"
                + "id=" + id
                + ", ipAddress='" + ipAddress + '\''
                + ", updatedDate=" + updatedDate
                + ", createdDate=" + createdDate
                + ", type='" + type + '\''
                + ", mappingId=" + mappingId
                + '}';
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
        History history = (History) o;
        return id == history.id
                && mappingId == history.mappingId
                && Objects.equals(ipAddress, history.ipAddress)
                && Objects.equals(updatedDate, history.updatedDate)
                && Objects.equals(createdDate, history.createdDate)
                && Objects.equals(type, history.type);
    }

    // hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(id, ipAddress, updatedDate, createdDate, type, mappingId);
    }

    // compareTo method
    @Override
    public int compareTo(History o) {
        // Compare by updatedDate timestamp
        return this.updatedDate.compareTo(o.updatedDate);
    }
}
