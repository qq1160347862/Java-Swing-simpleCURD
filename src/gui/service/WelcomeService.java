package gui.service;
import gui.LoginFrame;
import javax.swing.*;

/**
 * 欢迎页面的具体业务实现
 */
public class WelcomeService extends AbstractService{
    //构造函数
    public WelcomeService(){
        super();
    }
    /**
     * 通过延时绘制进度条来模拟程序加载
     * ps:时长实际由后端接受数据或读取本地文件的时间所决定,因为本地查询数据库耗时较短,实际调用网络接口获取服务器信息
     * 会产生一定的时间间隔,所以这里用Thread.sleep(millis)调用线程睡眠模拟
     * @param millis //延时时长
     */
    public void SleepAndGoToLogin(int millis){
        try {
            JProgressBar jProgressBar = (JProgressBar) this.abstractFrame.getComponentMap().get("WelcomeLoading");
            JLabel jLabel = (JLabel) this.abstractFrame.getComponentMap().get("WelcomeLoadingTip");
            //创建新线程绘制进度条
            new Thread(() -> {
                int mill = millis;
                int progress = 0;
                String percent = "0";
                while (mill > 0) {
                    try {
                        jProgressBar.setValue(progress);
                        jProgressBar.setString(percent + "%");
                        if (progress <= millis*0.25){
                            jLabel.setText("程序初始化...");
                        } else if (millis*0.8>=progress && progress >= millis*0.25) {
                            jLabel.setText("登陆界面加载...");
                        }else if (progress >= millis*0.8){
                            jLabel.setText("即将进入登陆...");
                        }
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    percent = String.format("%.0f",((double)progress/(double)millis)*100);
                    mill--;
                    progress++;
                }
                //设置当前窗口不可见且关闭当前页面
                this.abstractFrame.setVisible(false);
                this.abstractFrame.dispose();
                //跳转至登陆界面
                LoginFrame loginFrame = new LoginFrame();
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
