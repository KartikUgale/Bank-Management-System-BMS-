package main.java.com.bms;

import javax.swing.*;
import java.awt.*;

import java.awt.event.*;

public class SignupThirdW extends JFrame implements ActionListener{

    String formNo, name, fatherName, birthDate, maried, phoneNo, mail, accType, address, city, state, pin, religian, catagory, income, sEducation, occupation, pan, aadhaar, citizen, newUser;

    JLabel heading, labelName, labelFather, labelBirthDate, labelMarried, labelPhoneNo, labelMail, labelAccType, labelAddress;
    JLabel labelCity, labelState, labelPin, labelReligion, labelCategory, labelIncome, labelEducation, labelOccupation;
    JLabel labelPan, labelAadhaar, labelCitizen, labelNewUser;

    JButton submit, previous;

    SignupThirdW(String formNo, String name, String fatherName, String birthDate, String maried, String phoneNo, String mail, String accType, String address,
                 String city, String state, String pin, String religian, String catagory, String income, String sEducation, String occupation, String pan, String aadhaar, String citizen, String newUser) {
        this.formNo = formNo; this.name = name; this.fatherName = fatherName; this.birthDate = birthDate; this.maried = maried;
        this.phoneNo = phoneNo; this.mail = mail; this.accType = accType; this.address = address; this.city = city; this.state = state;
        this.pin = pin; this.religian = religian; this.catagory = catagory; this.income = income; this.sEducation = sEducation;
        this.occupation = occupation; this.pan = pan; this.aadhaar = aadhaar; this.citizen = citizen; this.newUser = newUser;

        setSize(700, 850);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBackground(Color.WHITE);
        setResizable(false);

        heading = new JLabel("Confirm Your Details");
        heading.setFont(new Font("Times New Roman", Font.BOLD, 24));
        heading.setBounds(220, 20, 300, 40);
        add(heading);
        setUndecorated(true);


        labelName = new JLabel("Name :   " + this.name);
        labelName.setBounds(100, 100, 200, 30);
        add(labelName);

        labelFather = new JLabel("Father's Name :    " + this.fatherName);
        labelFather.setBounds(100, 130, 200, 30);
        add(labelFather);

        labelBirthDate = new JLabel("Birth Date :   " + this.birthDate);
        labelBirthDate.setBounds(100, 160, 200, 30);
        add(labelBirthDate);

        labelMarried = new JLabel("Married :    " + this.maried);
        labelMarried.setBounds(100, 190, 200, 30);
        add(labelMarried);

        labelPhoneNo = new JLabel("Phone No :    " + this.phoneNo);
        labelPhoneNo.setBounds(100, 220, 200, 30);
        add(labelPhoneNo);

        labelMail = new JLabel("Email :  " + this.mail);
        labelMail.setBounds(100, 250, 200, 30);
        add(labelMail);

        labelAccType = new JLabel("Account Type :    " + this.accType);
        labelAccType.setBounds(100, 280, 200, 30);
        add(labelAccType);

        labelAddress = new JLabel("Address :     " + this.address);
        labelAddress.setBounds(100, 310, 200, 30);
        add(labelAddress);

        labelCity = new JLabel("City :   " + this.city);
        labelCity.setBounds(100, 340, 200, 30);
        add(labelCity);

        labelState = new JLabel("State :     " + this.state);
        labelState.setBounds(100, 370, 200, 30);
        add(labelState);

        labelPin = new JLabel("PIN Code :    " + this.pin);
        labelPin.setBounds(100, 400, 200, 30);
        add(labelPin);

        labelReligion = new JLabel("Religion :   " + this.religian);
        labelReligion.setBounds(100, 430, 200, 30);
        add(labelReligion);

        labelCategory = new JLabel("Category :   " + this.catagory);
        labelCategory.setBounds(100, 460, 200, 30);
        add(labelCategory);

        labelIncome = new JLabel("Income :   " + this.income);
        labelIncome.setBounds(100, 490, 200, 30);
        add(labelIncome);

        labelEducation = new JLabel("Education :     " + this.sEducation);
        labelEducation.setBounds(100, 520, 200, 30);
        add(labelEducation);

        labelOccupation = new JLabel("Occupation :   " + this.occupation);
        labelOccupation.setBounds(100, 550, 200, 30);
        add(labelOccupation);

        labelPan = new JLabel("PAN :     " + this.pan);
        labelPan.setBounds(100, 580, 200, 30);
        add(labelPan);

        labelAadhaar = new JLabel("Aadhaar :     " + this.aadhaar);
        labelAadhaar.setBounds(100, 610, 200, 30);
        add(labelAadhaar);

        labelCitizen = new JLabel("Citizen :    " + this.citizen);
        labelCitizen.setBounds(100, 640, 200, 30);
        add(labelCitizen);

        labelNewUser = new JLabel("New User :    " + this.newUser);
        labelNewUser.setBounds(100, 670, 200, 30);
        add(labelNewUser);

        previous = new JButton("← Fill Prev again");
        previous.setFont(new Font("Railway", Font.PLAIN, 16));
        previous.setBackground(Color.WHITE);
        previous.setForeground(Color.BLACK);
        previous.addActionListener(this);
        previous.setBounds(90, 750, 190, 25);
        add(previous);

        submit = new JButton("Submit");
        submit.setFont(new Font("Railway", Font.PLAIN, 16));
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        submit.setBounds(460, 750, 100, 30);
        add(submit);


        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {    // save data after pressed submit button
        try {


        } catch (Exception all){}
        String confirm = "";
        if(e.getSource() == submit) {
            try {
                JOptionPane.showMessageDialog(null, "Data Saved!");
                ConnectionCode conn = new ConnectionCode();
                String query = "insert into signUp values('"+formNo+"','"+name+"','"+fatherName+"','"+birthDate+"','"+maried+"','"+phoneNo+"','"+mail+"','"+accType+"','"+address+"','"+city+"','"+state+"','"+pin+"','"+religian+"','"+catagory+"','"+income+"','"+sEducation+"','"+occupation+"','"+pan+"','"+aadhaar+"','"+citizen+"','"+newUser+"')";
                conn.st.executeUpdate(query);
                System.out.println("Run---------");

                Submit s = new Submit(formNo);
                s.setVisible(true);
                this.setVisible(false);

            } catch (Exception e1) {
                System.out.println(e1);
                JOptionPane.showMessageDialog(null, "Something wrong!");
                JOptionPane.showMessageDialog(null, e1);

            }
        }
        else if(e.getSource() == previous) {
            SignupSecondW previousWindow = new SignupSecondW("","","","","","","","","","","","");
            previousWindow.setVisible(true);
            this.setVisible(false);
        }

    }

    public static void main(String[] args) {
        new SignupThirdW("","","","","","","","","","","","","","","","","","","","","");
    }
}
