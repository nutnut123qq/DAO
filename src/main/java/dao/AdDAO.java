package dao;

import dbconnect.DBConnect;
import model.Ad;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdDAO {
    private Connection connection;

    // Constructor to initialize the connection using DBConnect
    public AdDAO() {
        DBConnect dbConnect = new DBConnect();
        this.connection = dbConnect.getConnection(); // Get the connection from DBConnect
    }

    // Create a new record in the database
    public void createAd(Ad ad) {
        String sql = "INSERT INTO tblAds (img, description, link, position, status, createdDate, updatedDate, createdBy, updateBy, width, height) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, ad.getImg());
            pstmt.setString(2, ad.getDescription());
            pstmt.setString(3, ad.getLink());
            pstmt.setInt(4, ad.getPosition());
            pstmt.setString(5, ad.getStatus());
            pstmt.setTimestamp(6, ad.getCreatedDate());
            pstmt.setTimestamp(7, ad.getUpdatedDate());
            pstmt.setLong(8, ad.getCreatedBy());
            pstmt.setLong(9, ad.getUpdateBy());
            pstmt.setFloat(10, ad.getWidth());
            pstmt.setFloat(11, ad.getHeight());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Retrieve a record by ID
    public Ad getAdById(long id) {
        String sql = "SELECT * FROM tblAds WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToAd(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Retrieve all records
    public List<Ad> getAllAds() {
        List<Ad> ads = new ArrayList<>();
        String sql = "SELECT * FROM tblAds";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                ads.add(mapResultSetToAd(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ads;
    }

    // Update a record
    public void updateAd(Ad ad) {
        String sql = "UPDATE tblAds SET img = ?, description = ?, link = ?, position = ?, status = ?, createdDate = ?, updatedDate = ?, createdBy = ?, updateBy = ?, width = ?, height = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, ad.getImg());
            pstmt.setString(2, ad.getDescription());
            pstmt.setString(3, ad.getLink());
            pstmt.setInt(4, ad.getPosition());
            pstmt.setString(5, ad.getStatus());
            pstmt.setTimestamp(6, ad.getCreatedDate());
            pstmt.setTimestamp(7, ad.getUpdatedDate());
            pstmt.setLong(8, ad.getCreatedBy());
            pstmt.setLong(9, ad.getUpdateBy());
            pstmt.setFloat(10, ad.getWidth());
            pstmt.setFloat(11, ad.getHeight());
            pstmt.setLong(12, ad.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete a record by ID
    public void deleteAd(long id) {
        String sql = "DELETE FROM tblAds WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Map ResultSet to Ad object
    private Ad mapResultSetToAd(ResultSet rs) throws SQLException {
        long id = rs.getLong("id");
        String img = rs.getString("img");
        String description = rs.getString("description");
        String link = rs.getString("link");
        int position = rs.getInt("position");
        String status = rs.getString("status");
        Timestamp createdDate = rs.getTimestamp("createdDate");
        Timestamp updatedDate = rs.getTimestamp("updatedDate");
        long createdBy = rs.getLong("createdBy");
        long updateBy = rs.getLong("updateBy");
        float width = rs.getFloat("width");
        float height = rs.getFloat("height");
        return new Ad(id, img, description, link, position, status, createdDate, updatedDate, createdBy, updateBy, width, height);
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
