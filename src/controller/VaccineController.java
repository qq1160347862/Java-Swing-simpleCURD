package controller;
import dao.VaccineDao;
import entity.Vaccine;
import utils.Result;

/**
 * 疫苗控制类,用于实现有关管理员的业务逻辑
 */
public class VaccineController {
    private VaccineDao vaccineDao = new VaccineDao();

    /**
     * 获取所有疫苗
     * @return Result
     */
    public Result getAllVaccine(){
        String sql = "select * from vaccines";
        Object[] params = {};
        return vaccineDao.query(sql,params);
    }

    /**
     * 获取指定id疫苗
     * @param id    //疫苗id
     * @return Result
     */
    public Result getVaccineById(int id){
        String sql = "select * from vaccines where id = ?";
        Object[] params = {id};
        return vaccineDao.query(sql,params);
    }

    /**
     * 获取指定名字疫苗(有重复可能)
     * @param vaccineName
     * @return Result
     */
    public Result getVaccineByVaccineName(String vaccineName){
        String sql = "select * from vaccines where vaccine_name like ?";
        Object[] params = {"%"+vaccineName+"%"};
        return vaccineDao.query(sql,params);
    }

    /**
     * 增加疫苗
     * @param vaccine
     * @return Result
     */
    public Result addVaccine(Vaccine vaccine){
        String sql = "insert into vaccines(vaccine_name,company) values(?,?)";
        Object[] params = {
                vaccine.getVaccineName(),
                vaccine.getCompany()
        };
        return vaccineDao.update(sql,params);
    }

    /**
     * 更新疫苗信息
     * @param vaccine
     * @return Result
     */
    public Result updateVaccine(Vaccine vaccine){
        String sql = "update vaccines set vaccine_name=?,company=? where id=?";
        Object[] params = {
                vaccine.getVaccineName(),
                vaccine.getCompany(),
                vaccine.getId()
        };
        return vaccineDao.update(sql,params);
    }

    /**
     * 删除疫苗信息
     * @param id
     * @return Result
     */
    public Result deleteVaccine(int id){
        String sql = "delete from vaccines where id=?";
        Object[] params = {id};
        return vaccineDao.update(sql,params);
    }
}
