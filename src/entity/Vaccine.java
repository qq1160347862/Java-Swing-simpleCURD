package entity;

/**
 * 疫苗实体类
 */
public class Vaccine {
    private int id;             //疫苗ID
    private String vaccineName; //疫苗名称
    private String company;     //疫苗所属公司
    public Vaccine(){}
    public Vaccine(int id, String vaccineName, String company){
        this.id = id;
        this.vaccineName = vaccineName;
        this.company = company;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getVaccineName() {
        return vaccineName;
    }
    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }
    public String getCompany() {
        return company;
    }
    public void setCompany(String company) {
        this.company = company;
    }
}
