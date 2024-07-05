package dao;

import model.Messages;
import dbconnect.DBConnect;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessagesDAO {
    private Connection connection;

    // Constructor to initialize the connection using DBConnect
    public MessagesDAO() {
        DBConnect dbConnect = new DBConnect();
        this.connection = dbConnect.getConnection(); // Get the connection from DBConnect
    }

    // Create a new record in the database
    public void createMessage(Messages message) {
        String sql = "INSERT INTO tblMessages (subject, email, description, updatedBy, updatedDate, createdDate, createdBy, message) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, message.getSubject());
            pstmt.setString(2, message.getEmail());
            pstmt.setString(3, message.getDescription());
            pstmt.setLong(4, message.getUpdatedBy());
            pstmt.setTimestamp(5, message.getUpdatedDate());
            pstmt.setTimestamp(6, message.getCreatedDate());
            pstmt.setLong(7, message.getCreatedBy());
            pstmt.setString(8, message.getMessage());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Retrieve a record by ID
    public Messages getMessageById(long id) {
        String sql = "SELECT * FROM tblMessages WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToMessage(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Retrieve all records
    public List<Messages> getAllMessages() {
        List<Messages> messages = new ArrayList<>();
        String sql = "SELECT * FROM tblMessages";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                messages.add(mapResultSetToMessage(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messages;
    }

    // Update a record
    public void updateMessage(Messages message) {
        String sql = "UPDATE tblMessages SET subject = ?, email = ?, description = ?, updatedBy = ?, updatedDate = ?, createdDate = ?, createdBy = ?, message = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, message.getSubject());
            pstmt.setString(2, message.getEmail());
            pstmt.setString(3, message.getDescription());
            pstmt.setLong(4, message.getUpdatedBy());
            pstmt.setTimestamp(5, message.getUpdatedDate());
            pstmt.setTimestamp(6, message.getCreatedDate());
            pstmt.setLong(7, message.getCreatedBy());
            pstmt.setString(8, message.getMessage());
            pstmt.setLong(9, message.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete a record by ID
    public void deleteMessage(long id) {
        String sql = "DELETE FROM tblMessages WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Map ResultSet to Message object
    private Messages mapResultSetToMessage(ResultSet rs) throws SQLException {
        long id = rs.getLong("id");
        String subject = rs.getString("subject");
        String email = rs.getString("email");
        String description = rs.getString("description");
        long updatedBy = rs.getLong("updatedBy");
        Timestamp updatedDate = rs.getTimestamp("updatedDate");
        Timestamp createdDate = rs.getTimestamp("createdDate");
        long createdBy = rs.getLong("createdBy");
        String message = rs.getString("message");
        return new Messages(id, subject, email, description, updatedBy, updatedDate, createdDate, createdBy, message);
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
