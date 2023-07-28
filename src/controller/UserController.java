package controller;
import dao.UserDao;
import entity.User;
import utils.Result;

/**
 * 患者控制类,用于实现有关管理员的业务逻辑
 */
public class UserController {
    private UserDao userDao = new UserDao();

    /**
     * 获取所有患者
     * @return Result
     */
    public Result getAllUser(){
        String sql = "select * from users";
        Object[] params = {};
        return userDao.query(sql,params);
    }

    /**
     * 获取指定id患者
     * @param id    //患者id
     * @return Result
     */
    public Result getUserById(int id){
        String sql = "select * from users where id = ?";
        Object[] params = {id};
        return userDao.query(sql,params);
    }

    /**
     * 获取指定名字患者(有重复可能)
     * @param userName
     * @return Result
     */
    public Result getUserByUserName(String userName){
        String sql = "select * from users where user_name like ?";
        Object[] params = {"%"+userName+"%"};
        return userDao.query(sql,params);
    }

    /**
     * 增加患者
     * @param user
     * @return Result
     */
    public Result addUser(User user){
        String sql = "insert into users(user_name,gender,age,address,tel,email,symptom,user_id,doctor_id) values(?,?,?,?,?,?,?,?,?)";
        Object[] params = {
                user.getUserName(),
                user.getGender(),
                user.getAge(),
                user.getAddress(),
                user.getTel(),
                user.getEmail(),
                user.getSymptom(),
                user.getUserId(),
                user.getDoctorId()
        };
        return userDao.update(sql,params);
    }

    /**
     * 更新患者信息
     * @param user
     * @return Result
     */
    public Result updateUser(User user){
        String sql = "update users set user_name=?,gender=?,age=?,address=?,tel=?,email=?,symptom=?,user_id=?,doctor_id=? where id=?";
        Object[] params = {
                user.getUserName(),
                user.getGender(),
                user.getAge(),
                user.getAddress(),
                user.getTel(),
                user.getEmail(),
                user.getSymptom(),
                user.getUserId(),
                user.getDoctorId(),
                user.getId()
        };
        return userDao.update(sql,params);
    }

    /**
     * 删除患者信息
     * @param id
     * @return Result
     */
    public Result deleteUser(int id){
        String sql = "delete from users where id=?";
        Object[] params = {id};
        return userDao.update(sql,params);
    }
}
