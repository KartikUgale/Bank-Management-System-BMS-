package main.java.com.bms;

import javax.swing.*;
import java.awt.*;

import java.awt.event.*;
import java.sql.ResultSet;

public class BalanceEnquiry extends JFrame implements ActionListener{
    JLabel balanceAvl, amount;
    JButton back;
    String cardNumber;
    String balance;

    BalanceEnquiry(String cardNumber) {
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

        balanceAvl = new JLabel("Available Balance");
        balanceAvl.setFont(new Font("Arial", Font.BOLD, 16));
        balanceAvl.setForeground(Color.DARK_GRAY);
        balanceAvl.setBounds(300, 350, 200, 20);
        image.add(balanceAvl);

        getBalance();

        amount = new JLabel("Rs. "+ balance);
        amount.setHorizontalAlignment(SwingConstants.CENTER);
        amount.setFont(new Font("Arial", Font.BOLD, 16));
        amount.setBounds(280, 400, 180, 30);
        image.add(amount);


        back = new JButton("Back");
        back.setBackground(Color.RED);
        back.setForeground(Color.WHITE);
        back.setBounds(446, 510, 130, 25);
        back.addActionListener(this);
        image.add(back);


        setVisible(true);
    }

    public void getBalance() {
        ConnectionCode conn = new ConnectionCode();
        try {
            ResultSet rs = conn.st.executeQuery("SELECT total FROM transactions WHERE account_Number = '" + cardNumber + "' ORDER BY date DESC LIMIT 1");
            if(rs.next()) {
                balance = rs.getString("total");

            } else {
                amount.setText("No Transaction Found");
            }
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Server Down!");
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            new Transaction(cardNumber).setVisible(true);
            this.setVisible(false);
        }
    }

    public static void main(String[] args) {
        new BalanceEnquiry("");
    }
}
