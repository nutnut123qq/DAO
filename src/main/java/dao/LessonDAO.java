package dao;

import dbconnect.DBConnect;
import model.Lesson;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LessonDAO {
    private Connection connection;

    // Constructor to initialize the connection using DBConnect
    public LessonDAO() {
        DBConnect dbConnect = new DBConnect();
        this.connection = dbConnect.getConnection(); // Get the connection from DBConnect
    }

    // Create a new record in the database
    public void createLesson(Lesson lesson) {
        String sql = "INSERT INTO tblLessons (nameLesson, courseId, createdDate, updatedDate, createdBy, updatedBy, content, lessonId, timeLesson) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, lesson.getNameLesson());
            pstmt.setLong(2, lesson.getCourseId());
            pstmt.setTimestamp(3, lesson.getCreatedDate());
            pstmt.setTimestamp(4, lesson.getUpdatedDate());
            pstmt.setLong(5, lesson.getCreatedBy());
            pstmt.setLong(6, lesson.getUpdatedBy());
            pstmt.setString(7, lesson.getContent());
            pstmt.setString(8, lesson.getLessonId());
            pstmt.setTime(9, lesson.getTimeLesson());

            pstmt.executeUpdate();

            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Retrieve a record by ID
    public Lesson getLessonById(String id) {
        String sql = "SELECT * FROM tblLessons WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToLesson(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Retrieve all records
    public List<Lesson> getAllLessons() {
        List<Lesson> lessons = new ArrayList<>();
        String sql = "SELECT * FROM tblLessons";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lessons.add(mapResultSetToLesson(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lessons;
    }

    // Update a record
    public void updateLesson(Lesson lesson) {
        String sql = "UPDATE tblLessons SET nameLesson = ?, courseId = ?, createdDate = ?, updatedDate = ?, createdBy = ?, updatedBy = ?, content = ?, lessonId = ?, timeLesson = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, lesson.getNameLesson());
            pstmt.setLong(2, lesson.getCourseId());
            pstmt.setTimestamp(3, lesson.getCreatedDate());
            pstmt.setTimestamp(4, lesson.getUpdatedDate());
            pstmt.setLong(5, lesson.getCreatedBy());
            pstmt.setLong(6, lesson.getUpdatedBy());
            pstmt.setString(7, lesson.getContent());
            pstmt.setString(8, lesson.getLessonId());
            pstmt.setTime(9, lesson.getTimeLesson());
            pstmt.setString(10, lesson.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete a record by ID
    public void deleteLesson(String id) {
        String sql = "DELETE FROM tblLessons WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Map ResultSet to Lesson object
    private Lesson mapResultSetToLesson(ResultSet rs) throws SQLException {
        String id = rs.getString("id");
        String nameLesson = rs.getString("nameLesson");
        long courseId = rs.getLong("courseId");
        Timestamp createdDate = rs.getTimestamp("createdDate");
        Timestamp updatedDate = rs.getTimestamp("updatedDate");
        long createdBy = rs.getLong("createdBy");
        long updatedBy = rs.getLong("updatedBy");
        String content = rs.getString("content");
        String lessonId = rs.getString("lessonId");
        java.sql.Time timeLesson = rs.getTime("timeLesson");

        return new Lesson(id, nameLesson, courseId, createdDate, updatedDate, createdBy, updatedBy, content, lessonId, timeLesson);
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
