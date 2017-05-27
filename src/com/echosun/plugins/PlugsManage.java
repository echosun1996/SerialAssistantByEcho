package com.echosun.plugins;

import com.echosun.pluginsAPI.AnalyseAPI;
import com.echosun.ui.ShowUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
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

    private static Map<String, Plugs> PlugsMap;
    private static Map<String, AnalyseFuns> AnalysePlugs;

    static {
        File directory = new File("");
        ORI_FOLDER = directory.getAbsolutePath() + "/plugins/";
    }

    public static String test() {
        return ORI_FOLDER;
    }

//    public static Map<String, AnalyseFuns>

    public static Map<String, AnalyseFuns> getAnalysePlugs() {
        return AnalysePlugs;
    }

    public static void pluginsFrameInit(JTabbedPane functionsTP) {
        AnalysePlugs = new HashMap<>();

        functionsTP.removeAll();

        //need to make a class to establish the index tab.
//        for (int i = 1; i < functionsTP.getTabCount(); i++) {
//            functionsTP.remove(i);
//        }


        if (PlugsMap != null)
            for (Plugs plugs : PlugsMap.values()) {
                if (plugs.getEnable()) {
                    System.out.println(plugs.getInfoString() + "--FrameInit Started!");

                    if (plugs.getCategory().equals("Analyse")) {
                        AnalyseFuns analyseFuns = new AnalyseFuns((AnalyseAPI) plugs.getInstanceClass());
                        analyseFuns.initUI(functionsTP, plugs.getName());
                        AnalysePlugs.put(plugs.getName(), analyseFuns);
                    }
                }

            }
    }

    public static Map<String, Plugs> getPlugsList() {
        //ArrayList<String> ret = new ArrayList<>();//this is the list of the return
        PlugsMap = new HashMap<>();
        System.out.println("folder:" + ORI_FOLDER);//\\
        File file = new File(ORI_FOLDER);
        File[] tempList = file.listFiles();

        if (tempList == null) {
            boolean establishNew = file.mkdir();
            System.out.println("not found so establish:" + establishNew);//\\
            try {
                if (!establishNew) throw new EstablishFolderFailure();
                else ShowUtils.message("未找到插件文件夹，已新建。");
            } catch (EstablishFolderFailure establishFolderFailure) {
                ShowUtils.errorMessage(establishFolderFailure.toString());
                return null;
            }
        }
        for (File temp_file : (null != tempList) ? tempList : new File[0]) {

            String regEx = ".*(\\.jar)";
            Pattern pattern = Pattern.compile(regEx);
            Matcher matcher = pattern.matcher(temp_file.toString());
            boolean rs = matcher.matches();
            System.out.println(temp_file.toString() + ":" + rs);//\\
            if (!rs) continue;

            String fileName = temp_file.getName();
            System.out.println("fileName = " + fileName);

            //jar->info.xml->plug class->load

            URL url = null;
            try {
                JarFile jf = new JarFile(temp_file);
                Enumeration enu = jf.entries();
                while (enu.hasMoreElements()) {
                    JarEntry element = (JarEntry) enu.nextElement();

                    //find info.xml
                    if (element.getName().endsWith("info.xml")) {
                        System.out.println("info.xml load finish");

                        SAXReader reader = new SAXReader();
                        try {
                            // 通过reader对象的read方法加载books.xml文件,获取docuemnt对象。
                            Document document = reader.read(jf.getInputStream(element));

                            // 通过document对象获取根节点bookstore

                            Element pluginsNode = document.getRootElement();
                            System.out.println(pluginsNode.element("name").getText());
                            System.out.println(pluginsNode.element("category").getText());
                            System.out.println(pluginsNode.element("author").getText());
                            System.out.println(pluginsNode.element("mainClass").getText());
                            String plugin_name = pluginsNode.element("name").getText();
                            String plugin_category = pluginsNode.element("category").getText();
                            String plugin_mainClass = pluginsNode.element("mainClass").getText();
                            String plugin_auther = pluginsNode.element("author").getText();
                            System.out.println((fileName + '-' + plugin_name + '-' + plugin_auther));

                            Plugs ana_temp = new Plugs(plugin_name, plugin_auther, plugin_mainClass, plugin_category);
                            ana_temp.setInfoString(fileName + '-' + plugin_name + '-' + plugin_auther);

                            url = temp_file.toURI().toURL();
                            URLClassLoader loader = new URLClassLoader(new URL[]{url});

                            Object obj_temp = loader.loadClass(ana_temp.getMainClass()).newInstance();
                            ana_temp.setInstanceClass(obj_temp);

                            PlugsMap.put(fileName, ana_temp);
                            System.out.println("Load Finish");

                        } catch (DocumentException | NullPointerException e) {
                            e.printStackTrace();
                            System.out.println(jf.getName() + "不是标准化插件");//\\

                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InstantiationException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }


                    }
                }

            } catch (IOException e) {
                System.out.println("io error");//\\
                e.printStackTrace();

            }

        }
        return PlugsMap;
    }

    public void findAllPlugs() {

    }


}
