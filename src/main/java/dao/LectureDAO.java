package dao;

import dbconnect.DBConnect;
import model.Lecture;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LectureDAO {
    private Connection connection;

    // Constructor to initialize the connection using DBConnect
    public LectureDAO() {
        DBConnect dbConnect = new DBConnect();
        this.connection = dbConnect.getConnection(); // Get the connection from DBConnect
    }

    // Create a new record in the database
    public void createLecture(Lecture lecture) {
        String sql = "INSERT INTO tblLectures (name, email, address, description, subject) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, lecture.getName());
            pstmt.setString(2, lecture.getEmail());
            pstmt.setString(3, lecture.getAddress());
            pstmt.setString(4, lecture.getDescription());
            pstmt.setString(5, lecture.getSubject());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Retrieve a record by ID
    public Lecture getLectureById(long id) {
        String sql = "SELECT * FROM tblLectures WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToLecture(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Retrieve all records
    public List<Lecture> getAllLectures() {
        List<Lecture> lectures = new ArrayList<>();
        String sql = "SELECT * FROM tblLectures";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lectures.add(mapResultSetToLecture(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lectures;
    }

    // Update a record
    public void updateLecture(Lecture lecture) {
        String sql = "UPDATE tblLectures SET name = ?, email = ?, address = ?, description = ?, subject = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, lecture.getName());
            pstmt.setString(2, lecture.getEmail());
            pstmt.setString(3, lecture.getAddress());
            pstmt.setString(4, lecture.getDescription());
            pstmt.setString(5, lecture.getSubject());
            pstmt.setLong(6, lecture.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete a record by ID
    public void deleteLecture(long id) {
        String sql = "DELETE FROM tblLectures WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Map ResultSet to Lecture object
    private Lecture mapResultSetToLecture(ResultSet rs) throws SQLException {
        long id = rs.getLong("id");
        String name = rs.getString("name");
        String email = rs.getString("email");
        String address = rs.getString("address");
        String description = rs.getString("description");
        String subject = rs.getString("subject");
        return new Lecture(id, name, email, address, description, subject);
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
