package gui;
import gui.service.WelcomeService;
import javax.swing.*;
import java.awt.*;

/**
 * 欢迎页面
 */
public class WelcomeFrame extends AbstractFrame <WelcomeService>{
    private final int Width = 500;      //窗口宽度
    private final int Height = 400;     //窗口高度
    private final int millis = 4000;    //模拟加载延时的时长
    /**
     * 构造方法:
     * super()调用父级构造方法,同时自定义调用
     */
    public WelcomeFrame(){
        super("欢迎", new Dimension(500,400), false, JFrame.EXIT_ON_CLOSE);
        //连接对应的业务类
        this.service = new WelcomeService();
        this.service.setFrame(this);
        //初始化
        this.init();
        //设置过早会导致组件没来得及加载，但是Frame先执行渲染了
        this.setVisible(true);         //设置窗口可见
    }
    /**
     * 初始化,定义组件样式和功能
     */
    @Override
    protected void init() {
        this.addComponent("WelcomePanel", new JPanel(), panel -> {
            panel.setBounds(0,0,Width,Height);
            panel.setBackground(Color.WHITE);
            this.addComponent(panel,"vBox", Box.createVerticalBox(), vbox -> {
                vbox.add(Box.createVerticalStrut(50));
                Box tBox = Box.createHorizontalBox();
                this.addComponent(tBox,"WelcomeLogo",new JLabel("欢迎使用医院新冠管理系统!"), label-> {
                    label.setFont(new Font("16pxFont",1,16));
                    label.setForeground(Color.BLACK);
                });
                Box proBox = Box.createHorizontalBox();
                this.addComponent(proBox,"WelcomeLoading", new JProgressBar(),progressbar->{
                    progressbar.setMaximum(millis);
                    progressbar.setValue(0);    //设定进度值
                    progressbar.setStringPainted(true);    //进度条显示字符串
                });
                Box tipBox = Box.createHorizontalBox();
                this.addComponent(tipBox,"WelcomeLoadingTip", new JLabel(""), label-> {
                    label.setFont(new Font("12pxFont",1,12));
                    label.setForeground(Color.BLACK);
                });
                vbox.add(tBox);
                vbox.add(Box.createVerticalStrut(150));
                vbox.add(proBox);
                vbox.add(Box.createVerticalStrut(30));
                vbox.add(tipBox);
            });
        });
        //执行延时功能
        this.service.SleepAndGoToLogin(millis);
    }
}
