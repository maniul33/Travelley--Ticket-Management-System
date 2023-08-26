import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;

public class TrainTicket extends JFrame implements ActionListener
{
    private JTextArea ticket;
    private Customer customer;
    private JButton okay_button, print_button;
    private String t_from_string, t_to_string, t_date_string, t_type_string, t_class_string, seat_no_string, t_price, train_id, train_name;

    public TrainTicket(int t_id, Customer customer) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelley", "root", "travel");

        //Writing into purchase table.
        String query = "select * from trainJourneys where t_id = ?";
        PreparedStatement pstm = conn.prepareStatement(query);
        pstm.setString(1, Integer.toString(t_id));
        ResultSet resultSet = pstm.executeQuery();
        int t_id_int = t_id;
        if(resultSet.next()){
            t_from_string = resultSet.getString("t_from");
            t_to_string = resultSet.getString("t_to");
            t_date_string = resultSet.getString("t_date");
            t_type_string = resultSet.getString("t_type");
            t_class_string = resultSet.getString("t_class");
            seat_no_string = resultSet.getString("seat_no");
            t_price = resultSet.getString("t_price");
            train_id = resultSet.getString("train_id");
            train_name = resultSet.getString("train_name");
        }
        this.customer = customer;

        //Icon of application
        ImageIcon icon_img = new ImageIcon ("images/icon.png");
        setIconImage(icon_img.getImage());

        //okay_button
        okay_button= new JButton("OKAY");
        okay_button.setBounds(85,420,80,30);
        add(okay_button);
        okay_button.setFocusable(false);
        okay_button.addActionListener(this);
        okay_button.setBackground(new Color(171,205,239));
        okay_button.setForeground(Color.WHITE);

        //print_button
        print_button= new JButton("PRINT");
        print_button.setBounds(175,420,80,30);
        print_button.setFocusable(false);
        add(print_button);
        print_button.addActionListener(this);
        print_button.setBackground(new Color(171,205,239));
        print_button.setForeground(Color.WHITE);

        //Getting device date
        Date currentDate= new Date();
        String printDate=currentDate.toString();

        //Text Area for ticket
        ticket= new JTextArea();
        ticket.setBounds(400,110,350,490);
        ticket.setFont(new Font("Monospaced", Font.BOLD,16));
        ticket.setText(" ***********************************\n");
        ticket.setText(ticket.getText()+" *******    TRAIN TICKET     *******\n");
        ticket.setText(ticket.getText()+" ***********************************\n");

        ticket.setText(ticket.getText()+"\n Ticket ID      : "+t_id_int);
        ticket.setText(ticket.getText()+"\n From           : "+t_from_string);
        ticket.setText(ticket.getText()+"\n To             : "+t_to_string);
        ticket.setText(ticket.getText()+"\n Date           : "+t_date_string);
        ticket.setText(ticket.getText()+"\n Train Name     : "+train_name);
        ticket.setText(ticket.getText()+"\n Train ID       : "+train_id);
        ticket.setText(ticket.getText()+"\n Train Type     : "+t_class_string);
        ticket.setText(ticket.getText()+"\n Seat Number    : "+seat_no_string);
        ticket.setText(ticket.getText()+"\n Ticket Price   : "+t_price);
        ticket.setText(ticket.getText()+"\n Customer ID    : "+customer.getCus_id());
        ticket.setText(ticket.getText()+"\n Customer Name  : "+customer.getCus_name());
        ticket.setText(ticket.getText()+"\n Contact        : "+customer.getCus_phoneno());
        ticket.setText(ticket.getText()+"\n Email          : "+customer.getCus_email());

        ticket.setText(ticket.getText()+"\n PrintDate: ");
        ticket.setText(ticket.getText()+"\n "+printDate+"\n\n");
        add(ticket);
        ticket.setEditable(false);

        //MAIN JFRAME
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Ticket");
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        ticket.setBackground(new Color(230,230,250));
        conn.close();
    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==okay_button)
        {
            setVisible(false);
            new TrainHomepage(customer);
        }
        else if(e.getSource()==print_button)
        {
            try
            {
                ticket.print();
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }
}