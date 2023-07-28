package entity;

/**
 * 管理员实体类
 */
public class Admin {
    private int id;             //管理员ID
    private String adminName;   //管理员名称
    private String password;    //管理员密码
    public Admin() {}
    public Admin(int id, String adminName, String password) {
        this.id = id;
        this.adminName = adminName;
        this.password = password;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getAdminName() {
        return adminName;
    }
    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
