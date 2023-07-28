package entity;
import java.util.Date;

/**
 * 接种记录实体类
 */
public class Records {
    private int id;             //接种记录ID
    private int userId;         //被接种的患者ID
    private int vaccineId;      //使用的疫苗ID
    private Date vaccinateDate; //接种日期
    public Records(){}
    public Records(int id, int userId, int vaccineId, Date vaccinateDate){
        this.id = id;
        this.userId = userId;
        this.vaccineId = vaccineId;
        this.vaccinateDate = vaccinateDate;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public int getVaccineId() {
        return vaccineId;
    }
    public void setVaccineId(int vaccineId) {
        this.vaccineId = vaccineId;
    }
    public Date getVaccinateDate() {
        return vaccinateDate;
    }
    public void setVaccinateDate(Date vaccinateDate) {
        this.vaccinateDate = vaccinateDate;
    }
}
