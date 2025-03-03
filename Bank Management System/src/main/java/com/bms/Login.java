package main.java.com.bms;

import javax.swing.*;

import java.awt.Color;
import java.awt.Image;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.*;
import java.sql.*;
import java.util.Arrays;


public class Login extends JFrame implements ActionListener {
    JLabel title, cardNum, pin;
    JTextField cardTxtField;
    JPasswordField pinTxtField;
    JButton login, clear, signUp, helpBt;

    Login() {
        setTitle("Swing Bank Of India");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setResizable(false);

        Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);

        // Bank Logo
        ImageIcon bankLogoIcon = new ImageIcon(ClassLoader.getSystemResource("main/resources/Imgs/bankLogo.jpg"));
        Image bankLogoImage = bankLogoIcon.getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT);
        ImageIcon scaledBankLogo = new ImageIcon(bankLogoImage);
        JLabel bankLogoLabel = new JLabel(scaledBankLogo);
        bankLogoLabel.setBounds(345, 10, 100, 100);
        add(bankLogoLabel);

        // Left design Image
        ImageIcon leftImgIcon = new ImageIcon(ClassLoader.getSystemResource("main/resources/Imgs/LeftEdgeDesign.jpg"));
        Image leftImg = leftImgIcon.getImage().getScaledInstance(100, 500, Image.SCALE_DEFAULT);
        ImageIcon scaledLeftImg = new ImageIcon(leftImg);
        JLabel leftImgLabel = new JLabel(scaledLeftImg);
        leftImgLabel.setBounds(0, 0, 100, 500);
        add(leftImgLabel);

        // Right design Image
        ImageIcon rightImgIcon = new ImageIcon(ClassLoader.getSystemResource("main/resources/Imgs/RightEdgeDesign.jpg"));
        Image rightImg = rightImgIcon.getImage().getScaledInstance(100, 500, Image.SCALE_DEFAULT);
        ImageIcon scaledRightImg = new ImageIcon(rightImg);
        JLabel rightImgLabel = new JLabel(scaledRightImg);
        rightImgLabel.setBounds(690, 0, 100, 500);
        add(rightImgLabel);

        // Welcome Text
        title = new JLabel("Welcome to ATM");
        title.setFont(new Font("Times New Roman", Font.BOLD, 38));
        title.setBounds(250, 100, 500, 40);
        add(title);

        // Card Number Label
        cardNum = new JLabel("Card No :");
        cardNum.setFont(new Font("Times New Roman", Font.BOLD, 20));
        cardNum.setBounds(170, 170, 400, 30);
        add(cardNum);

        // Card Number text Field
        cardTxtField = new JTextField();
        cardTxtField.setBounds(280, 170, 250, 30);
        cardTxtField.setFont(new Font("Arial", Font.BOLD, 16));
        add(cardTxtField);

        // PIN Label
        pin = new JLabel("PIN :");
        pin.setFont(new Font("Times New Roman", Font.BOLD, 20));
        pin.setBounds(210, 250, 500, 30);
        add(pin);

        // card PIN text Field
        pinTxtField = new JPasswordField();
        pinTxtField.setBounds(280, 250, 250, 30);
        cardTxtField.setFont(new Font("Arial", Font.BOLD, 16));
        add(pinTxtField);

        // Login Button
        login = new JButton("Sign In");
        login.setBounds(285, 320, 110, 30);
        login.setForeground(Color.WHITE);
        login.setBackground(Color.BLACK);
        login.addActionListener(this);  // action Listener
        add(login);

        // Clear Button
        clear = new JButton("Clear");
        clear.setBounds(415, 320, 110, 30);
        clear.setForeground(Color.WHITE);
        clear.setBackground(Color.BLACK);
        clear.addActionListener(this);  // action Listener
        add(clear);

        // Sign up Button
        signUp = new JButton("New User?");
        signUp.setBounds(285, 380, 240, 30);
        signUp.setForeground(Color.WHITE);
        signUp.setBackground(Color.DARK_GRAY);
        signUp.addActionListener(this);     // action Listener
        add(signUp);

        // Help button
        helpBt = new JButton("Help?");
        helpBt.setBounds(600, 400, 70, 25);
        helpBt.setForeground(Color.WHITE);
        helpBt.setBackground(Color.RED);
        helpBt.addActionListener(this);   // action Listener
        add(helpBt);

        // Cursor Hand apply on buttons
        login.setCursor(handCursor);
        clear.setCursor(handCursor);
        signUp.setCursor(handCursor);


        //revalidate();
        //repaint();


        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == login) {
//            String cardNumber = cardTxtField.getText();
//            String pin = pinTxtField.getText();   // string makes password week
//            String query = "select * from userpass where card_num = '"+cardNumber+"' and pin = '"+pin+"'";
                                    // (SQL Injection) Attackers manipulates SQL queries by injecting malicious input
                                    // secure it using PreparedStatement & placeholders '?'

            String cardNumber  = cardTxtField.getText();
            char[] pinArray = pinTxtField.getPassword();
            String pin = new String(pinArray);

            try {
                ConnectionCode connect = new ConnectionCode();
                PreparedStatement ps = connect.conn.prepareStatement("select * from userpass where card_num = ? and pin = ?");
                ps.setString(1, cardNumber);
                ps.setString(2, pin);
                ResultSet rs = ps.executeQuery();

                if(rs.next()) {
                    Transaction loggedIn = new Transaction(cardNumber);
                    loggedIn.setVisible(true);
                    this.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Card Number or PIN Wrong!", "Warning!", JOptionPane.WARNING_MESSAGE);
                    cardTxtField.setText("");
                    pinTxtField.setText("");
                }

                Arrays.fill(pinArray, ' ');     // clear data for pin safety
            } catch(SQLException e1) {
                System.out.println(e1.getMessage());
            }
        }
        else if(e.getSource() == clear) {
            cardTxtField.setText("");
            pinTxtField.setText("");
        }
        else if(e.getSource() == signUp) {
            setVisible(false);
            new SignupFirstW().setVisible(true);
        }
        else if(e.getSource() == helpBt) {
            JOptionPane.showMessageDialog(null, "Contact Number: 12312 34566", "Contact Information", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
