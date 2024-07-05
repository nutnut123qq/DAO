package dao;

import model.Websetting;
import dbconnect.DBConnect;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WebsettingDAO {
    private DBConnect dbConnect;

    // Constructor to initialize the DBConnect instance
    public WebsettingDAO() {
        this.dbConnect = new DBConnect();
    }

    // Get a connection from DBConnect
    private Connection getConnection() throws SQLException {
        return dbConnect.getConnection(); // Use the method to get a connection from DBConnect
    }

    // Insert a new Websetting
    public void insert(Websetting websetting) {
        String sql = "INSERT INTO tblWebsetting (content, updatedBy, updatedDate, createdDate, createdBy, status, type) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, websetting.getContent());
            stmt.setLong(2, websetting.getUpdatedBy());
            stmt.setTimestamp(3, new Timestamp(websetting.getUpdatedDate().getTime())); // Changed to Timestamp
            stmt.setTimestamp(4, new Timestamp(websetting.getCreatedDate().getTime())); // Changed to Timestamp
            stmt.setLong(5, websetting.getCreatedBy());
            stmt.setString(6, websetting.getStatus());
            stmt.setString(7, websetting.getType());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete a Websetting by ID
    public void delete(long id) {
        String sql = "DELETE FROM tblWebsetting WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update an existing Websetting
    public void update(Websetting websetting) {
        String sql = "UPDATE tblWebsetting SET content = ?, updatedBy = ?, updatedDate = ?, createdDate = ?, createdBy = ?, status = ?, type = ? WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, websetting.getContent());
            stmt.setLong(2, websetting.getUpdatedBy());
            stmt.setTimestamp(3, new Timestamp(websetting.getUpdatedDate().getTime())); // Changed to Timestamp
            stmt.setTimestamp(4, new Timestamp(websetting.getCreatedDate().getTime())); // Changed to Timestamp
            stmt.setLong(5, websetting.getCreatedBy());
            stmt.setString(6, websetting.getStatus());
            stmt.setString(7, websetting.getType());
            stmt.setLong(8, websetting.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Retrieve all Websettings
    public List<Websetting> getAll() {
        List<Websetting> websettings = new ArrayList<>();
        String sql = "SELECT * FROM tblWebsetting";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Websetting websetting = new Websetting();
                websetting.setId(rs.getLong("id"));
                websetting.setContent(rs.getString("content"));
                websetting.setUpdatedBy(rs.getLong("updatedBy"));
                websetting.setUpdatedDate(rs.getTimestamp("updatedDate")); // Changed to Timestamp
                websetting.setCreatedDate(rs.getTimestamp("createdDate")); // Changed to Timestamp
                websetting.setCreatedBy(rs.getLong("createdBy"));
                websetting.setStatus(rs.getString("status"));
                websetting.setType(rs.getString("type"));
                websettings.add(websetting);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return websettings;
    }

    // Retrieve a Websetting by ID
    public Websetting getById(long id) {
        Websetting websetting = null;
        String sql = "SELECT * FROM tblWebsetting WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    websetting = new Websetting();
                    websetting.setId(rs.getLong("id"));
                    websetting.setContent(rs.getString("content"));
                    websetting.setUpdatedBy(rs.getLong("updatedBy"));
                    websetting.setUpdatedDate(rs.getTimestamp("updatedDate")); // Changed to Timestamp
                    websetting.setCreatedDate(rs.getTimestamp("createdDate")); // Changed to Timestamp
                    websetting.setCreatedBy(rs.getLong("createdBy"));
                    websetting.setStatus(rs.getString("status"));
                    websetting.setType(rs.getString("type"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return websetting;
    }

    // Close the connection
    public void close() {
        dbConnect.closeConnection(); // Close connection using DBConnect
    }
}
