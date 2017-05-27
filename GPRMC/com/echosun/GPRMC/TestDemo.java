package com.echosun.GPRMC;


import com.echosun.pluginsAPI.AnalyseAPI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by echosun.
 * All rights reserved.
 */
public class TestDemo implements AnalyseAPI {

    @Override
    public void analyze(JPanel jPanel, String data) {
        if (!data.contains("$GPRMC") && !data.contains("$GPVTG") && !data.contains("$GPGGA")) return;

        Component[] components = jPanel.getComponents();
        System.out.println("components size:" + components.length);
        for (String temp : data.split("\n")) {
            if (temp.contains("$GPRMC")) {
                //System.out.println(temp);
                //System.out.println("in");

                String[] temps = temp.split(",");
                //System.out.println(temps.length);
                if (temps.length != 13) break;
                //String check = temp.split("\\*")[1];
                //System.out.println("check" );
                int i = 1;
                for (String t : temps) {
                    if (t.equals("$GPRMC")) continue;
                    //System.out.println(i+"----"+t);
                    JLabel jLabel = (JLabel) components[i];
                    jLabel.setText(t);
                    i++;
                }
            }
        }

    }

    @Override
    public JPanel addFrame() {
        return new demo();
    }

}
