package util;

import java.io.InputStream;
import java.sql.*;
import java.util.*;

public class Link {

    private static String driver;

    private static String url;

    private static String username;

    private static String code;

    private static Connection conn;


    static {
        try {
            InputStream is = Link.class.getClassLoader().getResourceAsStream("sql.properties");
            Properties p = new Properties();
            p.load(is);

            driver = p.getProperty("driver");

            url = p.getProperty("url");

            username = p.getProperty("username");

            code = p.getProperty("code");

            Class.forName(driver);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Link() {
    }

    public static Connection getConn() {
        try {
            return DriverManager.getConnection(url, username, code);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void closeAll(Connection conn, PreparedStatement ps, ResultSet set) {
        try {
            if (conn != null)
                conn.close();
            if (ps != null)
                ps.close();
            if (set != null)
                set.close();
        } catch (Exception e) {
        }
    }

    public static int updata(String sql, Object... params) {
        Connection conn = getConn();

        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            setParam(ps, params);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeAll(conn, ps, null);
        }
    }

    public static List<Map<String,Object>> queryMap(String sql, Object... params) {
        Connection conn= getConn();
        PreparedStatement ps = null;
        ResultSet set =null;
        try {
            ps = conn.prepareStatement(sql);
            setParam(ps, params);
            set=ps.executeQuery();
            ResultSetMetaData metaData=set.getMetaData();
            ArrayList<Map<String,Object>> maps=new ArrayList<>();
            while(set.next()){
                HashMap<String,Object> map= new HashMap();
                for(int i=metaData.getColumnCount();i>=1;i--){
                    map.put(metaData.getColumnName(i),set.getObject(i));
                }
                maps.add(map);
            }
            return maps;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeAll(conn, ps, set);
        }
    }


    private static void setParam(PreparedStatement ps, Object... params) {
        try {
            if (params != null && params.length != 0) {
                for (int i = 0; i < params.length; i++) {
                    ps.setObject(i + 1, params[i]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
