package cn.mariozzj.sp1.session;
import java.sql.*;

public class DBRegister {
        public void register(String username,String password,String email,String token){
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            try(
            Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pmicu?characterEncoding=UTF-8&serverTimezone=UTC",
                        "pmicu", "mariozzj");
            Statement s = c.createStatement();
        )//用法：在try-catch的try后的（）中创建连接，后续不需要手动关闭
        {
            String sql = "insert into user values (null,'" + username +"', '" +password+"', '"+email+"')";
            System.out.println(sql);
            s.execute(sql);
            String sql2 = "update token set user='"+username+"' where token='"+token+"'";
            s.execute(sql2);

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
