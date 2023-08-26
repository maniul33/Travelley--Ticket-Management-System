import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class userLoginInterface extends JFrame {
    private JLabel welcomeLabel, selectLabel, trainImage;
    private JButton logoutButton, trainButton, busButton;
    private Customer customer;
    private Font f = new Font("Arial", Font.BOLD, 14);
    private Color fontColor = new Color(240, 248, 255);
    private Color bgColor = new Color(0, 123, 167);
    private Color buttonColor = new Color(0, 51, 102);
    private Border border = BorderFactory.createLineBorder(Color.DARK_GRAY, 2);

    userLoginInterface(Customer customer) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setBounds(100, 50, 600, 400);
        this.setTitle("Travelley - User");
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.customer = customer;

        ImageIcon icon = new ImageIcon("images/icon.png");
        this.setIconImage(icon.getImage());

        welcomeLabel = new JLabel("Welcome, " + customer.getCus_name());
        welcomeLabel.setBounds(50, 30, 300, 60);
        welcomeLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        welcomeLabel.setForeground(fontColor);
        this.add(welcomeLabel);

        selectLabel = new JLabel("Select one option - ");
        selectLabel.setBounds(230, 100, 200, 40);
        selectLabel.setFont(f);
        selectLabel.setForeground(fontColor);
        this.add(selectLabel);

        trainButton = new JButton("Train");
        trainButton.setSize(100, 40);
        trainButton.setLocation(150, 180);
        trainButton.setForeground(fontColor);
        trainButton.setBackground(buttonColor);
        trainButton.setFocusable(false);
        trainButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new TrainHomepage(customer);
            }
        });
        this.add(trainButton);

        busButton = new JButton("Bus");
        busButton.setSize(100, 40);
        busButton.setLocation(350, 180);
        busButton.setForeground(fontColor);
        busButton.setBackground(buttonColor);
        busButton.setFocusable(false);
        busButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new BusHomepage(customer);
            }
        });
        this.add(busButton);

        logoutButton = new JButton("Log Out");
        logoutButton.setSize(100, 30);
        logoutButton.setLocation((getWidth() - logoutButton.getWidth()) / 2, 300);
        logoutButton.setForeground(fontColor);
        logoutButton.setBackground(new Color(144, 0, 32));
        logoutButton.setFocusable(false);
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new homepage();
            }
        });
        this.add(logoutButton);

        this.getContentPane().setBackground(bgColor);
        this.setVisible(true);
    }
}
