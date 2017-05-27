package com.echosun.plugins;

import com.echosun.pluginsAPI.AnalyseAPI;

import javax.swing.*;

/**
 * Created by echosun.
 * All rights reserved.
 */
public class AnalyseFuns {
    private AnalyseAPI apiSave = null;
    private JPanel jPanel;

    public AnalyseFuns(AnalyseAPI apiSave) {
        this.apiSave = apiSave;
    }

    public void analyse(String data) {
        apiSave.analyze(jPanel, data);
    }

    void initUI(JTabbedPane functionsTP, String name) {
        jPanel = apiSave.addFrame();
        functionsTP.addTab(name, null, jPanel, null);
    }

    void use() {
        System.out.println("AnalyseFuns in use!");
    }

}
