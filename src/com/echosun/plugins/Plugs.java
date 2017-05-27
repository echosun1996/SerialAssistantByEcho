package com.echosun.plugins;

/**
 * this is a class in order to declare the Analyse.
 * Created by echosun.
 * All rights reserved.
 */
public class Plugs {
    private Object instanceClass;
    private String name;
    private String author;
    private String mainClass;
    private String category;
    private boolean enable;
    private String infoString;

    Plugs(String name, String author, String mainClass, String category) {
        this.author = author;
        this.mainClass = mainClass;
        this.name = name;
        this.category = category;
        //this.infoString=fileName + '-' + plugin_name + '-' + plugin_auther
        enable = true;
    }

    public String getInfoString() {
        return infoString;
    }

    public void setInfoString(String infoString) {
        this.infoString = infoString;
    }

    public Object getInstanceClass() {
        return instanceClass;
    }

    public void setInstanceClass(Object instanceClass) {
        this.instanceClass = instanceClass;
    }

    public boolean getEnable() {

        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getCategory() {
        return category;
    }

    public String getAuthor() {
        return author;
    }

    public String getName() {
        return name;

    }

    public String getMainClass() {
        return mainClass;
    }


}
