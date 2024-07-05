package dao;

import dbconnect.DBConnect;
import model.Category;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    private Connection connection;

    // Constructor to initialize the connection using DBConnect
    public CategoryDAO() {
        DBConnect dbConnect = new DBConnect();
        this.connection = dbConnect.getConnection(); // Get the connection from DBConnect
    }

    // Create a new record in the database
    public void createCategory(Category category) {
        String sql = "INSERT INTO tblCategory (name, description, updatedBy, updatedDate, createdDate, createdBy, status, id_course) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, category.getName());
            pstmt.setString(2, category.getDescription());
            pstmt.setLong(3, category.getUpdatedBy());
            pstmt.setTimestamp(4, category.getUpdatedDate());
            pstmt.setTimestamp(5, category.getCreatedDate());
            pstmt.setLong(6, category.getCreatedBy());
            pstmt.setString(7, category.getStatus());
            pstmt.setLong(8, category.getIdCourse());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Retrieve a record by ID
    public Category getCategoryById(long id) {
        String sql = "SELECT * FROM tblCategory WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToCategory(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Retrieve all records
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT * FROM tblCategory";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                categories.add(mapResultSetToCategory(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    // Update a record
    public void updateCategory(Category category) {
        String sql = "UPDATE tblCategory SET name = ?, description = ?, updatedBy = ?, updatedDate = ?, createdDate = ?, createdBy = ?, status = ?, id_course = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, category.getName());
            pstmt.setString(2, category.getDescription());
            pstmt.setLong(3, category.getUpdatedBy());
            pstmt.setTimestamp(4, category.getUpdatedDate());
            pstmt.setTimestamp(5, category.getCreatedDate());
            pstmt.setLong(6, category.getCreatedBy());
            pstmt.setString(7, category.getStatus());
            pstmt.setLong(8, category.getIdCourse());
            pstmt.setLong(9, category.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete a record by ID
    public void deleteCategory(long id) {
        String sql = "DELETE FROM tblCategory WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Map ResultSet to Category object
    private Category mapResultSetToCategory(ResultSet rs) throws SQLException {
        long id = rs.getLong("id");
        String name = rs.getString("name");
        String description = rs.getString("description");
        long updatedBy = rs.getLong("updatedBy");
        Timestamp updatedDate = rs.getTimestamp("updatedDate");
        Timestamp createdDate = rs.getTimestamp("createdDate");
        long createdBy = rs.getLong("createdBy");
        String status = rs.getString("status");
        long idCourse = rs.getLong("id_course");
        return new Category(id, name, description, updatedBy, updatedDate, createdDate, createdBy, status, idCourse);
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
