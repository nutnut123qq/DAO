package dao;

import dbconnect.DBConnect;
import model.Comment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentDAO {
    private Connection connection;

    // Constructor to initialize the connection using DBConnect
    public CommentDAO() {
        DBConnect dbConnect = new DBConnect();
        this.connection = dbConnect.getConnection(); // Get the connection from DBConnect
    }

    // Create a new record in the database
    public void createComment(Comment comment) {
        String sql = "INSERT INTO tblComments (name, email, commentId, status, quantity, website, message, createdDate, updatedDate, createdBy, updatedBy, courseId) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, comment.getName());
            pstmt.setString(2, comment.getEmail());
            pstmt.setLong(3, comment.getCommentId());
            pstmt.setString(4, comment.getStatus());
            pstmt.setInt(5, comment.getQuantity());
            pstmt.setString(6, comment.getWebsite());
            pstmt.setString(7, comment.getMessage());
            pstmt.setTimestamp(8, comment.getCreatedDate());
            pstmt.setTimestamp(9, comment.getUpdatedDate());
            pstmt.setLong(10, comment.getCreatedBy());
            pstmt.setLong(11, comment.getUpdatedBy());
            pstmt.setLong(12, comment.getCourseId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Retrieve a record by ID
    public Comment getCommentById(long id) {
        String sql = "SELECT * FROM tblComments WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToComment(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Retrieve all records
    public List<Comment> getAllComments() {
        List<Comment> comments = new ArrayList<>();
        String sql = "SELECT * FROM tblComments";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                comments.add(mapResultSetToComment(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comments;
    }

    // Update a record
    public void updateComment(Comment comment) {
        String sql = "UPDATE tblComments SET name = ?, email = ?, commentId = ?, status = ?, quantity = ?, website = ?, message = ?, createdDate = ?, updatedDate = ?, createdBy = ?, updatedBy = ?, courseId = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, comment.getName());
            pstmt.setString(2, comment.getEmail());
            pstmt.setLong(3, comment.getCommentId());
            pstmt.setString(4, comment.getStatus());
            pstmt.setInt(5, comment.getQuantity());
            pstmt.setString(6, comment.getWebsite());
            pstmt.setString(7, comment.getMessage());
            pstmt.setTimestamp(8, comment.getCreatedDate());
            pstmt.setTimestamp(9, comment.getUpdatedDate());
            pstmt.setLong(10, comment.getCreatedBy());
            pstmt.setLong(11, comment.getUpdatedBy());
            pstmt.setLong(12, comment.getCourseId());
            pstmt.setLong(13, comment.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete a record by ID
    public void deleteComment(long id) {
        String sql = "DELETE FROM tblComments WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Map ResultSet to Comment object
    private Comment mapResultSetToComment(ResultSet rs) throws SQLException {
        long id = rs.getLong("id");
        String name = rs.getString("name");
        String email = rs.getString("email");
        long commentId = rs.getLong("commentId");
        String status = rs.getString("status");
        int quantity = rs.getInt("quantity");
        String website = rs.getString("website");
        String message = rs.getString("message");
        Timestamp createdDate = rs.getTimestamp("createdDate");
        Timestamp updatedDate = rs.getTimestamp("updatedDate");
        long createdBy = rs.getLong("createdBy");
        long updatedBy = rs.getLong("updatedBy");
        long courseId = rs.getLong("courseId");
        return new Comment(id, name, email, commentId, status, quantity, website, message, createdDate, updatedDate, createdBy, updatedBy, courseId);
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
