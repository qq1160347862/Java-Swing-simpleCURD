package gui.service;
import controller.*;
import entity.Doctor;
import entity.Records;
import entity.User;
import entity.Vaccine;
import gui.component.DoctorPopup;
import gui.component.RecordPopup;
import gui.component.UserPopup;
import gui.component.VaccinePopup;
import utils.Result;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;
import java.util.List;

/**
 * 系统主界面的具体业务实现
 */
public class SystemService extends AbstractService{
    //表格数据
    private String[] DoctorColumnNames = {"医生ID","医生姓名","性别","电话"};
    private String[] RecordsColumnNames = {"接种记录ID","用户ID","疫苗ID","接种日期"};
    private String[] UserColumnNames = {"患者ID","患者姓名","性别","年龄","地址","电话","电子邮箱","发病症状","身份证号","主治医生ID"};
    private String[] VaccineColumnNames = {"疫苗ID","疫苗名称","生产公司"};

    //控制类变量
    private DoctorController doctorController = new DoctorController();
    private RecordsController recordsController = new RecordsController();
    private UserController userController = new UserController();
    private VaccineController vaccineController = new VaccineController();

    /**
     * 控制构造方法
     */
    public SystemService(){}

    /**
     * 增加对象
     * @param
     */
    public void addDoctor(Doctor doctor){
        Result result = doctorController.addDoctor(doctor);
        if (result.getSuccess()){
            JOptionPane.showMessageDialog(this.abstractFrame,result.getMessage(),"消息",JOptionPane.WARNING_MESSAGE);
            this.reLoadDoctor();
        }else {
            JOptionPane.showMessageDialog(this.abstractFrame,result.getMessage(),"消息",JOptionPane.WARNING_MESSAGE);
        }
    }
    public void addUser(User user){
        Result result = userController.addUser(user);
        if (result.getSuccess()){
            JOptionPane.showMessageDialog(this.abstractFrame,result.getMessage(),"消息",JOptionPane.WARNING_MESSAGE);
            this.reLoadUser();
        }else {
            JOptionPane.showMessageDialog(this.abstractFrame,result.getMessage(),"消息",JOptionPane.WARNING_MESSAGE);
        }
    }
    public void addVaccine(Vaccine vaccine){
        Result result = vaccineController.addVaccine(vaccine);
        if (result.getSuccess()){
            JOptionPane.showMessageDialog(this.abstractFrame,result.getMessage(),"消息",JOptionPane.WARNING_MESSAGE);
            this.reLoadVaccine();
        }else {
            JOptionPane.showMessageDialog(this.abstractFrame,result.getMessage(),"消息",JOptionPane.WARNING_MESSAGE);
        }
    }
    public void addRecord(Records records){
        Result result = recordsController.addRecords(records);
        if (result.getSuccess()){
            JOptionPane.showMessageDialog(this.abstractFrame,result.getMessage(),"消息",JOptionPane.WARNING_MESSAGE);
            this.reLoadRecord();
        }else {
            JOptionPane.showMessageDialog(this.abstractFrame,result.getMessage(),"消息",JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * 更新对象
     * @param doctor
     */
    public void updateDoctor(Doctor doctor){
        Result result = doctorController.updateDoctor(doctor);
        if (result.getSuccess()){
            JOptionPane.showMessageDialog(this.abstractFrame,result.getMessage(),"消息",JOptionPane.WARNING_MESSAGE);
            this.reLoadDoctor();
        }else {
            JOptionPane.showMessageDialog(this.abstractFrame,result.getMessage(),"消息",JOptionPane.WARNING_MESSAGE);
        }
    }
    public void updateUser(User user){
        Result result = userController.updateUser(user);
        if (result.getSuccess()){
            JOptionPane.showMessageDialog(this.abstractFrame,result.getMessage(),"消息",JOptionPane.WARNING_MESSAGE);
            this.reLoadUser();
        }else {
            JOptionPane.showMessageDialog(this.abstractFrame,result.getMessage(),"消息",JOptionPane.WARNING_MESSAGE);
        }
    }
    public void updateVaccine(Vaccine vaccine){
        Result result = vaccineController.updateVaccine(vaccine);
        if (result.getSuccess()){
            JOptionPane.showMessageDialog(this.abstractFrame,result.getMessage(),"消息",JOptionPane.WARNING_MESSAGE);
            this.reLoadVaccine();
        }else {
            JOptionPane.showMessageDialog(this.abstractFrame,result.getMessage(),"消息",JOptionPane.WARNING_MESSAGE);
        }
    }
    public void updateRecord(Records records){
        Result result = recordsController.updateRecords(records);
        if (result.getSuccess()){
            JOptionPane.showMessageDialog(this.abstractFrame,result.getMessage(),"消息",JOptionPane.WARNING_MESSAGE);
            this.reLoadRecord();
        }else {
            JOptionPane.showMessageDialog(this.abstractFrame,result.getMessage(),"消息",JOptionPane.WARNING_MESSAGE);
        }
    }
    /**
     * 查询对象
     * @return JTable
     */
    public JTable queryDoctor(String doctorName){
        JPopupMenu jPopupMenu = new JPopupMenu();
        JMenuItem jMenuItem1 = new JMenuItem("修改");
        JMenuItem jMenuItem2 = new JMenuItem("删除");
        jPopupMenu.add(jMenuItem1);
        jPopupMenu.add(jMenuItem2);

        List<Doctor> doctors = (List<Doctor>)doctorController.getDoctorByDoctorName(doctorName).getData().get("doctors");
        Object[][] objects = new Object[0][];
        if (doctors != null){
            objects = new Object[doctors.size()][4];
            for (int i = 0; i < doctors.size(); i++) {
                Doctor doctor = doctors.get(i);
                objects[i][0] = doctor.getId();
                objects[i][1] = doctor.getDoctorName();
                objects[i][2] = doctor.getGender();
                objects[i][3] = doctor.getTel();
            }
        }
        SystemService that = this;
        //需要用DefaultTableModel()构建JTable，否则无法使用jTable.getModel()的强转方法
        JTable jTable = new JTable(new DefaultTableModel(objects,this.DoctorColumnNames)){
            public boolean isCellEditable(int row, int column){
                return false;   //重写JTable方法，使其不能编辑
            }
        };
        jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//选择模式
        jTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //双击触发事件
                if (e.getClickCount() == 2){
                    DefaultTableModel tableModel = (DefaultTableModel)(jTable.getModel());
                    List data = tableModel.getDataVector().elementAt(jTable.getSelectedRow());
                    JTextArea consoleArea = (JTextArea)that.abstractFrame.getComponentMap().get("consoleArea");
                    String msg = "医生信息:" + "\n";
                    for (int i = 1; i < data.size(); i++) {
                        msg += data.get(i).toString() + "\n";
                    }
                    consoleArea.setText(consoleArea.getText()+"\n"+msg);
                }

                //右键单击事件
                if (e.getButton() == MouseEvent.BUTTON3){
                    jPopupMenu.show(jTable,e.getX(),e.getY());
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        //右键菜单删除事件
        jMenuItem2.addActionListener(e2 -> {
            //某一行被选中时
            if (jTable.getSelectedRow() != -1){
                DefaultTableModel tableModel = (DefaultTableModel)(jTable.getModel());
                List data = tableModel.getDataVector().elementAt(jTable.getSelectedRow());
                Result result = doctorController.deleteDoctor((int)data.get(0));
                if (result.getSuccess()){
                    JOptionPane.showMessageDialog(this.abstractFrame,result.getMessage(),"消息",JOptionPane.WARNING_MESSAGE);
                    this.reLoadDoctor();
                }else {
                    JOptionPane.showMessageDialog(this.abstractFrame,result.getMessage(),"消息",JOptionPane.WARNING_MESSAGE);
                }
            }

        });
        //右键菜单修改事件
        jMenuItem1.addActionListener(e3 -> {
            //某一行被选中时
            if (jTable.getSelectedRow() != -1){
                DefaultTableModel tableModel = (DefaultTableModel)(jTable.getModel());
                List data = tableModel.getDataVector().elementAt(jTable.getSelectedRow());
                Doctor doctor = new Doctor((int)data.get(0),(String)data.get(1),(String)data.get(2),(String)data.get(3));
                try {
                    DoctorPopup doctorPopup = new DoctorPopup(this,doctor);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return jTable;
    }
    public JTable queryRecord(String query){
        JPopupMenu jPopupMenu = new JPopupMenu();
        JMenuItem jMenuItem1 = new JMenuItem("修改");
        JMenuItem jMenuItem2 = new JMenuItem("删除");
        jPopupMenu.add(jMenuItem1);
        jPopupMenu.add(jMenuItem2);
        List<Records> records = (List<Records>)recordsController.getRecordsByUserNameOrVaccineName(query).getData().get("records");
        Object[][] objects = new Object[0][];
        if (records != null){
            objects = new Object[records.size()][4];
            for (int i = 0; i < records.size(); i++) {
                Records record = records.get(i);
                objects[i][0] = record.getId();
                objects[i][1] = record.getUserId();
                objects[i][2] = record.getVaccineId();
                objects[i][3] = record.getVaccinateDate();
            }
        }
        SystemService that = this;
        //需要用DefaultTableModel()构建JTable，否则无法使用jTable.getModel()的强转方法
        JTable jTable = new JTable(new DefaultTableModel(objects,this.RecordsColumnNames)){
            public boolean isCellEditable(int row, int column){
                return false;   //重写JTable方法，使其不能编辑
            }
        };
        jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//选择模式
        jTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //双击触发事件
                if (e.getClickCount() == 2){
                    DefaultTableModel tableModel = (DefaultTableModel)(jTable.getModel());
                    List data = tableModel.getDataVector().elementAt(jTable.getSelectedRow());
                    JTextArea consoleArea = (JTextArea)that.abstractFrame.getComponentMap().get("consoleArea");
                    String msg = "疫苗接种信息:" + "\n";
                    List<User> users = (List<User>)userController.getUserById(Integer.parseInt(data.get(1).toString())).getData().get("users");
                    List<Vaccine> vaccines = (List<Vaccine>) vaccineController.getVaccineById(Integer.parseInt(data.get(2).toString())).getData().get("vaccines");
                    msg += "患者信息:\n";
                    for (int i = 0; i < users.size(); i++) {
                        msg += users.get(i).getUserName() + "\n" + users.get(i).getGender() + "\n" + users.get(i).getAge() + "\n" +
                                users.get(i).getAddress() + "\n" + users.get(i).getTel() + "\n" + users.get(i).getEmail() + "\n" +
                                users.get(i).getSymptom() + "\n" + users.get(i).getUserId() + "\n";
                    }
                    msg += "疫苗信息:\n";
                    for (int i = 0; i < vaccines.size(); i++) {
                        msg += vaccines.get(i).getVaccineName() + "\n" + vaccines.get(i).getCompany() + "\n";
                    }
                    msg += data.get(3) + "\n";
                    consoleArea.setText(consoleArea.getText()+"\n"+msg);
                }

                //右键单击事件
                if (e.getButton() == MouseEvent.BUTTON3){
                    jPopupMenu.show(jTable,e.getX(),e.getY());
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        //右键菜单删除事件
        jMenuItem2.addActionListener(e2 -> {
            //某一行被选中时
            if (jTable.getSelectedRow() != -1){
                DefaultTableModel tableModel = (DefaultTableModel)(jTable.getModel());
                List data = tableModel.getDataVector().elementAt(jTable.getSelectedRow());
                Result result = recordsController.deleteRecords((int)data.get(0));
                if (result.getSuccess()){
                    JOptionPane.showMessageDialog(this.abstractFrame,result.getMessage(),"消息",JOptionPane.WARNING_MESSAGE);
                    this.reLoadRecord();
                }else {
                    JOptionPane.showMessageDialog(this.abstractFrame,result.getMessage(),"消息",JOptionPane.WARNING_MESSAGE);
                }
            }

        });
        //右键菜单修改事件
        jMenuItem1.addActionListener(e3 -> {
            //某一行被选中时
            if (jTable.getSelectedRow() != -1){
                DefaultTableModel tableModel = (DefaultTableModel)(jTable.getModel());
                List data = tableModel.getDataVector().elementAt(jTable.getSelectedRow());
                try {
                    Records records2 = new Records((int)data.get(0),(int)data.get(1),(int)data.get(2),(Date) data.get(3));
                    RecordPopup recordPopup = new RecordPopup(this,records2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return jTable;
    }
    public JTable queryUser(String userName){
        JPopupMenu jPopupMenu = new JPopupMenu();
        JMenuItem jMenuItem1 = new JMenuItem("修改");
        JMenuItem jMenuItem2 = new JMenuItem("删除");
        jPopupMenu.add(jMenuItem1);
        jPopupMenu.add(jMenuItem2);
        List<User> users = (List<User>)userController.getUserByUserName(userName).getData().get("users");
        Object[][] objects = new Object[0][];
        if (users != null){
            objects = new Object[users.size()][10];
            for (int i = 0; i < users.size(); i++) {
                User user = users.get(i);
                objects[i][0] = user.getId();
                objects[i][1] = user.getUserName();
                objects[i][2] = user.getGender();
                objects[i][3] = user.getAge();
                objects[i][4] = user.getAddress();
                objects[i][5] = user.getTel();
                objects[i][6] = user.getEmail();
                objects[i][7] = user.getSymptom();
                objects[i][8] = user.getUserId();
                objects[i][9] = user.getDoctorId();
            }
        }
        SystemService that = this;
        //需要用DefaultTableModel()构建JTable，否则无法使用jTable.getModel()的强转方法
        JTable jTable = new JTable(new DefaultTableModel(objects,this.UserColumnNames)){
            public boolean isCellEditable(int row, int column){
                return false;   //重写JTable方法，使其不能编辑
            }
        };
        jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//选择模式
        jTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //双击触发事件
                if (e.getClickCount() == 2){
                    DefaultTableModel tableModel = (DefaultTableModel)(jTable.getModel());
                    List data = tableModel.getDataVector().elementAt(jTable.getSelectedRow());
                    JTextArea consoleArea = (JTextArea)that.abstractFrame.getComponentMap().get("consoleArea");
                    String msg = "患者信息:" + "\n";
                    List<Doctor> doctors = (List<Doctor>)doctorController.getDoctorById(Integer.parseInt(data.get(data.size()-1).toString())).getData().get("doctors");
                    for (int i = 1; i < data.size()-1; i++) {
                        msg += data.get(i).toString() + "\n";
                    }
                    msg += "主治医生信息:\n";
                    for (int i = 0; i < doctors.size(); i++) {
                        msg += doctors.get(i).getDoctorName() + "\n" + doctors.get(i).getGender() + "\n" + doctors.get(i).getTel() + "\n";
                    }
                    consoleArea.setText(consoleArea.getText()+"\n"+msg);
                }

                //右键单击事件
                if (e.getButton() == MouseEvent.BUTTON3){
                    jPopupMenu.show(jTable,e.getX(),e.getY());
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        //右键菜单删除事件
        jMenuItem2.addActionListener(e2 -> {
            //某一行被选中时
            if (jTable.getSelectedRow() != -1){
                DefaultTableModel tableModel = (DefaultTableModel)(jTable.getModel());
                List data = tableModel.getDataVector().elementAt(jTable.getSelectedRow());
                Result result = userController.deleteUser((int)data.get(0));
                if (result.getSuccess()){
                    JOptionPane.showMessageDialog(this.abstractFrame,result.getMessage(),"消息",JOptionPane.WARNING_MESSAGE);
                    this.reLoadUser();
                }else {
                    JOptionPane.showMessageDialog(this.abstractFrame,result.getMessage(),"消息",JOptionPane.WARNING_MESSAGE);
                }
            }

        });
        //右键菜单修改事件
        jMenuItem1.addActionListener(e3 -> {
            //某一行被选中时
            if (jTable.getSelectedRow() != -1){
                DefaultTableModel tableModel = (DefaultTableModel)(jTable.getModel());
                List data = tableModel.getDataVector().elementAt(jTable.getSelectedRow());
                try {
                    User user = new User((int)data.get(0),(String)data.get(1),(String)data.get(2),(int)data.get(3),
                            (String)data.get(4),(String)data.get(5),(String)data.get(6),(String)data.get(7),
                            (String)data.get(8),(int)data.get(9));
                    UserPopup userPopup = new UserPopup(this,user);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return jTable;
    }
    public JTable queryVaccine(String vaccineName){
        JPopupMenu jPopupMenu = new JPopupMenu();
        JMenuItem jMenuItem1 = new JMenuItem("修改");
        JMenuItem jMenuItem2 = new JMenuItem("删除");
        jPopupMenu.add(jMenuItem1);
        jPopupMenu.add(jMenuItem2);

        List<Vaccine> vaccines = (List<Vaccine>)vaccineController.getVaccineByVaccineName(vaccineName).getData().get("vaccines");
        Object[][] objects = new Object[0][];
        if (vaccines != null){
            objects = new Object[vaccines.size()][3];
            for (int i = 0; i < vaccines.size(); i++) {
                Vaccine vaccine = vaccines.get(i);
                objects[i][0] = vaccine.getId();
                objects[i][1] = vaccine.getVaccineName();
                objects[i][2] = vaccine.getCompany();
            }
        }
        SystemService that = this;
        //需要用DefaultTableModel()构建JTable，否则无法使用jTable.getModel()的强转方法
        JTable jTable = new JTable(new DefaultTableModel(objects,this.VaccineColumnNames)){
            public boolean isCellEditable(int row, int column){
                return false;   //重写JTable方法，使其不能编辑
            }
        };
        jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//选择模式
        jTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //双击触发事件
                if (e.getClickCount() == 2){
                    DefaultTableModel tableModel = (DefaultTableModel)(jTable.getModel());
                    List data = tableModel.getDataVector().elementAt(jTable.getSelectedRow());
                    JTextArea consoleArea = (JTextArea)that.abstractFrame.getComponentMap().get("consoleArea");
                    String msg = "疫苗信息:" + "\n";
                    for (int i = 1; i < data.size(); i++) {
                        msg += data.get(i).toString() + "\n";
                    }
                    consoleArea.setText(consoleArea.getText()+"\n"+msg);
                }

                //右键单击事件
                if (e.getButton() == MouseEvent.BUTTON3){
                    jPopupMenu.show(jTable,e.getX(),e.getY());
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        //右键菜单删除事件
        jMenuItem2.addActionListener(e2 -> {
            //某一行被选中时
            if (jTable.getSelectedRow() != -1){
                DefaultTableModel tableModel = (DefaultTableModel)(jTable.getModel());
                List data = tableModel.getDataVector().elementAt(jTable.getSelectedRow());
                Result result = vaccineController.deleteVaccine((int)data.get(0));
                if (result.getSuccess()){
                    JOptionPane.showMessageDialog(this.abstractFrame,result.getMessage(),"消息",JOptionPane.WARNING_MESSAGE);
                    this.reLoadVaccine();
                }else {
                    JOptionPane.showMessageDialog(this.abstractFrame,result.getMessage(),"消息",JOptionPane.WARNING_MESSAGE);
                }
            }

        });
        //右键菜单修改事件
        jMenuItem1.addActionListener(e3 -> {
            //某一行被选中时
            if (jTable.getSelectedRow() != -1){
                DefaultTableModel tableModel = (DefaultTableModel)(jTable.getModel());
                List data = tableModel.getDataVector().elementAt(jTable.getSelectedRow());
                try {
                    Vaccine vaccine = new Vaccine((int)data.get(0),(String)data.get(1),(String)data.get(2));
                    VaccinePopup vaccinePopup = new VaccinePopup(this,vaccine);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return jTable;
    }

    /**
     * reLoad方法，用于调用渲染数据和加载列表界面
     */
    private void reLoadDoctor(){
        JSplitPane topPanel = (JSplitPane)this.abstractFrame.getComponentMap().get("topPanel");
        topPanel.setDividerLocation(200);   //分隔条位置在x = 200上
        topPanel.setDividerSize(5);
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
        bBox.add(new JScrollPane(this.RenderDoctorData()));
        box.add(tBox);
        box.add(bBox);
        //监听器设置
        addJButton.addActionListener(e -> {
            try {
                DoctorPopup doctorPopup = new DoctorPopup(this);
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
                    bBox.add(new JScrollPane(this.queryDoctor(jTextField.getText())));
                    box.add(bBox);
                    topPanel.setDividerLocation(200);   //分隔条位置在x = 200上
                    topPanel.setDividerSize(5);
                    topPanel.setRightComponent(box);
                }).start();
            } catch (Exception ex){
                ex.printStackTrace();
            }
        });
        topPanel.setRightComponent(box);
    }
    private void reLoadRecord(){
        JSplitPane topPanel = (JSplitPane)this.abstractFrame.getComponentMap().get("topPanel");
        topPanel.setDividerLocation(200);   //分隔条位置在x = 200上
        topPanel.setDividerSize(5);
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
        bBox.add(new JScrollPane(this.RenderRecordData()));
        box.add(tBox);
        box.add(bBox);
        //监听器设置
        addJButton.addActionListener(e -> {
            try {
                RecordPopup recordPopup = new RecordPopup(this);
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
                    bBox.add(new JScrollPane(this.queryRecord(jTextField.getText())));
                    box.add(bBox);
                    topPanel.setDividerLocation(200);   //分隔条位置在x = 200上
                    topPanel.setDividerSize(5);
                    topPanel.setRightComponent(box);
                }).start();
            } catch (Exception ex){
                ex.printStackTrace();
            }
        });
        topPanel.setRightComponent(box);
    }
    private void reLoadUser(){
        JSplitPane topPanel = (JSplitPane)this.abstractFrame.getComponentMap().get("topPanel");
        topPanel.setDividerLocation(200);   //分隔条位置在x = 200上
        topPanel.setDividerSize(5);
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
        bBox.add(new JScrollPane(this.RenderUserData()));
        box.add(tBox);
        box.add(bBox);
        //监听器设置
        addJButton.addActionListener(e -> {
            try {
                UserPopup userPopup = new UserPopup(this);
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
                    bBox.add(new JScrollPane(this.queryUser(jTextField.getText())));
                    box.add(bBox);
                    topPanel.setDividerLocation(200);   //分隔条位置在x = 200上
                    topPanel.setDividerSize(5);
                    topPanel.setRightComponent(box);
                }).start();
            } catch (Exception ex){
                ex.printStackTrace();
            }
        });
        topPanel.setRightComponent(box);
    }
    private void reLoadVaccine(){
        JSplitPane topPanel = (JSplitPane)this.abstractFrame.getComponentMap().get("topPanel");
        topPanel.setDividerLocation(200);   //分隔条位置在x = 200上
        topPanel.setDividerSize(5);
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
        bBox.add(new JScrollPane(this.RenderVaccineData()));
        box.add(tBox);
        box.add(bBox);
        //监听器设置
        addJButton.addActionListener(e -> {
            try {
                VaccinePopup vaccinePopup = new VaccinePopup(this);
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
                    bBox.add(new JScrollPane(this.queryVaccine(jTextField.getText())));
                    box.add(bBox);

                    topPanel.setDividerLocation(200);   //分隔条位置在x = 200上
                    topPanel.setDividerSize(5);
                    topPanel.setRightComponent(box);
                }).start();
            } catch (Exception ex){
                ex.printStackTrace();
            }
        });
        topPanel.setRightComponent(box);
    }

    /**
     * 渲染数据
     * @return JTable
     */
    public JTable RenderDoctorData(){
        JPopupMenu jPopupMenu = new JPopupMenu();
        JMenuItem jMenuItem1 = new JMenuItem("修改");
        JMenuItem jMenuItem2 = new JMenuItem("删除");
        jPopupMenu.add(jMenuItem1);
        jPopupMenu.add(jMenuItem2);

        List<Doctor> doctors = (List<Doctor>)doctorController.getAllDoctor().getData().get("doctors");
        Object[][] objects = new Object[0][];
        if (doctors != null){
            objects = new Object[doctors.size()][4];
            for (int i = 0; i < doctors.size(); i++) {
                Doctor doctor = doctors.get(i);
                objects[i][0] = doctor.getId();
                objects[i][1] = doctor.getDoctorName();
                objects[i][2] = doctor.getGender();
                objects[i][3] = doctor.getTel();
            }
        }
        SystemService that = this;
        //需要用DefaultTableModel()构建JTable，否则无法使用jTable.getModel()的强转方法
        JTable jTable = new JTable(new DefaultTableModel(objects,this.DoctorColumnNames)){
            public boolean isCellEditable(int row, int column){
                return false;   //重写JTable方法，使其不能编辑
            }
        };
        jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//选择模式
        jTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //双击触发事件
                if (e.getClickCount() == 2){
                    DefaultTableModel tableModel = (DefaultTableModel)(jTable.getModel());
                    List data = tableModel.getDataVector().elementAt(jTable.getSelectedRow());
                    JTextArea consoleArea = (JTextArea)that.abstractFrame.getComponentMap().get("consoleArea");
                    String msg = "医生信息:" + "\n";
                    for (int i = 1; i < data.size(); i++) {
                        msg += data.get(i).toString() + "\n";
                    }
                    consoleArea.setText(consoleArea.getText()+"\n"+msg);
                }

                //右键单击事件
                if (e.getButton() == MouseEvent.BUTTON3){
                    jPopupMenu.show(jTable,e.getX(),e.getY());
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        //右键菜单删除事件
        jMenuItem2.addActionListener(e2 -> {
            //某一行被选中时
            if (jTable.getSelectedRow() != -1){
                DefaultTableModel tableModel = (DefaultTableModel)(jTable.getModel());
                List data = tableModel.getDataVector().elementAt(jTable.getSelectedRow());
                Result result = doctorController.deleteDoctor((int)data.get(0));
                if (result.getSuccess()){
                    JOptionPane.showMessageDialog(this.abstractFrame,result.getMessage(),"消息",JOptionPane.WARNING_MESSAGE);
                    this.reLoadDoctor();
                }else {
                    JOptionPane.showMessageDialog(this.abstractFrame,result.getMessage(),"消息",JOptionPane.WARNING_MESSAGE);
                }
            }

        });
        //右键菜单修改事件
        jMenuItem1.addActionListener(e3 -> {
            //某一行被选中时
            if (jTable.getSelectedRow() != -1){
                DefaultTableModel tableModel = (DefaultTableModel)(jTable.getModel());
                List data = tableModel.getDataVector().elementAt(jTable.getSelectedRow());
                Doctor doctor = new Doctor((int)data.get(0),(String)data.get(1),(String)data.get(2),(String)data.get(3));
                try {
                    DoctorPopup doctorPopup = new DoctorPopup(this,doctor);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return jTable;
    }
    public JTable RenderRecordData(){
        JPopupMenu jPopupMenu = new JPopupMenu();
        JMenuItem jMenuItem1 = new JMenuItem("修改");
        JMenuItem jMenuItem2 = new JMenuItem("删除");
        jPopupMenu.add(jMenuItem1);
        jPopupMenu.add(jMenuItem2);
        List<Records> records = (List<Records>)recordsController.getAllRecords().getData().get("records");
        Object[][] objects = new Object[0][];
        if (records != null){
            objects = new Object[records.size()][4];
            for (int i = 0; i < records.size(); i++) {
                Records record = records.get(i);
                objects[i][0] = record.getId();
                objects[i][1] = record.getUserId();
                objects[i][2] = record.getVaccineId();
                objects[i][3] = record.getVaccinateDate();
            }
        }
        SystemService that = this;
        //需要用DefaultTableModel()构建JTable，否则无法使用jTable.getModel()的强转方法
        JTable jTable = new JTable(new DefaultTableModel(objects,this.RecordsColumnNames)){
            public boolean isCellEditable(int row, int column){
                return false;   //重写JTable方法，使其不能编辑
            }
        };
        jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//选择模式
        jTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //双击触发事件
                if (e.getClickCount() == 2){
                    DefaultTableModel tableModel = (DefaultTableModel)(jTable.getModel());
                    List data = tableModel.getDataVector().elementAt(jTable.getSelectedRow());
                    JTextArea consoleArea = (JTextArea)that.abstractFrame.getComponentMap().get("consoleArea");
                    String msg = "疫苗接种信息:" + "\n";
                    List<User> users = (List<User>)userController.getUserById(Integer.parseInt(data.get(1).toString())).getData().get("users");
                    List<Vaccine> vaccines = (List<Vaccine>) vaccineController.getVaccineById(Integer.parseInt(data.get(2).toString())).getData().get("vaccines");
                    msg += "患者信息:\n";
                    for (int i = 0; i < users.size(); i++) {
                        msg += users.get(i).getUserName() + "\n" + users.get(i).getGender() + "\n" + users.get(i).getAge() + "\n" +
                                users.get(i).getAddress() + "\n" + users.get(i).getTel() + "\n" + users.get(i).getEmail() + "\n" +
                                users.get(i).getSymptom() + "\n" + users.get(i).getUserId() + "\n";
                    }
                    msg += "疫苗信息:\n";
                    for (int i = 0; i < vaccines.size(); i++) {
                        msg += vaccines.get(i).getVaccineName() + "\n" + vaccines.get(i).getCompany() + "\n";
                    }
                    msg += data.get(3) + "\n";
                    consoleArea.setText(consoleArea.getText()+"\n"+msg);
                }

                //右键单击事件
                if (e.getButton() == MouseEvent.BUTTON3){
                    jPopupMenu.show(jTable,e.getX(),e.getY());
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        //右键菜单删除事件
        jMenuItem2.addActionListener(e2 -> {
            //某一行被选中时
            if (jTable.getSelectedRow() != -1){
                DefaultTableModel tableModel = (DefaultTableModel)(jTable.getModel());
                List data = tableModel.getDataVector().elementAt(jTable.getSelectedRow());
                Result result = recordsController.deleteRecords((int)data.get(0));
                if (result.getSuccess()){
                    JOptionPane.showMessageDialog(this.abstractFrame,result.getMessage(),"消息",JOptionPane.WARNING_MESSAGE);
                    this.reLoadRecord();
                }else {
                    JOptionPane.showMessageDialog(this.abstractFrame,result.getMessage(),"消息",JOptionPane.WARNING_MESSAGE);
                }
            }

        });
        //右键菜单修改事件
        jMenuItem1.addActionListener(e3 -> {
            //某一行被选中时
            if (jTable.getSelectedRow() != -1){
                DefaultTableModel tableModel = (DefaultTableModel)(jTable.getModel());
                List data = tableModel.getDataVector().elementAt(jTable.getSelectedRow());
                try {
                    Records records2 = new Records((int)data.get(0),(int)data.get(1),(int)data.get(2),(Date) data.get(3));
                    RecordPopup recordPopup = new RecordPopup(this,records2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return jTable;
    }
    public JTable RenderUserData(){
        JPopupMenu jPopupMenu = new JPopupMenu();
        JMenuItem jMenuItem1 = new JMenuItem("修改");
        JMenuItem jMenuItem2 = new JMenuItem("删除");
        jPopupMenu.add(jMenuItem1);
        jPopupMenu.add(jMenuItem2);
        List<User> users = (List<User>)userController.getAllUser().getData().get("users");
        Object[][] objects = new Object[0][];
        if (users != null){
            objects = new Object[users.size()][10];
            for (int i = 0; i < users.size(); i++) {
                User user = users.get(i);
                objects[i][0] = user.getId();
                objects[i][1] = user.getUserName();
                objects[i][2] = user.getGender();
                objects[i][3] = user.getAge();
                objects[i][4] = user.getAddress();
                objects[i][5] = user.getTel();
                objects[i][6] = user.getEmail();
                objects[i][7] = user.getSymptom();
                objects[i][8] = user.getUserId();
                objects[i][9] = user.getDoctorId();
            }
        }
        SystemService that = this;
        //需要用DefaultTableModel()构建JTable，否则无法使用jTable.getModel()的强转方法
        JTable jTable = new JTable(new DefaultTableModel(objects,this.UserColumnNames)){
            public boolean isCellEditable(int row, int column){
                return false;   //重写JTable方法，使其不能编辑
            }
        };
        jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//选择模式
        jTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //双击触发事件
                if (e.getClickCount() == 2){
                    DefaultTableModel tableModel = (DefaultTableModel)(jTable.getModel());
                    List data = tableModel.getDataVector().elementAt(jTable.getSelectedRow());
                    JTextArea consoleArea = (JTextArea)that.abstractFrame.getComponentMap().get("consoleArea");
                    String msg = "患者信息:" + "\n";
                    List<Doctor> doctors = (List<Doctor>)doctorController.getDoctorById(Integer.parseInt(data.get(data.size()-1).toString())).getData().get("doctors");
                    for (int i = 1; i < data.size()-1; i++) {
                        msg += data.get(i).toString() + "\n";
                    }
                    msg += "主治医生信息:\n";
                    for (int i = 0; i < doctors.size(); i++) {
                        msg += doctors.get(i).getDoctorName() + "\n" + doctors.get(i).getGender() + "\n" + doctors.get(i).getTel() + "\n";
                    }
                    consoleArea.setText(consoleArea.getText()+"\n"+msg);
                }

                //右键单击事件
                if (e.getButton() == MouseEvent.BUTTON3){
                    jPopupMenu.show(jTable,e.getX(),e.getY());
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        //右键菜单删除事件
        jMenuItem2.addActionListener(e2 -> {
            //某一行被选中时
            if (jTable.getSelectedRow() != -1){
                DefaultTableModel tableModel = (DefaultTableModel)(jTable.getModel());
                List data = tableModel.getDataVector().elementAt(jTable.getSelectedRow());
                Result result = userController.deleteUser((int)data.get(0));
                if (result.getSuccess()){
                    JOptionPane.showMessageDialog(this.abstractFrame,result.getMessage(),"消息",JOptionPane.WARNING_MESSAGE);
                    this.reLoadUser();
                }else {
                    JOptionPane.showMessageDialog(this.abstractFrame,result.getMessage(),"消息",JOptionPane.WARNING_MESSAGE);
                }
            }

        });
        //右键菜单修改事件
        jMenuItem1.addActionListener(e3 -> {
            //某一行被选中时
            if (jTable.getSelectedRow() != -1){
                DefaultTableModel tableModel = (DefaultTableModel)(jTable.getModel());
                List data = tableModel.getDataVector().elementAt(jTable.getSelectedRow());
                try {
                    User user = new User((int)data.get(0),(String)data.get(1),(String)data.get(2),(int)data.get(3),
                            (String)data.get(4),(String)data.get(5),(String)data.get(6),(String)data.get(7),
                            (String)data.get(8),(int)data.get(9));
                    UserPopup userPopup = new UserPopup(this,user);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return jTable;
    }
    public JTable RenderVaccineData(){
        JPopupMenu jPopupMenu = new JPopupMenu();
        JMenuItem jMenuItem1 = new JMenuItem("修改");
        JMenuItem jMenuItem2 = new JMenuItem("删除");
        jPopupMenu.add(jMenuItem1);
        jPopupMenu.add(jMenuItem2);

        List<Vaccine> vaccines = (List<Vaccine>)vaccineController.getAllVaccine().getData().get("vaccines");
        Object[][] objects = new Object[0][];
        if (vaccines != null){
            objects = new Object[vaccines.size()][3];
            for (int i = 0; i < vaccines.size(); i++) {
                Vaccine vaccine = vaccines.get(i);
                objects[i][0] = vaccine.getId();
                objects[i][1] = vaccine.getVaccineName();
                objects[i][2] = vaccine.getCompany();
            }
        }
        SystemService that = this;
        //需要用DefaultTableModel()构建JTable，否则无法使用jTable.getModel()的强转方法
        JTable jTable = new JTable(new DefaultTableModel(objects,this.VaccineColumnNames)){
            public boolean isCellEditable(int row, int column){
                return false;   //重写JTable方法，使其不能编辑
            }
        };
        jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//选择模式
        jTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //双击触发事件
                if (e.getClickCount() == 2){
                    DefaultTableModel tableModel = (DefaultTableModel)(jTable.getModel());
                    List data = tableModel.getDataVector().elementAt(jTable.getSelectedRow());
                    JTextArea consoleArea = (JTextArea)that.abstractFrame.getComponentMap().get("consoleArea");
                    String msg = "疫苗信息:" + "\n";
                    for (int i = 1; i < data.size(); i++) {
                        msg += data.get(i).toString() + "\n";
                    }
                    consoleArea.setText(consoleArea.getText()+"\n"+msg);
                }

                //右键单击事件
                if (e.getButton() == MouseEvent.BUTTON3){
                    jPopupMenu.show(jTable,e.getX(),e.getY());
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        //右键菜单删除事件
        jMenuItem2.addActionListener(e2 -> {
            //某一行被选中时
            if (jTable.getSelectedRow() != -1){
                DefaultTableModel tableModel = (DefaultTableModel)(jTable.getModel());
                List data = tableModel.getDataVector().elementAt(jTable.getSelectedRow());
                Result result = vaccineController.deleteVaccine((int)data.get(0));
                if (result.getSuccess()){
                    JOptionPane.showMessageDialog(this.abstractFrame,result.getMessage(),"消息",JOptionPane.WARNING_MESSAGE);
                    this.reLoadVaccine();
                }else {
                    JOptionPane.showMessageDialog(this.abstractFrame,result.getMessage(),"消息",JOptionPane.WARNING_MESSAGE);
                }
            }

        });
        //右键菜单修改事件
        jMenuItem1.addActionListener(e3 -> {
            //某一行被选中时
            if (jTable.getSelectedRow() != -1){
                DefaultTableModel tableModel = (DefaultTableModel)(jTable.getModel());
                List data = tableModel.getDataVector().elementAt(jTable.getSelectedRow());
                try {
                    Vaccine vaccine = new Vaccine((int)data.get(0),(String)data.get(1),(String)data.get(2));
                    VaccinePopup vaccinePopup = new VaccinePopup(this,vaccine);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return jTable;
    }

}
