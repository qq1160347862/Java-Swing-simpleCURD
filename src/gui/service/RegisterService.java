package gui.service;
import controller.AdminController;
import entity.Admin;
import utils.Result;
import javax.swing.*;
/**
 * 注册页面的具体业务实现
 */
public class RegisterService extends AbstractService{
    private AdminController adminController = new AdminController();    //管理员控制类

    /**
     * 注册流程:
     * 1. 前端输入框获取用户输入(包含注册码)
     * 2. 调用注册页面的业务类执行注册动作,调用管理员控制类并获取结果,根据结果判断从而对前端组件做出反馈
     * 3. 管理员控制类调用数据库操作类查询管理员账号是否存在,若存在则不予注册。注册成功后返回结果
     */
    public void register(){
        JTextField adminInput = (JTextField)this.abstractFrame.getComponentMap().get("registerAdminInput");
        JTextField passwordInput = (JPasswordField)this.abstractFrame.getComponentMap().get("registerPasswordInput");
        JTextField registerKey = (JPasswordField)this.abstractFrame.getComponentMap().get("registerKey");
        JLabel registerTipLabel = (JLabel)this.abstractFrame.getComponentMap().get("registerTipLabel");

        //封装管理员信息=>Admin
        Admin admin = new Admin();
        admin.setAdminName(adminInput.getText());
        admin.setPassword(passwordInput.getText());

        //输入判断和注册判断
        if (admin.getAdminName().equals("") || admin.getPassword().equals("") || registerKey.getText().equals("")){
            registerTipLabel.setText("请将表格填写完整!");
        }else {
            Result result = adminController.register(admin,registerKey.getText());
            if (result.getSuccess()) {
                registerTipLabel.setText(result.getMessage());
            }else {
                registerTipLabel.setText(result.getMessage());
            }
        }
    }
}
