package controller;
import dao.RecordsDao;
import entity.Records;
import utils.Result;

/**
 * 接种记录控制类,用于实现有关管理员的业务逻辑
 */
public class RecordsController {
    private RecordsDao recordsDao = new RecordsDao();

    /**
     * 获取所有接种记录
     * @return Result
     */
    public Result getAllRecords(){
        String sql = "select * from records";
        Object[] params = {};
        return recordsDao.query(sql,params);
    }

    /**
     * 获取指定id接种记录
     * @param id    //接种记录id
     * @return Result
     */
    public Result getRecordsById(int id){
        String sql = "select * from records where id = ?";
        Object[] params = {id};
        return recordsDao.query(sql,params);
    }

    /**
     * 根据患者名称或疫苗名称获取接种记录
     * @param query
     * @return Result
     */
    public Result getRecordsByUserNameOrVaccineName(String query){
        String sql = "select DISTINCT records.id,records.user_id,records.vaccine_id,records.vaccinate_date from records,users\n" +
                "where records.user_id = users.id and users.user_name like ? \n" +
                "\n" +
                "\n" +
                "UNION\n" +
                "\n" +
                "SELECT DISTINCT records.id,records.user_id,records.vaccine_id,records.vaccinate_date from records,vaccines\n" +
                "WHERE records.vaccine_id = vaccines.id and vaccines.vaccine_name like ?";
        Object[] params = {"%"+query+"%","%"+query+"%"};
        return recordsDao.query(sql,params);
    }

    /**
     * 增加接种记录
     * @param records
     * @return Result
     */
    public Result addRecords(Records records){
        String sql = "insert into records(user_id,vaccine_id,vaccinate_date) values(?,?,?)";
        Object[] params = {
                records.getUserId(),
                records.getVaccineId(),
                records.getVaccinateDate()
        };
        return recordsDao.update(sql,params);
    }

    /**
     * 更新接种记录信息
     * @param records
     * @return Result
     */
    public Result updateRecords(Records records){
        String sql = "update records set user_id=?,vaccine_id=?,vaccinate_date=? where id=?";
        Object[] params = {
                records.getUserId(),
                records.getVaccineId(),
                records.getVaccinateDate(),
                records.getId()
        };
        return recordsDao.update(sql,params);
    }

    /**
     * 删除接种记录信息
     * @param id
     * @return Result
     */
    public Result deleteRecords(int id){
        String sql = "delete from records where id=?";
        Object[] params = {id};
        return recordsDao.update(sql,params);
    }
}
