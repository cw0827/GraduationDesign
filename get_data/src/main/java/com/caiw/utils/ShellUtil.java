package com.caiw.utils;

import java.io.IOException;

/**
 * Created by 蔡维 in 15:34 2018/4/16
 */
public class ShellUtil {
    /**
     * 调用筛选词汇脚本
     */
    public static void doShell(){
        try {
            Process ps = Runtime.getRuntime().exec("/caiw/GraduationDesign/bin/screen.sh");
            ps.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("调用筛选失败！");
        }
    }
}
