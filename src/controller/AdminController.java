package controller;
import dao.AdminDao;
import entity.Admin;
import utils.Result;

/**
 * 管理员控制类,用于实现有关管理员的业务逻辑
 */
public class AdminController {
    private AdminDao adminDao = new AdminDao();  //管理员数据库类

    /**
     * 管理员登陆
     * @param admin
     * @return Result
     */
    public Result login(Admin admin){
        String sql = "select * from admins where admin_name=? and password=?";
        Object[] params = {admin.getAdminName(),admin.getPassword()};
        return adminDao.query(sql,params);
    }

    /**
     * 注册业务
     * @param admin
     * @param key
     * @return Result
     */
    public Result register(Admin admin,String key){
        String registerKey = "covid19ManagementSystem"; //注册秘钥,用于注册验证
        String sql = "insert into admins(admin_name,password) values(?,?)";
        String sql2 = "select * from admins where admin_name=?";
        Object[] params = {admin.getAdminName(),admin.getPassword()};
        Object[] params2 = {admin.getAdminName()};

        if (!key.equals(registerKey)) {
            return Result.error("注册码错误");
        }else {
            //先查询数据库是否存在管理员
            if (adminDao.query(sql2,params2).getSuccess()){
                return Result.error("注册的用户已存在");
            }else {
                return adminDao.update(sql,params);
            }
        }
    }

}
