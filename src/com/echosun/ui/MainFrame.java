package com.echosun.ui;


import com.echosun.serialport.*;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class MainFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    // 功能区参数
    private int whichFunction;

    private JComboBox<String> serialSelectCB;
    private JComboBox<Integer> baudSelectCB;
    private SerialPort serialport;
//    private JLabel gpsLB1;
//    private JLabel gpsTimeLB;
//    private JLabel gpsTxtLB;

    public MainFrame() {
        super("main");
        // 设置程序窗口居中显示
        Point p = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        int WIDTH = 1000;
        int HEIGHT = 600;
        setBounds(p.x - WIDTH / 2, p.y - HEIGHT / 2, WIDTH, HEIGHT);

        //创建菜单栏
        JMenuBar mainBar = new JMenuBar();
        this.setJMenuBar(mainBar);
        JMenu mainMenu[] = {new JMenu("数据库"), new JMenu("常用工具"), new JMenu("拓展插件")};
        JMenuItem mainItem[][] = {
                {new JMenuItem("上传"), new JMenuItem("重新连接")},
                {new JMenuItem("计算器"), new JMenuItem("记事本")},
                {}
        };
        for (int i = 0; i < mainMenu.length; i++) {
            mainBar.add(mainMenu[i]);
            for (int j = 0; j < mainItem[i].length; j++) {
                mainMenu[i].add(mainItem[i][j]);
            }

        }


        JPanel serialPortSetJP = new JPanel();
        serialPortSetJP.setBorder(BorderFactory.createTitledBorder("串口设置"));
        getContentPane().setLayout(null);
        serialPortSetJP.setBounds(10, 10, 351, 83);
        getContentPane().add(serialPortSetJP, BorderLayout.WEST);

        JButton setRunBT = new JButton("打开串口");
        serialPortSetJP.setLayout(null);
        getContentPane().setLayout(null);
        setRunBT.setBounds(229, 48, 90, 21);
        serialPortSetJP.add(setRunBT);
        setRunBT.addActionListener((ActionEvent actionEvent) -> {
                    int tp = 1;
                    int baud = (int) baudSelectCB.getSelectedItem();
                    String serial = (String) serialSelectCB.getSelectedItem();
                    if (serial.equals("请选择串口"))
                        ShowUtils.warningMessage("请设置正确的串口");
                    else if ("打开串口".equals(setRunBT.getText())) {

                        try {
                            serialport = SerialportFun.openPort(serial, baud);
                        } catch (SerialPortParameterFailure | NoSuchPort | NotASerialPort | PortInUse e1) {
                            ShowUtils.warningMessage(e1.toString());
                            tp = 0;
                        }
                        if (tp == 1)
                            setRunBT.setText("关闭串口");
                    } else if ("关闭串口".equals(setRunBT.getText())) {
                        SerialportFun.closePort(serialport);
                        setRunBT.setText("打开串口");
                    } else {
                        ShowUtils.errorMessage("串口关闭失败");
                    }
                    try {
                        System.out.println(serialport);
                        if (tp == 1)
                            SerialportFun.addListener(serialport, new SerialListener());
                    } catch (TooManyListeners ignored) {
                    }

                }
        );

        //串口选择
        serialSelectCB = new JComboBox<>();
        serialSelectCB.setBounds(86, 17, 233, 21);
        serialPortSetJP.add(serialSelectCB);
        serialSelectCB.removeAllItems();
        serialSelectCB.addItem("请选择串口");
        // 添加下拉框的功能
        serialSelectCB.addPopupMenuListener(new PopupMenuListener() {

            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                serialSelectCB.removeAllItems();
                ArrayList<String> commList = SerialportFun.findPort();
                // 检查是否有可用串口，有则加入选项中
                if (commList == null || commList.size() < 1) {
                    ShowUtils.warningMessage("没有搜索到有效串口！");
                } else {
                    for (String s : commList) {
                        serialSelectCB.addItem(s);
                    }
                }
            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent arg0) {

            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {

            }
        });


        //波特率选择
        baudSelectCB = new JComboBox<>();
        baudSelectCB.setBounds(86, 48, 119, 21);
        serialPortSetJP.add(baudSelectCB);
        baudSelectCB.addItem(9600);
        baudSelectCB.addItem(19200);
        baudSelectCB.addItem(38400);
        baudSelectCB.addItem(57600);
        baudSelectCB.addItem(115200);

        JLabel serialSelectLab = new JLabel("串口选择");
        serialSelectLab.setFont(new Font("宋体", Font.BOLD, 14));
        serialSelectLab.setBounds(10, 20, 66, 15);
        serialPortSetJP.add(serialSelectLab);

        JLabel baudSelectLab = new JLabel("波特率");
        baudSelectLab.setFont(new Font("宋体", Font.BOLD, 14));
        baudSelectLab.setBounds(10, 51, 66, 15);
        serialPortSetJP.add(baudSelectLab);

        JPanel functionsJP = new JPanel();
        functionsJP.setBorder(BorderFactory.createTitledBorder("功能面板"));
        functionsJP.setBounds(10, 103, 454, 428);
        getContentPane().add(functionsJP);
        functionsJP.setLayout(null);

        JTabbedPane functionsTP = new JTabbedPane(JTabbedPane.TOP);
        functionsTP.setBounds(10, 21, 434, 397);
        functionsJP.add(functionsTP);
        whichFunction = 0;

        // 新页面
        String[] tabNames = {"首页"};
        JPanel functionFirJP = new JPanel();
        functionsTP.addTab(tabNames[0], null, functionFirJP, null);// 加入第一个页面
        functionFirJP.setLayout(null);


        JPanel serialMessagesJP = new JPanel();
        serialMessagesJP.setBorder(BorderFactory.createTitledBorder("串口消息"));
        serialMessagesJP.setBounds(474, 10, 500, 367);
        getContentPane().add(serialMessagesJP);
        serialMessagesJP.setLayout(null);

        JScrollPane serialMessagesSP = new JScrollPane();
        serialMessagesSP.setBounds(10, 20, 490, 337);
        serialMessagesJP.add(serialMessagesSP);

        JTextArea serialMessagesTF = new JTextArea();
        serialMessagesTF.setForeground(Color.GREEN);
        serialMessagesTF.setBackground(Color.BLACK);
        serialMessagesTF.setFont(new Font("黑体", Font.BOLD, 11));
        serialMessagesSP.setViewportView(serialMessagesTF);

        JPanel messageSendJP = new JPanel();
        messageSendJP.setBorder(BorderFactory.createTitledBorder("消息发送"));
        messageSendJP.setBounds(474, 387, 502, 144);
        getContentPane().add(messageSendJP);
        messageSendJP.setLayout(null);

        JTextField messagesTF = new JTextField();
        messagesTF.setBounds(10, 29, 389, 105);
        messageSendJP.add(messagesTF);

        JButton sendMessagesBT = new JButton("发送");
        sendMessagesBT.setBounds(409, 29, 93, 23);
        messageSendJP.add(sendMessagesBT);

        JButton cleanMessagesBT = new JButton("清除");
        cleanMessagesBT.setBounds(409, 58, 93, 23);
        messageSendJP.add(cleanMessagesBT);

        JCheckBox sendNewlineCB = new JCheckBox("发送换行");
        sendNewlineCB.setActionCommand("发送新行");
        sendNewlineCB.setBounds(409, 87, 103, 23);
        messageSendJP.add(sendNewlineCB);

        JButton messageCleanBT = new JButton("保存数据");
        messageCleanBT.setBounds(371, 10, 93, 23);
        getContentPane().add(messageCleanBT);

        JButton aboutBT = new JButton("关于");
        aboutBT.setBounds(371, 68, 93, 23);
        getContentPane().add(aboutBT);

        JButton messageSaveBT = new JButton("清空数据");
        messageSaveBT.setBounds(371, 39, 93, 23);
        getContentPane().add(messageSaveBT);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.setVisible(true);
    }

    public int getWhichFunction() {
        return whichFunction;
    }

    public void setWhichFunction(int whichFunction) {
        this.whichFunction = whichFunction;
    }

    private class SerialListener implements SerialPortEventListener {

        void messageAnalyse(String data) {

        }

        /**
         * 处理监控到的串口事件
         */
        public void serialEvent(SerialPortEvent serialPortEvent) {

            switch (serialPortEvent.getEventType()) {

                case SerialPortEvent.BI: // 10 通讯中断
                    ShowUtils.errorMessage("与串口设备通讯中断");
                    break;

                case SerialPortEvent.OE: // 7 溢位（溢出）错误

                case SerialPortEvent.FE: // 9 帧错误

                case SerialPortEvent.PE: // 8 奇偶校验错误

                case SerialPortEvent.CD: // 6 载波检测

                case SerialPortEvent.CTS: // 3 清除待发送数据

                case SerialPortEvent.DSR: // 4 待发送数据准备好了

                case SerialPortEvent.RI: // 5 振铃指示

                case SerialPortEvent.OUTPUT_BUFFER_EMPTY: // 2 输出缓冲区已清空
                    break;

                case SerialPortEvent.DATA_AVAILABLE: // 1 串口存在可用数据
                    byte[] data;
                    try {
                        if (serialport == null) {
                            ShowUtils.errorMessage("串口对象为空！监听失败！");
                        } else {
                            data = SerialportFun.readFromPort(serialport);
                            String message = new String(data);
                            messageAnalyse(message);
                            //serialMessagesTF.append(message + "\r\n");
                            //serialMessagesTF.setCaretPosition(serialMessagesTF.getText().length());

                        }
                    } catch (Exception e) {
                        ShowUtils.errorMessage(e.toString());
                        System.exit(0);
                    }
                    break;
            }
        }
    }


}
