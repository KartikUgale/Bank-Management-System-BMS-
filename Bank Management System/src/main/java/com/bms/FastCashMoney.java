package main.java.com.bms;

import javax.swing.*;
import java.awt.*;

import java.awt.event.*;
import java.sql.ResultSet;

public class FastCashMoney extends JFrame implements ActionListener{
    JLabel selectTransaction;
    JButton f100, f500, f1000, f2000, f5000, f10000, back;
    String cardNumber, userName;

    FastCashMoney(String cardNumber) {
        this.cardNumber = cardNumber;
        setSize(900, 900);
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setUndecorated(true);

        ImageIcon atmImg = new ImageIcon(ClassLoader.getSystemResource("main/resources/Imgs/ATMmcUI.jpg"));
        Image i1 = atmImg.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon ic1 = new ImageIcon(i1);
        JLabel image = new JLabel(ic1);
        image.setBounds(0, 0, 900, 900);
        add(image);

        // Bank Logo
        ImageIcon logoImg = new ImageIcon(ClassLoader.getSystemResource("main/resources/Imgs/bankLogo.jpg"));
        Image logo1 = logoImg.getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT);
        ImageIcon icl1 = new ImageIcon(logo1);
        JLabel label1 = new JLabel(icl1);
        label1.setBounds(320, 230, 100, 100);
        image.add(label1);

        // ATM Card with slot
        ImageIcon card = new ImageIcon(ClassLoader.getSystemResource("main/resources/Imgs/atmcard.png"));
        Image cardSlot = card.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon cs = new ImageIcon(cardSlot);
        JLabel cardLabel = new JLabel(cs);
        cardLabel.setBounds(595, 340, 350, 250);
        image.add(cardLabel);

        selectTransaction = new JLabel("Select Withdrawal Amount");
        selectTransaction.setFont(new Font("Arial", Font.BOLD, 16));
        selectTransaction.setForeground(Color.DARK_GRAY);
        selectTransaction.setBounds(260, 350, 250, 20);
        image.add(selectTransaction);

        f100 = new JButton("Rs. 100");
        f100.setBackground(Color.BLUE);
        f100.setForeground(Color.WHITE);
        f100.setBounds(155, 392, 98, 25);
        f100.addActionListener(this);
        image.add(f100);

        f500 = new JButton("Rs. 500");
        f500.setBackground(Color.BLUE);
        f500.setForeground(Color.WHITE);
        f500.setBounds(446, 392, 128, 25);
        f500.addActionListener(this);
        image.add(f500);

        f1000 = new JButton("Rs. 1000");
        f1000.setBackground(Color.BLUE);
        f1000.setForeground(Color.WHITE);
        f1000.setBounds(153, 430, 100, 25);
        f1000.addActionListener(this);
        image.add(f1000);

        f2000 = new JButton("Rs. 2000");
        f2000.setBackground(Color.BLUE);
        f2000.setForeground(Color.WHITE);
        f2000.setBounds(445, 430, 130, 25);
        f2000.addActionListener(this);
        image.add(f2000);

        f5000 = new JButton("Rs. 5000");
        f5000.setBackground(Color.BLUE);
        f5000.setForeground(Color.WHITE);
        f5000.setBounds(152, 470, 101, 25);
        f5000.addActionListener(this);
        image.add(f5000);


        f10000 = new JButton("Rs. 10000");
        f10000.setBackground(Color.BLUE);
        f10000.setForeground(Color.WHITE);
        f10000.setBounds(445, 470, 130, 25);
        f10000.addActionListener(this);
        image.add(f10000);


        back = new JButton("EXIT");
        back.setBackground(Color.RED);
        back.setForeground(Color.WHITE);
        back.setBounds(446, 510, 130, 25);
        back.addActionListener(this);
        image.add(back);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            new Transaction(cardNumber).setVisible(true);
            this.setVisible(false);
        } else {
            try {
                String amountText = ((JButton) e.getSource()).getText().replace("Rs. ", "").trim();
                int amount = Integer.parseInt(amountText);

                ConnectionCode conn = new ConnectionCode();
                int balance = 0;

                // Fetch total balance from transactions table
                String balanceQuery = "SELECT COALESCE(SUM(CASE WHEN type = 'credited' THEN CAST(amount AS UNSIGNED) ELSE -CAST(amount AS UNSIGNED) END), 0) AS total FROM transactions WHERE account_Number = '" + cardNumber + "'";
                ResultSet rs = conn.st.executeQuery(balanceQuery);

                if (rs.next()) {
                    balance = rs.getInt("total");
                }

                // Check if balance is sufficient
                if (balance < amount) {
                    JOptionPane.showMessageDialog(null, "Insufficient Balance!");
                    return;
                }

                // Retrieve user name from userpass table
                String userQuery = "SELECT form_number FROM userpass WHERE card_num = '" + cardNumber + "'";
                ResultSet userRs = conn.st.executeQuery(userQuery);

                if (userRs.next()) {
                    userName = userRs.getString("form_number");
                } else {
                    JOptionPane.showMessageDialog(null, "User not found!");
                    return;
                }

                int totalBalance = balance - amount; // Calculate new balance

                // Insert withdrawal transaction into transactions table
                String query = "INSERT INTO transactions (account_Number, date, user_name, type, amount, total) VALUES ('"
                        + cardNumber + "', NOW(), '" + userName + "', 'debited', '" + amount + "', '" + totalBalance + "')";
                conn.st.executeUpdate(query);

                JOptionPane.showMessageDialog(null, "Rs. " + amount + " Debited Successfully");

                this.setVisible(false);
                new Transaction(cardNumber).setVisible(true);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid amount entered!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Something went wrong: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }



    public static void main(String[] args) {
        new FastCashMoney("");
    }
}
