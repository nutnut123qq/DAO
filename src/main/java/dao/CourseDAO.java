package dao;

import dbconnect.DBConnect;
import model.Course;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {
    private Connection connection;

    // Constructor to initialize the connection using DBConnect
    public CourseDAO() {
        DBConnect dbConnect = new DBConnect();
        this.connection = dbConnect.getConnection(); // Get the connection from DBConnect
    }

    // Create a new course record in the database
    public void createCourse(Course course) {
        String sql = "INSERT INTO tblCourse (name, rate, price, lectureId, timeCourse, description, createdDate, updatedDate, createdBy, updatedBy, idCategory, img) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, course.getName());
            pstmt.setInt(2, course.getRate());
            pstmt.setFloat(3, course.getPrice());
            pstmt.setString(4, course.getLectureId());
            pstmt.setTimestamp(5, course.getTimeCourse());
            pstmt.setString(6, course.getDescription());
            pstmt.setTimestamp(7, course.getCreatedDate());
            pstmt.setTimestamp(8, course.getUpdatedDate());
            pstmt.setString(9, course.getCreatedBy());
            pstmt.setString(10, course.getUpdatedBy());
            pstmt.setString(11, course.getIdCategory());
            pstmt.setString(12, course.getImg());
            pstmt.executeUpdate();

            // Retrieve the generated ID
            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                course.setId(generatedKeys.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Retrieve a course record by ID
    public Course getCourseById(long id) {
        String sql = "SELECT * FROM tblCourse WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToCourse(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Retrieve all course records
    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM tblCourse";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                courses.add(mapResultSetToCourse(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    // Update a course record
    public void updateCourse(Course course) {
        String sql = "UPDATE tblCourse SET name = ?, rate = ?, price = ?, lectureId = ?, timeCourse = ?, description = ?, " +
                     "createdDate = ?, updatedDate = ?, createdBy = ?, updatedBy = ?, idCategory = ?, img = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, course.getName());
            pstmt.setInt(2, course.getRate());
            pstmt.setFloat(3, course.getPrice());
            pstmt.setString(4, course.getLectureId());
            pstmt.setTimestamp(5, course.getTimeCourse());
            pstmt.setString(6, course.getDescription());
            pstmt.setTimestamp(7, course.getCreatedDate());
            pstmt.setTimestamp(8, course.getUpdatedDate());
            pstmt.setString(9, course.getCreatedBy());
            pstmt.setString(10, course.getUpdatedBy());
            pstmt.setString(11, course.getIdCategory());
            pstmt.setString(12, course.getImg());
            pstmt.setLong(13, course.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete a course record by ID
    public void deleteCourse(long id) {
        String sql = "DELETE FROM tblCourse WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Map ResultSet to Course object
    private Course mapResultSetToCourse(ResultSet rs) throws SQLException {
        long id = rs.getLong("id");
        String name = rs.getString("name");
        int rate = rs.getInt("rate");
        float price = rs.getFloat("price");
        String lectureId = rs.getString("lectureId");
        Timestamp timeCourse = rs.getTimestamp("timeCourse");
        String description = rs.getString("description");
        Timestamp createdDate = rs.getTimestamp("createdDate");
        Timestamp updatedDate = rs.getTimestamp("updatedDate");
        String createdBy = rs.getString("createdBy");
        String updatedBy = rs.getString("updatedBy");
        String idCategory = rs.getString("idCategory");
        String img = rs.getString("img");
        return new Course(id, name, rate, price, lectureId, timeCourse, description, createdDate, updatedDate, createdBy, updatedBy, idCategory, img);
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
