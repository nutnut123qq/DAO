package dao;

import dbconnect.DBConnect;
import model.Cart;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDAO {
    private Connection connection;

    // Constructor to initialize the connection using DBConnect
    public CartDAO() {
        DBConnect dbConnect = new DBConnect();
        this.connection = dbConnect.getConnection(); // Get the connection from DBConnect
    }

    // Create a new record in the database
    public void createCart(Cart cart) {
        String sql = "INSERT INTO tblCart (id_user, id_course, addedAt, updatedBy, updatedDate, createdDate, createdBy) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setLong(1, cart.getIdUser());
            pstmt.setLong(2, cart.getIdCourse());
            pstmt.setTimestamp(3, cart.getAddedAt());
            pstmt.setLong(4, cart.getUpdatedBy());
            pstmt.setTimestamp(5, cart.getUpdatedDate());
            pstmt.setTimestamp(6, cart.getCreatedDate());
            pstmt.setLong(7, cart.getCreatedBy());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Retrieve a record by ID
    public Cart getCartById(String id) {
        String sql = "SELECT * FROM tblCart WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToCart(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Retrieve all records
    public List<Cart> getAllCarts() {
        List<Cart> carts = new ArrayList<>();
        String sql = "SELECT * FROM tblCart";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                carts.add(mapResultSetToCart(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carts;
    }

    // Update a record
    public void updateCart(Cart cart) {
        String sql = "UPDATE tblCart SET id_user = ?, id_course = ?, addedAt = ?, updatedBy = ?, updatedDate = ?, createdDate = ?, createdBy = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setLong(1, cart.getIdUser());
            pstmt.setLong(2, cart.getIdCourse());
            pstmt.setTimestamp(3, cart.getAddedAt());
            pstmt.setLong(4, cart.getUpdatedBy());
            pstmt.setTimestamp(5, cart.getUpdatedDate());
            pstmt.setTimestamp(6, cart.getCreatedDate());
            pstmt.setLong(7, cart.getCreatedBy());
            pstmt.setString(8, cart.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete a record by ID
    public void deleteCart(String id) {
        String sql = "DELETE FROM tblCart WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Map ResultSet to Cart object
    private Cart mapResultSetToCart(ResultSet rs) throws SQLException {
        String id = rs.getString("id");
        long idUser = rs.getLong("id_user");
        long idCourse = rs.getLong("id_course");
        Timestamp addedAt = rs.getTimestamp("addedAt");
        long updatedBy = rs.getLong("updatedBy");
        Timestamp updatedDate = rs.getTimestamp("updatedDate");
        Timestamp createdDate = rs.getTimestamp("createdDate");
        long createdBy = rs.getLong("createdBy");
        return new Cart(id, idUser, idCourse, addedAt, updatedBy, updatedDate, createdDate, createdBy);
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
