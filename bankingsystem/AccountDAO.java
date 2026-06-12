/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankingsystem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList; 
import java.util.List;

/**
 *
 * @author easterPC
 */
public class AccountDAO {
 public void addAccount(Account account) {
    
    String sql = "INSERT INTO Account (customer_id, account_type, balance) VALUES (?, ?, ?)";
    
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setInt(1, account.getCustomerID());
        pstmt.setString(2, account.getAccountType());
        pstmt.setDouble(3, account.getBalance());
        
        pstmt.executeUpdate();
        System.out.println("Account created successfully in database!");
        
    } catch (SQLException e) {
        System.err.println("CRITICAL ERROR inside AccountDAO.addAccount():");
        e.printStackTrace();
    }
}

   public List<Account> getAllAccounts() {
    List<Account> accountList = new ArrayList<>();
    String sql = "SELECT * FROM Account"; 
    
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery()) {
        
        while (rs.next()) {
            
            Account acc = new Account(
                rs.getInt(1),    
                rs.getInt(2),    
                rs.getString(3), 
                rs.getDouble(4)  
            );
            accountList.add(acc);
        }
    } catch (Exception e) {
       
        javax.swing.JOptionPane.showMessageDialog(null, "Database Error Loading Accounts:\n" + e.getMessage(), "SQL Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
    
    return accountList;
}
   
    public void updateBalance(int accountId, double newBalance) {
        String sql = "UPDATE Account SET balance = ? WHERE account_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, newBalance);
            pstmt.setInt(2, accountId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateAccount(Account account) {
    String sql = "UPDATE Account SET account_type = ?, balance = ? WHERE account_id = ?";
    try (Connection conn = DBConnection.getConnection(); 
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, account.getAccountType());
        pstmt.setDouble(2, account.getBalance());
        pstmt.setInt(3, account.getAccountID());
        pstmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

public void deleteAccount(int accountId) {
    String sql = "DELETE FROM Account WHERE account_id = ?";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, accountId);
        pstmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
}