import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class homepage extends JFrame {
    private JLabel bgImage, logo;
    private JButton loginButton, signupButton;
    private Font f = new Font("Arial", Font.BOLD,14);
    private Color buttonColor = new Color(0,151,178);
    homepage()
    {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setBounds(100,50,1100,500);
        this.setTitle("Travelley - Home");
        this.setLayout(null);
        this.setLocationRelativeTo(null);

        ImageIcon icon = new ImageIcon("images/icon.png");
        this.setIconImage(icon.getImage());

        bgImage = new JLabel(new ImageIcon("images/bghome.png"));
        bgImage.setBounds(0,0,750,500);
        this.add(bgImage);

        logo = new JLabel(new ImageIcon("images/logo.png"));
        logo.setBounds(820,50,200,200);
        this.add(logo);

        signupButton = new JButton("Sign Up");
        signupButton.setBounds(870,250,100,30);
        signupButton.setFont(f);
        signupButton.setForeground(Color.WHITE);
        signupButton.setBackground(buttonColor);
        signupButton.setFocusable(false);
        signupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new signup();
            }
        });
        this.add(signupButton);

        loginButton = new JButton("Log In");
        loginButton.setBounds(870,300,100,30);
        loginButton.setFont(f);
        loginButton.setForeground(Color.WHITE);
        loginButton.setBackground(buttonColor);
        loginButton.setFocusable(false);
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new login();
            }
        });
        this.add(loginButton);

        this.getContentPane().setBackground(new Color(246,236,210));
        this.setVisible(true);
    }
}
