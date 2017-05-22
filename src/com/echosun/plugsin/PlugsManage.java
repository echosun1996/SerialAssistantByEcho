package com.echosun.plugsin;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by echosun.
 * All rights reserved.
 */
public class PlugsManage {
    public static void main(String[] args) throws MalformedURLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        System.out.println(122);
        PlugsManage plugsManage = new PlugsManage();
        plugsManage.addPlugs();
    }

    public void findAllPlugs() {

    }

    private void addPlugs() throws MalformedURLException, ClassNotFoundException {


        String path = "/Users/echosun/Workspaces/IDEA/SerialAssistantByEcho/out/artifacts/Plugs_jar/Plugs.jar";
        File file = new File(path);
        URL url = file.toURI().toURL();
        URLClassLoader loader = new URLClassLoader(new URL[]{url});
        try {
            AnalyseAPI cl1 = (AnalyseAPI) loader.loadClass("com.echosun.plugs.testclass").newInstance();
            AnalyseFuns use = new AnalyseFuns(cl1);
            use.use();

        } catch (InstantiationException e) {
            System.out.println("INS ERROR!");
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            System.out.println("ILL ERROR!");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("NOT FOUND!");
            e.printStackTrace();
        }


    }

}
