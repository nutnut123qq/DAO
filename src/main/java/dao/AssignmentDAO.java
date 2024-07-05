package dao;

import dbconnect.DBConnect;
import model.Assignment;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AssignmentDAO {
    private Connection connection;

    // Constructor to initialize the connection using DBConnect
    public AssignmentDAO() {
        DBConnect dbConnect = new DBConnect();
        this.connection = dbConnect.getConnection(); // Get the connection from DBConnect
    }

    // Create a new record in the database
    public void createAssignment(Assignment assignment) {
        String sql = "INSERT INTO tblAssignments (id_course, title, description, deadline, fileURL, updatedBy, updatedDate, createdDate, createdBy) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setLong(1, assignment.getIdCourse());
            pstmt.setString(2, assignment.getTitle());
            pstmt.setString(3, assignment.getDescription());
            pstmt.setTimestamp(4, assignment.getDeadline());
            pstmt.setString(5, assignment.getFileURL());
            pstmt.setLong(6, assignment.getUpdatedBy());
            pstmt.setTimestamp(7, assignment.getUpdatedDate());
            pstmt.setTimestamp(8, assignment.getCreatedDate());
            pstmt.setLong(9, assignment.getCreatedBy());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Retrieve a record by ID
    public Assignment getAssignmentById(String id) {
        String sql = "SELECT * FROM tblAssignments WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToAssignment(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Retrieve all records
    public List<Assignment> getAllAssignments() {
        List<Assignment> assignments = new ArrayList<>();
        String sql = "SELECT * FROM tblAssignments";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                assignments.add(mapResultSetToAssignment(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return assignments;
    }

    // Update a record
    public void updateAssignment(Assignment assignment) {
        String sql = "UPDATE tblAssignments SET id_course = ?, title = ?, description = ?, deadline = ?, fileURL = ?, updatedBy = ?, updatedDate = ?, createdDate = ?, createdBy = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setLong(1, assignment.getIdCourse());
            pstmt.setString(2, assignment.getTitle());
            pstmt.setString(3, assignment.getDescription());
            pstmt.setTimestamp(4, assignment.getDeadline());
            pstmt.setString(5, assignment.getFileURL());
            pstmt.setLong(6, assignment.getUpdatedBy());
            pstmt.setTimestamp(7, assignment.getUpdatedDate());
            pstmt.setTimestamp(8, assignment.getCreatedDate());
            pstmt.setLong(9, assignment.getCreatedBy());
            pstmt.setString(10, assignment.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete a record by ID
    public void deleteAssignment(String id) {
        String sql = "DELETE FROM tblAssignments WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Map ResultSet to Assignment object
    private Assignment mapResultSetToAssignment(ResultSet rs) throws SQLException {
        String id = rs.getString("id");
        long idCourse = rs.getLong("id_course");
        String title = rs.getString("title");
        String description = rs.getString("description");
        Timestamp deadline = rs.getTimestamp("deadline");
        String fileURL = rs.getString("fileURL");
        long updatedBy = rs.getLong("updatedBy");
        Timestamp updatedDate = rs.getTimestamp("updatedDate");
        Timestamp createdDate = rs.getTimestamp("createdDate");
        long createdBy = rs.getLong("createdBy");
        return new Assignment(id, idCourse, title, description, deadline, fileURL, updatedBy, updatedDate, createdDate, createdBy);
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
