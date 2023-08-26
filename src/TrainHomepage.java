import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

public class TrainHomepage extends JFrame implements ActionListener {
    private JComboBox<String> t_from_combo, t_to_combo, day_combo, month_combo, year_combo;
    private String t_class_string;
    private int t_id_int = 0;
    private JButton seat_button, back_button, logout_button;
    public static JButton buy_button;
    private Customer customer;
    private JRadioButton ac_radioButton, nonac_radioButton;
    private Font font = new Font("Arial", Font.BOLD, 16);

    //Method for ticket id
    public void generateID() {
        int min = 1000;
        int max = 9999;

        Random random = new Random();
        boolean uniqueIdFound = false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelley", "root", "travel");
            Statement stm = conn.createStatement();
            String query = "select t_id from ticket";
            ResultSet rs = stm.executeQuery(query);
            ArrayList<Integer> existingIDs = new ArrayList<Integer>();
            while (rs.next()) {
                existingIDs.add(rs.getInt(1));
            }
            conn.close();
            while (!uniqueIdFound) {
                int randomID = random.nextInt(max - min + 1) + min;
                String t_id_string = "1010" + randomID;
                t_id_int = Integer.parseInt(t_id_string);
                if (!existingIDs.contains(t_id_int)) {
                    uniqueIdFound = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public TrainHomepage(Customer customer) {
        ImageIcon icon = new ImageIcon("images/icon.png");
        this.setIconImage(icon.getImage());
        this.customer = customer;
        //Calling generateID() to generate t_id
        generateID();

        //Welcome label
        JLabel welcome_label = new JLabel("WELCOME, " + customer.getCus_name());
        welcome_label.setBounds(90, 30, 200, 40);
        welcome_label.setFont(font);
        add(welcome_label);
        welcome_label.setForeground(Color.WHITE);

        //Select destination label
        JLabel selectYourDestination_label = new JLabel("Select Your Destination");
        selectYourDestination_label.setFont(font);
        selectYourDestination_label.setBounds(90, 70, 200, 30);
        add(selectYourDestination_label);
        selectYourDestination_label.setForeground(Color.WHITE);

        //Ticket from label
        JLabel t_from_label = new JLabel("From Station");
        t_from_label.setBounds(90, 120, 200, 40);
        t_from_label.setFont(font);
        add(t_from_label);
        t_from_label.setForeground(Color.WHITE);

        //Ticket from combo box
        String[] fromStation = {"NONE", "DHAKA", "SYLHET", "CHITTAGONG", "RAJSHAHI"};
        t_from_combo = new JComboBox<>(fromStation);
        t_from_combo.setBounds(90, 170, 150, 40);
        t_from_combo.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        add(t_from_combo);
        t_from_combo.setFocusable(false);

        //Ticket to label
        JLabel t_to_label = new JLabel("To Station");
        t_to_label.setBounds(340, 120, 200, 40);
        t_to_label.setFont(font);
        add(t_to_label);
        t_to_label.setForeground(Color.WHITE);

        //Ticket to combo box
        String[] toStation = {"NONE", "DHAKA", "SYLHET", "CHITTAGONG", "RAJSHAHI"};
        t_to_combo = new JComboBox(toStation);
        t_to_combo.setBounds(340, 170, 150, 40);
        t_to_combo.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        add(t_to_combo);
        t_to_combo.setFocusable(false);

        //Days label for t_date_String
        JLabel day_label = new JLabel("Date");
        day_label.setBounds(90, 220, 100, 40);
        day_label.setFont(font);
        add(day_label);
        day_label.setForeground(Color.WHITE);

        //Days combo box
        String[] dayList = {"None", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        day_combo = new JComboBox(dayList);
        day_combo.setBounds(90, 270, 100, 40);
        day_combo.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        add(day_combo);
        day_combo.setFocusable(false);

        //Months label for t_date_String
        JLabel month_label = new JLabel("Month");
        month_label.setBounds(240, 220, 100, 40);
        month_label.setFont(font);
        add(month_label);
        month_label.setForeground(Color.WHITE);

        //Month combo box
        String[] monthList = {"None", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        month_combo = new JComboBox(monthList);
        month_combo.setBounds(240, 270, 100, 40);
        month_combo.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        add(month_combo);
        month_combo.setFocusable(false);

        //Years label for t_date_String
        JLabel year_label = new JLabel("Year");
        year_label.setBounds(390, 220, 100, 40);
        year_label.setFont(font);
        add(year_label);
        year_label.setForeground(Color.WHITE);

        //Year combo box
        String[] yearList = {"None", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040"};
        year_combo = new JComboBox(yearList);
        year_combo.setBounds(390, 270, 100, 40);
        year_combo.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        add(year_combo);
        year_combo.setFocusable(false);

        //Train type label
        JLabel selectTrainType_label = new JLabel("Choose a Class");
        selectTrainType_label.setBounds(90, 320, 250, 40);
        selectTrainType_label.setFont(font);
        add(selectTrainType_label);
        selectTrainType_label.setForeground(Color.WHITE);

        //AC radio button
        ac_radioButton = new JRadioButton("AC");
        ac_radioButton.setBounds(90, 365, 50, 40);
        ac_radioButton.setFont(font);
        add(ac_radioButton);
        ac_radioButton.setFocusable(false);
        ac_radioButton.addActionListener(this);
        ac_radioButton.setBackground(null);
        ac_radioButton.setOpaque(false);
        ac_radioButton.setForeground(Color.WHITE);

        //Non ac radio button
        nonac_radioButton = new JRadioButton("NON-AC");
        nonac_radioButton.setBounds(160, 365, 100, 40);
        nonac_radioButton.setFont(font);
        add(nonac_radioButton);
        nonac_radioButton.setFocusable(false);
        nonac_radioButton.addActionListener(this);
        nonac_radioButton.setBackground(null);
        nonac_radioButton.setOpaque(false);
        nonac_radioButton.setForeground(Color.WHITE);

        //Button group for ac and non-ac
        ButtonGroup group = new ButtonGroup();
        group.add(ac_radioButton);
        group.add(nonac_radioButton);

        //Vertical bars
        int initial_Y = 312;
        for (int i = 0; i < 7; i++) {
            JLabel verticalLine_label = new JLabel("||");
            verticalLine_label.setForeground(Color.LIGHT_GRAY);
            verticalLine_label.setBounds(270, initial_Y, 20, 40);
            add(verticalLine_label);
            initial_Y += 11;
        }

        //Seat label
        JLabel selectSeat_label = new JLabel("Choose a Seat");
        selectSeat_label.setBounds(310, 320, 250, 40);
        selectSeat_label.setFont(font);
        selectSeat_label.setForeground(Color.WHITE);
        add(selectSeat_label);

        //Seat button
        seat_button = new JButton("SELECT YOUR SEAT");
        seat_button.setBounds(310, 365, 165, 35);
        add(seat_button);
        seat_button.addActionListener(this);
        seat_button.setForeground(Color.WHITE);
        seat_button.setBackground(new Color(0, 151, 178));
        seat_button.setFocusable(false);

        //Buy button
        buy_button = new JButton("BUY TICKET");
        buy_button.setBounds(90, 440, 400, 40);
        add(buy_button);
        buy_button.addActionListener(this);
        buy_button.setForeground(Color.WHITE);
        buy_button.setBackground(new Color(0, 172, 193));
        buy_button.setFocusable(false);

        //Back button
        back_button = new JButton("BACK");
        back_button.setBounds(187, 510, 90, 30);
        add(back_button);
        back_button.addActionListener(this);
        back_button.setForeground(Color.WHITE);
        back_button.setBackground(new Color(255, 3, 62));
        back_button.setFocusable(false);


        //Logout button
        logout_button = new JButton("LOGOUT");
        logout_button.setBounds(312, 510, 90, 30);
        add(logout_button);
        logout_button.addActionListener(this);
        logout_button.setForeground(Color.WHITE);
        logout_button.setBackground(new Color(255, 3, 62));
        logout_button.setFocusable(false);

        //MAIN JFRAME
        setTitle("Train Ticket Booking");
        setLayout(null);
        setResizable(false);
        setSize(1000, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        JLabel background_img = new JLabel(new ImageIcon("images/train_standing.png"));
        background_img.setBounds(0, 0, 1000, 650);
        add(background_img);
        background_img.repaint();
    }

    public void actionPerformed(ActionEvent e) {
        if (ac_radioButton.isSelected()) {
            t_class_string = "AC";
        } else if (nonac_radioButton.isSelected()) {
            t_class_string = "NON_AC";
        }
        if (e.getSource() == buy_button) {

            if ((t_from_combo.getSelectedIndex() == 0) || (t_to_combo.getSelectedIndex() == 0)) {
                JOptionPane.showMessageDialog(this, "Please select desired destination.");
            } else if (t_from_combo.getSelectedIndex() == t_to_combo.getSelectedIndex()) {
                JOptionPane.showMessageDialog(this, "You cannot select same destination.");
            } else if (((t_from_combo.getSelectedIndex() == 2) && (t_to_combo.getSelectedIndex() == 3)) || ((t_from_combo.getSelectedIndex() == 3) && (t_to_combo.getSelectedIndex() == 2))) {
                JOptionPane.showMessageDialog(this, "Sorry no trains available in this route.");
            } else if ((day_combo.getSelectedIndex() == 0) || (month_combo.getSelectedIndex() == 0) || (year_combo.getSelectedIndex() == 0)) {
                JOptionPane.showMessageDialog(this, "Please select travel date.");
            } else if ((((t_from_combo.getSelectedIndex() == 1 || t_to_combo.getSelectedIndex() == 1) && (t_to_combo.getSelectedIndex() == 2 || t_from_combo.getSelectedIndex() == 2)) && ac_radioButton.isSelected()) || (((t_from_combo.getSelectedIndex() == 3 || t_to_combo.getSelectedIndex() == 3) && (t_to_combo.getSelectedIndex() == 4 || t_from_combo.getSelectedIndex() == 4)) && ac_radioButton.isSelected())) {
                JOptionPane.showMessageDialog(this, "AC class is not available for this route.");
            } else if ((((t_from_combo.getSelectedIndex() == 1 || t_to_combo.getSelectedIndex() == 1) && (t_to_combo.getSelectedIndex() == 3 || t_from_combo.getSelectedIndex() == 3)) && nonac_radioButton.isSelected()) || (((t_from_combo.getSelectedIndex() == 1 || t_to_combo.getSelectedIndex() == 1) && (t_to_combo.getSelectedIndex() == 4 || t_from_combo.getSelectedIndex() == 4)) && nonac_radioButton.isSelected()) || (((t_from_combo.getSelectedIndex() == 2 || t_to_combo.getSelectedIndex() == 2) && (t_to_combo.getSelectedIndex() == 4 || t_from_combo.getSelectedIndex() == 4)) && nonac_radioButton.isSelected())) {
                JOptionPane.showMessageDialog(this, "NON-AC class is not available for this route.");
            } else if (!Seat.seatIsSelected) {
                JOptionPane.showMessageDialog(null, "Please select a seat.");
            } else {
                String t_from_string = (String) t_from_combo.getSelectedItem();
                String t_to_string = (String) t_to_combo.getSelectedItem();

                int fromIndex = (int) t_from_combo.getSelectedIndex();
                int toIndex = (int) t_to_combo.getSelectedIndex();

                String day = (String) day_combo.getSelectedItem();
                String month = (String) month_combo.getSelectedItem();
                String year = (String) year_combo.getSelectedItem();
                String t_date_string = year + "-" + month + "-" + day;
                String t_type_string = "T-TICKET";

                trainDetails trDetails = new trainDetails(fromIndex, toIndex);
                int t_price = Integer.parseInt(trDetails.getT_price());
                int train_id = Integer.parseInt(trDetails.getTrain_id());
                String train_name = trDetails.getTrain_name();
                //Writing into purchase table
                try {
                    //Establishing connection.
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelley", "root", "travel");

                    //Writing into purchase table.
                    String purchaseQuery = "insert into purchase(t_id, t_from, t_to, t_date, t_type, t_class, seat_no, t_price, cus_id ) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement pstm = conn.prepareStatement(purchaseQuery);
                    pstm.setInt(1, t_id_int);
                    pstm.setString(2, t_from_string);
                    pstm.setString(3, t_to_string);
                    //Converting string type date into sql date
                    Date t_date = Date.valueOf(t_date_string);
                    pstm.setDate(4, t_date);
                    pstm.setString(5, t_type_string);
                    pstm.setString(6, t_class_string);
                    pstm.setString(7, Seat.seat_no);
                    pstm.setInt(8, t_price);
                    pstm.setInt(9, customer.getCus_id());
                    pstm.execute();

                    //Writing into Ticket table.
                    String ticketQuery = "insert into ticket (t_id, t_from, t_to, t_date, t_type, t_class, seat_no, t_price) values (?, ?, ?, ?, ?, ?, ?, ?)";
                    pstm = conn.prepareStatement(ticketQuery);
                    pstm.setInt(1, t_id_int);
                    pstm.setString(2, t_from_string);
                    pstm.setString(3, t_to_string);
                    pstm.setDate(4, t_date);
                    pstm.setString(5, t_type_string);
                    pstm.setString(6, t_class_string);
                    pstm.setString(7, Seat.seat_no);
                    pstm.setInt(8, t_price);
                    pstm.execute();

                    //Writing into Belong table.
                    String belongQuery = "insert into belong(t_id, train_id) values (?, ?)";
                    pstm = conn.prepareStatement(belongQuery);
                    pstm.setInt(1, t_id_int);
                    pstm.setInt(2, train_id);
                    pstm.execute();

                    //Writing into Manage table.
                    String ad_username = "admin";
                    String manageQuery = "insert into manage(t_id, t_from, t_to, t_date, t_type, t_class, seat_no, t_price, ad_username ) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    pstm = conn.prepareStatement(manageQuery);
                    pstm.setInt(1, t_id_int);
                    pstm.setString(2, t_from_string);
                    pstm.setString(3, t_to_string);
                    pstm.setDate(4, t_date);
                    pstm.setString(5, t_type_string);
                    pstm.setString(6, t_class_string);
                    pstm.setString(7, Seat.seat_no);
                    pstm.setInt(8, t_price);
                    pstm.setString(9, ad_username);
                    pstm.execute();

                    new TrainTicket(t_id_int, customer);

                    //Closing connection.
                    conn.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                setVisible(false);
            }
        } else if (e.getSource() == seat_button) {
            new Seat();
        } else if (e.getSource() == back_button) {
            setVisible(false);
            new userLoginInterface(customer);
        } else if (e.getSource() == logout_button) {
            setVisible(false);
            new login();
        }
    }
}
