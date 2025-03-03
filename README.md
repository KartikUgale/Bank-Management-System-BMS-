# **🏦 Bank Management System**  

This is a **Java-based Bank Management System** designed to function like an ATM. Users can create an account, log in using their ATM card number and PIN, and perform various banking transactions.

---

## 📌 **Features**
🔑 User authentication (Card Number & PIN)      
🏧 ATM Machine UI      
🪙 Balance inquiry  
💰 Cash withdrawal & deposit  
🔏 PIN change functionality  
⌛ Fast cash withdrawal options  
📃 Receipt generation    
🛡️ Secure SQL-based database  

---

## 🛠️ **Tech Stack**
- **Java (Swing, AWT)** – GUI development  
- **MySQL** – Database for storing user data  
- **JDBC** – Connecting Java with MySQL  
- **Git & GitHub** – Version control  

---
> **`NOTE:`** I have not removed the `out` folder because it contains `BMS.jar`. Anyone who wants to experience my project can download `BMS.jar` and run the software.

---

## 📂 **Project Structure**
```
🗂️Bank-Management-System        
|
│── libs    # Libraries folder
│   |── jcalender-1.4.jar   # Provides a graphical calendar for selecting dates.
│   |── mysql-connector-j-9.2.0.jar # JDBC driver for connecting Java applications to MySQL databases.
|
│── out>artifact>BMS.jar
│── ScreenShots
│── src/main
│   |── java/com/bms/
│   |    │── Login.java              # User authentication (Main File)
│   |    │── Transaction.java        # main ATM menu
│   |    │── BalanceEnquiry.java     # Balance checking
│   |    │── WithdrawAmount.java           # Cash withdrawal
│   |    │── FastCashMoney.java      # Fast Cash Withdrawal
│   |    │── Deposit.java            # Cash deposit
│   |    │── ChangePin.java          # PIN change functionality
│   |    │── ConnectionCode.java     # Database connection
│   |    │── ReceiptWindow.java      # Transaction Receipt
│   |    │── SignupFirstW.java       # New user Form-1
│   |    │── SignupSecondW.java      # New user Form-2
│   |    │── SignupThirdW.java       # New user Form-3
│   |    │── Submit.java             # Card Number & Pin for new User
|   |
|   │── resources/Imgs/         # UI assets (logos, buttons)
|       │── atmcard.png
|       │── ATMmcUI.jpg
|       │── bankLogo.jpg
|       │── LeftEdgeDesign.jpg
|       │── receipt.png
|       │── RightEdgeDesign.jpg
|
│── README.md      # Documentation (You are currently reading this 😁)
```

---

## 🍵 **How to Run the Project**
### **1️. Clone the Repository**
```sh
git clone https://github.com/your-username/Bank-Management-System.git
cd Bank-Management-System
```

### **2. Add an External Library in IntelliJ IDEA**
```
IntelliJ IDEA  
│  
├── Open Your Project  
│  
├── Go to "File"  
│  
├── Select "Project Structure" (press-> Ctrl + Alt + Shift + S)  
│  
├── In the Left Panel, Click "Libraries" 
│  
├── Click "+ (Add)" → Choose "Java"  
│  
├── Navigate to the ".jar" File (mysql-connector-j-9.2.0.jar & jcalender-1.4.jar)
│  
├── Select the '.jar" File → Click "OK"  
│  
├── Apply Changes → Click "OK"  
```

### **3. Setup Database**
- Import `bank_management.sql` into MySQL  
- Update **ConnectionCode.java** with your MySQL credentials  

### **4. Run the Project**
```sh
javac -d bin src/main/java/com/bms/*.java
java -cp bin com.bms.Login
```
> OR simply Download BMS.jar file and run it.
---
---

## 🎯 **Future Improvements**
- [x] Improve Receipt UI
- [x] Improve safety using PreparedStatement with place holder `'?'`
- [ ] Improve UI with modern design  
- [ ] Implement mobile banking support
---

## 🤝 **Contributing**
Want to improve this project? Fork it, create a feature branch, and submit a pull request!  

---

## 📞 **Contact**
🧑‍💻 **Developer:** Kartik Ugale  
📧 **Email:** kartikugale822@gmail.com  
🐈 **GitHub:** [click here](https://github.com/KartikUgale)  
🔎 **Linked in:** [click here](www.linkedin.com/in/kartik-ugale) 

---
