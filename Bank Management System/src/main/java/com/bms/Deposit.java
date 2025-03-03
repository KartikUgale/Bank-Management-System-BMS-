package main.java.com.bms;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import javax.swing.text.*;

import java.awt.event.*;
import java.sql.ResultSet;

public class Deposit extends JFrame implements ActionListener{
    JLabel selectTransaction, warning, rupee;
    JTextField amountField;
    JButton deposit, cancel;
    String userName,cardNumber;

    Deposit(String cardNumber) {
        this.cardNumber = cardNumber;
        setSize(900, 900);
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setUndecorated(true);

        Cursor numClick = new Cursor(Cursor.HAND_CURSOR);

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

        selectTransaction = new JLabel("Enter Amount below :");
        selectTransaction.setFont(new Font("Arial", Font.BOLD, 16));
        selectTransaction.setForeground(Color.DARK_GRAY);
        selectTransaction.setBounds(280, 350, 200, 20);
        image.add(selectTransaction);

        amountField = new JTextField();
        ((AbstractDocument) amountField.getDocument()).setDocumentFilter(new NumericDocumentFilter());
        amountField.setFont(new Font("Arial", Font.PLAIN, 20));
        amountField.setBounds(300, 400, 160, 30);
        image.add(amountField);

        rupee = new JLabel(" Rs.");
        rupee.setFont(new Font("Arial", Font.PLAIN, 20));
        rupee.setBounds(260, 400, 40, 30);
//        rupee.setEditable(false);
        image.add(rupee);

        deposit = new JButton("Deposit");
        deposit.setBackground(Color.BLUE);
        deposit.setForeground(Color.WHITE);
        deposit.setBounds(446, 470, 130, 25);
        deposit.addActionListener(this);
        image.add(deposit);

        warning = new JLabel("");
        warning.setFont(new Font("Arial", Font.PLAIN, 14));
        warning.setForeground(Color.RED);
        warning.setBounds(300 ,440, 200, 25);
        image.add(warning);

        cancel = new JButton("Back");
        cancel.setBackground(Color.RED);
        cancel.setForeground(Color.WHITE);
        cancel.setBounds(446, 510, 130, 25);
        cancel.addActionListener(this);
        image.add(cancel);


        setVisible(true);
    }

    class NumericDocumentFilter extends DocumentFilter {
        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            if (string == null) return;
            if (string.matches("\\d+")) { // Allow only digits
                super.insertString(fb, offset, string, attr);
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String string, AttributeSet attr) throws BadLocationException {
            if (string == null) return;
            if (string.matches("\\d+")) { // Allow only digits
                super.replace(fb, offset, length, string, attr);
            }
        }
    }


    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == cancel) {
            new Transaction(cardNumber).setVisible(true);
            this.setVisible(false);
        }

        else if (e.getSource() == deposit) {
            int amount;
            try {
                amount = Integer.parseInt(amountField.getText().trim()); // Ensure numeric input
            } catch (NumberFormatException ex) {
                warning.setText("Invalid amount entered!");
                return;
            }

            if (amount <= 0) {
                warning.setText("Enter a valid amount!");
                return;
            }

            ConnectionCode conn = new ConnectionCode();
            int totalBalance = 0;

            try {
                // Fetch total balance by summing all transactions (cast amount to integer)
                ResultSet rs = conn.st.executeQuery("SELECT SUM(CASE WHEN type = 'credited' THEN CAST(amount AS UNSIGNED) " +
                        "ELSE -CAST(amount AS UNSIGNED) END) AS total " + "FROM transactions WHERE account_Number = '" + cardNumber + "'");

                if (rs.next() && rs.getString("total") != null) {
                    totalBalance = rs.getInt("total");
                }
                totalBalance += amount;

                String query = "INSERT INTO transactions (account_Number, date, user_name, type, amount, total) VALUES ('"
                        + cardNumber + "', NOW(), '" + userName + "', 'credited', '" + amount + "', '" + totalBalance + "')";

                conn.st.executeUpdate(query);

                warning.setText("");
                JOptionPane.showMessageDialog(null, "Rs. " + amount + " Credited Successfully");

            } catch (Exception exc) {
                warning.setText("Database Error!");
                exc.printStackTrace(); // Print full error for debugging
                JOptionPane.showMessageDialog(null, "Something went wrong: " + exc.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Deposit("");
    }
}
