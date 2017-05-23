package com.echosun.plugins;

/**
 * Created by echosun.
 * All rights reserved.
 */
public class EstablishFolderFailure extends Exception {
    public EstablishFolderFailure() {

    }

    @Override
    public String toString() {
        return "新建插件(plugsin)文件夹失败。请检查写入权限。";
    }
}
