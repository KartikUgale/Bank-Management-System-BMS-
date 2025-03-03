package main.java.com.bms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SignupSecondW extends JFrame implements ActionListener{

    JLabel heading, formNumId, form1, additional, religion, category, income, education, occupation, pan, aadhaar, citizenship, newUser, AccountDetails, AccountType, serviceReq;
    JTextField panField, adhaarField, job;
    JButton prevButton, nextButton;
    JRadioButton newYes, newNo, citizenYes, citizenNo, saving, current, fixed;
    ButtonGroup newUserGroup, citizenGroup, accBG;
    JComboBox religianBox, catagoryBox, incomeBox, eduBox;

    String formNo, name, fatherName, birthDate, maried, phoneNo, mail, accType, address, city, state, pin;

    SignupSecondW(String formNo, String name, String fatherName, String birthDate, String maried, String phoneNo, String mail, String accType, String address, String city, String state, String pin) {
        this.formNo = formNo; this.name = name; this.fatherName = fatherName; this.birthDate = birthDate;
        this.maried = maried; this.phoneNo = phoneNo; this.mail = mail; this.accType = accType;
        this.address = address; this.city = city; this.state = state; this.pin = pin;

        setTitle("Swing Bank Of India");
        setSize(700, 850);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setUndecorated(true);

        // Heading
        heading = new JLabel("Account Opening Form");
        heading.setFont(new Font("Times New Roman", Font.BOLD, 24));
        heading.setBounds(220, 20, 300, 40);
        add(heading);

        formNumId = new JLabel("Application ID - "+ formNo);
        formNumId.setFont(new Font("Arial", Font.PLAIN, 12));
        formNumId.setBounds(550, 10, 300, 20);
        add(formNumId);

        form1 = new JLabel("Form 2 : Additional Details");
        form1.setFont(new Font("Arial", Font.BOLD, 12));
        form1.setBounds(525, 45, 300, 20);
        add(form1);

        additional = new JLabel("Additional Details : * ");
        additional.setFont(new Font("Times New Roman", Font.BOLD, 16));
        additional.setBounds(60, 80, 200, 30);
        add(additional);

        // religion
        religion = new JLabel("Religion :");
        religion.setFont(new Font("Arial", Font.PLAIN, 14));
        religion.setBounds(167, 120, 200, 25);
        add(religion);

        String[] religianStr = {"Hindu", "Muslim", "Sikh", "Christian", "Other"};
        religianBox = new JComboBox(religianStr);
        religianBox.setBounds(300, 122, 200, 20);
        religianBox.setBackground(Color.WHITE);
        add(religianBox);

        // cast
        category = new JLabel("Category :");
        category.setFont(new Font("Arial", Font.PLAIN, 14));
        category.setBounds(160, 160, 200, 25);
        add(category);

        String[] catagoryStr = {"Open", "OBC", "ST/NT", "Other"};
        catagoryBox = new JComboBox(catagoryStr);
        catagoryBox.setBounds(300, 162, 200, 20);
        catagoryBox.setBackground(Color.WHITE);
        add(catagoryBox);

        // Income
        income = new JLabel("Income :");
        income.setFont(new Font("Arial", Font.PLAIN, 14));
        income.setBounds(170, 200, 200, 25);
        add(income);

        String[] incomeRange = {"(0L - 1L) / month", "(1L - 3L) / month" , "(3L - 5L) / month", "(5L - 10L) / month" , "(above 10L) / month"};
        incomeBox = new JComboBox(incomeRange);
        incomeBox.setBounds(300, 202, 200, 20);
        incomeBox.setBackground(Color.WHITE);
        add(incomeBox);

        // Education Qualification
        education = new JLabel("Education :");
        education.setFont(new Font("Arial", Font.PLAIN, 14));
        education.setBounds(152, 240, 200, 25);
        add(education);

        String[] graduation = {"Non Graduate","10th","UG","PG","PHD","Other"};
        eduBox = new JComboBox(graduation);
        eduBox.setBounds(300, 242, 200, 20);
        eduBox.setBackground(Color.WHITE);
        add(eduBox);


        // occupation
        occupation = new JLabel("Occupation :");
        occupation.setFont(new Font("Arial", Font.PLAIN, 14));
        occupation.setBounds(145, 280, 200, 25);
        add(occupation);

        job = new JTextField();
        job.setBounds(280, 280, 250, 25);
        add(job);

        // Pan card Number
        pan = new JLabel("Pan card Number :");
        pan.setFont(new Font("Arial", Font.PLAIN, 14));
        pan.setBounds(105, 320, 200, 25);
        add(pan);

        panField = new JTextField();
        panField.setBounds(280, 320, 250, 25);
        add(panField);

        // Aadhaar card Number
        aadhaar = new JLabel("Aadhaar card Number :");
        aadhaar.setFont(new Font("Arial", Font.PLAIN, 14));
        aadhaar.setBounds(77, 360, 200, 25);
        add(aadhaar);

        adhaarField = new JTextField();
        adhaarField.setBounds(280, 360, 250, 25);
        add(adhaarField);

        // Citizenship
        citizenship = new JLabel("citizenship :");
        citizenship.setFont(new Font("Arial", Font.PLAIN, 14));
        citizenship.setBounds(145, 400, 200, 25);
        add(citizenship);

        citizenYes = new JRadioButton("Yes");
        citizenYes.setBounds(300, 400, 100, 25);
        citizenYes.setBackground(Color.WHITE);

        citizenNo = new JRadioButton("No");
        citizenNo.setBounds(400, 400, 100, 25);
        citizenNo.setBackground(Color.WHITE);

        citizenGroup = new ButtonGroup();
        citizenGroup.add(citizenYes);
        citizenGroup.add(citizenNo);

        add(citizenYes);
        add(citizenNo);

        // New User
        newUser = new JLabel("New User? :");
        newUser.setFont(new Font("Arial", Font.PLAIN, 14));
        newUser.setBounds(145, 440, 200, 25);
        add(newUser);

        newYes = new JRadioButton("Yes");
        newYes.setBounds(300, 440, 100, 25);
        newYes.setBackground(Color.WHITE);

        newNo = new JRadioButton("No");
        newNo.setBounds(400, 440, 100, 25);
        newNo.setBackground(Color.WHITE);

        newUserGroup = new ButtonGroup();
        newUserGroup.add(newYes);
        newUserGroup.add(newNo);

        add(newYes);
        add(newNo);

        AccountDetails = new JLabel("Account Details :*");
        AccountDetails.setFont(new Font("Times new Roman", Font.BOLD, 16));
        AccountDetails.setBounds(60, 530, 200, 30);
        add(AccountDetails);

        AccountType = new JLabel("Account Type  :");
        AccountType.setFont(new Font("Arial", Font.PLAIN, 14));
        AccountType.setBounds(120, 560, 200, 30);
        add(AccountType);

        saving = new JRadioButton("Saving");
        saving.setBackground(Color.WHITE);
        saving.setBounds(250, 560, 100, 30);

        current = new JRadioButton("Current");
        current.setBackground(Color.WHITE);
        current.setBounds(350, 560, 100, 30);

        fixed = new JRadioButton("Fixed");
        fixed.setBackground(Color.WHITE);
        fixed.setBounds(450, 560, 100, 30);

        accBG = new ButtonGroup();
        accBG.add(saving);
        accBG.add(current);
        accBG.add(fixed);

        add(saving);
        add(current);
        add(fixed);

        // service required
        serviceReq = new JLabel("Services Required  :");
        serviceReq.setFont(new Font("Arial", Font.PLAIN, 14));
        serviceReq.setBounds(90, 620, 200, 30);
        add(serviceReq);

        JCheckBox atm = new JCheckBox("ATM card");
        atm.setFont(new Font("Arial", Font.PLAIN, 14));
        atm.setBounds(230, 620, 100, 30);
        atm.setBackground(Color.WHITE);
        add(atm);

        JCheckBox mobile = new JCheckBox("Mobile Banking");
        mobile.setFont(new Font("Arial", Font.PLAIN, 14));
        mobile.setBounds(230, 650, 150, 30);
        mobile.setBackground(Color.WHITE);
        add(mobile);

        JCheckBox sms = new JCheckBox("SMS Service");
        sms.setFont(new Font("Arial", Font.PLAIN, 14));
        sms.setBounds(400, 620, 200, 30);
        sms.setBackground(Color.WHITE);
        add(sms);

        JCheckBox checkBook = new JCheckBox("Check Book");
        checkBook.setFont(new Font("Arial", Font.PLAIN, 14));
        checkBook.setBounds(400, 650, 200, 30);
        checkBook.setBackground(Color.WHITE);
        add(checkBook);


        // Prev Button
        prevButton = new JButton("← Fill Prev again");
        prevButton.setFont(new Font("Railway", Font.PLAIN, 16));
        prevButton.setBounds(90, 750, 190, 25);
        prevButton.setBackground(Color.WHITE);
        prevButton.setForeground(Color.BLACK);
        prevButton.addActionListener(this);     // action Listener
        add(prevButton);
        prevButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Next Button
        nextButton = new JButton("Next →");
        nextButton.setFont(new Font("Railway", Font.PLAIN, 16));
        nextButton.setBounds(460, 750, 100, 25);
        nextButton.setBackground(Color.BLACK);
        nextButton.setForeground(Color.WHITE);
        nextButton.addActionListener(this);     // action Listener
        add(nextButton);
        nextButton.setCursor(new Cursor(Cursor.HAND_CURSOR));


        setVisible(true);
    }


    public void actionPerformed (ActionEvent e) {
        String religian = (String) religianBox.getSelectedItem();
        String catagory = (String) catagoryBox.getSelectedItem();
        String income = (String) incomeBox.getSelectedItem();
        String sEducation = (String) eduBox.getSelectedItem();
        String occupation = job.getText();
        String pan = panField.getText();
        String aadhaar = adhaarField.getText();
        String citizen = null;
        if(citizenYes.isSelected()) {
            citizen = "Yes";
        } else {
            citizen = "No";
        }
        String newUser = null;
        if(newYes.isSelected()) {
            newUser = "Yes";
        } else {
            newUser = "No";
        }

        if(e.getSource() == nextButton) {
            try {
                if(religian.equals("") || catagory.equals("") || income.equals("") || sEducation.equals("") || occupation.equals("") || pan.equals("") || aadhaar.equals("") || citizen.equals("") || newUser.equals("")) {
                    JOptionPane.showMessageDialog(null, "all sections are mandatory *");
                } else {
                    //ConnectionCode conn = new ConnectionCode();
                    //String query = "insert into signupSecond values('"+formNo+"','"+religian+"','"+catagory+"','"+income+"','"+sEducation+"','"+occupation+"','"+pan+"','"+aadhaar+"','"+citizen+"','"+newUser+"')";
                    //conn.st.executeUpdate(query);

                    // next button to 3rd window
                    setVisible(false);
                    new SignupThirdW(formNo, name, fatherName, birthDate, maried, phoneNo, mail, accType, address, city, state, pin, religian, catagory, income, sEducation, occupation, pan, aadhaar, citizen, newUser).setVisible(true);
                }

            } catch (Exception e1) {
                System.out.println(e1);
            }
        }
        else if (e.getSource() == prevButton) {
            SignupFirstW prevWindow = new SignupFirstW();
            prevWindow.setVisible(true);

            this.setVisible(false);
        }


    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLACK);
        g.drawLine(0, 75, 700, 75);
        g.drawLine(0, 525, 700, 525);
    }

    public static void main(String[] args) {
        new SignupSecondW("","","","","","","","","","","","");
    }
}
