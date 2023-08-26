import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class Seat extends JFrame implements ActionListener {
    private JCheckBox[] seat_cb = new JCheckBox[24];
    private JLabel[] seat_label = new JLabel[24];
    private JButton saveClose_button;
    public static String seat_no;
    public static Boolean seatIsSelected = false;

    public Seat() {
        //Vertical bars
        int initial_Y = 10;
        for (int i = 0; i < 40; i++) {
            JLabel border_lb = new JLabel("||");
            border_lb.setBounds(202, initial_Y, 20, 40);
            add(border_lb);
            initial_Y += 11;
        }

        //All seat labels and check boxes
        seat_cb[0] = new JCheckBox();
        seat_cb[0].setBounds(27, 10, 20, 40);
        seat_cb[0].setFocusable(false);
        add(seat_cb[0]);

        seat_label[0] = new JLabel("T01");
        seat_label[0].setBounds(23, 40, 70, 40);
        add(seat_label[0]);

        seat_cb[1] = new JCheckBox();
        seat_cb[1].setBounds(108, 10, 20, 40);
        seat_cb[1].setFocusable(false);
        add(seat_cb[1]);

        seat_label[1] = new JLabel("T02");
        seat_label[1].setBounds(101, 40, 70, 40);
        add(seat_label[1]);

        seat_cb[2] = new JCheckBox();
        seat_cb[2].setBounds(279, 10, 20, 40);
        seat_cb[2].setFocusable(false);
        add(seat_cb[2]);

        seat_label[2] = new JLabel("T03");
        seat_label[2].setBounds(273, 40, 70, 40);
        add(seat_label[2]);

        seat_cb[3] = new JCheckBox();
        seat_cb[3].setBounds(358, 10, 20, 40);
        seat_cb[3].setFocusable(false);
        add(seat_cb[3]);

        seat_label[3] = new JLabel("T04");
        seat_label[3].setBounds(351, 40, 70, 40);
        add(seat_label[3]);

        seat_cb[4] = new JCheckBox();
        seat_cb[4].setBounds(27, 90, 20, 40);
        seat_cb[4].setFocusable(false);
        add(seat_cb[4]);

        seat_label[4] = new JLabel("T05");
        seat_label[4].setBounds(23, 120, 70, 40);
        add(seat_label[4]);

        seat_cb[5] = new JCheckBox();
        seat_cb[5].setBounds(108, 90, 20, 40);
        seat_cb[5].setFocusable(false);
        add(seat_cb[5]);

        seat_label[5] = new JLabel("T06");
        seat_label[5].setBounds(101, 120, 70, 40);
        add(seat_label[5]);

        seat_cb[6] = new JCheckBox();
        seat_cb[6].setBounds(279, 90, 20, 40);
        seat_cb[6].setFocusable(false);
        add(seat_cb[6]);

        seat_label[6] = new JLabel("T07");
        seat_label[6].setBounds(273, 120, 70, 40);
        add(seat_label[6]);

        seat_cb[7] = new JCheckBox();
        seat_cb[7].setBounds(358, 90, 20, 40);
        seat_cb[7].setFocusable(false);
        add(seat_cb[7]);

        seat_label[7] = new JLabel("T08");
        seat_label[7].setBounds(351, 120, 70, 40);
        add(seat_label[7]);

        seat_cb[8] = new JCheckBox();
        seat_cb[8].setBounds(27, 170, 20, 40);
        seat_cb[8].setFocusable(false);
        add(seat_cb[8]);

        seat_label[8] = new JLabel("T09");
        seat_label[8].setBounds(23, 200, 70, 40);
        add(seat_label[8]);

        seat_cb[9] = new JCheckBox();
        seat_cb[9].setBounds(108, 170, 20, 40);
        seat_cb[9].setFocusable(false);
        add(seat_cb[9]);

        seat_label[9] = new JLabel("T10");
        seat_label[9].setBounds(101, 200, 70, 40);
        add(seat_label[9]);

        seat_cb[10] = new JCheckBox();
        seat_cb[10].setBounds(279, 170, 20, 40);
        seat_cb[10].setFocusable(false);
        add(seat_cb[10]);

        seat_label[10] = new JLabel("T11");
        seat_label[10].setBounds(273, 200, 70, 40);
        add(seat_label[10]);

        seat_cb[11] = new JCheckBox();
        seat_cb[11].setBounds(358, 170, 20, 40);
        seat_cb[11].setFocusable(false);
        add(seat_cb[11]);

        seat_label[11] = new JLabel("T12");
        seat_label[11].setBounds(351, 200, 70, 40);
        add(seat_label[11]);

        seat_cb[12] = new JCheckBox();
        seat_cb[12].setBounds(27, 250, 20, 40);
        seat_cb[12].setFocusable(false);
        add(seat_cb[12]);

        seat_label[12] = new JLabel("T13");
        seat_label[12].setBounds(23, 280, 70, 40);
        add(seat_label[12]);

        seat_cb[13] = new JCheckBox();
        seat_cb[13].setBounds(108, 250, 20, 40);
        seat_cb[13].setFocusable(false);
        add(seat_cb[13]);

        seat_label[13] = new JLabel("T14");
        seat_label[13].setBounds(101, 280, 70, 40);
        add(seat_label[13]);

        seat_cb[14] = new JCheckBox();
        seat_cb[14].setBounds(279, 250, 20, 40);
        seat_cb[14].setFocusable(false);
        add(seat_cb[14]);

        seat_label[14] = new JLabel("T15");
        seat_label[14].setBounds(273, 280, 70, 40);
        add(seat_label[14]);

        seat_cb[15] = new JCheckBox();
        seat_cb[15].setBounds(358, 250, 20, 40);
        seat_cb[15].setFocusable(false);
        add(seat_cb[15]);

        seat_label[15] = new JLabel("T16");
        seat_label[15].setBounds(351, 280, 70, 40);
        add(seat_label[15]);

        seat_cb[16] = new JCheckBox();
        seat_cb[16].setBounds(27, 330, 20, 40);
        seat_cb[16].setFocusable(false);
        add(seat_cb[16]);

        seat_label[16] = new JLabel("T17");
        seat_label[16].setBounds(23, 360, 70, 40);
        add(seat_label[16]);

        seat_cb[17] = new JCheckBox();
        seat_cb[17].setBounds(108, 330, 20, 40);
        seat_cb[17].setFocusable(false);
        add(seat_cb[17]);

        seat_label[17] = new JLabel("T18");
        seat_label[17].setBounds(101, 360, 70, 40);
        add(seat_label[17]);

        seat_cb[18] = new JCheckBox();
        seat_cb[18].setBounds(279, 330, 20, 40);
        seat_cb[18].setFocusable(false);
        add(seat_cb[18]);

        seat_label[18] = new JLabel("T19");
        seat_label[18].setBounds(273, 360, 70, 40);
        add(seat_label[18]);

        seat_cb[19] = new JCheckBox();
        seat_cb[19].setBounds(358, 330, 20, 40);
        seat_cb[19].setFocusable(false);
        add(seat_cb[19]);

        seat_label[19] = new JLabel("T20");
        seat_label[19].setBounds(351, 360, 70, 40);
        add(seat_label[19]);

        seat_cb[20] = new JCheckBox();
        seat_cb[20].setBounds(27, 410, 20, 40);
        seat_cb[20].setFocusable(false);
        add(seat_cb[20]);

        seat_label[20] = new JLabel("T21");
        seat_label[20].setBounds(23, 440, 70, 40);
        add(seat_label[20]);

        seat_cb[21] = new JCheckBox();
        seat_cb[21].setBounds(108, 410, 20, 40);
        seat_cb[21].setFocusable(false);
        add(seat_cb[21]);

        seat_label[21] = new JLabel("T22");
        seat_label[21].setBounds(101, 440, 70, 40);
        add(seat_label[21]);

        seat_cb[22] = new JCheckBox();
        seat_cb[22].setBounds(279, 410, 20, 40);
        seat_cb[22].setFocusable(false);
        add(seat_cb[22]);

        seat_label[22] = new JLabel("T23");
        seat_label[22].setBounds(273, 440, 70, 40);
        add(seat_label[22]);

        seat_cb[23] = new JCheckBox();
        seat_cb[23].setBounds(358, 410, 20, 40);
        seat_cb[23].setFocusable(false);
        add(seat_cb[23]);

        seat_label[23] = new JLabel("T24");
        seat_label[23].setBounds(351, 440, 70, 40);
        add(seat_label[23]);

        //Setting the background of check boxes to null and setting font of each seat_label
        for (int i = 0; i < 24; i++) {
            seat_cb[i].setBackground(null);
            seat_label[i].setFont(new Font("Consolas", Font.PLAIN, 16));
        }

        //Adding button group so only one check box can be selected
        ButtonGroup buttonGroup = new ButtonGroup();
        for (int i = 0; i < 24; i++) {
            buttonGroup.add(seat_cb[i]);
        }

        //saveClose button
        saveClose_button = new JButton("Save & Close");
        saveClose_button.setBounds(150, 500, 115, 40);
        saveClose_button.setFocusable(false);
        add(saveClose_button);
        saveClose_button.setBackground(new Color(0, 63, 63));
        saveClose_button.setForeground(Color.WHITE);
        saveClose_button.addActionListener(this);

        //MAIN JFRAME
        ImageIcon icon_img = new ImageIcon("images/icon.png");
        setIconImage(icon_img.getImage());
        getContentPane().setBackground(new Color(173, 239, 209));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Seat Selection");
        setLayout(null);
        setResizable(false);
        setSize(420, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveClose_button) {
            ArrayList<String> seats = new ArrayList<>(Arrays.asList("T01", "T02", "T03", "T04", "T05", "T06", "T07", "T08", "T09", "T10", "T11", "T12", "T13", "T14", "T15", "T16", "T17", "T18", "T19", "T20", "T21", "T22", "T23", "T24"));
            boolean anySeatSelected = false;
            for (int i = 0; i < seat_cb.length; i++) {
                if (seat_cb[i].isSelected()) {
                    if (!anySeatSelected) {
                        seat_no = seats.get(i);
                        anySeatSelected = true;
                        TrainHomepage.buy_button.setEnabled(true);
                    } else {
                        seat_cb[i].setSelected(false);
                    }
                }
            }
            if (anySeatSelected) {
                seatIsSelected = true;
            } else {
                seatIsSelected = false;
                TrainHomepage.buy_button.setEnabled(false);
            }
            setVisible(false);
        }
    }
}
