/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankingsystem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author easterPC
 */
public class TransactionDAO {
   public void addTransaction(int accountId, String type, double amount) {
        String sql = "INSERT INTO Transaction (account_id, transaction_type, amount, transaction_date) VALUES (?, ?, ?, NOW())";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, accountId);
            pstmt.setString(2, type);
            pstmt.setDouble(3, amount);
            pstmt.executeUpdate();
            System.out.println("Transaction log saved successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Object[]> getAllTransactionLogs() {
        List<Object[]> logs = new ArrayList<>();
        String sql = "SELECT transaction_id, account_id, transaction_type, amount, transaction_date FROM Transaction";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                logs.add(new Object[]{
                    rs.getInt("transaction_id"),
                    rs.getInt("account_id"),
                    rs.getString("transaction_type"),
                    rs.getDouble("amount"),
                    rs.getTimestamp("transaction_date")
                });
            }
        } catch (SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Database Log Error: " + e.getMessage());
            e.printStackTrace();
        }
        return logs;
    }
    public List<Object[]> searchTransactions(String searchType, String keyword) {
    List<Object[]> logs = new ArrayList<>();
    String sql = "";

    if (searchType.equals("Transaction ID")) {
        sql = "SELECT transaction_id, account_id, transaction_type, amount, transaction_date FROM Transaction WHERE transaction_id = ?";
    } else if (searchType.equals("Account ID")) {
        sql = "SELECT transaction_id, account_id, transaction_type, amount, transaction_date FROM Transaction WHERE account_id = ?";
    } else if (searchType.equals("Transaction Type")) {
        sql = "SELECT transaction_id, account_id, transaction_type, amount, transaction_date FROM Transaction WHERE transaction_type LIKE ?";
    }

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        if (searchType.equals("Transaction Type")) {
            pstmt.setString(1, "%" + keyword + "%"); // Partial text match (e.g., "Dep" finds "Deposit")
        } else {
            pstmt.setInt(1, Integer.parseInt(keyword)); // Exact number match
        }

        try (ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                logs.add(new Object[]{
                    rs.getInt("transaction_id"),
                    rs.getInt("account_id"),
                    rs.getString("transaction_type"),
                    rs.getDouble("amount"),
                    rs.getTimestamp("transaction_date")
                });
            }
        }
    } catch (SQLException | NumberFormatException e) {
        System.out.println("Invalid search format.");
    }
    return logs;
}
}