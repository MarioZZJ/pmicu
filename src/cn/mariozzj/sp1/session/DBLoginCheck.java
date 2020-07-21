package cn.mariozzj.sp1.session;
import java.sql.*;

public class DBLoginCheck {
    public boolean logincheck(String username,String password) {
        boolean flag=false;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (
                Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pmicu?characterEncoding=UTF-8&serverTimezone=UTC",
                        "pmicu", "mariozzj");
                Statement s = c.createStatement();
        )//用法：在try-catch的try后的（）中创建连接，后续不需要手动关闭
        {
            String sql = "select password from user where username = '" + username+"'";
            System.out.println(sql);
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                flag = password.equals(rs.getString("password"));
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return flag;
    }
}
