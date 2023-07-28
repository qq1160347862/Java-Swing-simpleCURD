package entity;

/**
 * 医生实体类
 */
public class Doctor {
    private int id;             //医生ID
    private String doctorName;  //医生名字
    private String gender;      //医生性别
    private String tel;         //医生电话
    public Doctor(){}
    public Doctor(int id, String doctorName, String gender, String tel){
        this.id = id;
        this.doctorName = doctorName;
        this.gender = gender;
        this.tel = tel;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getDoctorName() {
        return doctorName;
    }
    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getTel() {
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
}
