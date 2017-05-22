package com.echosun.plugsin;

/**
 * Created by echosun.
 * All rights reserved.
 */
public class AnalyseFuns {
    private AnalyseAPI apiSave = null;
    private boolean plugEnable = false;

    AnalyseFuns(AnalyseAPI in) {
        apiSave = in;
    }

    public boolean getEnable() {
        return plugEnable;
    }

    void setEnable(boolean in) {
        this.plugEnable = in;
    }

    void use() {
        apiSave.fun();
    }

}
