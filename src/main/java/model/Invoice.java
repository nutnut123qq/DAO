package model;

import java.sql.Timestamp;
import java.util.Objects;

public class Invoice implements Comparable<Invoice> {

    private long id;
    private Timestamp purchDate;
    private long userId;
    private boolean orderStatus;

    // Constructors
    public Invoice(long id, Timestamp purchDate, long userId, boolean orderStatus) {
        this.id = id;
        this.purchDate = purchDate;
        this.userId = userId;
        this.orderStatus = orderStatus;
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Timestamp getPurchDate() {
        return purchDate;
    }

    public void setPurchDate(Timestamp purchDate) {
        this.purchDate = purchDate;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public boolean isOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(boolean orderStatus) {
        this.orderStatus = orderStatus;
    }

    // equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return id == invoice.id &&
                userId == invoice.userId &&
                orderStatus == invoice.orderStatus &&
                Objects.equals(purchDate, invoice.purchDate);
    }

    // hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(id, purchDate, userId, orderStatus);
    }

    // compareTo method
    @Override
    public int compareTo(Invoice o) {
        // Compare by purchase date
        return this.purchDate.compareTo(o.purchDate);
    }
}
