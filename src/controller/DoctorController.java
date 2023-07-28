package controller;
import dao.DoctorDao;
import entity.Doctor;
import utils.Result;

/**
 * 医生控制类,用于实现有关管理员的业务逻辑
 */
public class DoctorController {
    private DoctorDao doctorDao = new DoctorDao();

    /**
     * 获取所有医生
     * @return Result
     */
    public Result getAllDoctor(){
        String sql = "select * from doctors";
        Object[] params = {};
        return doctorDao.query(sql,params);
    }

    /**
     * 获取指定id医生
     * @param id    //医生id
     * @return Result
     */
    public Result getDoctorById(int id){
        String sql = "select * from doctors where id = ?";
        Object[] params = {id};
        return doctorDao.query(sql,params);
    }

    /**
     * 获取指定名字医生(有重复可能)
     * @param doctorName
     * @return Result
     */
    public Result getDoctorByDoctorName(String doctorName){
        String sql = "select * from doctors where doctor_name like ?";
        Object[] params = {"%"+doctorName+"%"};
        return doctorDao.query(sql,params);
    }

    /**
     * 增加医生
     * @param doctor
     * @return Result
     */
    public Result addDoctor(Doctor doctor){
        String sql = "insert into doctors(doctor_name,gender,tel) values(?,?,?)";
        Object[] params = {
                doctor.getDoctorName(),
                doctor.getGender(),
                doctor.getTel()
        };
        return doctorDao.update(sql,params);
    }

    /**
     * 更新医生信息
     * @param doctor
     * @return Result
     */
    public Result updateDoctor(Doctor doctor){
        String sql = "update doctors set doctor_name=?,gender=?,tel=? where id=?";
        Object[] params = {
                doctor.getDoctorName(),
                doctor.getGender(),
                doctor.getTel(),
                doctor.getId()
        };
        return doctorDao.update(sql,params);
    }

    /**
     * 删除医生信息
     * @param id
     * @return Result
     */
    public Result deleteDoctor(int id){
        String sql = "delete from doctors where id=?";
        Object[] params = {id};
        return doctorDao.update(sql,params);
    }
}
