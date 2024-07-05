package dao;

import dbconnect.DBConnect;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private Connection connection;

    // Constructor to initialize the connection using DBConnect
    public UserDAO() {
        DBConnect dbConnect = new DBConnect();
        this.connection = dbConnect.getConnection(); // Get the connection from DBConnect
    }

    // Create a new record in the database
    public void createUser(User user) {
        String sql = "INSERT INTO tblUsers (username, email, password, role, status, createdDate, updatedDate, createdBy, updatedBy, img) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getRole());
            pstmt.setString(5, user.getStatus());
            pstmt.setTimestamp(6, user.getCreatedDate());
            pstmt.setTimestamp(7, user.getUpdatedDate());
            pstmt.setString(8, user.getCreatedBy());
            pstmt.setString(9, user.getUpdatedBy());
            pstmt.setString(10, user.getImg());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Retrieve a record by ID
    public User getUserById(String id) {
        String sql = "SELECT * FROM tblUsers WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToUser(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Retrieve all records
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM tblUsers";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                users.add(mapResultSetToUser(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    // Update a record
    public void updateUser(User user) {
        String sql = "UPDATE tblUsers SET username = ?, email = ?, password = ?, role = ?, status = ?, createdDate = ?, updatedDate = ?, createdBy = ?, updatedBy = ?, img = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getRole());
            pstmt.setString(5, user.getStatus());
            pstmt.setTimestamp(6, user.getCreatedDate());
            pstmt.setTimestamp(7, user.getUpdatedDate());
            pstmt.setString(8, user.getCreatedBy());
            pstmt.setString(9, user.getUpdatedBy());
            pstmt.setString(10, user.getImg());
            // Assuming id in User class is now String
            pstmt.setString(11, user.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete a record by ID
    public void deleteUser(String id) {
        String sql = "DELETE FROM tblUsers WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Map ResultSet to User object
    private User mapResultSetToUser(ResultSet rs) throws SQLException {
        String id = rs.getString("id");
        String username = rs.getString("username");
        String email = rs.getString("email");
        String password = rs.getString("password");
        String role = rs.getString("role");
        String status = rs.getString("status");
        Timestamp createdDate = rs.getTimestamp("createdDate");
        Timestamp updatedDate = rs.getTimestamp("updatedDate");
        String createdBy = rs.getString("createdBy");
        String updatedBy = rs.getString("updatedBy");
        String img = rs.getString("img");

        return new User(id, username, email, password, role, status, createdDate, updatedDate, createdBy, updatedBy, img);
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
