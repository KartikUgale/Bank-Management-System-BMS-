package main.java.com.bms;

import javax.swing.*;
import java.awt.*;

import java.awt.event.*;

public class Transaction extends JFrame implements ActionListener{
    JLabel selectTransaction, cardLabel, image, recieptimage;
    JButton deposit, withdraw, fastcash, balance, reciept, pinChange, exit;
    String cardNumber;

    Transaction(String cardNumber) {
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
        image = new JLabel(ic1);
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
        cardLabel = new JLabel(cs);
        cardLabel.setBounds(595, 340, 350, 250);
        image.add(cardLabel);

        // receipt
        ImageIcon recieptImg = new ImageIcon(ClassLoader.getSystemResource("main/resources/Imgs/receipt.png"));
        Image atmR = recieptImg.getImage().getScaledInstance(160, 160, Image.SCALE_DEFAULT);
        ImageIcon reciptIcon = new ImageIcon(atmR);
        recieptimage = new JLabel(reciptIcon);
        recieptimage.setBounds(690, 135, 150, 250);
        image.add(recieptimage);
        recieptimage.setVisible(false);
        recieptimage.setCursor(new Cursor(Cursor.HAND_CURSOR));

        recieptimage.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new ReceiptWindow(cardNumber).setVisible(true);
                recieptimage.setVisible(false);
            }
        });

        selectTransaction = new JLabel("Select Transaction from below");
        selectTransaction.setFont(new Font("Arial", Font.BOLD, 16));
        selectTransaction.setForeground(Color.DARK_GRAY);
        selectTransaction.setBounds(250, 350, 250, 20);
        image.add(selectTransaction);

        deposit = new JButton("Deposit");
        deposit.setBackground(Color.BLUE);
        deposit.setForeground(Color.WHITE);
        deposit.setBounds(155, 392, 98, 25);
        deposit.addActionListener(this);
        image.add(deposit);

        withdraw = new JButton("Withdraw");
        withdraw.setBackground(Color.BLUE);
        withdraw.setForeground(Color.WHITE);
        withdraw.setBounds(446, 392, 128, 25);
        withdraw.addActionListener(this);
        image.add(withdraw);

        fastcash = new JButton("Fast Cash");
        fastcash.setBackground(Color.BLUE);
        fastcash.setForeground(Color.WHITE);
        fastcash.setBounds(153, 430, 100, 25);
        fastcash.addActionListener(this);
        image.add(fastcash);

        balance = new JButton("Balance Enquiry");
        balance.setBackground(Color.BLUE);
        balance.setForeground(Color.WHITE);
        balance.setBounds(445, 430, 130, 25);
        balance.addActionListener(this);
        image.add(balance);

        reciept = new JButton("Reciept");
        reciept.setBackground(Color.BLUE);
        reciept.setForeground(Color.WHITE);
        reciept.setBounds(152, 470, 101, 25);
        reciept.addActionListener(this);
        image.add(reciept);


        pinChange = new JButton("Change PIN");
        pinChange.setBackground(Color.BLUE);
        pinChange.setForeground(Color.WHITE);
        pinChange.setBounds(445, 470, 130, 25);
        pinChange.addActionListener(this);
        image.add(pinChange);

        exit = new JButton("EXIT");
        exit.setBackground(Color.RED);
        exit.setForeground(Color.WHITE);
        exit.setBounds(446, 510, 130, 25);
        exit.addActionListener(this);
        image.add(exit);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == exit) {
            cardLabel.setVisible(false);
            JOptionPane.showMessageDialog(null, "Card Removed");
            new Login().setVisible(true);
            this.setVisible(false);
        }

        else if(e.getSource() == deposit) {
            new Deposit(cardNumber).setVisible(true);
            this.setVisible(false);
        }
        else if(e.getSource() == withdraw) {
            new WithdrawAmount(cardNumber).setVisible(true);
            this.setVisible(false);
        }
        else if (e.getSource() == fastcash) {
            new FastCashMoney(cardNumber).setVisible(true);
            this.setVisible(false);
        }
        else if(e.getSource() == balance) {
            new BalanceEnquiry(cardNumber).setVisible(true);
            this.setVisible(false);
        }
        else if(e.getSource() == reciept) {
            recieptimage.setVisible(true);
        }
        else if(e.getSource() == pinChange) {
            new ChangePin(cardNumber).setVisible(true);
            this.setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Transaction("");
    }
}
