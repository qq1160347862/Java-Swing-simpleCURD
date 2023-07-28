package gui;
import gui.service.RegisterService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * 注册页面
 */
public class RegisterFrame extends AbstractFrame <RegisterService>{
    private final int Width = 500;  //窗口宽度
    private final int Height = 400; //窗口高度

    /**
     * 构造方法
     * ps:注意区别super参数中的DISPOSE_ON_CLOSE和EXIT_ON_CLOSE
     * 前者是关闭窗口,但是进程还在后台,后者是彻底关闭所有关闭方式为EXIT_ON_CLOSE的窗口
     */
    public RegisterFrame(){
        super("管理员注册", new Dimension(500,400), false, JFrame.DISPOSE_ON_CLOSE);
        //连接对应的业务类
        this.service = new RegisterService();
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
        this.addComponent("registerPanel", new JPanel(),jPanel -> {
            jPanel.setBounds(0,0,Width,Height);
            jPanel.setBackground(Color.WHITE);
            this.addComponent(jPanel,"registerVBox", Box.createVerticalBox(), box -> {
                box.add(Box.createVerticalStrut(50));
                //大标题
                Box titleBox = Box.createHorizontalBox();
                this.addComponent(titleBox, "registerTitle", new JLabel("管理员注册"), jLabel -> {
                    jLabel.setFont(new Font("24pxFont",1,24));
                    jLabel.setForeground(Color.BLACK);
                } );
                box.add(titleBox);
                box.add(Box.createVerticalStrut(30));

                //账号输入框
                Box uBox = Box.createHorizontalBox();
                this.addComponent(uBox, "registerAdminInputTitle", new JLabel("账号:"), jLabel -> {
                    jLabel.setFont(new Font("16pxFont",1,16));
                    jLabel.setForeground(Color.BLACK);
                } );
                uBox.add(Box.createHorizontalStrut(20));
                this.addComponent(uBox, "registerAdminInput", new JTextField(18), jTextField -> {
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
                this.addComponent(pBox, "registerPasswordInputTitle", new JLabel("密码:"), jLabel -> {
                    jLabel.setFont(new Font("16pxFont",1,16));
                    jLabel.setForeground(Color.BLACK);
                } );
                pBox.add(Box.createHorizontalStrut(20));
                this.addComponent(pBox, "registerPasswordInput", new JPasswordField(18), jPasswordField -> {

                } );
                box.add(pBox);
                box.add(Box.createVerticalStrut(30));

                //注册码
                Box keyBox = Box.createHorizontalBox();
                this.addComponent(keyBox, "registerKeyTitle", new JLabel("注册码:"), jLabel -> {
                    jLabel.setFont(new Font("16pxFont",1,16));
                    jLabel.setForeground(Color.BLACK);
                } );
                keyBox.add(Box.createHorizontalStrut(20));
                this.addComponent(keyBox, "registerKey", new JPasswordField(), jPasswordField -> {

                });
                box.add(keyBox);
                box.add(Box.createVerticalStrut(30));

                //按钮注册
                //并绑定事件
                Box btnBox = Box.createHorizontalBox();
                btnBox.add(Box.createHorizontalStrut(50));
                this.addComponent(btnBox, "registerBtn", new JButton("注册"), jButton -> {
                    jButton.addActionListener(e -> this.service.register());
                });
                box.add(btnBox);
                box.add(Box.createVerticalStrut(30));

                //tip信息提示
                Box tipBox = Box.createHorizontalBox();
                this.addComponent(tipBox, "registerTipLabel", new JLabel(""), jLabel -> {
                    jLabel.setFont(new Font("12pxFont",1,12));
                    jLabel.setForeground(Color.BLACK);
                });
                box.add(tipBox);
            });

        });
    }
}
