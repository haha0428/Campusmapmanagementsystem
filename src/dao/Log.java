package dao;

import util.Link;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;


public class Log {

    private static Connection conn;

    private static PreparedStatement ps;

    private static ResultSet set;

    static {
        try {
            conn = Link.getConn();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static boolean Log(String name, String password) {
        List<Map<String,Object>> maps=Link.queryMap("select *from user where name = ?",name);
        for (int i = 0; i < maps.size(); i++) {
            if(password.equals((String)(maps.get(i).get("password"))))
                return true;
        }
        return false;
    }

    public static boolean Register(String name, String password, String repassword) {
        List<Map<String,Object>> maps=Link.queryMap("select *from user where name = ?",name);
        for (int i = 0; i < maps.size(); i++) {
            if(password.equals((String)(maps.get(i).get("password"))))
                return false;
        }
        return true;
    }

    public static int addUser(String name,String password){
        return Link.updata("insert into user(name,password) values (?,?)",name,password);
    }


    public Log() {
    }
}
