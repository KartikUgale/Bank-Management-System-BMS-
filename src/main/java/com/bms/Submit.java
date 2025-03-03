package main.java.com.bms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Submit extends JFrame implements ActionListener{
    JLabel cardNum, pinNum, yourCard, pin, card;
    Random atmRandomNum, pinRandumNum;
    Long atmCardNumber, pinNumber;
    JButton close;
    String formNo;

    Submit(String formNo) {
        this.formNo = formNo;
        setTitle("User ID and Password");
        setSize(700, 300);
        setResizable(false);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);

        Cursor showPin = new Cursor(Cursor.HAND_CURSOR);

        // Left design Image
        ImageIcon leftImg = new ImageIcon(ClassLoader.getSystemResource("main/resources/Imgs/LeftEdgeDesign.jpg"));
        Image i2 = leftImg.getImage().getScaledInstance(100, 300, Image.SCALE_DEFAULT);
        ImageIcon ic2 = new ImageIcon(i2);
        JLabel label2 = new JLabel(ic2);
        label2.setBounds(0, 0, 100, 300);
        add(label2);

        // Right design Image
        ImageIcon rightImg = new ImageIcon(ClassLoader.getSystemResource("main/resources/Imgs/RightEdgeDesign.jpg"));
        Image i3 = rightImg.getImage().getScaledInstance(100, 300, Image.SCALE_DEFAULT);
        ImageIcon ic3 = new ImageIcon(i3);
        JLabel label3 = new JLabel(ic3);
        label3.setBounds(600, 0, 100, 300);
        add(label3);

        atmRandomNum = new Random();
        atmCardNumber = Math.abs(atmRandomNum.nextLong() * 1000 % 999999999);
        atmCardNumber+=1953468729;
        System.out.println(atmCardNumber);

        // Card Number
        cardNum = new JLabel("Card Number :");
        cardNum.setFont(new Font("Arial", Font.BOLD, 20));
        cardNum.setBounds(150, 50, 400, 40);
        add(cardNum);

        card = new JLabel("XXXX XXXX XXXX XXXX");
        card.setFont(new Font("Arial", Font.BOLD, 20));
        card.setBounds(300, 50, 250, 40);
        card.setCursor(new Cursor(Cursor.HAND_CURSOR));
        card.addMouseListener(new mouse1());
        add(card);

        yourCard = new JLabel("Your 12 Digit card number");
        yourCard.setFont(new Font("Arial", Font.PLAIN, 10));
        yourCard.setBounds(160, 80, 500, 20);
        add(yourCard);

        // PIN
        pinNum = new JLabel("PIN :");
        pinNum.setFont(new Font("Arial", Font.BOLD, 20));
        pinNum.setBounds(240, 130, 100, 40);
        add(pinNum);

        pinRandumNum = new Random();
        pinNumber = Math.abs(pinRandumNum.nextLong() * 1000 % 9999);
        System.out.println(pinNumber);

        pin = new JLabel("Show PIN");
        pin.setFont(new Font("Arial", Font.BOLD, 20));
        pin.setBounds(300, 130, 100, 40);
        add(pin);
        pin.addMouseListener(new mouse1());
        pin.setCursor(new Cursor(Cursor.TEXT_CURSOR));

        // close Button
        close = new JButton("Close");
        close.setBounds(290, 220, 100, 30);
        close.setFont(new Font("Railway", Font.BOLD, 14));
        close.setBackground(Color.BLACK);
        close.setForeground(Color.WHITE);
        close.addActionListener(this);
        close.setCursor(showPin);
        add(close);
        setUndecorated(true);



        setVisible(true);
    }

    class mouse1 implements MouseListener {
        public void mouseEntered(MouseEvent e1) {
            pin.setText(""+pinNumber);
            card.setText("98"+atmCardNumber);
        }
        public void mouseExited(MouseEvent e1) {
            pin.setText("Show Pin");
            card.setText("XXXX XXXX XXXX XXXX");
        }
        public void mouseClicked(MouseEvent e1) {

        }
        public void mouseReleased(MouseEvent e1) {

        }
        public void mousePressed(MouseEvent e1) {

        }
    }

    public void actionPerformed(ActionEvent e) {    // save data after pressed submit button
        if(e.getSource() == close) {
            try {
                ConnectionCode conn = new ConnectionCode();
                String query = "insert into userpass values('"+formNo+"','"+"98"+atmCardNumber+"','"+pinNumber+"')";
                conn.st.executeUpdate(query);
                System.out.println("Run---------");
                JOptionPane.showMessageDialog(null, "Account Created Successfully!");

                Login home = new Login();
                home.setVisible(true);
                this.setVisible(false);

            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, "Something wrong!");
                System.out.println(e1);
            }
        }
    }

    public static void main(String[] args) {
        new Submit("");
    }
}
