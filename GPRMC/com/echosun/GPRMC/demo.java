package com.echosun.GPRMC;

import javax.swing.*;

/**
 * Created by echosun.
 * All rights reserved.
 */
class demo extends JPanel {

    demo() {
        setLayout(null);

        JLabel titleLB = new JLabel("$GPRMC推荐定位信息");
        titleLB.setHorizontalAlignment(SwingConstants.CENTER);
        titleLB.setBounds(6, 6, 438, 35);
        add(titleLB);

        JLabel label_0 = new JLabel("--");
        label_0.setBounds(114, 53, 115, 16);
        add(label_0);

        JLabel label_1 = new JLabel("--");
        label_1.setBounds(114, 81, 115, 16);
        add(label_1);

        JLabel label_2 = new JLabel("--");
        label_2.setBounds(114, 109, 115, 16);
        add(label_2);

        JLabel label_3 = new JLabel("--");
        label_3.setBounds(114, 137, 115, 16);
        add(label_3);

        JLabel label_4 = new JLabel("--");
        label_4.setBounds(114, 165, 115, 16);
        add(label_4);

        JLabel label_5 = new JLabel("--");
        label_5.setBounds(114, 193, 115, 16);
        add(label_5);

        JLabel label_6 = new JLabel("--");
        label_6.setBounds(315, 53, 115, 16);
        add(label_6);

        JLabel label_7 = new JLabel("--");
        label_7.setBounds(315, 81, 115, 16);
        add(label_7);

        JLabel label_8 = new JLabel("--");
        label_8.setBounds(315, 109, 115, 16);
        add(label_8);

        JLabel label_9 = new JLabel("--");
        label_9.setBounds(315, 137, 115, 16);
        add(label_9);


        JLabel label_10 = new JLabel("--");
        label_10.setBounds(315, 165, 115, 16);
        add(label_10);

        JLabel label_11 = new JLabel("--");
        label_11.setBounds(315, 193, 115, 16);
        add(label_11);

        JLabel lblNewLabel = new JLabel("UTC时间");
        lblNewLabel.setBounds(41, 53, 61, 16);
        add(lblNewLabel);

        JLabel label_12 = new JLabel("定位状态");
        label_12.setBounds(41, 81, 61, 16);
        add(label_12);

        JLabel label_13 = new JLabel("纬度");
        label_13.setBounds(41, 109, 61, 16);
        add(label_13);

        JLabel label_14 = new JLabel("纬度半球");
        label_14.setBounds(41, 137, 61, 16);
        add(label_14);

        JLabel label_15 = new JLabel("经度");
        label_15.setBounds(41, 165, 61, 16);
        add(label_15);

        JLabel label_16 = new JLabel("经度半球");
        label_16.setBounds(41, 193, 61, 16);
        add(label_16);

        JLabel label_17 = new JLabel("地面速率");
        label_17.setBounds(242, 53, 61, 16);
        add(label_17);

        JLabel label_18 = new JLabel("地面航向");
        label_18.setBounds(242, 81, 61, 16);
        add(label_18);

        JLabel lblUtc = new JLabel("UTC日期");
        lblUtc.setBounds(242, 109, 61, 16);
        add(lblUtc);

        JLabel label_20 = new JLabel("磁偏角");
        label_20.setBounds(242, 137, 61, 16);
        add(label_20);

        JLabel label_21 = new JLabel("磁偏角方向");
        label_21.setBounds(242, 165, 74, 16);
        add(label_21);

        JLabel label_22 = new JLabel("模式指示");
        label_22.setBounds(242, 193, 61, 16);
        add(label_22);
        setVisible(true);


    }
}
