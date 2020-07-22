package cn.mariozzj.sp2.dao;

import cn.mariozzj.sp2.bean.Token;
import cn.mariozzj.sp2.bean.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TokenDAO {
    public TokenDAO(){
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
            String sql = "select count(*) from token";
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt(1);
            }
            System.out.println("totalToken:" + total);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    public void add(Token token) {

        String sql = "insert into token values(null,?,?)";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setString(1, token.tokenname);
            ps.setString(2, token.username);

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                token.id = rs.getInt(1);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public void update(Token token) {

        String sql = "update user set tokenname= ?, username = ? where id = ?";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setString(1, token.tokenname);
            ps.setString(2, token.username);
            ps.setInt(3, token.id);

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

    public Token get(int id) {
        Token token = null;

        try (Connection c = getConnection(); Statement s = c.createStatement();) {

            String sql = "select * from token where id = " + id;

            ResultSet rs = s.executeQuery(sql);

            if (rs.next()) {
                token = new Token();
                String tokenname = rs.getString("tokenname");
                String username = rs.getString("username");
                token.tokenname = tokenname;
                token.username = username;
                token.id = id;
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return token;
    }

    public List<Token> list() {
        return list(0, Short.MAX_VALUE);
    }

    public List<Token> list(int start, int count) {
        List<Token> tokens = new ArrayList<Token>();

        String sql = "select * from token order by id desc limit ?,? ";

        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, start);
            ps.setInt(2, count);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Token token = new Token();
                int id = rs.getInt("id");
                String tokenname = rs.getString("tokenname");
                String username = rs.getString("username");
                token.id = id;
                token.tokenname = tokenname;
                token.username = username;
                tokens.add(token);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return tokens;
    }

    public boolean hasUser(String tokenname){
        try (Connection c = getConnection(); Statement s = c.createStatement();) {

            String sql = "select username from token where tokenname = '" + tokenname+"'";

            ResultSet rs = s.executeQuery(sql);

            if (rs.next()) {
                return !"".equals(rs.getString("username"));
            }else{return true;}


        } catch (SQLException e) {

            e.printStackTrace();
        }
        return true;
    }


}
