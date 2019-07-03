package util;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DBconn {
    private static String port = "3306";
    private static String database = "dm_lab1";
    private static String url = "jdbc:mysql://localhost:"+port+"/"+database+"?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8";
    private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String username = "root";
    private static String password = "1122334";
    private static Connection conn =null;
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;

//	private static DataSource dataSource = null;
//	private static Connection conn = null;
//	private static PreparedStatement ps = null;
//	private static ResultSet rs = null;
//	static {
//		dataSource = new ComboPooledDataSource("mysql");
//	}
static
{
    try
    {
        Class.forName(driver);
    }
    catch(Exception ex)
    {
        ex.printStackTrace();
    }
}

    public static Connection getConnection() throws Exception
    {
        if(conn==null)
        {
            conn = DriverManager.getConnection(url, username, password);
            return conn;
        }
        return conn;
    }

    /**
     * 数据库连接初始化
     */
    public static void init() {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
//			conn = dataSource.getConnection();
//			System.out.println("连接成功");
        } catch (Exception e) {
            System.out.println("SQL驱动程序初始化失败！");
            e.printStackTrace();
        }
    }

    /**
     * 数据库增删改操作
     * @param sql  对数据库进行操作的sql语句
     * @return  返回表中受到该sql语句影响的行数（不操作行则默认返回0）
     */
    public static int addUpdDel(String sql) {
        int i = 0;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            i = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SQL数据库增删改异常！");
            e.printStackTrace();
        }
        return i;
    }

    /**
     * 数据库查询操作
     * @param sql 对数据库进行查询的sql语句
     * @return 查询到的单个结果集
     */
    public static ResultSet selectSql(String sql) {
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println("SQL数据库查询异常！");
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * 数据库清空某张表
     *
     * @param table
     */
    public static boolean emptyTable(String table){
        try {
            Statement ps = conn.createStatement();
            String sql = "truncate table "+table+"";
            ps.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 关闭数据库的连接
     */
    public static void closeConn() {
        try {
            if(conn != null && conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("SQL数据库关闭异常！");
            e.printStackTrace();
        }
    }

}
