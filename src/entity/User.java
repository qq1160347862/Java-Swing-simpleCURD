package entity;

/**
 * 患者实体类
 */
public class User {
    private int id;             //患者ID
    private String userName;    //患者名称
    private String gender;      //患者性别
    private int age;            //患者年龄
    private String address;     //患者居住地址
    private String tel;         //患者电话
    private String email;       //患者电子邮箱
    private String symptom;     //患者症状
    private String userId;      //患者身份证号
    private int doctorId;       //主治医生ID
    public User() {}
    public User(int id, String userName, String gender, int age, String address, String tel, String email, String symptom, String userId, int doctorId) {
        this.id = id;
        this.userName = userName;
        this.gender = gender;
        this.age = age;
        this.address = address;
        this.tel = tel;
        this.email = email;
        this.symptom = symptom;
        this.userId = userId;
        this.doctorId = doctorId;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getTel() {
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSymptom() {
        return symptom;
    }
    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public int getDoctorId() {
        return doctorId;
    }
    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }
}
