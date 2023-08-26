import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class login extends JFrame{
    private JLabel pleaseLogInLabel, enterIDLabel, enterPasswordLabel, signupLabel;
    private JTextField idField;
    private JPasswordField passwordField;
    private JButton loginButton, clearButton, homeButton, signupButton;
    private Customer customer;
    private Font f = new Font("Arial", Font.BOLD,14);
    private Color fontColor = new Color(240,248,255);
    private Color bgColor = new Color(0,123,167);
    private Color buttonColor = new Color(0,51,102);
    private Border border = BorderFactory.createLineBorder(Color.DARK_GRAY,2);
    login()
    {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setBounds(100,50,600,400);
        this.setTitle("Travelley - Log in");
        this.setLayout(null);
        this.setLocationRelativeTo(null);

        ImageIcon icon = new ImageIcon("images/icon.png");
        this.setIconImage(icon.getImage());

        pleaseLogInLabel = new JLabel("Please Log in :");
        pleaseLogInLabel.setBounds(50,50,250,50);
        pleaseLogInLabel.setFont(f);
        pleaseLogInLabel.setForeground(fontColor);
        this.add(pleaseLogInLabel);

        enterIDLabel = new JLabel("Enter UserID / Email : ");
        enterIDLabel.setFont(f);
        enterIDLabel.setBounds(155,90,250,50);
        enterIDLabel.setForeground(fontColor);
        this.add(enterIDLabel);

        idField = new JTextField();
        idField.setBounds(310,100,200,30);
        idField.setBorder(border);
        this.add(idField);

        enterPasswordLabel = new JLabel("Enter password : ");
        enterPasswordLabel.setFont(f);
        enterPasswordLabel.setBounds(155,130,250,50);
        enterPasswordLabel.setForeground(fontColor);
        this.add(enterPasswordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(310,140,200,30);
        passwordField.setBorder(border);
        this.add(passwordField);

        clearButton = new JButton("Clear");
        clearButton.setBounds(310,190,90,30);
        clearButton.setForeground(fontColor);
        clearButton.setBackground(buttonColor);
        clearButton.setFocusable(false);
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                idField.setText("");
                passwordField.setText("");
            }
        });
        this.add(clearButton);

        loginButton = new JButton("Log In");
        loginButton.setBounds(420,190,90,30);
        loginButton.setFont(f);
        loginButton.setForeground(fontColor);
        loginButton.setBackground(buttonColor);
        loginButton.setFocusable(false);
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String idORemail = idField.getText();
                String password = passwordField.getText();
                if (idORemail.isEmpty() || password.isEmpty())
                {
                    JOptionPane.showMessageDialog(null,"Please enter all the informations!","Empty box",JOptionPane.WARNING_MESSAGE);
                }
                else
                {
                    Boolean idPassFound = false;
                    if (idORemail.length()==4)
                    {
                        idORemail = "2023"+idORemail;
                    }
                    String ID_PASS = idORemail+password;
                    try{
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        String url = "jdbc:mysql://localhost:3306/travelley";
                        String username = "root";
                        String pass = "travel";
                        Connection conn = DriverManager.getConnection(url,username,pass);
                        String query = "select cus_name, cus_id, cus_email, cus_pass, cus_phoneNo from customer";
                        PreparedStatement preparedStatement = conn.prepareStatement(query);
                        ResultSet resultSet = preparedStatement.executeQuery();
                        Customer customerToLoggedIn = null;

                        while(resultSet.next())
                        {
                            String name = resultSet.getString("cus_name");
                            String id = resultSet.getString("cus_id");
                            String email = resultSet.getString("cus_email");
                            String passWord = resultSet.getString("cus_pass");
                            String phone = resultSet.getString("cus_phoneNo");
                            String idPass = id+passWord;
                            String emailPass = email+passWord;

                            if(ID_PASS.equals(emailPass) || ID_PASS.equals(idPass))
                            {
                                idPassFound = true;
                                customerToLoggedIn = new Customer(name, Integer.parseInt(id), email, phone, passWord);
                                break;
                            }
                        }

                        if(!idPassFound && !ID_PASS.equals("adminadmin"))
                        {
                            JOptionPane.showMessageDialog(null,"Invalid ID or Password!\nPlease try again!","Wrong Info",JOptionPane.YES_OPTION);
                        }
                        else if(ID_PASS.equals("adminadmin"))
                        {
                            setVisible(false);
                            new admin();
                        }
                        else
                        {
                            setVisible(false);
                            new userLoginInterface(customerToLoggedIn);
                        }
                        conn.close();
                    }catch (Exception exception)
                    {
                        exception.printStackTrace();
                    }
                }

            }
        });
        this.add(loginButton);

        signupLabel = new JLabel("Don't have any account? Please ");
        signupLabel.setFont(new Font("Arial",Font.ITALIC,12));
        signupLabel.setBounds(190,240,250,50);
        signupLabel.setForeground(fontColor);
        this.add(signupLabel);

        signupButton = new JButton("SignUp");
        signupButton.setBounds(345,250,80,30);
        signupButton.setFont(new Font("Arial",Font.ITALIC,12));
        signupButton.setForeground(fontColor);
        signupButton.setBackground(new Color(0,0,0,0));
        signupButton.setOpaque(false);
        signupButton.setBorderPainted(false);
        signupButton.setFocusable(false);
        signupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new signup();
            }
        });
        this.add(signupButton);

        homeButton = new JButton("Home");
        homeButton.setSize(90,30);
        homeButton.setLocation((getWidth()-homeButton.getWidth())/2,300);
        homeButton.setForeground(fontColor);
        homeButton.setBackground(new Color(144,0,32));
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