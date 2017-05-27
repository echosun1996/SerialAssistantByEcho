package com.echosun.Plug;

import javax.swing.*;

/**
 * Created by echosun.
 * All rights reserved.
 */
public class demo extends JPanel {
    private static JLabel JTextPane;

    public demo() {
        setLayout(null);

        JTextPane gpstxtTP = new JTextPane();
        gpstxtTP.setBounds(118, 53, 310, 165);
        add(gpstxtTP);

        JLabel gpsLB = new JLabel("GPS txt");
        gpsLB.setBounds(37, 118, 54, 15);
        add(gpsLB);

        JLabel titleLB = new JLabel("Txt of GPS");
        titleLB.setHorizontalAlignment(SwingConstants.CENTER);
        titleLB.setBounds(108, 6, 215, 35);
        add(titleLB);
        setVisible(true);


    }

    public static JLabel getJTextPane() {
        return JTextPane;
    }
}
