import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

public class BusHomepage extends JFrame implements ActionListener {
    private JLabel bgImage, welcome_label, destination_label, departure_label, t_from_label, t_to_label, day_label, month_label, year_label, selectBusType_label, selectSeat_label;
    private JComboBox<String> t_from_combo, t_to_combo, day_combo, month_combo, year_combo, seat_button_combo;
    private String t_from_string, t_to_string, t_date_string, t_type_string = "B-TICKET", t_id_string, t_class_string, seat_no_string;
    private int fromIndex, toIndex, t_id_int = 0;
    private JButton logout_button, back_button;
    public static JButton buy_button;
    private Customer customer;
    private JRadioButton ac_radioButton, nonac_radioButton;
    private Font font = new Font("Arial", Font.BOLD, 16);
    private Color color = new Color(113, 140, 176);
    private Border border = BorderFactory.createLineBorder(Color.DARK_GRAY, 2);
    private Color fontColor = new Color(240, 248, 255);
    private Color buyButton_color = new Color(0, 172, 193);
    private Color backAndLogoutButton_color = new Color(255, 3, 62);

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
                t_id_string = "1010" + randomID;
                t_id_int = Integer.parseInt(t_id_string);
                if (!existingIDs.contains(t_id_int)) {
                    uniqueIdFound = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    BusHomepage(Customer customer) {
        ImageIcon icon = new ImageIcon("images/icon.png");
        this.setIconImage(icon.getImage());
        this.customer = customer;
        //Calling generateID() to generate t_id
        generateID();

        //Welcome label
        welcome_label = new JLabel("WELCOME, "+customer.getCus_name());
        welcome_label.setBounds(20, 10, 300, 50);
        welcome_label.setFont(font);
        add(welcome_label);

        //Destination label
        destination_label = new JLabel("DESTINATIOM:-");
        destination_label.setBounds(20, 55, 150, 30);
        destination_label.setFont(font);
        add(destination_label);

        //Ticket from label
        t_from_label = new JLabel("FROM:");
        t_from_label.setBounds(20, 80, 100, 30);
        t_from_label.setFont(font);
        add(t_from_label);

        String[] fromStation = {"NONE", "DHAKA", "SYLHET", "CHITTAGONG", "RAJSHAHI"};
        t_from_combo = new JComboBox<>(fromStation);
        t_from_combo.setBounds(20, 110, 120, 40);
        t_from_combo.setBorder(border);
        add(t_from_combo);
        t_from_combo.setFocusable(false);

        //Ticket to label
        t_to_label = new JLabel("TO:");
        t_to_label.setBounds(180, 80, 100, 30);
        t_to_label.setFont(font);
        add(t_to_label);

        String[] toStation = {"NONE", "DHAKA", "SYLHET", "CHITTAGONG", "RAJSHAHI"};
        t_to_combo = new JComboBox(toStation);
        t_to_combo.setBounds(180, 110, 120, 40);
        t_to_combo.setBorder(border);
        add(t_to_combo);
        t_to_combo.setFocusable(false);

        //Days label for t_date_String
        day_label = new JLabel("DATE:");
        day_label.setBounds(20, 190, 100, 40);
        day_label.setFont(font);
        add(day_label);

        String[] dayList = {"NONE", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        day_combo = new JComboBox(dayList);
        day_combo.setBounds(20, 230, 120, 40);
        day_combo.setBorder(border);
        add(day_combo);
        day_combo.setFocusable(false);

        //Departure label
        departure_label = new JLabel("DEPARTURE:-");
        departure_label.setBounds(20, 170, 150, 30);
        departure_label.setFont(font);
        add(departure_label);

        //Months label for t_date_String
        month_label = new JLabel("MONTH:");
        month_label.setBounds(165, 190, 100, 30);
        month_label.setFont(font);
        add(month_label);

        String[] monthList = {"NONE", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        month_combo = new JComboBox(monthList);
        month_combo.setBounds(165, 230, 120, 40);
        month_combo.setBorder(border);
        add(month_combo);
        month_combo.setFocusable(false);

        //Years label for t_date_String
        year_label = new JLabel("YEAR:");
        year_label.setBounds(310, 190, 100, 30);
        year_label.setFont(font);
        add(year_label);

        String[] yearList = {"NONE", "2023", "2024", "2025", "2026"};
        year_combo = new JComboBox(yearList);
        year_combo.setBounds(310, 230, 120, 40);
        year_combo.setBorder(border);
        add(year_combo);
        year_combo.setFocusable(false);

        //Train type label
        selectBusType_label = new JLabel("CLASS:");
        selectBusType_label.setBounds(20, 380, 200, 40);
        selectBusType_label.setFont(font);
        add(selectBusType_label);

        ac_radioButton = new JRadioButton("AC");
        ac_radioButton.setBounds(20, 415, 130, 30);
        ac_radioButton.setFont(font);
        add(ac_radioButton);
        ac_radioButton.setFocusable(false);
        ac_radioButton.addActionListener(this);
        ac_radioButton.setBackground(null);
        ac_radioButton.setOpaque(false);

        nonac_radioButton = new JRadioButton("NON-AC");
        nonac_radioButton.setBounds(20, 450, 130, 30);
        nonac_radioButton.setFont(font);
        add(nonac_radioButton);
        nonac_radioButton.setFocusable(false);
        nonac_radioButton.addActionListener(this);
        nonac_radioButton.setBackground(null);
        nonac_radioButton.setOpaque(false);

        //Button group for ac and non-ac
        ButtonGroup group = new ButtonGroup();
        group.add(ac_radioButton);
        group.add(nonac_radioButton);


        //Seat label
        selectSeat_label = new JLabel("SEAT-NO:");
        selectSeat_label.setBounds(20, 300, 100, 30);
        selectSeat_label.setFont(font);
        add(selectSeat_label);

        String[] seatButton = {"NONE", "B01", "B02", "B03", "B04", "B05", "B06", "B07", "B08", "B09", "B10", "B11", "B12", "B13", "B14", "B15", "B16", "B17", "B18", "B19", "B20"};
        seat_button_combo = new JComboBox(seatButton);
        seat_button_combo.setBounds(20, 330, 120, 40);
        seat_button_combo.setBorder(border);
        add(seat_button_combo);
        seat_button_combo.setFocusable(false);

        //Buy button
        buy_button = new JButton("BUY TICKET");
        buy_button.setBounds(20, 510, 280, 40);
        add(buy_button);
        buy_button.addActionListener(this);
        buy_button.setBackground(buyButton_color);
        buy_button.setFocusable(false);

        //Back button
        back_button = new JButton("BACK");
        back_button.setBounds(320, 510, 100, 40);
        add(back_button);
        back_button.addActionListener(this);
        back_button.setBackground(backAndLogoutButton_color);
        back_button.setFocusable(false);
        back_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new userLoginInterface(customer);
            }
        });

        //Logout button
        logout_button = new JButton("LOGOUT");
        logout_button.setBounds(20, 560, 120, 40);
        add(logout_button);
        logout_button.addActionListener(this);
        logout_button.setBackground(backAndLogoutButton_color);
        logout_button.setFocusable(false);
        logout_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new homepage();
            }
        });

        //MAIN JFRAME
        setTitle("Bus Ticket Booking");
        setLayout(null);
        setResizable(false);
        setSize(1000, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        getContentPane().setBackground(color);
        bgImage = new JLabel(new ImageIcon("images/bgImage.png"));
        bgImage.setBounds(0, 0, 1470, 650);
        add(bgImage);
        bgImage.repaint();
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
            } else if (((t_from_combo.getSelectedIndex() == 2 || t_to_combo.getSelectedIndex() == 2) && (t_to_combo.getSelectedIndex() == 3 || t_from_combo.getSelectedIndex() == 3))) {
                JOptionPane.showMessageDialog(this, "Sorry no bus available in this route.");
            } else if ((day_combo.getSelectedIndex() == 0) || (month_combo.getSelectedIndex() == 0) || (year_combo.getSelectedIndex() == 0)) {
                JOptionPane.showMessageDialog(this, "Please select travel date.");
            } else if ((((t_from_combo.getSelectedIndex() == 1 || t_to_combo.getSelectedIndex() == 1) && (t_to_combo.getSelectedIndex() == 3 || t_from_combo.getSelectedIndex() == 3)) && ac_radioButton.isSelected()) || ((t_from_combo.getSelectedIndex() == 2 || t_to_combo.getSelectedIndex() == 2) && (t_to_combo.getSelectedIndex() == 4 || t_from_combo.getSelectedIndex() == 4) && ac_radioButton.isSelected()) || (((t_from_combo.getSelectedIndex() == 3 || t_to_combo.getSelectedIndex() == 3) && (t_to_combo.getSelectedIndex() == 4 || t_from_combo.getSelectedIndex() == 4)) && ac_radioButton.isSelected())) {
                JOptionPane.showMessageDialog(this, "AC class is not available for this route.");
            } else if ((((t_from_combo.getSelectedIndex() == 1 || t_to_combo.getSelectedIndex() == 1) && (t_to_combo.getSelectedIndex() == 4 || t_from_combo.getSelectedIndex() == 4)) && nonac_radioButton.isSelected()) || (((t_from_combo.getSelectedIndex() == 1 || t_to_combo.getSelectedIndex() == 1) && (t_to_combo.getSelectedIndex() == 2 || t_from_combo.getSelectedIndex() == 2)) && nonac_radioButton.isSelected())) {
                JOptionPane.showMessageDialog(this, "NON-AC class is not available for this route.");
            } else if (seat_button_combo.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(null, "Please select a seat.");
            } else if (!ac_radioButton.isSelected() && !nonac_radioButton.isSelected()) {
                JOptionPane.showMessageDialog(null, "Please select a bus class.");
            } else {
                t_from_string = (String) t_from_combo.getSelectedItem();
                t_to_string = (String) t_to_combo.getSelectedItem();

                fromIndex = (int) t_from_combo.getSelectedIndex();
                toIndex = (int) t_to_combo.getSelectedIndex();
                seat_no_string = (String) seat_button_combo.getSelectedItem();

                String day = (String) day_combo.getSelectedItem();
                String month = (String) month_combo.getSelectedItem();
                String year = (String) year_combo.getSelectedItem();
                t_date_string = year + "-" + month + "-" + day;
                int cus_id = 20231145;

                BusDetails busDetails = new BusDetails(fromIndex,toIndex);
                int t_price = Integer.parseInt(busDetails.getT_price());
                int bus_id = Integer.parseInt(busDetails.getBus_id());
                String bus_name = busDetails.getBus_name();
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelley", "root", "travel");

                    //Writing into purchase table
                    String PurchaseQuery = "insert into purchase(t_id, t_from, t_to, t_date, t_type, t_class, seat_no, t_price, cus_id ) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement pstm = conn.prepareStatement(PurchaseQuery);
                    pstm.setInt(1, t_id_int);
                    pstm.setString(2, t_from_string);
                    pstm.setString(3, t_to_string);
                    //Converting string type date into sql date
                    Date t_date = Date.valueOf(t_date_string);
                    pstm.setDate(4, t_date);
                    pstm.setString(5, t_type_string);
                    pstm.setString(6, t_class_string);
                    pstm.setString(7, seat_no_string);
                    pstm.setInt(8, t_price);
                    pstm.setInt(9, customer.getCus_id());
                    pstm.execute();

                    //Writing into ticket table
                    String TicketQuery = "insert into ticket (t_id, t_from, t_to, t_date, t_type, t_class, seat_no, t_price) values (?, ?, ?, ?, ?, ?, ?, ?)";
                    pstm = conn.prepareStatement(TicketQuery);
                    pstm.setInt(1, t_id_int);
                    pstm.setString(2, t_from_string);
                    pstm.setString(3, t_to_string);
                    //Converting string type date into sql date
                    Date t_date_forTicketTable = Date.valueOf(t_date_string);
                    pstm.setDate(4, t_date_forTicketTable);
                    pstm.setString(5, t_type_string);
                    pstm.setString(6, t_class_string);
                    pstm.setString(7, seat_no_string);
                    pstm.setInt(8, t_price);
                    pstm.execute();

                    //Writing into Belong table
                    String BelongQuery = "insert into belong (t_id, bus_id) values (?, ?)";
                    pstm = conn.prepareStatement(BelongQuery);
                    pstm.setInt(1, t_id_int);
                    pstm.setInt(2, bus_id);
                    pstm.execute();

                    //Writing into Manage table
                    String ad_username = "admin";
                    String ManageQuery = "insert into manage (t_id, t_from, t_to, t_date, t_type, t_class, seat_no, t_price, ad_username) values (?, ?, ?, ?, ?, ?, ?, ?,?)";
                    pstm = conn.prepareStatement(ManageQuery);
                    pstm.setInt(1, t_id_int);
                    pstm.setString(2, t_from_string);
                    pstm.setString(3, t_to_string);
                    pstm.setDate(4, t_date_forTicketTable);
                    pstm.setString(5, t_type_string);
                    pstm.setString(6, t_class_string);
                    pstm.setString(7, seat_no_string);
                    pstm.setInt(8, t_price);
                    pstm.setString(9, ad_username);
                    pstm.execute();
                    conn.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                try {
                    new busTicket(t_id_int, customer);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                setVisible(false);
            }
        }
    }
}
