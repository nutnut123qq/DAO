package dao;

import dbconnect.DBConnect;
import model.Invoice;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InvoiceDAO {
    private Connection connection;

    // Constructor to initialize the connection using DBConnect
    public InvoiceDAO() {
        DBConnect dbConnect = new DBConnect();
        this.connection = dbConnect.getConnection(); // Get the connection from DBConnect
    }

    // Create a new record in the database
    public void createInvoice(Invoice invoice) {
        String sql = "INSERT INTO tblInvoices (purchDate, userId, orderStatus) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setTimestamp(1, invoice.getPurchDate());
            pstmt.setLong(2, invoice.getUserId());
            pstmt.setBoolean(3, invoice.isOrderStatus());

            pstmt.executeUpdate();

            // Retrieve the generated ID
            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                invoice.setId(generatedKeys.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Retrieve a record by ID
    public Invoice getInvoiceById(long id) {
        String sql = "SELECT * FROM tblInvoices WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToInvoice(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Retrieve all records
    public List<Invoice> getAllInvoices() {
        List<Invoice> invoices = new ArrayList<>();
        String sql = "SELECT * FROM tblInvoices";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                invoices.add(mapResultSetToInvoice(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invoices;
    }

    // Update a record
    public void updateInvoice(Invoice invoice) {
        String sql = "UPDATE tblInvoices SET purchDate = ?, userId = ?, orderStatus = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setTimestamp(1, invoice.getPurchDate());
            pstmt.setLong(2, invoice.getUserId());
            pstmt.setBoolean(3, invoice.isOrderStatus());
            pstmt.setLong(4, invoice.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete a record by ID
    public void deleteInvoice(long id) {
        String sql = "DELETE FROM tblInvoices WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Map ResultSet to Invoice object
    private Invoice mapResultSetToInvoice(ResultSet rs) throws SQLException {
        long id = rs.getLong("id");
        Timestamp purchDate = rs.getTimestamp("purchDate");
        long userId = rs.getLong("userId");
        boolean orderStatus = rs.getBoolean("orderStatus");

        return new Invoice(id, purchDate, userId, orderStatus);
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
