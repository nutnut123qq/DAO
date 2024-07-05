package dao;

import dbconnect.DBConnect;
import model.DetailInvoice;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DetailInvoiceDAO {
    private Connection connection;

    // Constructor to initialize the connection using DBConnect
    public DetailInvoiceDAO() {
        DBConnect dbConnect = new DBConnect();
        this.connection = dbConnect.getConnection(); // Get the connection from DBConnect
    }

    // Create a new record in the database
    public void createDetailInvoice(DetailInvoice detailInvoice) {
        String sql = "INSERT INTO tblDetailInvoices (invoiceId, courseId, quantityPurchased) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setLong(1, detailInvoice.getInvoiceId());
            pstmt.setLong(2, detailInvoice.getCourseId());
            pstmt.setInt(3, detailInvoice.getQuantityPurchased());

            pstmt.executeUpdate();

            // Retrieve the generated ID
            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                detailInvoice.setId(generatedKeys.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Retrieve a record by ID
    public DetailInvoice getDetailInvoiceById(long id) {
        String sql = "SELECT * FROM tblDetailInvoices WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToDetailInvoice(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Retrieve all records
    public List<DetailInvoice> getAllDetailInvoices() {
        List<DetailInvoice> detailInvoices = new ArrayList<>();
        String sql = "SELECT * FROM tblDetailInvoices";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                detailInvoices.add(mapResultSetToDetailInvoice(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return detailInvoices;
    }

    // Update a record
    public void updateDetailInvoice(DetailInvoice detailInvoice) {
        String sql = "UPDATE tblDetailInvoices SET invoiceId = ?, courseId = ?, quantityPurchased = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setLong(1, detailInvoice.getInvoiceId());
            pstmt.setLong(2, detailInvoice.getCourseId());
            pstmt.setInt(3, detailInvoice.getQuantityPurchased());
            pstmt.setLong(4, detailInvoice.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete a record by ID
    public void deleteDetailInvoice(long id) {
        String sql = "DELETE FROM tblDetailInvoices WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Map ResultSet to DetailInvoice object
    private DetailInvoice mapResultSetToDetailInvoice(ResultSet rs) throws SQLException {
        long id = rs.getLong("id");
        long invoiceId = rs.getLong("invoiceId");
        long courseId = rs.getLong("courseId");
        int quantityPurchased = rs.getInt("quantityPurchased");

        return new DetailInvoice(id, invoiceId, courseId, quantityPurchased);
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
