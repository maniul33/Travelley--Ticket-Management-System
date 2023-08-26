import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class admin extends JFrame {
    private JLabel adminLabel, nameLabel,  UserIdLabel, emailLabel, contactLabel, searchLabel, userListLabel;
    private JTextField nameField, UserIdField, emailField, contactField, searchField;
    private JTable userList;
    private JButton searchButton, deleteButton, updateButton, clearButton, logoutButton, ticketHistoryButton;
    private Font f = new Font("Arial", Font.BOLD,14);
    private Color fontColor = new Color(240,248,255);
    private Color bgColor = new Color(0,123,167);
    private Color buttonColor = new Color(0,51,102);
    private Border border = BorderFactory.createLineBorder(Color.DARK_GRAY,2);

    public void settingText(String finalLine)
    {
        String[] parts = finalLine.split(" ");
        nameField.setText(parts[0]);
        UserIdField.setText(parts[1]);
        contactField.setText(parts[2]);
        emailField.setText(parts[3]);
    }
    public void refreshList(Boolean fullFresh) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/travelley";
            String username = "root";
            String password = "travel";
            Connection conn = DriverManager.getConnection(url,username,password);

            String query = "SELECT cus_name, cus_id, cus_email, cus_phoneno FROM customer order by cus_name";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            String[] columnNames = {"SL","Name", "User ID", "Email", "Contact"};
            DefaultTableModel model = new DefaultTableModel(columnNames, 0);
            int SL = 0;
            while (resultSet.next()) {
                SL++;
                String name = resultSet.getString("cus_name");
                String userID = resultSet.getString("cus_id");
                String email = resultSet.getString("cus_email");
                String contact = resultSet.getString("cus_phoneno");
                String[] userDetails = {Integer.toString(SL), name.toUpperCase(), userID, email.toLowerCase(), contact};
                model.addRow(userDetails);
            }

            if(fullFresh)
            {
                userList = new JTable(model);
                userList.setRowSelectionAllowed(true);
                userList.setDefaultEditor(Object.class, null);

                userList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                    public void valueChanged(ListSelectionEvent e) {
                        if(!e.getValueIsAdjusting())
                        {
                            int selectedRow = userList.getSelectedRow();
                            if(selectedRow!=-1)
                            {
                                String name = userList.getValueAt(selectedRow, 1).toString();
                                String userID = userList.getValueAt(selectedRow, 2).toString();
                                String email = userList.getValueAt(selectedRow, 3).toString();
                                String contact = userList.getValueAt(selectedRow, 4).toString();
                                settingText(name+" "+userID+" "+contact+" "+email);
                            }
                        }
                    }
                });

                DefaultTableCellRenderer tableCellRenderer = new DefaultTableCellRenderer();
                tableCellRenderer.setHorizontalAlignment(JLabel.LEFT);
                tableCellRenderer.setBackground(new Color(208, 240, 192));
                userList.setDefaultRenderer(Object.class, tableCellRenderer);
                TableColumnModel columnModel = userList.getColumnModel();
                columnModel.getColumn(0).setPreferredWidth(10);
                columnModel.getColumn(1).setPreferredWidth(40);
                columnModel.getColumn(2).setPreferredWidth(40);
                columnModel.getColumn(4).setPreferredWidth(50);
                DefaultTableCellRenderer tableCellRendererCenter = new DefaultTableCellRenderer();
                tableCellRendererCenter.setHorizontalAlignment(JLabel.CENTER);
                tableCellRendererCenter.setBackground(new Color(208, 240, 192));
                columnModel.getColumn(0).setCellRenderer(tableCellRendererCenter);
                columnModel.getColumn(2).setCellRenderer(tableCellRendererCenter);
                columnModel.getColumn(4).setCellRenderer(tableCellRendererCenter);
                JScrollPane scrollPane = new JScrollPane(userList);
                scrollPane.setBounds(400, 150, 550, 350);
                this.add(scrollPane);
            }
            else{
                userList.setModel(model);
                userList.revalidate();
                userList.repaint();
                TableColumnModel columnModel = userList.getColumnModel();
                columnModel.getColumn(0).setPreferredWidth(10);
                columnModel.getColumn(1).setPreferredWidth(40);
                columnModel.getColumn(2).setPreferredWidth(40);
                columnModel.getColumn(4).setPreferredWidth(50);
                DefaultTableCellRenderer tableCellRendererCenter = new DefaultTableCellRenderer();
                tableCellRendererCenter.setHorizontalAlignment(JLabel.CENTER);
                tableCellRendererCenter.setBackground(new Color(208, 240, 192));
                columnModel.getColumn(0).setCellRenderer(tableCellRendererCenter);
                columnModel.getColumn(2).setCellRenderer(tableCellRendererCenter);
                columnModel.getColumn(4).setCellRenderer(tableCellRendererCenter);
            }
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    admin()
    {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setBounds(100,50,1000,650);
        this.setTitle("Admin");
        this.setLayout(null);
        this.setLocationRelativeTo(null);

        ImageIcon icon = new ImageIcon("images/icon.png");
        this.setIconImage(icon.getImage());

        adminLabel = new JLabel("Admin");
        adminLabel.setBounds(50,30,500,50);
        adminLabel.setFont(new Font("SansSerif",Font.ITALIC,48));
        adminLabel.setForeground(fontColor);
        this.add(adminLabel);

        searchLabel = new JLabel("Search user by ID : ");
        searchLabel.setBounds(50,150,500,50);
        searchLabel.setFont(f);
        searchLabel.setForeground(fontColor);
        this.add(searchLabel);

        searchField = new JTextField();
        searchField.setBounds(185,160,100,30);
        searchField.setBorder(border);
        this.add(searchField);

        searchButton = new JButton("Search");
        searchButton.setBounds(295,160,90,30);
        searchButton.setForeground(fontColor);
        searchButton.setBackground(buttonColor);
        searchButton.setFocusable(false);
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String ID = searchField.getText();
                if(ID.length()==4)
                {
                    ID = "2023"+ID;
                }

                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    String url = "jdbc:mysql://localhost:3306/travelley";
                    String username = "root";
                    String password = "travel";
                    Connection conn = DriverManager.getConnection(url,username,password);
                    String getidQuery = "select cus_id from customer";
                    PreparedStatement preparedStatement = conn.prepareStatement(getidQuery);
                    ResultSet resultSet = preparedStatement.executeQuery();

                    Boolean idFound = false;
                    String finalLine = null;
                    while(resultSet.next())
                    {
                        String id = resultSet.getString("cus_id");

                        if (id.equals(ID))
                        {
                            idFound = true;
                            String getDetailsQuery = "select * from customer where cus_id = ?";
                            PreparedStatement preparedStatement2 = conn.prepareStatement(getDetailsQuery);
                            preparedStatement2.setString(1, id);
                            ResultSet details = preparedStatement2.executeQuery();

                            if(details.next())
                            {
                                String name = details.getString("cus_name");
                                String userID = details.getString("cus_id");
                                String email = details.getString("cus_email");
                                String contact = details.getString("cus_phoneno");
                                finalLine = name+" "+userID+" "+contact+" "+email;
                            }
                            preparedStatement2.close();
                            break;
                        }
                    }

                    if (idFound)
                    {
                        settingText(finalLine);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,"Invalid ID","",JOptionPane.YES_OPTION);
                    }
                    preparedStatement.close();
                    conn.close();
                }catch(Exception ex){
                    ex.getStackTrace();
                }
            }
        });
        this.add(searchButton);

        nameLabel = new JLabel("Name : ");
        nameLabel.setFont(f);
        nameLabel.setBounds(50,230,250,50);
        nameLabel.setForeground(fontColor);
        this.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(140,240,200,30);
        nameField.setBorder(border);
        this.add(nameField);

        emailLabel = new JLabel("Email : ");
        emailLabel.setFont(f);
        emailLabel.setBounds(50,350,250,50);
        emailLabel.setForeground(fontColor);
        this.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(140,360,200,30);
        emailField.setBorder(border);
        this.add(emailField);

        contactLabel = new JLabel("Contact : ");
        contactLabel.setFont(f);
        contactLabel.setBounds(50,310,250,50);
        contactLabel.setForeground(fontColor);
        this.add(contactLabel);

        contactField = new JTextField();
        contactField.setBounds(140,320,200,30);
        contactField.setBorder(border);
        this.add(contactField);

        UserIdLabel = new JLabel("User ID : ");
        UserIdLabel.setFont(f);
        UserIdLabel.setBounds(50,270,250,50);
        UserIdLabel.setForeground(fontColor);
        this.add(UserIdLabel);

        UserIdField = new JTextField();
        UserIdField.setBounds(140,280,200,30);
        UserIdField.setBorder(border);
        UserIdField.setEditable(false);
        this.add(UserIdField);

        ticketHistoryButton = new JButton("Ticket History");
        ticketHistoryButton.setBounds(140,405,200,30);
        ticketHistoryButton.setForeground(fontColor);
        ticketHistoryButton.setBackground(buttonColor);
        ticketHistoryButton.setFocusable(false);
        ticketHistoryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new ticketHistory();
            }
        });
        this.add(ticketHistoryButton);

        clearButton = new JButton("Clear");
        clearButton.setBounds(140,450,90,30);
        clearButton.setForeground(fontColor);
        clearButton.setBackground(buttonColor);
        clearButton.setFocusable(false);
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nameField.setText("");
                emailField.setText("");
                contactField.setText("");
                UserIdField.setText("");
                searchField.setText("");
            }
        });
        this.add(clearButton);

        updateButton = new JButton("Update");
        updateButton.setBounds(250,450,90,30);
        updateButton.setForeground(fontColor);
        updateButton.setBackground(buttonColor);
        updateButton.setFocusable(false);
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText().toUpperCase();
                String id = UserIdField.getText();
                String email = emailField.getText().toLowerCase();
                String contact = contactField.getText();

                if(name.isEmpty() || email.isEmpty() || contact.isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Please fillup all the informations");
                }
                else
                {
                    try{
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        String url = "jdbc:mysql://localhost:3306/travelley";
                        String username = "root";
                        String pass = "travel";
                        Connection conn = DriverManager.getConnection(url,username,pass);
                        String queryForCustomer = "update customer set cus_name = ?, cus_email = ?, cus_phoneno = ? where cus_id = ?";
                        String queryForControl = "update control set cus_name = ?, cus_email = ?, cus_phoneno = ? where cus_id = ?";
                        PreparedStatement preparedStatement1 = conn.prepareStatement(queryForCustomer);
                        preparedStatement1.setString(1,name);
                        preparedStatement1.setString(2,email);
                        preparedStatement1.setString(3,contact);
                        preparedStatement1.setString(4,id);
                        int updates = preparedStatement1.executeUpdate();
                        preparedStatement1.close();

                        PreparedStatement preparedStatement2 = conn.prepareStatement(queryForControl);
                        preparedStatement2.setString(1,name);
                        preparedStatement2.setString(2,email);
                        preparedStatement2.setString(3,contact);
                        preparedStatement2.setString(4,id);
                        preparedStatement2.executeUpdate();
                        preparedStatement2.close();

                        if(updates>0)
                        {
                            JOptionPane.showMessageDialog(null, "User details Updated!");
                            clearButton.doClick();
                            refreshList(false);
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "No details Updated!");
                        }

                        conn.close();
                    }catch(Exception ex)
                    {
                        ex.getStackTrace();
                    }
                }
            }
        });
        this.add(updateButton);

        deleteButton = new JButton("Delete user");
        deleteButton.setBounds(180,490,120,30);
        deleteButton.setForeground(fontColor);
        deleteButton.setBackground(new Color(144,0,32));
        deleteButton.setFocusable(false);
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = UserIdField.getText();
                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    String url = "jdbc:mysql://localhost:3306/travelley";
                    String username = "root";
                    String password = "travel";
                    Connection conn = DriverManager.getConnection(url,username,password);
                    String queryForCus = "delete from customer where cus_id = ?";
                    PreparedStatement preparedStatement1 = conn.prepareStatement(queryForCus);
                    preparedStatement1.setString(1,id);
                    int updates = preparedStatement1.executeUpdate();
                    preparedStatement1.close();

                    String queryForControl = "delete from control where cus_id = ?";
                    PreparedStatement preparedStatement2 = conn.prepareStatement(queryForControl);
                    preparedStatement2.setString(1,id);
                    preparedStatement2.executeUpdate();
                    preparedStatement2.close();

                    if(updates>0)
                    {
                        JOptionPane.showMessageDialog(null, "User Deleted!");
                        clearButton.doClick();
                        refreshList(false);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "No user selected!");
                    }
                    conn.close();

                }catch (Exception ex)
                {
                    ex.getStackTrace();
                }
            }
        });
        this.add(deleteButton);

        logoutButton = new JButton("Log Out");
        logoutButton.setSize(90,30);
        logoutButton.setLocation((getWidth()-logoutButton.getWidth())/2,570);
        logoutButton.setForeground(fontColor);
        logoutButton.setBackground(new Color(144,0,32));
        logoutButton.setFocusable(false);
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new homepage();
            }
        });
        this.add(logoutButton);

        userListLabel = new JLabel("User List");
        userListLabel.setBounds(600,100,250,50);
        userListLabel.setFont(new Font("SansSerif",Font.BOLD,24));
        userListLabel.setForeground(fontColor);
        this.add(userListLabel);

        refreshList(true);

        this.getContentPane().setBackground(bgColor);
        this.setVisible(true);

    }
}
