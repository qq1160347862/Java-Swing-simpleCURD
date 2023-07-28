package dao;
import entity.User;
import utils.Result;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 操作患者数据库的底层类
 */
public class UserDao {
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
     * 查询患者数据
     * @param sql //select * from users where param=? and param2=?
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
            List<User> users = new ArrayList<User>();
            while (rst.next()){
                users.add(new User(
                        rst.getInt("id"),
                        rst.getString("user_name"),
                        rst.getString("gender"),
                        rst.getInt("age"),
                        rst.getString("address"),
                        rst.getString("tel"),
                        rst.getString("email"),
                        rst.getString("symptom"),
                        rst.getString("user_id"),
                        rst.getInt("doctor_id"))
                );
            }
            if (!users.isEmpty()){
                //返回执行结果
                return Result.ok("users查询成功").data("users",users);
            }else {
                return Result.error("users查询异常");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return Result.error("数据库连接异常");
    }

    /**
     * 患者数据的增删改
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
                return Result.ok("users更新成功!");
            }else {
                return Result.error("users更新失败!");
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
