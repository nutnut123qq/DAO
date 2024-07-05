package model;

import java.util.Objects;

public class DetailInvoice implements Comparable<DetailInvoice> {

    private String id;
    private long invoiceId;
    private long courseId;
    private int quantityPurchased;

    // Constructor
    public DetailInvoice(String id, long invoiceId, long courseId, int quantityPurchased) {
        this.id = id;
        this.invoiceId = invoiceId;
        this.courseId = courseId;
        this.quantityPurchased = quantityPurchased;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public int getQuantityPurchased() {
        return quantityPurchased;
    }

    public void setQuantityPurchased(int quantityPurchased) {
        this.quantityPurchased = quantityPurchased;
    }

    // equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetailInvoice that = (DetailInvoice) o;
        return Objects.equals(id, that.id) &&
                invoiceId == that.invoiceId &&
                courseId == that.courseId &&
                quantityPurchased == that.quantityPurchased;
    }

    // hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(id, invoiceId, courseId, quantityPurchased);
    }
    
    // compareTo method
    @Override
    public int compareTo(DetailInvoice other) {
        // Compare by id
        return this.id.compareTo(other.id);
    }
}
