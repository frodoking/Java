package cn.com.frodo.knowledge.spi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MysqlDriverSpiTest {
    public static final String URL = "jdbc:mysql://localhost:3306/test";
    public static final String USER = "root";
    public static final String PASSWORD = "root";

    public static void main(String[] args) throws Exception {
        //1.加载驱动程序
        Class.forName("com.mysql.jdbc.Driver");
        //2. 获得数据库连接
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        //3.操作数据库，实现增删改查
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT user_name, age FROM user");
        //如果有数据，rs.next()返回true
        while(rs.next()){
            System.out.println(rs.getString("user_name")+" 年龄："+rs.getInt("age"));
        }
    }
}
