import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Date;

public class busTicket extends JFrame implements ActionListener {
    private JTextArea ticket;
    private String t_from_string, t_to_string, t_date_string, t_type_string, t_class_string, seat_no_string, bus_name, bus_id, t_price;
    private ImageIcon icon_img;
    private JButton okay_button, print_button;
    private Customer customer;
    private Color buttonColors = new Color(171, 205, 239);
    private Font font = new Font("Monospaced", Font.BOLD, 16);

    public busTicket(int t_id_int, Customer customer) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelley", "root", "travel");

        //Reading from view BusJourneys.
        String query = "select * from busJourneys where t_id = ?";
        PreparedStatement pstm = conn.prepareStatement(query);
        pstm.setString(1, Integer.toString(t_id_int));
        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()){
            t_from_string = resultSet.getString("t_from");
            t_to_string = resultSet.getString("t_to");
            t_date_string = resultSet.getString("t_date");
            t_type_string = resultSet.getString("t_type");
            t_class_string = resultSet.getString("t_class");
            seat_no_string = resultSet.getString("seat_no");
            t_price = resultSet.getString("t_price");
            bus_id = resultSet.getString("bus_id");
            bus_name = resultSet.getString("bus_name");
        }
        this.customer = customer;

        //Icon of application
        icon_img = new ImageIcon("images/icon.png");
        setIconImage(icon_img.getImage());

        //okay_button
        okay_button = new JButton("OKAY");
        okay_button.setBounds(85, 420, 80, 30);
        add(okay_button);
        okay_button.setFocusable(false);
        okay_button.addActionListener(this);
        okay_button.setBackground(buttonColors);

        //print_button
        print_button = new JButton("PRINT");
        print_button.setBounds(175, 420, 80, 30);
        print_button.setFocusable(false);
        add(print_button);
        print_button.addActionListener(this);
        print_button.setBackground(buttonColors);

        //Getting device date
        Date currentDate = new Date();
        String printDate = currentDate.toString();

        //Text Area for ticket
        ticket = new JTextArea();
        ticket.setBounds(400, 110, 350, 490);
        ticket.setFont(font);
        ticket.setText(" ***********************************\n");
        ticket.setText(ticket.getText() + " ********    BUS TICKET     ********\n");
        ticket.setText(ticket.getText() + " ***********************************\n");
        ticket.setText(ticket.getText() + "\n Ticket ID     : " + t_id_int);
        ticket.setText(ticket.getText() + "\n From          : " + t_from_string);
        ticket.setText(ticket.getText() + "\n To            : " + t_to_string);
        ticket.setText(ticket.getText() + "\n Date          : " + t_date_string);
        ticket.setText(ticket.getText() + "\n Bus Name      : " + bus_name+"_ENTERPRISE");
        ticket.setText(ticket.getText() + "\n Bus ID        : " + bus_id);
        ticket.setText(ticket.getText() + "\n Bus Type      : " + t_class_string);
        ticket.setText(ticket.getText() + "\n Seat Number   : " + seat_no_string);
        ticket.setText(ticket.getText() + "\n Ticket Price  : " + t_price);
        ticket.setText(ticket.getText() + "\n Customer ID   : " + customer.getCus_id());
        ticket.setText(ticket.getText() + "\n Customer Name : " + customer.getCus_name());
        ticket.setText(ticket.getText() + "\n Contact       : " + customer.getCus_phoneno());
        ticket.setText(ticket.getText() + "\n Email         : " + customer.getCus_email());
        ticket.setText(ticket.getText() + "\n PrintDate: ");
        ticket.setText(ticket.getText() + "\n " + printDate + "\n\n");
        add(ticket);
        ticket.setEditable(false);

        //MAIN JFRAME
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Ticket");
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        ticket.setBackground(new Color(230, 230, 250));
        conn.close();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == okay_button) {
            setVisible(false);
            new BusHomepage(customer);
        } else if (e.getSource() == print_button) {
            try {
                ticket.print();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
