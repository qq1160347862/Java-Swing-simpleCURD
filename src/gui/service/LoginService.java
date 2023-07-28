package gui.service;
import controller.AdminController;
import entity.Admin;
import gui.RegisterFrame;
import gui.SystemFrame;
import utils.Result;
import javax.swing.*;

/**
 * 登陆页面的具体业务实现
 */
public class LoginService extends AbstractService{
    private AdminController adminController = new AdminController();    //管理员控制类

    /**
     * 登陆流程:
     * 1. 前端输入框获取用户输入
     * 2. 调用登陆页面的业务类执行登陆动作,调用管理员控制类并获取结果,根据结果判断从而对前端组件做出反馈
     * 3. 管理员控制类调用数据库操作类查询管理员账号和密码的正确性,并返回结果
     */
    public void login(){
        JTextField adminInput = (JTextField)this.abstractFrame.getComponentMap().get("adminInput");
        JTextField passwordInput = (JPasswordField)this.abstractFrame.getComponentMap().get("passwordInput");
        JLabel tipLabel = (JLabel)this.abstractFrame.getComponentMap().get("tipLabel");
        //封装管理员信息=>Admin
        Admin admin = new Admin();
        admin.setAdminName(adminInput.getText());
        admin.setPassword(passwordInput.getText());

        //输入判断和登陆判断
        if (admin.getAdminName().equals("") || admin.getPassword().equals("")){
            tipLabel.setText("请输入管理员账号和密码!");
        }else {
            //验证结果
            Result result = adminController.login(admin);
            //接收后端验证判断并执行相应操作
            if (result.getSuccess()) {
                tipLabel.setText("登陆成功!");
                //进入主界面,创建新线程(主要是避免提示语被直接覆盖)
                new Thread(() -> {
                    try {
                        Thread.sleep(2000);
                        this.abstractFrame.setVisible(false);
                        this.abstractFrame.dispose();
                        SystemFrame systemFrame = new SystemFrame();    //登陆成功跳转系统主界面
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }).start();
            }else {
                tipLabel.setText(result.getMessage());
                passwordInput.setText("");
            }
        }
    }

    /**
     * 跳转至注册页面
     */
    public void goToRegister(){
        RegisterFrame registerFrame = new RegisterFrame();  //跳转注册页面
    }
}
