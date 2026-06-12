/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankingsystem;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author easterPC
 */
public class CustomerDAO {
public void addCustomer(Customer customer) {
        String sql = "INSERT INTO Customer (last_name, first_name, email, phone_number) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, customer.getLastName());
            pstmt.setString(2, customer.getFirstName());
            pstmt.setString(3, customer.getEmail());
            pstmt.setString(4, customer.getPhoneNumber());
            pstmt.executeUpdate();
            System.out.println("Customer saved successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Customer> getAllCustomers() {
        List<Customer> customerList = new ArrayList<>();
        String sql = "SELECT * FROM Customer";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Customer c = new Customer(
                    rs.getInt("customer_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("email"),
                    rs.getString("phone_number")
                );
                customerList.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerList;
    }
    public Customer getCustomer(int id) {
    String sql = "SELECT * FROM Customer WHERE customer_id = ?";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setInt(1, id);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return new Customer(
                    rs.getInt("customer_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("email"),
                    rs.getString("phone_number")
                );
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null; 
}
    public void updateCustomer(Customer customer) {
    String sql = "UPDATE Customer SET last_name = ?, first_name = ?, email = ?, phone_number = ? WHERE customer_id = ?";
    try (Connection conn = DBConnection.getConnection(); 
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, customer.getLastName());
        pstmt.setString(2, customer.getFirstName());
        pstmt.setString(3, customer.getEmail());
        pstmt.setString(4, customer.getPhoneNumber());
        pstmt.setInt(5, customer.getCustomerID());
        pstmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

public void deleteCustomer(int customerId) {
    String sql = "DELETE FROM Customer WHERE customer_id = ?";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, customerId);
        pstmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
public List<Customer> searchCustomers(String searchType, String keyword) {
    List<Customer> customerList = new ArrayList<>();
    String sql = "";

    
    if (searchType.equals("Customer ID")) {
        sql = "SELECT * FROM Customer WHERE customer_id = ?";
    } else if (searchType.equals("First Name")) {
        sql = "SELECT * FROM Customer WHERE first_name LIKE ?";
    } else if (searchType.equals("Last Name")) {
        sql = "SELECT * FROM Customer WHERE last_name LIKE ?";
    }

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        
        if (searchType.equals("Customer ID")) {
            pstmt.setInt(1, Integer.parseInt(keyword));
        } else {
            
            pstmt.setString(1, "%" + keyword + "%"); 
        }

        try (ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Customer c = new Customer(
                    rs.getInt("customer_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("email"),
                    rs.getString("phone_number")
                );
                customerList.add(c);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } catch (NumberFormatException e) {
        
        System.out.println("Invalid ID format searched."); 
    }
    return customerList;
}
}