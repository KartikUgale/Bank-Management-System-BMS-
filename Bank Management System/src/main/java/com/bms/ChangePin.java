package main.java.com.bms;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ChangePin extends JFrame implements ActionListener {
    String cardNumber;
    JLabel oldPin, newPin;
    JPasswordField oldPinField, newPinField;
    JButton back, change;

    ChangePin(String cardNumber) {
        this.cardNumber = cardNumber;

        setLayout(null);
        setSize(900, 900);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setUndecorated(true);

        // ATM Image
        ImageIcon atmImage = new ImageIcon(ClassLoader.getSystemResource("main/resources/Imgs/ATMmcUI.jpg"));
        Image atmI1 = atmImage.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon atmImage2 = new ImageIcon(atmI1);
        JLabel imageLabel1 = new JLabel(atmImage2);
        imageLabel1.setBounds(0, 0, 900, 900);
        add(imageLabel1);

        // Bank Logo
        ImageIcon logoImg = new ImageIcon(ClassLoader.getSystemResource("main/resources/Imgs/bankLogo.jpg"));
        Image logo1 = logoImg.getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT);
        ImageIcon icl1 = new ImageIcon(logo1);
        JLabel label1 = new JLabel(icl1);
        label1.setBounds(300, 210, 100, 100);
        imageLabel1.add(label1);

        // ATM Card with slot
        ImageIcon card = new ImageIcon(ClassLoader.getSystemResource("main/resources/Imgs/atmcard.png"));
        Image cardSlot = card.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon cs = new ImageIcon(cardSlot);
        JLabel cardLabel = new JLabel(cs);
        cardLabel.setBounds(595, 340, 350, 250);
        imageLabel1.add(cardLabel);

        oldPin = new JLabel("Enter Old PIN");
        oldPin.setFont(new Font("Arial", Font.BOLD, 16));
        oldPin.setForeground(Color.DARK_GRAY);
        oldPin.setBounds(300, 320, 200, 20);
        imageLabel1.add(oldPin);

        oldPinField = new JPasswordField();
        oldPinField.setHorizontalAlignment(JTextField.CENTER);
        ((AbstractDocument) oldPinField.getDocument()).setDocumentFilter(new NumericDocumentFilter());
        oldPinField.setFont(new Font("Arial", Font.PLAIN, 25));
        oldPinField.setBounds(270, 345, 160, 30);
        oldPinField.setBackground(Color.DARK_GRAY);
        oldPinField.setForeground(Color.WHITE);
        imageLabel1.add(oldPinField);

        newPin = new JLabel("Enter New PIN");
        newPin.setFont(new Font("Arial", Font.BOLD, 16));
        newPin.setForeground(Color.DARK_GRAY);
        newPin.setBounds(300, 395, 200, 20);
        imageLabel1.add(newPin);

        newPinField = new JPasswordField();
        newPinField.setHorizontalAlignment(JTextField.CENTER);
        ((AbstractDocument) newPinField.getDocument()).setDocumentFilter(new NumericDocumentFilter());
        newPinField.setFont(new Font("Arial", Font.PLAIN, 25));
        newPinField.setBounds(270, 420, 160, 30);
        newPinField.setBackground(Color.DARK_GRAY);
        newPinField.setForeground(Color.WHITE);
        imageLabel1.add(newPinField);

        change = new JButton("Change");
        change.setBackground(Color.BLUE);
        change.setForeground(Color.WHITE);
        change.setBounds(446, 470, 130, 25);
        change.addActionListener(this);
        imageLabel1.add(change);

        back = new JButton("Back");
        back.setBackground(Color.RED);
        back.setForeground(Color.WHITE);
        back.setBounds(446, 510, 130, 25);
        back.addActionListener(this);
        imageLabel1.add(back);

        setVisible(true);
    }

    class NumericDocumentFilter extends DocumentFilter {
        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            if (string != null && string.matches("\\d+")) {
                super.insertString(fb, offset, string, attr);
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String string, AttributeSet attr) throws BadLocationException {
            if (string != null && string.matches("\\d+")) {
                super.replace(fb, offset, length, string, attr);
            }
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == back) {
            new Transaction(cardNumber).setVisible(true);
            this.setVisible(false);
        } else if (ae.getSource() == change) {
            char[] oldPin = oldPinField.getPassword();
            char[] newPin = newPinField.getPassword();

            if (oldPin.length == 0 || newPin.length == 0) {
                JOptionPane.showMessageDialog(this, "Please fill in both fields.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (newPin.length != 4) {
                JOptionPane.showMessageDialog(this, "New PIN must be 4 digits.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                ConnectionCode conn = new ConnectionCode();
                PreparedStatement ps = conn.conn.prepareStatement("SELECT pin FROM userpass WHERE card_num = ?");
                ps.setString(1, cardNumber);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    String currentPin = rs.getString("pin");

                    String oldPinInput = new String(oldPin);

                    if (!currentPin.equals(oldPinInput)) {
                        JOptionPane.showMessageDialog(this, "Old PIN is incorrect.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    String newPinInput = new String(newPin);
                    PreparedStatement updatePs = conn.conn.prepareStatement("UPDATE userpass SET pin = ? WHERE card_num = ?");
                    updatePs.setString(1, newPinInput);
                    updatePs.setString(2, cardNumber);
                    updatePs.executeUpdate();

                    JOptionPane.showMessageDialog(this, "PIN changed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    new Transaction(cardNumber).setVisible(true);
                    this.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(this, "Card number not found.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                conn.conn.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        new ChangePin(""); // Example card number
    }
}