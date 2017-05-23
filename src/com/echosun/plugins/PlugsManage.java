package com.echosun.plugins;

import com.echosun.pluginsAPI.AnalyseAPI;
import com.echosun.ui.ShowUtils;

import javax.swing.*;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by echosun.
 * All rights reserved.
 */
public class PlugsManage {
    private static final String ORI_FOLDER;

    static {
        File directory = new File("");
        ORI_FOLDER = directory.getAbsolutePath() + "/plugins/";
    }

    public static String test() {
        return ORI_FOLDER;
    }

    public static ArrayList<String> frameInit(JTabbedPane functionsTP) {
        ArrayList<String> ret = new ArrayList<>();
        return null;
    }

    public static void main(String[] args) throws MalformedURLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        PlugsManage plugsManage = new PlugsManage();
        plugsManage.getPlugsList();
        //plugsManage.addPlugs();
        //System.out.println(plugsManage.getClass().getClassLoader().getResource(""));
    }

    private ArrayList<String> getPlugsList() {
        System.out.println(ORI_FOLDER);
        File file = new File(ORI_FOLDER);
        File[] tempList = file.listFiles();

        if (tempList == null) {
            boolean establishNew = file.mkdir();
            System.out.println(establishNew);
            try {
                if (!establishNew) throw new EstablishFolderFailure();
                else ShowUtils.message("未找到插件文件夹，已新建。");
            } catch (EstablishFolderFailure establishFolderFailure) {
                ShowUtils.errorMessage(establishFolderFailure.toString());
                return null;
            }
        }
        System.out.println(tempList != null ? tempList.length : 0);
        for (File tempfile : (null != tempList) ? tempList : new File[0]) {

            String regEx = ".*(\\.jar)";    // Non-greedy match on filler
            Pattern pattern = Pattern.compile(regEx);
            Matcher matcher = pattern.matcher(tempfile.toString());
            // 字符串是否与正则表达式相匹配
            boolean rs = matcher.matches();
            System.out.println(tempfile.toString() + ":" + rs);
            if (!rs) continue;
            URL url = null;
            try {
                JarFile jf = null;
                try {
                    jf = new JarFile(tempfile);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
                Enumeration enu = jf != null ? jf.entries() : null;
                while (enu.hasMoreElements()) {
                    JarEntry element = (JarEntry) enu.nextElement();
                    String name = element.getName();
                    if (name.toUpperCase().endsWith(".CLASS")) {
                        System.out.println(name);
                    }
                }


                url = tempfile.toURI().toURL();
                URLClassLoader loader = new URLClassLoader(new URL[]{url});
                System.out.println(loader.getParent());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return null;
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
