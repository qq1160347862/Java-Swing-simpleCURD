import gui.WelcomeFrame;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class Main {
    public static void main(String[] args) throws Exception {
        //主题引入
        UIManager.setLookAndFeel(new NimbusLookAndFeel());
        //程序从欢迎页面启动
        WelcomeFrame welcomeFrame = new WelcomeFrame();
    }
}