package com.caiw.utils;

import java.sql.*;

/**
 * jdbc工具类
 * @author 蔡维
 */
public class JdbcUtil {
//    private final static String url = PropertiesUtil.getValues("url");
//    private final static String name = PropertiesUtil.getValues("user");
//    private final static String password = PropertiesUtil.getValues("password");
//    private final static String driverName = PropertiesUtil.getValues("driverName");
    /**
     * 加载驱动
     */
    static {
        try {
//            Class.forName(driverName).newInstance();
//            Class.forName("com.mysql.jdbc.Driver");
            Class.forName(PropertiesUtil.getValues("driverName"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 创建并获取链接
     * @return
     */
    public static Connection getCon(){
        Connection con = null;
        try {
//             con = DriverManager.getConnection("jdbc:mysql://localhost:3306/data_user","root","");
            con = DriverManager.getConnection(PropertiesUtil.getValues("url"),PropertiesUtil.getValues("user"),PropertiesUtil.getValues("password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    /**
     * 释放资源方法
     * @param con
     * @param stat
     * @param result
     */
    public static void close(Connection con, Statement stat, ResultSet result){
        if(result != null){
            try {
                result.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(stat != null){
            try {
                stat.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(con != null){
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 匹配  “？”
     * @param ps
     * @param objects
     * @throws SQLException
     * Object...objects:声明只能放在最后
     */
    public static void setValues(PreparedStatement ps,Object...objects) throws SQLException {
        for(int i= 0;i<objects.length;i++){
            ps.setObject(i+1,objects[i]);
        }
    }
}
