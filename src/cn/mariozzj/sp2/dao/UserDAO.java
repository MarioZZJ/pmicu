package cn.mariozzj.sp2.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import cn.mariozzj.sp2.bean.User;

public class UserDAO {

    public UserDAO(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pmicu?characterEncoding=UTF-8&serverTimezone=UTC", "pmicu",
                "mariozzj");
    }

    public int getTotal() {
        int total = 0;
        try (Connection c = getConnection(); Statement s = c.createStatement();) {
            String sql = "select count(*) from user";
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt(1);
            }
            System.out.println("totalUser:" + total);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    public void add(User user) {

        String sql = "insert into user values(null,?,?,?,?,CURRENT_TIMESTAMP)";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setString(1, user.username);
            ps.setString(2, user.password);
            ps.setString(3, user.email);
            ps.setInt(4, user.userType);

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                user.id = rs.getInt(1);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public void update(User user) {

        String sql = "update user set username= ?, password = ? , email = ? , userType= ? where id = ?";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setString(1, user.username);
            ps.setString(2, user.password);
            ps.setString(3, user.email);
            ps.setInt(4, user.userType);
            ps.setInt(5, user.id);

            ps.execute();

        } catch (SQLException e) {

            e.printStackTrace();
        }

    }

    public void delete(int id) {

        try (Connection c = getConnection(); Statement s = c.createStatement();) {

            String sql = "delete from user where id = " + id;

            s.execute(sql);

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public User get(int id) {
        User user = null;

        try (Connection c = getConnection(); Statement s = c.createStatement();) {

            String sql = "select * from user where id = " + id;

            ResultSet rs = s.executeQuery(sql);

            if (rs.next()) {
                user = new User();
                String username = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");
                int userType = rs.getInt("userType");
                user.username = username;
                user.password = password;
                user.email = email;
                user.userType = userType;
                user.id = id;
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return user;
    }

    public List<User> list() {
        return list(0, Short.MAX_VALUE);
    }

    public List<User> list(int start, int count) {
        List<User> users = new ArrayList<User>();

        String sql = "select * from user order by id desc limit ?,? ";

        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, start);
            ps.setInt(2, count);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User user = new User();
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");
                int userType = rs.getInt("userType");
                user.id = id;
                user.username = username;
                user.password = password;
                user.email = email;
                user.userType = userType;
                users.add(user);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return users;
    }

    public boolean exist(String username){
        String sql = "select * from user where username = '" + username+"';";
        try (Connection c = getConnection(); Statement s = c.createStatement();) {
            ResultSet rs = s.executeQuery(sql);
            System.out.println(rs);
            if (rs.next()) {
                return true;
            }
            else{
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean logincheck(User user){
        try (Connection c = getConnection(); Statement s = c.createStatement();) {
            String sql = "select password from user where username = '" +user.username+"'";
            ResultSet rs = s.executeQuery(sql);
            if (rs.next()) {
                System.out.println(user.password);
                System.out.println(rs.getString("password"));
                return (user.password).equals(rs.getString("password"));
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return false;
    }
}
