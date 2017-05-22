package com.echosun.serialport;

public class PortInUse extends Exception {

    private static final long serialVersionUID = 1L;

    public PortInUse() {
    }

    @Override
    public String toString() {
        System.out.println(super.getStackTrace());
        return "端口已被占用！打开串口操作失败！";
    }
}
