package cn.com.frodo.knowledge.mysql;

import java.sql.*;

public class MySqlClient {

    private static final String DRIVEER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://192.168.10.104:3306/email?"
            + "user=email&password=kingsoft_email&useUnicode=true&characterEncoding=UTF8";

    private Connection conn;
    private Statement sm;

    public void connect() {
        try {
            Class.forName(DRIVEER);
            // 动态加载mysql驱动
            System.out.println("成功加载MySQL驱动程序");
            // 一个Connection代表一个数据库连接
            conn = DriverManager.getConnection(URL);
            // Statement里面带有很多方法，比如executeUpdate可以实现插入，更新和删除等
            sm = conn.createStatement();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet select(String sql) {
        try {
            return sm.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public int update(String sql) {
        // if (result != -1) {
        int result = 0;
        try {
            result = sm.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public int[] batchInsert(String sql) {
        int[] result = null;
        try {
            sm.addBatch(sql);
            result = sm.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void close() {
        try {
            sm.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
