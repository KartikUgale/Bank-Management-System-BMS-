package main.java.com.bms;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.util.*;

import com.toedter.calendar.JDateChooser;
import java.awt.event.*;

public class SignupFirstW extends JFrame implements ActionListener {
    JLabel heading, formNumId, form1, personal, userName, fatherName, dob, gender, nationality, motherName, maritalStatus, phoneNumber, gmail, customerType, address, userAddress, city, state, pin;
    JTextField usernameField, fatherNameField, nationalityField, motherNameField, phoneNumberField, gmailField, addressField, cityField, stateField, pinField;
    JRadioButton publicType, staffType, male, female, single, married;
    ButtonGroup customerGroup, genderGroup, maritalGroup;
    JDateChooser dateChooser;
    JButton nextButton, homeButton;
    Random rNum;
    Long formNumber;

    SignupFirstW() {
        setTitle("Swing Bank Of India");
        setSize(700, 850);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setResizable(false);
        setUndecorated(true);

        // Heading
        heading = new JLabel("Account Opening Form");
        heading.setFont(new Font("Times New Roman", Font.BOLD, 24));
        heading.setBounds(220, 20, 300, 40);
        add(heading);

        rNum = new Random();
        formNumber = Math.abs(rNum.nextLong() * 1000 % 99999);
        System.out.println(formNumber);

        formNumId = new JLabel("Application ID - "+ formNumber);
        formNumId.setFont(new Font("Arial", Font.PLAIN, 12));
        formNumId.setBounds(550, 10, 300, 20);
        add(formNumId);


        form1 = new JLabel("Form 1 : Personal Details");
        form1.setFont(new Font("Arial", Font.BOLD, 12));
        form1.setBounds(525, 45, 300, 20);
        add(form1);

        personal = new JLabel("Personal Details : * ");
        personal.setFont(new Font("Times New Roman", Font.BOLD, 16));
        personal.setBounds(60, 80, 200, 30);
        add(personal);

        // Account Holder Name
        userName = new JLabel("Account Holder Full Name :");
        userName.setFont(new Font("Arial", Font.PLAIN, 14));
        userName.setBounds(95, 120, 200, 25);
        add(userName);

        usernameField = new JTextField();
        usernameField.setBounds(300, 120, 250, 25);
        add(usernameField);

        // Father's/Gardian Name
        fatherName = new JLabel("Father/Gardian Name :");
        fatherName.setFont(new Font("Arial", Font.PLAIN, 14));
        fatherName.setBounds(122, 160, 200, 25);
        add(fatherName);

        fatherNameField = new JTextField();
        fatherNameField.setBounds(300, 160, 250, 25);
        add(fatherNameField);

        // Date of Birth
        dob = new JLabel("Date of Birth :");
        dob.setFont(new Font("Arial", Font.PLAIN, 14));
        dob.setBounds(180, 200, 200, 25);
        add(dob);

        dateChooser = new JDateChooser();
        dateChooser.setBounds(300, 200, 250, 25);
        add(dateChooser);

        // Gender
        gender = new JLabel("Gender :");
        gender.setFont(new Font("Arial", Font.PLAIN, 14));
        gender.setBounds(210, 240, 200, 25);
        add(gender);

        male = new JRadioButton("Male");
        male.setBounds(300, 240, 80, 25);
        male.setBackground(Color.WHITE);

        female = new JRadioButton("Female");
        female.setBounds(380, 240, 80, 25);
        female.setBackground(Color.WHITE);

        genderGroup = new ButtonGroup();
        genderGroup.add(male);
        genderGroup.add(female);

        add(male);
        add(female);

        // Nationality
        nationality = new JLabel("Nationality :");
        nationality.setFont(new Font("Arial", Font.PLAIN, 14));
        nationality.setBounds(192, 280, 200, 25);
        add(nationality);

        nationalityField = new JTextField();
        nationalityField.setBounds(300, 280, 250, 25);
        add(nationalityField);

        // Mother's Name
        motherName = new JLabel("Mother's Name :");
        motherName.setFont(new Font("Arial", Font.PLAIN, 14));
        motherName.setBounds(162, 320, 200, 25);
        add(motherName);

        motherNameField = new JTextField();
        motherNameField.setBounds(300, 320, 250, 25);
        add(motherNameField);

        // Marital Status
        maritalStatus = new JLabel("Marital Status :");
        maritalStatus.setFont(new Font("Arial", Font.PLAIN, 14));
        maritalStatus.setBounds(170, 360, 200, 25);
        add(maritalStatus);

        single = new JRadioButton("Single");
        single.setBounds(300, 360, 80, 25);
        single.setBackground(Color.WHITE);

        married = new JRadioButton("Married");
        married.setBounds(380, 360, 100, 25);
        married.setBackground(Color.WHITE);

        maritalGroup = new ButtonGroup();
        maritalGroup.add(single);
        maritalGroup.add(married);

        add(single);
        add(married);

        // Phone Number
        phoneNumber = new JLabel("Phone Number :       +91");
        phoneNumber.setFont(new Font("Arial", Font.PLAIN, 14));
        phoneNumber.setBounds(160, 400, 200, 25);
        add(phoneNumber);

        phoneNumberField = new JTextField();
        ((AbstractDocument) phoneNumberField.getDocument()).setDocumentFilter(new NumericLimitDocumentFilter(10));
        phoneNumberField.setBounds(330, 400, 220, 25);
        add(phoneNumberField);

        // Gmail
        gmail = new JLabel("Gmail :");
        gmail.setFont(new Font("Arial", Font.PLAIN, 14));
        gmail.setBounds(217, 440, 200, 25);
        add(gmail);

        gmailField = new JTextField("@gmail.com");
        gmailField.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        gmailField.setBounds(300, 440, 250, 25);
        add(gmailField);

        // Customer Type
        customerType = new JLabel("Customer Type * :");
        customerType.setFont(new Font("Arial", Font.PLAIN, 14));
        customerType.setBounds(160, 480, 200, 25);
        add(customerType);

        publicType = new JRadioButton("Public");
        publicType.setBounds(300, 480, 80, 25);
        publicType.setBackground(Color.WHITE);

        staffType = new JRadioButton("Staff");
        staffType.setBounds(380, 480, 80, 25);
        staffType.setBackground(Color.WHITE);

        customerGroup = new ButtonGroup();
        customerGroup.add(publicType);
        customerGroup.add(staffType);

        add(publicType);
        add(staffType);


        // Address
        address = new JLabel("Address :");
        address.setFont(new Font("Times New Roman", Font.BOLD, 16));
        address.setBounds(50, 520, 300, 40);
        add(address);

        // User Address
        userAddress = new JLabel("Your Address :");
        userAddress.setFont(new Font("Arial", Font.PLAIN, 14));
        userAddress.setBounds(80, 560, 200, 25);
        add(userAddress);

        addressField = new JTextField();
        addressField.setBounds(200, 560, 380, 25);
        add(addressField);

        // city
        city = new JLabel("City :");
        city.setFont(new Font("Arial", Font.PLAIN, 14));
        city.setBounds(100, 600, 100, 25);
        add(city);

        cityField = new JTextField();
        cityField.setBounds(160, 600, 150, 25);
        add(cityField);

        // state
        state = new JLabel("State :");
        state.setFont(new Font("Arial", Font.PLAIN, 14));
        state.setBounds(330, 600, 200, 25);
        add(state);

        stateField = new JTextField();
        stateField.setBounds(400, 600, 180, 25);
        add(stateField);

        // pin
        pin = new JLabel("PIN :");
        pin.setFont(new Font("Arial", Font.PLAIN, 14));
        pin.setBounds(100, 650, 200, 25);
        add(pin);

        pinField = new JTextField();
        pinField.setBounds(160, 650, 150, 25);
        add(pinField);


        // Next Button
        nextButton = new JButton("Next →");
        nextButton.setFont(new Font("Railway", Font.PLAIN, 16));
        nextButton.setBounds(460, 750, 100, 25);
        nextButton.setBackground(Color.BLACK);
        nextButton.setForeground(Color.WHITE);
        nextButton.addActionListener(this);     // action Listener
        nextButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(nextButton);

        // Home Button
        homeButton = new JButton("🏠");
        homeButton.setFont(new Font("Railway", Font.PLAIN, 16));
        homeButton.setBounds(80, 750, 50, 25);
        homeButton.setBackground(Color.WHITE);
        homeButton.addActionListener(this);    // action Listener
        homeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(homeButton);


        setVisible(true);
    }

    class NumericLimitDocumentFilter extends DocumentFilter {       // for phone number text field restriction (10 digits only, nums only)
        private final int limit;

        public NumericLimitDocumentFilter(int limit) {
            this.limit = limit;
        }

        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            if (string != null && string.matches("\\d+") && (fb.getDocument().getLength() + string.length() <= limit)) {
                super.insertString(fb, offset, string, attr);
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            if (text.matches("\\d+") && (fb.getDocument().getLength() - length + text.length() <= limit)) {
                super.replace(fb, offset, length, text, attrs);
            }
        }
    }

    public void actionPerformed (ActionEvent e) {
        String formNo = ""+ formNumber;
        String name = usernameField.getText();
        String fatherName = fatherNameField.getText();
        String birthDate = ((JTextField)dateChooser.getDateEditor().getUiComponent()).getText();
        String gender = null;
            if (male.isSelected()) {
                gender = "male";
            } else {
                gender = "female";
            }
        String nationality = nationalityField.getText();
        String motherName  = motherNameField.getText();
        String maried = null;
            if(single.isSelected()) {
                maried = "single";
            } else {
                maried = "maried";
            }
        String phoneNo = phoneNumberField.getText();
        String mail = gmailField.getText();
        String accType = null;
            if(publicType.isSelected()) {
                accType = "public";
            } else {
                accType = "staff";
            }
        String address = addressField.getText();
        String city = cityField.getText();
        String state = stateField.getText();
        String pin = pinField.getText();



        if(e.getSource() == nextButton) {
            try {
                if(name.equals("") || fatherName.equals("") || birthDate.equals("") || gender.equals("") || nationality.equals("") || motherName.equals("") || phoneNo.equals("") || mail.equals("") || accType.equals("") || maried.equals("")) {
                    JOptionPane.showMessageDialog(null, "Personal Info Section mandatory *");
                }
                if(address.equals("") || city.equals("") || state.equals("") || pin.equals("")) {
                    JOptionPane.showMessageDialog(null, "Address Section mandatory *");
                }else {
                    //ConnectionCode conn = new ConnectionCode();
                    //String query = "insert into signUp values('"+formNo+"','"+name+"','"+fatherName+"','"+birthDate+"','"+maried+"','"+phoneNo+"','"+mail+"','"+accType+"','"+address+"','"+city+"','"+state+"','"+pin+"')";
                    //conn.st.executeUpdate(query);

                    // nextButton to 2nd Window
                    setVisible(false);
                    new SignupSecondW(formNo, name, fatherName, birthDate, maried, phoneNo, mail, accType, address, city, state, pin).setVisible(true);
                }

            } catch (Exception e1) {
                System.out.println(e1);
            }
        }
        else if(e.getSource() == homeButton) {
            Login home = new Login();
            this.setVisible(false);
            home.setVisible(true);


        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLACK);
        g.drawLine(0, 75, 700, 75);
        g.drawLine(40, 520, 660, 520);
    }

    public static void main(String[] args) {
        new SignupFirstW();
    }
}
