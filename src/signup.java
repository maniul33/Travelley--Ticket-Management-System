import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class signup extends JFrame {
    private JLabel welcomeLabel, pleaseSignupLabel, nameLabel, passwordLabel, confirmPasswordLabel, UserIdLabel, emailLabel, contactLabel;
    private JTextField nameField, UserIdField, emailField, contactField;
    private JPasswordField passwordField, confirmPasswordField;
    private JButton clearButton, signupButton, homeButton, getIdButton;
    private Font f = new Font("Arial", Font.BOLD, 14);
    private Color fontColor = new Color(240, 248, 255);
    private Color bgColor = new Color(0, 123, 167);
    private Color buttonColor = new Color(0, 51, 102);
    private Border border = BorderFactory.createLineBorder(Color.DARK_GRAY, 2);

    public int generateID() {
        Random random = new Random();
        int uniqueId = random.nextInt(10000) + 1000;
        if (uniqueId >= 10000) {
            uniqueId -= 1000;
        }
        return uniqueId;
    }

    public Boolean duplicateIdChecker(String id) {
        Boolean idFound = false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/travelley";
            String username = "root";
            String password = "travel";
            Connection conn = DriverManager.getConnection(url, username, password);
            String query = "select cus_id from customer where cus_id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                idFound = true;
            }
            conn.close();
        } catch (Exception ex) {
            ex.getStackTrace();
        }
        return idFound;
    }

    signup() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setBounds(100, 50, 900, 600);
        this.setTitle("Sign Up");
        this.setLayout(null);
        this.setLocationRelativeTo(null);

        ImageIcon icon = new ImageIcon("images/icon.png");
        this.setIconImage(icon.getImage());

        welcomeLabel = new JLabel("WELCOME TO TRAVELLEY!");
        welcomeLabel.setBounds(220, 30, 500, 50);
        welcomeLabel.setFont(new Font("SansSerif", Font.BOLD, 32));
        welcomeLabel.setForeground(fontColor);
        this.add(welcomeLabel);

        pleaseSignupLabel = new JLabel("Please Sign Up :");
        pleaseSignupLabel.setBounds(90, 90, 250, 50);
        pleaseSignupLabel.setFont(f);
        pleaseSignupLabel.setForeground(fontColor);
        this.add(pleaseSignupLabel);

        nameLabel = new JLabel("Enter Your Name : ");
        nameLabel.setFont(f);
        nameLabel.setBounds(220, 170, 250, 50);
        nameLabel.setForeground(fontColor);
        this.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(360, 180, 200, 30);
        nameField.setBorder(border);
        this.add(nameField);

        emailLabel = new JLabel("Enter Email : ");
        emailLabel.setFont(f);
        emailLabel.setBounds(220, 210, 250, 50);
        emailLabel.setForeground(fontColor);
        this.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(360, 220, 200, 30);
        emailField.setBorder(border);
        this.add(emailField);

        contactLabel = new JLabel("Enter Contact : ");
        contactLabel.setFont(f);
        contactLabel.setBounds(220, 250, 250, 50);
        contactLabel.setForeground(fontColor);
        this.add(contactLabel);

        contactField = new JTextField();
        contactField.setBounds(360, 260, 200, 30);
        contactField.setBorder(border);
        this.add(contactField);

        passwordLabel = new JLabel("Enter password : ");
        passwordLabel.setFont(f);
        passwordLabel.setBounds(220, 290, 250, 50);
        passwordLabel.setForeground(fontColor);
        this.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(360, 300, 200, 30);
        passwordField.setBorder(border);
        this.add(passwordField);

        confirmPasswordLabel = new JLabel("Confirm password : ");
        confirmPasswordLabel.setFont(f);
        confirmPasswordLabel.setBounds(220, 330, 250, 50);
        confirmPasswordLabel.setForeground(fontColor);
        this.add(confirmPasswordLabel);

        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(360, 340, 200, 30);
        confirmPasswordField.setBorder(border);
        this.add(confirmPasswordField);

        UserIdLabel = new JLabel("Get your user ID : ");
        UserIdLabel.setFont(f);
        UserIdLabel.setBounds(630, 170, 250, 50);
        UserIdLabel.setForeground(fontColor);
        this.add(UserIdLabel);

        UserIdField = new JTextField();
        UserIdField.setBounds(630, 215, 130, 30);
        UserIdField.setBorder(border);
        UserIdField.setEditable(false);
        this.add(UserIdField);

        getIdButton = new JButton("Get ID");
        getIdButton.setBounds(630, 255, 80, 30);
        getIdButton.setForeground(fontColor);
        getIdButton.setBackground(buttonColor);
        getIdButton.setFocusable(false);
        getIdButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = "2023" + String.valueOf(generateID());
                Boolean idNotFound = false;
                while (!idNotFound) {
                    if (duplicateIdChecker(id)) {
                        id = "2023" + String.valueOf(generateID());
                    } else {
                        idNotFound = true;
                    }
                }
                UserIdField.setText(id);
            }
        });
        this.add(getIdButton);

        clearButton = new JButton("Clear");
        clearButton.setBounds(360, 390, 90, 30);
        clearButton.setForeground(fontColor);
        clearButton.setBackground(buttonColor);
        clearButton.setFocusable(false);
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nameField.setText("");
                nameField.setText("");
                emailField.setText("");
                contactField.setText("");
                passwordField.setText("");
                confirmPasswordField.setText("");
                UserIdField.setText("");
            }
        });
        this.add(clearButton);

        signupButton = new JButton("Sign Up");
        signupButton.setBounds(470, 390, 90, 30);
        signupButton.setForeground(fontColor);
        signupButton.setBackground(buttonColor);
        signupButton.setFocusable(false);
        signupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText().toUpperCase();
                String id = UserIdField.getText();
                String email = emailField.getText();
                String phone = contactField.getText();
                String confirmPasswordFieldText = confirmPasswordField.getText();
                String passwordFieldText = passwordField.getText();
                if (name.isEmpty() || id.isEmpty() || email.isEmpty() || phone.isEmpty() || confirmPasswordFieldText.isEmpty() || passwordFieldText.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please give all the informations!");
                } else {
                    if (passwordFieldText.equals(confirmPasswordFieldText)) {
                        String password = confirmPasswordFieldText;
                        try {

                            //Registering the JDBC Driver
                            Class.forName("com.mysql.cj.jdbc.Driver");

                            //Establishing connection with the database - "TRAVELLEY"
                            String url = "jdbc:mysql://localhost:3306/travelley";
                            String username = "root";
                            String pass = "travel";
                            Connection conn = DriverManager.getConnection(url, username, pass);

                            // Creating and preparing a statement for execution
                            String queryForCustomer = "INSERT INTO customer(cus_name, cus_id, cus_pass, cus_email, cus_phoneno) VALUES(?,?,?,?,?)";

                            // Creating a prepared statement for the customer insertion query
                            PreparedStatement preparedStatementForCustomer = conn.prepareStatement(queryForCustomer);

                            // Setting parameter values for the prepared statement
                            preparedStatementForCustomer.setString(1, name);
                            preparedStatementForCustomer.setString(2, id);
                            preparedStatementForCustomer.setString(3, password);
                            preparedStatementForCustomer.setString(4, email);
                            preparedStatementForCustomer.setString(5, phone);

                            // Executing the prepared statement to insert the data into the database
                            preparedStatementForCustomer.executeUpdate();
                            // Closing the prepared statement to release resources
                            preparedStatementForCustomer.close();


                            String queryForControl = "insert into control(cus_name, cus_id, cus_pass, cus_email, cus_phoneno, ad_username) values(?,?,?,?,?,'admin')";
                            PreparedStatement preparedStatementForControl = conn.prepareStatement(queryForControl);
                            preparedStatementForControl.setString(1, name);
                            preparedStatementForControl.setString(2, id);
                            preparedStatementForControl.setString(3, password);
                            preparedStatementForControl.setString(4, email);
                            preparedStatementForControl.setString(5, phone);
                            preparedStatementForControl.executeUpdate();
                            preparedStatementForControl.close();

                            //Terminating the database connection
                            conn.close();

                            setVisible(false);
                            new login();
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Error!");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Please check password!", "Wrong password", JOptionPane.WARNING_MESSAGE);
                        confirmPasswordField.setText("");
                    }
                }
            }
        });
        this.add(signupButton);

        homeButton = new JButton("Home");
        homeButton.setBounds(670, 450, 90, 30);
        homeButton.setForeground(fontColor);
        homeButton.setBackground(new Color(144, 0, 32));
        homeButton.setFocusable(false);
        homeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new homepage();
            }
        });
        this.add(homeButton);


        this.getContentPane().setBackground(bgColor);
        this.setVisible(true);
    }
}

