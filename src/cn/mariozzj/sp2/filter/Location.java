package cn.mariozzj.sp2.filter;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.sql.*;

import net.sf.json.JSONObject;

public class Location {
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
    public static JSONObject readJsonFromUrl(String url) throws IOException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String jsonText = readAll(rd);
            JSONObject j = JSONObject.fromObject(jsonText);
            return j;
        } finally {
            is.close();
        }
    }
    public JSONObject baiduMap(String ip) throws IOException{
        String url = "http://api.map.baidu.com/location/ip?ak=Gla1WH48KHwFzHGFfcAtQdKavZCURdNA&ip="+ip+"&coor=bd09ll";
        JSONObject json = readJsonFromUrl(url);
        System.out.println(json.toString());
        return json;
    }

    public String GetLocation(String ip){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pmicu?characterEncoding=UTF-8&serverTimezone=UTC", "pmicu","mariozzj"); Statement s = c.createStatement();)
        {
            String sql = "select province,city from ip_location where ipaddr = '" + ip +"'" ;
            ResultSet rs = s.executeQuery(sql);
            if(rs.next()) {
                String province = rs.getString("province");
                String city = rs.getString("city");
                return province.concat(city);
            }
            else{
                JSONObject json = baiduMap(ip);
                if(0==(int)json.get("status")) {
                    String x = (String) ((JSONObject) json.get("content")).getJSONObject("point").get("x");
                    String y = (String) ((JSONObject) json.get("content")).getJSONObject("point").get("y");
                    String city = (String) ((JSONObject) json.get("content")).getJSONObject("address_detail").get("city");
                    String province = (String) ((JSONObject) json.get("content")).getJSONObject("address_detail").get("province");
                    int city_code = (int) ((JSONObject) json.get("content")).getJSONObject("address_detail").get("city_code");
                    sql = "insert into ip_location VALUES ('"+ip+"', '"+city_code+"', '"+province+"', '"+city+"', '"+x+"', '"+y+"')";
                    System.out.println(sql);
                    s.execute(sql);
                    return province.concat(city);
                }
                else{
                    System.out.println("Error".concat(json.get("status").toString()));
                    return "Error".concat(json.get("status").toString());
                }
            }
        }
     catch (Exception e) {
            e.printStackTrace();
        }
    return null;
    }

}


