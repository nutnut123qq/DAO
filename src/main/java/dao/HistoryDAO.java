package dao;

import dbconnect.DBConnect;
import model.History;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistoryDAO {

    private Connection connection;

    // Constructor to initialize the connection using DBConnect
    public HistoryDAO() {
        DBConnect dbConnect = new DBConnect();
        this.connection = dbConnect.getConnection(); // Get the connection from DBConnect
    }

    // Create a new record in the database
    public void createHistory(History history) {
        String sql = "INSERT INTO tblHistory (ipAddress, updatedDate, createdDate, type, mappingId) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, history.getIpAddress());
            pstmt.setTimestamp(2, history.getUpdatedDate());
            pstmt.setTimestamp(3, history.getCreatedDate());
            pstmt.setString(4, history.getType());
            pstmt.setInt(5, history.getMappingId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Retrieve a record by ID
    public History getHistoryById(String id) {
        String sql = "SELECT * FROM tblHistory WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToTblHistory(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Retrieve all records
    public List<History> getAllHistories() {
        List<History> histories = new ArrayList<>();
        String sql = "SELECT * FROM tblHistory";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                histories.add(mapResultSetToTblHistory(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return histories;
    }

    // Update a record
    public void updateHistory(History history) {
        String sql = "UPDATE tblHistory SET ipAddress = ?, updatedDate = ?, createdDate = ?, type = ?, mappingId = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, history.getIpAddress());
            pstmt.setTimestamp(2, history.getUpdatedDate());
            pstmt.setTimestamp(3, history.getCreatedDate());
            pstmt.setString(4, history.getType());
            pstmt.setInt(5, history.getMappingId());
            pstmt.setString(6, history.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete a record by ID
    public void deleteHistory(String id) {
        String sql = "DELETE FROM tblHistory WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Map ResultSet to TblHistory object
    private History mapResultSetToTblHistory(ResultSet rs) throws SQLException {
        String id = rs.getString("id");
        String ipAddress = rs.getString("ipAddress");
        Timestamp updatedDate = rs.getTimestamp("updatedDate");
        Timestamp createdDate = rs.getTimestamp("createdDate");
        String type = rs.getString("type");
        int mappingId = rs.getInt("mappingId");
        return new History(id, ipAddress, updatedDate, createdDate, type, mappingId);
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
