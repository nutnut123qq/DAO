package model;

import java.util.Objects;

public class Lecture implements Comparable<Lecture> {

    private String id; // Chuyển từ long sang String
    private String name;
    private String email;
    private String address;
    private String description;
    private String subject;

    // Constructors
    public Lecture(String id, String name, String email, String address, String description, String subject) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.description = description;
        this.subject = subject;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    // equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lecture lecture = (Lecture) o;
        return Objects.equals(id, lecture.id) &&
                Objects.equals(name, lecture.name) &&
                Objects.equals(email, lecture.email) &&
                Objects.equals(address, lecture.address) &&
                Objects.equals(description, lecture.description) &&
                Objects.equals(subject, lecture.subject);
    }

    // hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, address, description, subject);
    }

    // compareTo method
    @Override
    public int compareTo(Lecture o) {
        // Compare by name
        return this.name.compareTo(o.name);
    }
}
