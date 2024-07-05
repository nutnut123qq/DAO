package dao;

import dbconnect.DBConnect;
import model.UserInfor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserInforDAO {
    private Connection connection;

    // Constructor to initialize the connection using DBConnect
    public UserInforDAO() {
        DBConnect dbConnect = new DBConnect();
        this.connection = dbConnect.getConnection(); // Get the connection from DBConnect
    }

    // Create a new record in the database
    public void createUserInfor(UserInfor userInfor) {
        String sql = "INSERT INTO tblUserInfor (phone, address, fullName, birthDay, sex, avatar, userId, createdDate, updatedDate, createdBy, updatedBy) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, userInfor.getPhone());
            pstmt.setString(2, userInfor.getAddress());
            pstmt.setString(3, userInfor.getFullName());
            pstmt.setTimestamp(4, userInfor.getBirthDay());
            pstmt.setBoolean(5, userInfor.isSex());
            pstmt.setString(6, userInfor.getAvatar());
            pstmt.setLong(7, userInfor.getUserId());
            pstmt.setTimestamp(8, userInfor.getCreatedDate());
            pstmt.setTimestamp(9, userInfor.getUpdatedDate());
            pstmt.setLong(10, userInfor.getCreatedBy());
            pstmt.setLong(11, userInfor.getUpdatedBy());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Retrieve a record by ID
    public UserInfor getUserInforById(String id) {
        String sql = "SELECT * FROM tblUserInfor WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToUserInfor(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Retrieve all records
    public List<UserInfor> getAllUserInfors() {
        List<UserInfor> userInfors = new ArrayList<>();
        String sql = "SELECT * FROM tblUserInfor";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                userInfors.add(mapResultSetToUserInfor(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userInfors;
    }

    // Update a record
    public void updateUserInfor(UserInfor userInfor) {
        String sql = "UPDATE tblUserInfor SET phone = ?, address = ?, fullName = ?, birthDay = ?, sex = ?, avatar = ?, userId = ?, createdDate = ?, updatedDate = ?, createdBy = ?, updatedBy = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, userInfor.getPhone());
            pstmt.setString(2, userInfor.getAddress());
            pstmt.setString(3, userInfor.getFullName());
            pstmt.setTimestamp(4, userInfor.getBirthDay());
            pstmt.setBoolean(5, userInfor.isSex());
            pstmt.setString(6, userInfor.getAvatar());
            pstmt.setLong(7, userInfor.getUserId());
            pstmt.setTimestamp(8, userInfor.getCreatedDate());
            pstmt.setTimestamp(9, userInfor.getUpdatedDate());
            pstmt.setLong(10, userInfor.getCreatedBy());
            pstmt.setLong(11, userInfor.getUpdatedBy());
            pstmt.setString(12, userInfor.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete a record by ID
    public void deleteUserInfor(String id) {
        String sql = "DELETE FROM tblUserInfor WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Map ResultSet to UserInfor object
    private UserInfor mapResultSetToUserInfor(ResultSet rs) throws SQLException {
        String id = rs.getString("id");
        String phone = rs.getString("phone");
        String address = rs.getString("address");
        String fullName = rs.getString("fullName");
        Timestamp birthDay = rs.getTimestamp("birthDay");
        boolean sex = rs.getBoolean("sex");
        String avatar = rs.getString("avatar");
        long userId = rs.getLong("userId");
        Timestamp createdDate = rs.getTimestamp("createdDate");
        Timestamp updatedDate = rs.getTimestamp("updatedDate");
        long createdBy = rs.getLong("createdBy");
        long updatedBy = rs.getLong("updatedBy");

        return new UserInfor(id, phone, address, fullName, birthDay, sex, avatar, userId, createdDate, updatedDate, createdBy, updatedBy);
    }

    // Close the connection
    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Connection closed successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
