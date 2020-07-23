package cn.mariozzj.sp2.dao;


import cn.mariozzj.sp2.bean.User;
import cn.mariozzj.sp2.bean.VisitRecord;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VisitRecordDAO {
    public VisitRecordDAO(){
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
            String sql = "select count(*) from visitrecord";
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt(1);
            }
            System.out.println("totalVisitRecord:" + total);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }
    public void add(VisitRecord record) {

        String sql = "insert into visitrecord values (null,?,?,?,?)";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);) {
            ps.setString(1, record.ipaddr);
            ps.setString(2,record.location);
            ps.setString(3, record.url);
            ps.setString(4, record.date);
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                record.id = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {

        try (Connection c = getConnection(); Statement s = c.createStatement();) {

            String sql = "delete from visitrecord where id = " + id;

            s.execute(sql);

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public VisitRecord get(int id) {
        VisitRecord record = null;

        try (Connection c = getConnection(); Statement s = c.createStatement();) {

            String sql = "select * from visitrecord where id = " + id;

            ResultSet rs = s.executeQuery(sql);

            if (rs.next()) {
                record = new VisitRecord();
                String ipaddr = rs.getString("ipaddr");
                String url = rs.getString("url");
                String date  = rs.getString("date");
                record.ipaddr = ipaddr;
                record.url = url;
                record.date = date;
                record.id = id;
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return record;
    }

    public List<VisitRecord> list() {
        return list(0, Short.MAX_VALUE);
    }

    public List<VisitRecord> list(int start, int count) {
        List<VisitRecord> records = new ArrayList<VisitRecord>();

        String sql = "select * from visitrecord order by id desc limit ?,? ";

        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, start);
            ps.setInt(2, count);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                VisitRecord record = new VisitRecord();
                int id = rs.getInt("id");
                String url = rs.getString("url");
                String ipaddr = rs.getString("ipaddr");
                String date = rs.getString("date");
                record.id = id;
                record.ipaddr = ipaddr;
                record.url = url;
                record.date = date;
                records.add(record);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return records;
    }

}
