package com.caiw.utils;

/**
 * Created by 蔡维 in 15:34 2018/4/16
 */
public class ShellUtil {
    /**
     * 调用打分脚本
     */
    public static void doShell(){
        try {
            Process ps = Runtime.getRuntime().exec("/caiw/GraduationDesign/bin/term_score.sh");
            ps.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("调用打分失败！");
        }
    }
}
