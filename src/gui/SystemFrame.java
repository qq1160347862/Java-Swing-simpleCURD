package gui;
import gui.component.DoctorPopup;
import gui.component.RecordPopup;
import gui.component.UserPopup;
import gui.component.VaccinePopup;
import gui.service.SystemService;
import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;

/**
 * 系统主页面
 */
public class SystemFrame extends AbstractFrame<SystemService>{
    /**
     * 构造方法
     */
    public SystemFrame(){
        super("医院新冠管理系统", new Dimension(1080,680), false, JFrame.EXIT_ON_CLOSE);
        //连接对应的业务类
        this.service = new SystemService();
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
        //定义分割面板
        this.addComponent("spiltPanel", new JSplitPane(), jSplitPane -> {
            //首先分割上下
            jSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);

            //设置下半部分样式
            jSplitPane.setBottomComponent(this.createConsole());
            jSplitPane.setDividerLocation(380); //分隔条位置在y = 300上
            jSplitPane.setDividerSize(5);

            //设置上半部分样式
            JSplitPane topPanel = new JSplitPane();
            topPanel.setDividerLocation(200);   //分隔条位置在x = 200上
            topPanel.setDividerSize(5);
            this.getComponentMap().put("topPanel",topPanel);
            DefaultMutableTreeNode InfoManagement = new DefaultMutableTreeNode("信息管理");
            DefaultMutableTreeNode usersInfo = new DefaultMutableTreeNode("患者信息");
            DefaultMutableTreeNode doctorsInfo = new DefaultMutableTreeNode("医生信息");
            DefaultMutableTreeNode vaccinesInfo = new DefaultMutableTreeNode("疫苗信息");
            DefaultMutableTreeNode recordsInfo = new DefaultMutableTreeNode("接种信息");
            InfoManagement.add(usersInfo);
            InfoManagement.add(doctorsInfo);
            InfoManagement.add(vaccinesInfo);
            InfoManagement.add(recordsInfo);
            JTree tree = new JTree(InfoManagement);
            topPanel.setRightComponent(this.welcomePanel());
            /**
             * 声明外部指针that
             * ps:由于匿名函数(如下面的new TreeSelectionListener() {})中的代码作用域是TreeSelectionListener,
             * 因此想要调用外部空间(这里指SystemFrame系统主页面类)的方法或变量时,就需要在外部先定义外部空间的变量(这里是SystemFrame)
             * 通过浅拷贝(只赋值栈地址中的堆引用地址,而不是真正在堆中重新创建对象的值)的方式将this赋值给that,
             * 这样就可以在内部函数中调用外部函数的方法和变量了
             */
            SystemFrame that = this;
            //为结构树添加改变事件(切换左边菜单栏)
            tree.addTreeSelectionListener(new TreeSelectionListener() {
                @Override
                public void valueChanged(TreeSelectionEvent e) {
                    if (e.getNewLeadSelectionPath() != null){
                        //获取当前选中的节点对象
                        Object lastPathComponent = e.getNewLeadSelectionPath().getLastPathComponent();
                        topPanel.setDividerLocation(200);   //分隔条位置在x = 200上
                        topPanel.setDividerSize(5);
                        if (usersInfo.equals(lastPathComponent)){
                            topPanel.setRightComponent(that.createUsersInfoPanel());
                        } else if (doctorsInfo.equals(lastPathComponent)) {
                            topPanel.setRightComponent(that.createDoctorsInfoPanel());
                        } else if (vaccinesInfo.equals(lastPathComponent)) {
                            topPanel.setRightComponent(that.createVaccinesInfoPanel());
                        } else if (recordsInfo.equals(lastPathComponent)) {
                            topPanel.setRightComponent(that.createRecordsInfoPanel());
                        }
                    }
                }
            });
            this.getComponentMap().put("tree",tree);    //添加至组件列表中
            topPanel.setLeftComponent(tree);
            jSplitPane.setTopComponent(topPanel);
        });
    }
    /**
     * 生成控制台面板
     * @return
     */
    public Component createConsole() {
        JTextArea consoleArea = new JTextArea("");
        consoleArea.setFont(new Font("16pxFont",1,12));
        consoleArea.setForeground(Color.BLACK);
        //控制台应该是不可编辑的
        consoleArea.setEnabled(false);
        //添加至组件列表中
        this.getComponentMap().put("consoleArea",consoleArea);
        return new JScrollPane(consoleArea); //返回滚动面板,控制台输出的信息应该是可滚动查看的
    }
    /**
     * createPanel 用于生成列表界面，包括数据渲染和生成
     * @return
     */
    public Component createRecordsInfoPanel() {
        //布局设置
        Box box = Box.createVerticalBox();
        Box tBox = Box.createHorizontalBox();
        Box bBox = Box.createVerticalBox();
        JButton addJButton = new JButton("新增");
        JButton queryJButton = new JButton("查询");
        JTextField jTextField = new JTextField(18);

        tBox.add(addJButton);
        tBox.add(Box.createHorizontalStrut(20));
        tBox.add(queryJButton);
        tBox.add(Box.createHorizontalStrut(20));
        tBox.add(jTextField);
        bBox.add(new JScrollPane(this.service.RenderRecordData()));

        box.add(tBox);
        box.add(bBox);


        //监听器设置
        addJButton.addActionListener(e -> {
            try {
                RecordPopup recordPopup = new RecordPopup(this.service);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        queryJButton.addActionListener(e -> {
            try {
                //创建新线程处理查询
                new Thread(()->{
                    bBox.remove(0);
                    box.remove(1);
                    bBox.add(new JScrollPane(this.service.queryRecord(jTextField.getText())));
                    box.add(bBox);
                    JSplitPane topPanel = (JSplitPane)this.getComponentMap().get("topPanel");
                    topPanel.setDividerLocation(200);   //分隔条位置在x = 200上
                    topPanel.setDividerSize(5);
                    topPanel.setRightComponent(box);
                }).start();
            } catch (Exception ex){
                ex.printStackTrace();
            }
        });

        return box;
    }
    public Component createVaccinesInfoPanel() {
        //布局设置
        Box box = Box.createVerticalBox();
        Box tBox = Box.createHorizontalBox();
        Box bBox = Box.createVerticalBox();
        JButton addJButton = new JButton("新增");
        JButton queryJButton = new JButton("查询");
        JTextField jTextField = new JTextField(18);

        tBox.add(addJButton);
        tBox.add(Box.createHorizontalStrut(20));
        tBox.add(queryJButton);
        tBox.add(Box.createHorizontalStrut(20));
        tBox.add(jTextField);
        bBox.add(new JScrollPane(this.service.RenderVaccineData()));

        box.add(tBox);
        box.add(bBox);


        //监听器设置
        addJButton.addActionListener(e -> {
            try {
                VaccinePopup vaccinePopup = new VaccinePopup(this.service);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        queryJButton.addActionListener(e -> {
            try {
                //创建新线程处理查询
                new Thread(()->{
                    bBox.remove(0);
                    box.remove(1);
                    bBox.add(new JScrollPane(this.service.queryVaccine(jTextField.getText())));
                    box.add(bBox);
                    JSplitPane topPanel = (JSplitPane)this.getComponentMap().get("topPanel");
                    topPanel.setDividerLocation(200);   //分隔条位置在x = 200上
                    topPanel.setDividerSize(5);
                    topPanel.setRightComponent(box);
                }).start();
            } catch (Exception ex){
                ex.printStackTrace();
            }
        });

        return box;
    }
    public Component createDoctorsInfoPanel() {

        //布局设置
        Box box = Box.createVerticalBox();
        Box tBox = Box.createHorizontalBox();
        Box bBox = Box.createVerticalBox();
        JButton addJButton = new JButton("新增");
        JButton queryJButton = new JButton("查询");
        JTextField jTextField = new JTextField(18);

        tBox.add(addJButton);
        tBox.add(Box.createHorizontalStrut(20));
        tBox.add(queryJButton);
        tBox.add(Box.createHorizontalStrut(20));
        tBox.add(jTextField);
        bBox.add(new JScrollPane(this.service.RenderDoctorData()));

        box.add(tBox);
        box.add(bBox);


        //监听器设置
        addJButton.addActionListener(e -> {
            try {
                DoctorPopup doctorPopup = new DoctorPopup(this.service);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        queryJButton.addActionListener(e -> {
            try {
                //创建新线程处理查询
                new Thread(()->{
                    bBox.remove(0);
                    box.remove(1);
                    bBox.add(new JScrollPane(this.service.queryDoctor(jTextField.getText())));
                    box.add(bBox);
                    JSplitPane topPanel = (JSplitPane)this.getComponentMap().get("topPanel");
                    topPanel.setDividerLocation(200);   //分隔条位置在x = 200上
                    topPanel.setDividerSize(5);
                    topPanel.setRightComponent(box);
                }).start();
            } catch (Exception ex){
                ex.printStackTrace();
            }
        });

        return box;
    }
    public Component createUsersInfoPanel() {
        //布局设置
        Box box = Box.createVerticalBox();
        Box tBox = Box.createHorizontalBox();
        Box bBox = Box.createVerticalBox();
        JButton addJButton = new JButton("新增");
        JButton queryJButton = new JButton("查询");
        JTextField jTextField = new JTextField(18);

        tBox.add(addJButton);
        tBox.add(Box.createHorizontalStrut(20));
        tBox.add(queryJButton);
        tBox.add(Box.createHorizontalStrut(20));
        tBox.add(jTextField);
        bBox.add(new JScrollPane(this.service.RenderUserData()));

        box.add(tBox);
        box.add(bBox);

        //监听器设置
        addJButton.addActionListener(e -> {
            try {
                UserPopup userPopup = new UserPopup(this.service);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        queryJButton.addActionListener(e -> {
            try {
                //创建新线程处理查询
                new Thread(()->{
                    bBox.remove(0);
                    box.remove(1);
                    bBox.add(new JScrollPane(this.service.queryUser(jTextField.getText())));
                    box.add(bBox);
                    JSplitPane topPanel = (JSplitPane)this.getComponentMap().get("topPanel");
                    topPanel.setDividerLocation(200);   //分隔条位置在x = 200上
                    topPanel.setDividerSize(5);
                    topPanel.setRightComponent(box);
                }).start();
            } catch (Exception ex){
                ex.printStackTrace();
            }
        });

        return box;
    }
    /**
     * 首次进入系统主界面的提示
     * @return
     */
    private Component welcomePanel() {
        JLabel jLabel = new JLabel("欢迎使用该系统！");
        jLabel.setFont(new Font("24pxFont",1,24));
        jLabel.setForeground(Color.BLACK);
        JLabel jLabel2 = new JLabel("左侧为菜单条目，右侧为数据列表");
        jLabel2.setFont(new Font("16pxFont",1,16));
        jLabel2.setForeground(Color.BLACK);
        JLabel jLabel3 = new JLabel("底下是数据详情");
        jLabel3.setFont(new Font("16pxFont",1,16));
        jLabel3.setForeground(Color.BLACK);

        Box vbox = Box.createVerticalBox();
        Box box1 = Box.createHorizontalBox();
        Box box2 = Box.createHorizontalBox();
        Box box3 = Box.createHorizontalBox();

        box1.add(jLabel);
        box2.add(jLabel2);
        box3.add(jLabel3);

        vbox.add(Box.createVerticalStrut(50));
        vbox.add(box1);
        vbox.add(Box.createVerticalStrut(50));
        vbox.add(box2);
        vbox.add(Box.createVerticalStrut(50));
        vbox.add(box3);

        JPanel jPanel = new JPanel();
        jPanel.add(vbox);
        return jPanel;
    }
}
