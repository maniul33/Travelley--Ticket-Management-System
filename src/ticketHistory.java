import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class ticketHistory extends JFrame {
    private JTable busTable, trainTable;
    private JScrollPane busScrollPane, trainScrollPane;
    private JLabel searchLabel;
    private JTextField searchField, ticketIdField, transportTypeField, transportNameField, fromField, toField, customerIdField, customerNameField;
    private JButton searchButton, deleteButton, updateButton, clearButton, backButton;
    private JDateChooser dateChooser;
    private Font f = new Font("Arial", Font.BOLD,12);
    private Color fontColor = new Color(240,248,255);
    private Color bgColor = new Color(0,123,167);
    private Color buttonColor = new Color(0,51,102);
    private Border border = BorderFactory.createLineBorder(Color.DARK_GRAY,2);

    private void customizeTableAppearance(JTable table) {
        table.setRowSelectionAllowed(true);
        table.setDefaultEditor(Object.class, null);

        DefaultTableCellRenderer tableCellRenderer = new DefaultTableCellRenderer();
        tableCellRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableCellRenderer.setBackground(new Color(208, 240, 192));
        table.setDefaultRenderer(Object.class, tableCellRenderer);
    }

    public void settingText(String finalLine) throws ParseException {
        String[] parts = finalLine.split(" ");
        ticketIdField.setText(parts[0]);
        transportTypeField.setText(parts[1]);
        transportNameField.setText(parts[2]);
        fromField.setText(parts[3]);
        toField.setText(parts[4]);
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        Date date = dateFormat.parse(parts[5]);
        dateChooser.setDate(date);
        customerIdField.setText(parts[7]);
        customerNameField.setText(parts[6]);
    }

    public void refreshTables(Boolean fullFresh) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/travelley";
            String username = "root";
            String password = "travel";
            Connection conn = DriverManager.getConnection(url,username,password);

            String queryforBusList = "SELECT * FROM busJourneys";
            String queryforTrainList = "SELECT * FROM trainJourneys";
            PreparedStatement preparedStatement1 = conn.prepareStatement(queryforBusList);
            ResultSet resultSetForBus = preparedStatement1.executeQuery();

            PreparedStatement preparedStatement2 = conn.prepareStatement(queryforTrainList);
            ResultSet resultSetForTrain = preparedStatement2.executeQuery();

            List<String[]> busHistory = new ArrayList<>();
            List<String[]> trainHistory = new ArrayList<>();


            while (resultSetForBus.next()) {
                String ticketID = resultSetForBus.getString("t_id");
                String busName = resultSetForBus.getString("bus_name");
                String from = resultSetForBus.getString("t_from");
                String to = resultSetForBus.getString("t_to");
                String date = resultSetForBus.getString("t_date");
                String Class = resultSetForBus.getString("t_class");
                String price = resultSetForBus.getString("t_price");
                String cusName = resultSetForBus.getString("cus_name");
                String cusID = resultSetForBus.getString("cus_id");
                String[] journeyDetails = {ticketID, busName, from, to, date, Class, price, cusName, cusID};
                busHistory.add(journeyDetails);
            }

            while (resultSetForTrain.next()) {
                String ticketID = resultSetForTrain.getString("t_id");
                String trainName = resultSetForTrain.getString("train_name");
                String from = resultSetForTrain.getString("t_from");
                String to = resultSetForTrain.getString("t_to");
                String date = resultSetForTrain.getString("t_date");
                String Class = resultSetForTrain.getString("t_class");
                String price = resultSetForTrain.getString("t_price");
                String cusName = resultSetForTrain.getString("cus_name");
                String cusID = resultSetForTrain.getString("cus_id");
                String[] journeyDetails = {ticketID, trainName, from, to, date, Class, price, cusName, cusID};
                trainHistory.add(journeyDetails);
            }

            String[] busColumnNames = {"TICKET ID", "BUS NAME", "FROM", "TO", "Date", "CLASS", "PRICE", "CUS-NAME", "CUS-ID"};
            DefaultTableModel busTableModel = new DefaultTableModel(busColumnNames, 0);
            for (String[] journeyDetails : busHistory) {
                busTableModel.addRow(journeyDetails);
            }


            String[] trainColumnNames = {"TICKET ID", "TRAIN NAME", "FROM", "TO", "Date", "CLASS", "PRICE", "CUS-NAME", "CUS-ID"};
            DefaultTableModel trainTableModel = new DefaultTableModel(trainColumnNames, 0);
            for (String[] journeyDetails : trainHistory) {
                trainTableModel.addRow(journeyDetails);
            }

            if(fullFresh)
            {
                busTable = new JTable(busTableModel);
                customizeTableAppearance(busTable);
                busTable.setRowSelectionAllowed(true);
                busTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                busTable.setDefaultEditor(Object.class, null);
                busTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                    public void valueChanged(ListSelectionEvent e) {
                        if(!e.getValueIsAdjusting())
                        {
                            int selectedRow = busTable.getSelectedRow();
                            if(selectedRow!=-1)
                            {
                                String ticketID = busTable.getValueAt(selectedRow, 0).toString();
                                String busName = busTable.getValueAt(selectedRow, 1).toString();
                                String from = busTable.getValueAt(selectedRow, 2).toString();
                                String to = busTable.getValueAt(selectedRow, 3).toString();
                                String date = busTable.getValueAt(selectedRow, 4).toString();
                                String cusName = busTable.getValueAt(selectedRow, 7).toString();
                                String cusID = busTable.getValueAt(selectedRow, 8).toString();
                                try {
                                    settingText(ticketID+" "+"BUS "+busName+"_ENTERPRISE"+" "+from+" "+to+" "+date+" "+cusName+" "+cusID);
                                } catch (ParseException ex) {
                                    throw new RuntimeException(ex);
                                }
                            }
                        }
                    }
                });

                trainTable = new JTable(trainTableModel);
                customizeTableAppearance(trainTable);
                trainTable.setRowSelectionAllowed(true);
                trainTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                trainTable.setDefaultEditor(Object.class, null);
                trainTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                    public void valueChanged(ListSelectionEvent e) {
                        if(!e.getValueIsAdjusting())
                        {
                            int selectedRow = trainTable.getSelectedRow();
                            if(selectedRow!=-1)
                            {
                                String ticketID = trainTable.getValueAt(selectedRow, 0).toString();
                                String trainName = trainTable.getValueAt(selectedRow, 1).toString();
                                String from = trainTable.getValueAt(selectedRow, 2).toString();
                                String to = trainTable.getValueAt(selectedRow, 3).toString();
                                String date = trainTable.getValueAt(selectedRow, 4).toString();
                                String cusName = trainTable.getValueAt(selectedRow, 7).toString();
                                String cusID = trainTable.getValueAt(selectedRow, 8).toString();
                                try {
                                    settingText(ticketID+" "+"TRAIN "+trainName+"_EXPRESS "+from+" "+to+" "+date+" "+cusName+" "+cusID);
                                } catch (ParseException ex) {
                                    throw new RuntimeException(ex);
                                }
                            }
                        }
                    }
                });


                busScrollPane = new JScrollPane(busTable);
                busScrollPane.setBounds(50, 50, 900, 300);
                this.add(busScrollPane);

                trainScrollPane = new JScrollPane(trainTable);
                trainScrollPane.setBounds(50, 370, 900, 300);
                this.add(trainScrollPane);
            }
            else{
                busTable.setModel(busTableModel);
                busTable.revalidate();
                busTable.repaint();

                trainTable.setModel(trainTableModel);
                trainTable.revalidate();
                trainTable.repaint();
            }
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    ticketHistory() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setBounds(100, 50, 1300, 750);
        this.setTitle("Ticket History");
        this.setLayout(null);
        this.setLocationRelativeTo(null);

        ImageIcon icon = new ImageIcon("images/icon.png");
        this.setIconImage(icon.getImage());

        searchLabel = new JLabel("Search by Ticket ID:");
        searchLabel.setBounds(970, 50, 130, 30);
        searchLabel.setFont(f);
        searchLabel.setForeground(fontColor);
        this.add(searchLabel);

        searchField = new JTextField();
        searchField.setBounds(1100, 50, 150, 30);
        searchField.setBorder(border);
        this.add(searchField);

        searchButton = new JButton("Search");
        searchButton.setBounds(1100, 90, 80, 30);
        searchButton.setForeground(fontColor);
        searchButton.setBackground(buttonColor);
        searchButton.setBorder(border);
        searchButton.setFocusable(false);
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String ID = searchField.getText();
                if (ID.length() == 4) {
                    ID = "1010" + ID;
                }

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    String url = "jdbc:mysql://localhost:3306/travelley";
                    String username = "root";
                    String password = "travel";
                    Connection conn = DriverManager.getConnection(url, username, password);

                    String queryForBus = "SELECT * FROM busJourneys WHERE t_id = ?";
                    PreparedStatement preparedStatementBus = conn.prepareStatement(queryForBus);
                    preparedStatementBus.setString(1, ID);

                    ResultSet resultSetBus = preparedStatementBus.executeQuery();

                    if (resultSetBus.next()) {
                        String vehicleType = "BUS";
                        String vehicleName = resultSetBus.getString("bus_name");
                        String from = resultSetBus.getString("t_from");
                        String to = resultSetBus.getString("t_to");
                        String date = resultSetBus.getString("t_date");
                        String cusName = resultSetBus.getString("cus_name");
                        String cusID = resultSetBus.getString("cus_id");

                        settingText(ID+" "+vehicleType+" "+vehicleName+" "+from+" "+to+" "+date+" "+cusName+" "+cusID);
                        resultSetBus.close();
                        preparedStatementBus.close();
                    } else {
                        String queryForTrain = "SELECT * FROM trainJourneys WHERE t_id = ?";
                        PreparedStatement preparedStatementTrain = conn.prepareStatement(queryForTrain);
                        preparedStatementTrain.setString(1, ID);

                        ResultSet resultSetTrain = preparedStatementTrain.executeQuery();

                        if (resultSetTrain.next()) {
                            String vehicleType = "TRAIN";
                            String vehicleName = resultSetTrain.getString("train_name")+"_EXPRESS";
                            String from = resultSetTrain.getString("t_from");
                            String to = resultSetTrain.getString("t_to");
                            String date = resultSetTrain.getString("t_date");
                            String cusName = resultSetTrain.getString("cus_name");
                            String cusID = resultSetTrain.getString("cus_id");

                            settingText(ID+" "+vehicleType+" "+vehicleName+" "+from+" "+to+" "+date+" "+cusName+" "+cusID);

                            resultSetTrain.close();
                            preparedStatementTrain.close();
                        } else {
                            JOptionPane.showMessageDialog(null, "Invalid ID", "", JOptionPane.WARNING_MESSAGE);
                        }
                    }

                    if (!preparedStatementBus.isClosed()) {
                        preparedStatementBus.close();
                    }

                    conn.close();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        this.add(searchButton);

        JLabel ticketIdLabel = new JLabel("Ticket ID:");
        ticketIdLabel.setBounds(970, 130 + 30, 100, 30);
        ticketIdLabel.setFont(f);
        ticketIdLabel.setForeground(fontColor);
        this.add(ticketIdLabel);

        ticketIdField = new JTextField();
        ticketIdField.setBounds(1100, 130 + 30, 150, 30);
        ticketIdField.setBorder(border);
        ticketIdField.setEditable(false);
        this.add(ticketIdField);

        JLabel transportTypeLabel = new JLabel("Transportation Type:");
        transportTypeLabel.setBounds(970, 170 + 30, 150, 30);
        transportTypeLabel.setFont(f);
        transportTypeLabel.setForeground(fontColor);
        this.add(transportTypeLabel);

        transportTypeField = new JTextField();
        transportTypeField.setBounds(1100, 170 + 30, 150, 30);
        transportTypeField.setBorder(border);
        transportTypeField.setEditable(false);
        this.add(transportTypeField);

        JLabel transportNameLabel = new JLabel("Transportation Name:");
        transportNameLabel.setBounds(970, 210 + 30, 150, 30);
        transportNameLabel.setFont(f);
        transportNameLabel.setForeground(fontColor);
        this.add(transportNameLabel);

        transportNameField = new JTextField();
        transportNameField.setBounds(1100, 210 + 30, 150, 30);
        transportNameField.setBorder(border);
        transportNameField.setEditable(false);
        this.add(transportNameField);

        JLabel fromLabel = new JLabel("From:");
        fromLabel.setBounds(970, 250 + 30, 100, 30);
        fromLabel.setFont(f);
        fromLabel.setForeground(fontColor);
        this.add(fromLabel);

        fromField = new JTextField();
        fromField.setBounds(1100, 250 + 30, 150, 30);
        fromField.setBorder(border);
        fromField.setEditable(false);
        this.add(fromField);

        JLabel toLabel = new JLabel("To:");
        toLabel.setBounds(970, 290 + 30, 100, 30);
        toLabel.setFont(f);
        toLabel.setForeground(fontColor);
        this.add(toLabel);

        toField = new JTextField();
        toField.setBounds(1100, 290 + 30, 150, 30);
        toField.setBorder(border);
        toField.setEditable(false);
        this.add(toField);

        JLabel dateLabel = new JLabel("Date:");
        dateLabel.setBounds(970, 330 + 30, 100, 30);
        dateLabel.setFont(f);
        dateLabel.setForeground(fontColor);
        this.add(dateLabel);

        dateChooser = new JDateChooser();
        dateChooser.setBounds(1100, 330 + 30, 150, 30);
        dateChooser.setBorder(border);
        dateChooser.setDateFormatString("yyyy-MM-dd");
        dateChooser.setMinSelectableDate(new Date());
        dateChooser.getDateEditor().setEnabled(false);
        dateChooser.setFont(f);

        this.add(dateChooser);

        JLabel customerIdLabel = new JLabel("Customer ID:");
        customerIdLabel.setBounds(970, 370 + 30, 100, 30);
        customerIdLabel.setFont(f);
        customerIdLabel.setForeground(fontColor);
        this.add(customerIdLabel);

        customerIdField = new JTextField();
        customerIdField.setBounds(1100, 370 + 30, 150, 30);
        customerIdField.setBorder(border);
        customerIdField.setEditable(false);
        this.add(customerIdField);

        JLabel customerNameLabel = new JLabel("Customer Name:");
        customerNameLabel.setBounds(970, 410 + 30, 130, 30);
        customerNameLabel.setFont(f);
        customerNameLabel.setForeground(fontColor);
        this.add(customerNameLabel);

        customerNameField = new JTextField();
        customerNameField.setBounds(1100, 410 + 30, 150, 30);
        customerNameField.setBorder(border);
        customerNameField.setEditable(false);
        this.add(customerNameField);

        updateButton = new JButton("Update");
        updateButton.setBounds(1100,520,100,30);
        updateButton.setForeground(fontColor);
        updateButton.setBackground(new Color(12,134,0));
        updateButton.setFocusable(false);
        updateButton.setBorder(border);
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = ticketIdField.getText();
                Date selectedDate = dateChooser.getDate();
                String pattern = "yyyy-MM-dd";
                SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
                String date = dateFormat.format(selectedDate);
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    String url = "jdbc:mysql://localhost:3306/travelley";
                    String username = "root";
                    String password = "travel";
                    Connection conn = DriverManager.getConnection(url, username, password);
                    String query1 = "update purchase set t_date = ? where t_id = ?";
                    PreparedStatement preparedStatement1 = conn.prepareStatement(query1);
                    preparedStatement1.setString(1,date);
                    preparedStatement1.setString(2,id);
                    int rowsEffected1 = preparedStatement1.executeUpdate();
                    String query2 = "update manage set t_date = ? where t_id = ?";
                    PreparedStatement preparedStatement2 = conn.prepareStatement(query2);
                    preparedStatement2.setString(1,date);
                    preparedStatement2.setString(2,id);
                    int rowsEffected2 = preparedStatement2.executeUpdate();
                    String query3 = "update ticket set t_date = ? where t_id = ?";
                    PreparedStatement preparedStatement3 = conn.prepareStatement(query3);
                    preparedStatement3.setString(1,date);
                    preparedStatement3.setString(2,id);
                    int rowsEffected3 = preparedStatement3.executeUpdate();

                    preparedStatement1.close();
                    preparedStatement2.close();
                    preparedStatement3.close();
                    conn.close();
                    if(rowsEffected1>0 || rowsEffected2>0 || rowsEffected3>0)
                    {
                        JOptionPane.showMessageDialog(null, "Date updated succesfully!", "Successful!", JOptionPane.INFORMATION_MESSAGE);
                        refreshTables(false);
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "No Updates", "", JOptionPane.WARNING_MESSAGE);
                    }

                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        this.add(updateButton);

        clearButton = new JButton("Clear");
        clearButton.setBounds(1100,480,100,30);
        clearButton.setForeground(fontColor);
        clearButton.setBackground(buttonColor);
        clearButton.setFocusable(false);
        clearButton.setBorder(border);
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchField.setText("");
                ticketIdField.setText("");
                transportTypeField.setText("");
                transportNameField.setText("");
                fromField.setText("");
                toField.setText("");
                dateChooser.setDate(null);
                customerIdField.setText("");
                customerNameField.setText("");
            }
        });
        this.add(clearButton);

        deleteButton = new JButton("Delete");
        deleteButton.setBounds(1100,560,100,30);
        deleteButton.setForeground(fontColor);
        deleteButton.setBackground(new Color(144,0,32));
        deleteButton.setFocusable(false);
        deleteButton.setBorder(border);
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!ticketIdField.getText().isEmpty())
                {
                    String ticketId = ticketIdField.getText();
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        String url = "jdbc:mysql://localhost:3306/travelley";
                        String username = "root";
                        String password = "travel";
                        Connection conn = DriverManager.getConnection(url, username, password);

                        String query1 = "delete from purchase where t_id = ?";
                        PreparedStatement preparedStatement1 = conn.prepareStatement(query1);
                        preparedStatement1.setString(1,ticketId);
                        int rowsEffected1 = preparedStatement1.executeUpdate();
                        String query2 = "delete from ticket where t_id = ?";
                        PreparedStatement preparedStatement2 = conn.prepareStatement(query2);
                        preparedStatement2.setString(1,ticketId);
                        int rowsEffected2 = preparedStatement2.executeUpdate();
                        String query3 = "delete from manage where t_id = ?";
                        PreparedStatement preparedStatement3 = conn.prepareStatement(query3);
                        preparedStatement3.setString(1,ticketId);
                        int rowsEffected3 = preparedStatement3.executeUpdate();
                        String query4 = "delete from belong where t_id = ?";
                        PreparedStatement preparedStatement4 = conn.prepareStatement(query4);
                        preparedStatement4.setString(1,ticketId);
                        int rowsEffected4 = preparedStatement4.executeUpdate();

                        preparedStatement1.close();
                        preparedStatement2.close();
                        preparedStatement3.close();
                        preparedStatement4.close();
                        conn.close();
                        if(rowsEffected1>0 || rowsEffected2>0 || rowsEffected3>0 || rowsEffected4>0)
                        {
                            JOptionPane.showMessageDialog(null, "Ticket deleted succesfully!", "Successful!", JOptionPane.INFORMATION_MESSAGE);
                            refreshTables(false);
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "Coudn't delete!", "", JOptionPane.WARNING_MESSAGE);
                        }
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "Please select a ticket!", "", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        this.add(deleteButton);

        backButton = new JButton("Back");
        backButton.setBounds(1070,650,100,30);
        backButton.setForeground(fontColor);
        backButton.setBackground(new Color(192,0,34));
        backButton.setFocusable(false);
        backButton.setBorder(border);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new admin();
            }
        });
        this.add(backButton);


        refreshTables(true);
        this.getContentPane().setBackground(bgColor);
        this.setVisible(true);
    }
}
