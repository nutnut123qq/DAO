package model;

import java.sql.Timestamp;
import java.util.Objects;

public class UserInfor implements Comparable<UserInfor> {

    private String id;
    private String phone;
    private String address;
    private String fullName;
    private Timestamp birthDay;
    private boolean sex;
    private String avatar;
    private long userId;
    private Timestamp createdDate;
    private Timestamp updatedDate;
    private long createdBy;
    private long updatedBy;

    // Constructors
    public UserInfor(String id, String phone, String address, String fullName, Timestamp birthDay, boolean sex, String avatar, long userId, Timestamp createdDate, Timestamp updatedDate, long createdBy, long updatedBy) {
        this.id = id;
        this.phone = phone;
        this.address = address;
        this.fullName = fullName;
        this.birthDay = birthDay;
        this.sex = sex;
        this.avatar = avatar;
        this.userId = userId;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Timestamp getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Timestamp birthDay) {
        this.birthDay = birthDay;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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

    // equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfor userInfor = (UserInfor) o;
        return Objects.equals(id, userInfor.id) &&
                Objects.equals(phone, userInfor.phone) &&
                Objects.equals(address, userInfor.address) &&
                Objects.equals(fullName, userInfor.fullName) &&
                Objects.equals(birthDay, userInfor.birthDay) &&
                sex == userInfor.sex &&
                Objects.equals(avatar, userInfor.avatar) &&
                userId == userInfor.userId &&
                Objects.equals(createdDate, userInfor.createdDate) &&
                Objects.equals(updatedDate, userInfor.updatedDate) &&
                createdBy == userInfor.createdBy &&
                updatedBy == userInfor.updatedBy;
    }

    // hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(id, phone, address, fullName, birthDay, sex, avatar, userId, createdDate, updatedDate, createdBy, updatedBy);
    }

    // compareTo method
    @Override
    public int compareTo(UserInfor o) {
        // Compare by full name
        return this.fullName.compareTo(o.fullName);
    }
}
