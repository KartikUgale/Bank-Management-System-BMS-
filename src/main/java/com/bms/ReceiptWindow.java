package main.java.com.bms;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class ReceiptWindow extends JFrame {
    String cardNumber, userName, transactionType, transactionAmount, totalBalance, transactionDate;

    ReceiptWindow(String cardNumber) {
        this.cardNumber = cardNumber;
        fetchTransactionData();
        fetchUserData();

        setSize(350, 500);
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setUndecorated(true);

        JPanel receiptPanel = new JPanel();
        receiptPanel.setBounds(10, 10, 330, 480);
        receiptPanel.setBackground(Color.WHITE);
        receiptPanel.setLayout(null);
        add(receiptPanel);

        JLabel title = new JLabel("ATM TRANSACTION", SwingConstants.CENTER);
        title.setFont(new Font("Monospaced", Font.BOLD, 16));
        title.setBounds(50, 20, 230, 20);
        receiptPanel.add(title);

        JLabel terminal = new JLabel("TERMINAL #       65425899");
        terminal.setFont(new Font("Monospaced", Font.PLAIN, 14));
        terminal.setBounds(20, 60, 300, 20);
        receiptPanel.add(terminal);

        JLabel sequence = new JLabel("SEQUENCE #      8564");
        sequence.setFont(new Font("Monospaced", Font.PLAIN, 14));
        sequence.setBounds(20, 80, 300, 20);
        receiptPanel.add(sequence);

        JLabel dateLabel = new JLabel("DATE            " + transactionDate);
        dateLabel.setFont(new Font("Monospaced", Font.PLAIN, 14));
        dateLabel.setBounds(20, 100, 300, 20);
        receiptPanel.add(dateLabel);

        JLabel cardLabel = new JLabel("CARD NUMBER     " + maskCardNumber(cardNumber));
        cardLabel.setFont(new Font("Monospaced", Font.PLAIN, 14));
        cardLabel.setBounds(20, 140, 300, 20);
        receiptPanel.add(cardLabel);

        JLabel userLabel = new JLabel("CUSTOMER NAME   " + userName);
        userLabel.setFont(new Font("Monospaced", Font.PLAIN, 14));
        userLabel.setBounds(20, 160, 300, 20);
        receiptPanel.add(userLabel);

        JLabel typeLabel = new JLabel("TRANSACTION     " + transactionType);
        typeLabel.setFont(new Font("Monospaced", Font.PLAIN, 14));
        typeLabel.setBounds(20, 200, 300, 20);
        receiptPanel.add(typeLabel);

        JLabel amountLabel = new JLabel("REQUESTED AMT  Rs. " + transactionAmount);
        amountLabel.setFont(new Font("Monospaced", Font.PLAIN, 14));
        amountLabel.setBounds(20, 220, 300, 20);
        receiptPanel.add(amountLabel);

        JLabel totalLabel = new JLabel("TOTAL BALANCE  Rs. " + totalBalance);
        totalLabel.setFont(new Font("Monospaced", Font.BOLD, 14));
        totalLabel.setBounds(20, 260, 300, 20);
        receiptPanel.add(totalLabel);

        JSeparator separator = new JSeparator();
        separator.setBounds(20, 300, 290, 1);
        receiptPanel.add(separator);

        JButton close = new JButton("OK");
        close.setBounds(120, 350, 80, 30);
        close.setBackground(Color.LIGHT_GRAY);
        close.addActionListener(e -> dispose());
        receiptPanel.add(close);
        close.setCursor(new Cursor(Cursor.HAND_CURSOR));

        setVisible(true);
    }

    // Fetch user data from signup table
    public void fetchUserData() {
        ConnectionCode conn = new ConnectionCode();
        try {
            ResultSet rs = conn.st.executeQuery("SELECT user_name FROM signup WHERE formNumber IN (SELECT form_number FROM userpass WHERE card_num = '" + cardNumber + "')");
            if (rs.next()) {
                userName = rs.getString("user_name");
            } else {
                userName = "UNKNOWN";
            }
        } catch (Exception e) {
            userName = "ERROR";
            System.out.println("User Fetch Error: " + e);
        }
    }

    // Fetch latest transaction from transactions table
    public void fetchTransactionData() {
        ConnectionCode conn = new ConnectionCode();
        try {
            ResultSet rs = conn.st.executeQuery("SELECT * FROM transactions WHERE account_Number = '" + cardNumber + "' ORDER BY date DESC LIMIT 1");
            if (rs.next()) {
                transactionDate = rs.getString("date");
                transactionType = rs.getString("type");
                transactionAmount = rs.getString("amount");
                totalBalance = rs.getString("total");
            } else {
                transactionDate = "N/A";
                transactionType = "N/A";
                transactionAmount = "0.00";
                totalBalance = "0.00";
            }
        } catch (Exception e) {
            transactionDate = "ERROR";
            transactionType = "ERROR";
            transactionAmount = "ERROR";
            totalBalance = "ERROR";
            System.out.println("Transaction Fetch Error: " + e);
        }
    }

    // Mask card number for security (e.g. XXXXXXXXXXXX5698)
    private String maskCardNumber(String cardNum) {
        if (cardNum.length() >= 4) {
            return "XXXXXXXXXXXX" + cardNum.substring(cardNum.length() - 4);
        }
        return "INVALID CARD";
    }

    public static void main(String[] args) {
        new ReceiptWindow(""); // Pass a valid card number
    }
}
