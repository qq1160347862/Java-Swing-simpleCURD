package dao;
import entity.Admin;
import utils.Result;
import java.sql.*;
import java.util.*;

/**
 * 操作管理员数据库的底层类
 */
public class AdminDao {
    private String username = "root";
    private String password = "123456";
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/covidms";
    private Connection connection;
    private PreparedStatement pst;
    private ResultSet rst;

    /**
     * 创建数据库连接
     * @return Connection
     */
    public Connection getConnection(){
        try {
            if (connection == null || connection.isClosed()){

                Class.forName(driver);
                connection = DriverManager.getConnection(url,username,password);
            }
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 查询管理员数据
     * @param sql //select * from admins where param=? and param2=?
     * @param params //Object[] params = {"","","",...,""}
     * @return Result //
     */
    public Result query(String sql, Object... params){
        System.out.println("SQL:"+sql);
        System.out.println("params:"+ Arrays.toString(params));
        try {
            // 连接
            connection = getConnection();
            // 声明preparedStatement
            pst = connection.prepareStatement(sql);
            //给sql参数赋值
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    pst.setObject(i+1,params[i]);
                }
            }
            // 执行sql
            rst = pst.executeQuery();
            //数据库返回结果处理
            List<Admin> admins = new ArrayList<Admin>();
            if (rst.next()){
                int id = rst.getInt("id");
                String adminName = rst.getString("admin_name");
                String password = rst.getString("password");
                admins.add(new Admin(id,adminName,password));
            }

            //是否查询到数据
            if (!admins.isEmpty()){
                // 返回执行结果
                return Result.ok("admins查询成功").data("admins",admins);
            }else {
                return Result.error("该管理员未注册或密码错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭
            close();
        }
        return Result.error("数据库连接异常");
    }

    /**
     * 管理员数据的增删改
     * @param sql //update...delete...insert...
     * @param params //Object[] params = {"","","",...,""}
     * @return Result
     */
    public Result update(String sql, Object... params){
        System.out.println("SQL:"+sql);
        System.out.println("params:"+ Arrays.toString(params));
        try {
            // 连接
            connection = getConnection();
            // 声明preparedStatement
            pst = connection.prepareStatement(sql);
            //给sql参数赋值
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    pst.setObject(i+1,params[i]);
                }
            }
            // 执行sql
            int rows = pst.executeUpdate();
            //管理员添加成功
            if (rows > 0) {
                return Result.ok("管理员注册成功!");
            }else {
                return Result.error("数据库错误,管理员注册失败!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return Result.error("数据库连接异常");
    }

    /**
     * 执行关闭
     */
    public void close(){
        try {
            if (rst != null){
                rst.close();
                rst = null;
            }
            if (pst != null){
                pst.close();
                pst = null;
            }
            if (connection != null){
                connection.close();
                connection = null;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
