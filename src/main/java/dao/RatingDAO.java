package dao;

import model.Rating;
import dbconnect.DBConnect;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RatingDAO {
    private Connection connection;

    // Constructor to initialize the connection using DBConnect
    public RatingDAO() {
        DBConnect dbConnect = new DBConnect();
        this.connection = dbConnect.getConnection(); // Get the connection from DBConnect
    }

    // Create a new record in the database
    public void createRating(Rating rating) {
        String sql = "INSERT INTO tblRating (comment, website, updatedBy, updatedDate, ratedBy, ratedAt, id_course, numberStar) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, rating.getComment());
            pstmt.setString(2, rating.getWebsite());
            pstmt.setLong(3, rating.getUpdatedBy());
            pstmt.setTimestamp(4, rating.getUpdatedDate());
            pstmt.setLong(5, rating.getRatedBy());
            pstmt.setTimestamp(6, rating.getRatedAt());
            pstmt.setLong(7, rating.getIdCourse());
            pstmt.setByte(8, rating.getNumberStar());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Retrieve a record by ID
    public Rating getRatingById(String id) {
        String sql = "SELECT * FROM tblRating WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToRating(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Retrieve all records
    public List<Rating> getAllRatings() {
        List<Rating> ratings = new ArrayList<>();
        String sql = "SELECT * FROM tblRating";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                ratings.add(mapResultSetToRating(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ratings;
    }

    // Update a record
    public void updateRating(Rating rating) {
        String sql = "UPDATE tblRating SET comment = ?, website = ?, updatedBy = ?, updatedDate = ?, ratedBy = ?, ratedAt = ?, id_course = ?, numberStar = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, rating.getComment());
            pstmt.setString(2, rating.getWebsite());
            pstmt.setLong(3, rating.getUpdatedBy());
            pstmt.setTimestamp(4, rating.getUpdatedDate());
            pstmt.setLong(5, rating.getRatedBy());
            pstmt.setTimestamp(6, rating.getRatedAt());
            pstmt.setLong(7, rating.getIdCourse());
            pstmt.setByte(8, rating.getNumberStar());
            pstmt.setString(9, rating.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete a record by ID
    public void deleteRating(String id) {
        String sql = "DELETE FROM tblRating WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Map ResultSet to Rating object
    private Rating mapResultSetToRating(ResultSet rs) throws SQLException {
        String id = rs.getString("id");
        String comment = rs.getString("comment");
        String website = rs.getString("website");
        long updatedBy = rs.getLong("updatedBy");
        Timestamp updatedDate = rs.getTimestamp("updatedDate");
        long ratedBy = rs.getLong("ratedBy");
        Timestamp ratedAt = rs.getTimestamp("ratedAt");
        long idCourse = rs.getLong("id_course");
        byte numberStar = rs.getByte("numberStar");
        return new Rating(id, comment, website, updatedBy, updatedDate, ratedBy, ratedAt, idCourse, numberStar);
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
