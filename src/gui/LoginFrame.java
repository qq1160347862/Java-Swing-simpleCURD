package gui;
import gui.service.LoginService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * 登陆界面
 */
public class LoginFrame extends AbstractFrame <LoginService>{

    private final int Width = 500;  //窗口宽度
    private final int Height = 400; //窗口高度

    /**
     * 构造方法
     */
    public LoginFrame() {
        super("管理员登陆", new Dimension(500,400), false, JFrame.EXIT_ON_CLOSE);
        //连接对应的业务类
        this.service = new LoginService();
        this.service.setFrame(this);
        this.init();
        //设置过早会导致组件没来得及加载，但是Frame先执行渲染了
        this.setVisible(true);         //设置窗口可见
    }

    /**
     * 初始化
     */
    @Override
    protected void init() {
        this.addComponent("loginPanel", new JPanel(),jPanel -> {
            jPanel.setBounds(0,0,Width,Height);
            jPanel.setBackground(Color.WHITE);
            this.addComponent(jPanel,"loginVBox", Box.createVerticalBox(), box -> {
                box.add(Box.createVerticalStrut(50));

                //大标题
                Box titleBox = Box.createHorizontalBox();
                this.addComponent(titleBox, "title", new JLabel("医院新冠管理系统"), jLabel -> {
                    jLabel.setFont(new Font("24pxFont",1,24));
                    jLabel.setForeground(Color.BLACK);
                } );
                box.add(titleBox);
                box.add(Box.createVerticalStrut(30));

                //账号输入框
                Box uBox = Box.createHorizontalBox();
                this.addComponent(uBox, "adminInputTitle", new JLabel("账号:"), jLabel -> {
                    jLabel.setFont(new Font("16pxFont",1,16));
                    jLabel.setForeground(Color.BLACK);
                } );
                uBox.add(Box.createHorizontalStrut(20));
                this.addComponent(uBox, "adminInput", new JTextField(18), jTextField -> {
                    jTextField.addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyTyped(KeyEvent e) {
                            int keyChar = e.getKeyChar();
                            if (jTextField.getText().length() >= 10) {
                                e.consume();    // 屏蔽输入，限制输入总长度在10
                            }
                        }
                    });
                } );
                box.add(uBox);
                box.add(Box.createVerticalStrut(30));

                //密码输入框
                Box pBox = Box.createHorizontalBox();
                this.addComponent(pBox, "passwordInputTitle", new JLabel("密码:"), jLabel -> {
                    jLabel.setFont(new Font("16pxFont",1,16));
                    jLabel.setForeground(Color.BLACK);
                } );
                pBox.add(Box.createHorizontalStrut(20));
                this.addComponent(pBox, "passwordInput", new JPasswordField(18), jPasswordField -> {

                } );
                box.add(pBox);
                box.add(Box.createVerticalStrut(30));

                //按钮:登陆 and 注册按钮
                //并绑定事件
                Box btnBox = Box.createHorizontalBox();
                this.addComponent(btnBox, "loginBtn", new JButton("登陆"), jButton -> {
                    jButton.addActionListener(e -> this.service.login());
                });
                btnBox.add(Box.createHorizontalStrut(50));
                this.addComponent(btnBox, "registerBtn", new JButton("注册"), jButton -> {
                    jButton.addActionListener(e -> this.service.goToRegister());
                });
                box.add(btnBox);
                box.add(Box.createVerticalStrut(30));

                //tip信息提示
                Box tipBox = Box.createHorizontalBox();
                this.addComponent(tipBox, "tipLabel", new JLabel(""), jLabel -> {
                    jLabel.setFont(new Font("12pxFont",1,12));
                    jLabel.setForeground(Color.BLACK);
                });
                box.add(tipBox);
            });
        });
    }
}
