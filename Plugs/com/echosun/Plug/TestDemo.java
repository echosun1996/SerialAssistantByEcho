package com.echosun.Plug;


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
        if (!data.contains("$GPTXT")) return;
        for (Component component : jPanel.getComponents()) {

            System.out.println(component);
            System.out.println(component.getClass().toString());
            if (component.getClass().toString().contains("JTextPane")) {
                JTextPane jTextPane = (JTextPane) component;
                jTextPane.setText(data);
            }

        }

    }

    @Override
    public JPanel addFrame() {
        demo demo = new demo();
        return demo;
    }

}
