# BankingSystem_Manalang_SantiagoRafael

## System Description
# This application helps bank tellers manage customer records and process financial transactions through a simple graphical interface.

*Customers: Create user profiles and search for them by name or ID.

*Accounts: Open checking, savings, credit, or loan accounts for existing customers.

*Transactions: Process instant deposits and withdrawals with balance checks (prevents overdrawing).

*Logs: View a complete, unalterable history of every successful transaction.

## How to Run
Open MySQL Workbench, paste this short script into a new query window, and click the lightning bolt to execute it:
CREATE DATABASE IF NOT EXISTS banking;
USE banking;

CREATE TABLE Customer (
    customer_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50), last_name VARCHAR(50), email VARCHAR(100), phone_number VARCHAR(15)
);

CREATE TABLE Account (
    account_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT, account_type VARCHAR(50), balance DECIMAL(10, 2),
    FOREIGN KEY (customer_id) REFERENCES Customer(customer_id) ON DELETE CASCADE
);

CREATE TABLE Transaction (
    transaction_id INT AUTO_INCREMENT PRIMARY KEY,
    account_id INT, transaction_type VARCHAR(50), amount DECIMAL(10, 2), transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (account_id) REFERENCES Account(account_id) ON DELETE CASCADE
);


Configure NetBeans
*Open this project folder inside Apache NetBeans.

*Expand your project layout, right-click the Libraries folder, and choose Add JAR/Folder.

*Select your local mysql-connector-j-x.x.x.jar file so Java can talk to your database.

*Open DBConnection.java and make sure the database username and password match your own MySQL credentials.


Run the App
*Click the Clean and Build (Hammer and Broom) button at the top of NetBeans.

*Right-click MainFrame.java in your project navigator.

*Select Run File.
