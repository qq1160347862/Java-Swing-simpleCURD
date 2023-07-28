package gui.component;
import entity.User;
import gui.service.SystemService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * 对患者添加和修改的弹出界面
 */
public class UserPopup extends JFrame {
    /**
     *
     * @param systemService //传入业务类便于调用业务方法
     * @throws Exception
     */
    public UserPopup(SystemService systemService) throws Exception{
        this.setSize(new Dimension(500,640));         //设置窗口大小
        this.setTitle("添加患者");       //设置窗口标题
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);          //设置窗口关闭方式
        this.setResizable(false);          //设置窗口尺寸再调整
        this.setLocationRelativeTo(null);       //设置窗口位置居中

        Box box = Box.createVerticalBox();
        Box userNameBox = Box.createHorizontalBox();
        Box genderBox = Box.createHorizontalBox();
        Box ageBox = Box.createHorizontalBox();
        Box addressBox = Box.createHorizontalBox();
        Box telBox = Box.createHorizontalBox();
        Box emailBox = Box.createHorizontalBox();
        Box symptomBox = Box.createHorizontalBox();
        Box userIdBox = Box.createHorizontalBox();
        Box doctorIdBox = Box.createHorizontalBox();

        JLabel userNameLabel = new JLabel("患者姓名");
        userNameLabel.setFont(new Font("24pxFont",1,12));
        userNameLabel.setForeground(Color.BLACK);
        JLabel genderLabel = new JLabel("性别");
        genderLabel.setFont(new Font("24pxFont",1,12));
        genderLabel.setForeground(Color.BLACK);
        JLabel ageLabel = new JLabel("年龄");
        ageLabel.setFont(new Font("24pxFont",1,12));
        ageLabel.setForeground(Color.BLACK);
        JLabel addressLabel = new JLabel("地址");
        addressLabel.setFont(new Font("24pxFont",1,12));
        addressLabel.setForeground(Color.BLACK);
        JLabel telLabel = new JLabel("电话");
        telLabel.setFont(new Font("24pxFont",1,12));
        telLabel.setForeground(Color.BLACK);
        JLabel emailLabel = new JLabel("电子邮箱");
        emailLabel.setFont(new Font("24pxFont",1,12));
        emailLabel.setForeground(Color.BLACK);
        JLabel symptomLabel = new JLabel("发病症状");
        symptomLabel.setFont(new Font("24pxFont",1,12));
        symptomLabel.setForeground(Color.BLACK);
        JLabel userIdLabel = new JLabel("身份证号");
        userIdLabel.setFont(new Font("24pxFont",1,12));
        userIdLabel.setForeground(Color.BLACK);
        JLabel doctorIdLabel = new JLabel("主治医生ID");
        doctorIdLabel.setFont(new Font("24pxFont",1,12));
        doctorIdLabel.setForeground(Color.BLACK);

        JTextField userNameTextField = new JTextField(10);
        JTextField genderTextField = new JTextField(10);
        JTextField ageTextField = new JTextField(10);
        ageTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                int keyChar = e.getKeyChar();
                if (ageTextField.getText().length() >= 3 || (keyChar > KeyEvent.VK_9 || keyChar < KeyEvent.VK_0)) {
                    e.consume();    // 屏蔽输入，限制输入总长度在3,且输入为0-9
                }
            }
        });
        JTextField addressTextField = new JTextField(10);
        JTextField telTextField = new JTextField(10);
        telTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                int keyChar = e.getKeyChar();
                if (keyChar > KeyEvent.VK_9 || keyChar < KeyEvent.VK_0) {
                    e.consume();    // 屏蔽输入，限制输入为0-9
                }
            }
        });
        JTextField emailTextField = new JTextField(10);
        emailTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                int keyChar = e.getKeyChar();
                if ((keyChar>=KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9) || (keyChar >= 64 && keyChar <= 90) || (keyChar >= 97 && keyChar <= 122) || keyChar==46) {

                }else {
                    e.consume();    // 屏蔽输入，限制输入只能为数字和字母和@和.
                }
            }
        });
        JTextField symptomTextField = new JTextField(10);
        JTextField userIdTextField = new JTextField(10);
        userIdTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                int keyChar = e.getKeyChar();
                if (userIdTextField.getText().length() < 18 && ((keyChar>=KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9) || (keyChar >= 65 && keyChar <= 90) || (keyChar >= 97 && keyChar <= 122))) {

                }else {
                    e.consume();    // 屏蔽输入，限制输入为18,并且只能为数字和字母
                }
            }
        });
        JTextField doctorIdTextField = new JTextField(10);
        doctorIdTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                int keyChar = e.getKeyChar();
                if (keyChar > KeyEvent.VK_9 || keyChar < KeyEvent.VK_0) {
                    e.consume();    // 屏蔽输入，限制输入为0-9
                }
            }
        });

        JButton submit = new JButton("提交");

        userNameBox.add(Box.createHorizontalStrut(100));
        userNameBox.add(userNameLabel);
        userNameBox.add(Box.createHorizontalStrut(30));
        userNameBox.add(userNameTextField);
        userNameBox.add(Box.createHorizontalStrut(100));

        genderBox.add(Box.createHorizontalStrut(100));
        genderBox.add(genderLabel);
        genderBox.add(Box.createHorizontalStrut(30));
        genderBox.add(genderTextField);
        genderBox.add(Box.createHorizontalStrut(100));

        ageBox.add(Box.createHorizontalStrut(100));
        ageBox.add(ageLabel);
        ageBox.add(Box.createHorizontalStrut(30));
        ageBox.add(ageTextField);
        ageBox.add(Box.createHorizontalStrut(100));

        addressBox.add(Box.createHorizontalStrut(100));
        addressBox.add(addressLabel);
        addressBox.add(Box.createHorizontalStrut(30));
        addressBox.add(addressTextField);
        addressBox.add(Box.createHorizontalStrut(100));

        telBox.add(Box.createHorizontalStrut(100));
        telBox.add(telLabel);
        telBox.add(Box.createHorizontalStrut(30));
        telBox.add(telTextField);
        telBox.add(Box.createHorizontalStrut(100));

        emailBox.add(Box.createHorizontalStrut(100));
        emailBox.add(emailLabel);
        emailBox.add(Box.createHorizontalStrut(30));
        emailBox.add(emailTextField);
        emailBox.add(Box.createHorizontalStrut(100));

        symptomBox.add(Box.createHorizontalStrut(100));
        symptomBox.add(symptomLabel);
        symptomBox.add(Box.createHorizontalStrut(30));
        symptomBox.add(symptomTextField);
        symptomBox.add(Box.createHorizontalStrut(100));

        userIdBox.add(Box.createHorizontalStrut(100));
        userIdBox.add(userIdLabel);
        userIdBox.add(Box.createHorizontalStrut(30));
        userIdBox.add(userIdTextField);
        userIdBox.add(Box.createHorizontalStrut(100));

        doctorIdBox.add(Box.createHorizontalStrut(100));
        doctorIdBox.add(doctorIdLabel);
        doctorIdBox.add(Box.createHorizontalStrut(30));
        doctorIdBox.add(doctorIdTextField);
        doctorIdBox.add(Box.createHorizontalStrut(100));

        box.add(Box.createVerticalStrut(30));
        box.add(userNameBox);
        box.add(Box.createVerticalStrut(30));
        box.add(genderBox);
        box.add(Box.createVerticalStrut(30));
        box.add(ageBox);
        box.add(Box.createVerticalStrut(30));
        box.add(addressBox);
        box.add(Box.createVerticalStrut(30));
        box.add(telBox);
        box.add(Box.createVerticalStrut(30));
        box.add(emailBox);
        box.add(Box.createVerticalStrut(30));
        box.add(symptomBox);
        box.add(Box.createVerticalStrut(30));
        box.add(userIdBox);
        box.add(Box.createVerticalStrut(30));
        box.add(doctorIdBox);
        box.add(Box.createVerticalStrut(30));
        box.add(submit);
        box.add(Box.createVerticalStrut(30));

        submit.addActionListener(e -> {
            User user = new User();
            user.setUserName(userNameTextField.getText());
            user.setGender(genderTextField.getText());
            if (!ageTextField.getText().equals("")){
                user.setAge(Integer.parseInt(ageTextField.getText()));
            }else {
                user.setAge(0);
            }
            user.setAddress(addressTextField.getText());
            user.setTel(telTextField.getText());
            user.setEmail(emailTextField.getText());
            user.setSymptom(symptomTextField.getText());
            user.setUserId(userIdTextField.getText());
            if (!doctorIdTextField.getText().equals("")){
                user.setDoctorId(Integer.parseInt(doctorIdTextField.getText()));
            }else {
                user.setDoctorId(0);
            }
            if (    user.getUserName().equals("") ||
                    user.getGender().equals("") ||
                    user.getAge() == 0 ||
                    user.getAddress().equals("") ||
                    user.getTel().equals("") ||
                    user.getEmail().equals("") ||
                    user.getSymptom().equals("") ||
                    user.getUserId().equals("") ||
                    user.getDoctorId() == 0){
                System.out.println("请完善信息");
            }else {
                systemService.addUser(user);
                this.setVisible(false);
                this.dispose();
            }
        });
        this.add(box);
        this.setVisible(true);
    }

    /**
     *
     * @param systemService //传入业务类便于调用业务方法
     * @param prop_user     //传入列表中被选中行的数据,并渲染到弹出框中,便于修改
     * @throws Exception
     */
    public UserPopup(SystemService systemService,User prop_user) throws Exception{
        this.setSize(new Dimension(500,640));         //设置窗口大小
        this.setTitle("修改患者信息");       //设置窗口标题
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);          //设置窗口关闭方式
        this.setResizable(false);          //设置窗口尺寸再调整
        this.setLocationRelativeTo(null);       //设置窗口位置居中

        Box box = Box.createVerticalBox();
        Box userNameBox = Box.createHorizontalBox();
        Box genderBox = Box.createHorizontalBox();
        Box ageBox = Box.createHorizontalBox();
        Box addressBox = Box.createHorizontalBox();
        Box telBox = Box.createHorizontalBox();
        Box emailBox = Box.createHorizontalBox();
        Box symptomBox = Box.createHorizontalBox();
        Box userIdBox = Box.createHorizontalBox();
        Box doctorIdBox = Box.createHorizontalBox();

        JLabel userNameLabel = new JLabel("患者姓名");
        userNameLabel.setFont(new Font("24pxFont",1,12));
        userNameLabel.setForeground(Color.BLACK);
        JLabel genderLabel = new JLabel("性别");
        genderLabel.setFont(new Font("24pxFont",1,12));
        genderLabel.setForeground(Color.BLACK);
        JLabel ageLabel = new JLabel("年龄");
        ageLabel.setFont(new Font("24pxFont",1,12));
        ageLabel.setForeground(Color.BLACK);
        JLabel addressLabel = new JLabel("地址");
        addressLabel.setFont(new Font("24pxFont",1,12));
        addressLabel.setForeground(Color.BLACK);
        JLabel telLabel = new JLabel("电话");
        telLabel.setFont(new Font("24pxFont",1,12));
        telLabel.setForeground(Color.BLACK);
        JLabel emailLabel = new JLabel("电子邮箱");
        emailLabel.setFont(new Font("24pxFont",1,12));
        emailLabel.setForeground(Color.BLACK);
        JLabel symptomLabel = new JLabel("发病症状");
        symptomLabel.setFont(new Font("24pxFont",1,12));
        symptomLabel.setForeground(Color.BLACK);
        JLabel userIdLabel = new JLabel("身份证号");
        userIdLabel.setFont(new Font("24pxFont",1,12));
        userIdLabel.setForeground(Color.BLACK);
        JLabel doctorIdLabel = new JLabel("主治医生ID");
        doctorIdLabel.setFont(new Font("24pxFont",1,12));
        doctorIdLabel.setForeground(Color.BLACK);

        JTextField userNameTextField = new JTextField(10);
        userNameTextField.setText(prop_user.getUserName());
        JTextField genderTextField = new JTextField(10);
        genderTextField.setText(prop_user.getGender());
        JTextField ageTextField = new JTextField(10);
        ageTextField.setText(Integer.toString(prop_user.getAge()));
        ageTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                int keyChar = e.getKeyChar();
                if (ageTextField.getText().length() >= 3 || (keyChar > KeyEvent.VK_9 || keyChar < KeyEvent.VK_0)) {
                    e.consume();    // 屏蔽输入，限制输入总长度在3,且输入为0-9
                }
            }
        });
        JTextField addressTextField = new JTextField(10);
        addressTextField.setText(prop_user.getAddress());
        JTextField telTextField = new JTextField(10);
        telTextField.setText(prop_user.getTel());
        telTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                int keyChar = e.getKeyChar();
                if (keyChar > KeyEvent.VK_9 || keyChar < KeyEvent.VK_0) {
                    e.consume();    // 屏蔽输入，限制输入为0-9
                }
            }
        });
        JTextField emailTextField = new JTextField(10);
        emailTextField.setText(prop_user.getEmail());
        emailTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                int keyChar = e.getKeyChar();
                if ((keyChar>=KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9) || (keyChar >= 64 && keyChar <= 90) || (keyChar >= 97 && keyChar <= 122) || keyChar==46) {

                }else {
                    e.consume();    // 屏蔽输入，限制输入只能为数字和字母和@和.
                }
            }
        });
        JTextField symptomTextField = new JTextField(10);
        symptomTextField.setText(prop_user.getSymptom());
        JTextField userIdTextField = new JTextField(10);
        userIdTextField.setText(prop_user.getUserId());
        userIdTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                int keyChar = e.getKeyChar();
                if (userIdTextField.getText().length() < 18 && ((keyChar>=KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9) || (keyChar >= 65 && keyChar <= 90) || (keyChar >= 97 && keyChar <= 122))) {

                }else {
                    e.consume();    // 屏蔽输入，限制输入为18,并且只能为数字和字母
                }
            }
        });
        JTextField doctorIdTextField = new JTextField(10);
        doctorIdTextField.setText(Integer.toString(prop_user.getDoctorId()));
        doctorIdTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                int keyChar = e.getKeyChar();
                if (keyChar > KeyEvent.VK_9 || keyChar < KeyEvent.VK_0) {
                    e.consume();    // 屏蔽输入，限制输入为0-9
                }
            }
        });

        JButton submit = new JButton("提交");

        userNameBox.add(Box.createHorizontalStrut(100));
        userNameBox.add(userNameLabel);
        userNameBox.add(Box.createHorizontalStrut(30));
        userNameBox.add(userNameTextField);
        userNameBox.add(Box.createHorizontalStrut(100));

        genderBox.add(Box.createHorizontalStrut(100));
        genderBox.add(genderLabel);
        genderBox.add(Box.createHorizontalStrut(30));
        genderBox.add(genderTextField);
        genderBox.add(Box.createHorizontalStrut(100));

        ageBox.add(Box.createHorizontalStrut(100));
        ageBox.add(ageLabel);
        ageBox.add(Box.createHorizontalStrut(30));
        ageBox.add(ageTextField);
        ageBox.add(Box.createHorizontalStrut(100));

        addressBox.add(Box.createHorizontalStrut(100));
        addressBox.add(addressLabel);
        addressBox.add(Box.createHorizontalStrut(30));
        addressBox.add(addressTextField);
        addressBox.add(Box.createHorizontalStrut(100));

        telBox.add(Box.createHorizontalStrut(100));
        telBox.add(telLabel);
        telBox.add(Box.createHorizontalStrut(30));
        telBox.add(telTextField);
        telBox.add(Box.createHorizontalStrut(100));

        emailBox.add(Box.createHorizontalStrut(100));
        emailBox.add(emailLabel);
        emailBox.add(Box.createHorizontalStrut(30));
        emailBox.add(emailTextField);
        emailBox.add(Box.createHorizontalStrut(100));

        symptomBox.add(Box.createHorizontalStrut(100));
        symptomBox.add(symptomLabel);
        symptomBox.add(Box.createHorizontalStrut(30));
        symptomBox.add(symptomTextField);
        symptomBox.add(Box.createHorizontalStrut(100));

        userIdBox.add(Box.createHorizontalStrut(100));
        userIdBox.add(userIdLabel);
        userIdBox.add(Box.createHorizontalStrut(30));
        userIdBox.add(userIdTextField);
        userIdBox.add(Box.createHorizontalStrut(100));

        doctorIdBox.add(Box.createHorizontalStrut(100));
        doctorIdBox.add(doctorIdLabel);
        doctorIdBox.add(Box.createHorizontalStrut(30));
        doctorIdBox.add(doctorIdTextField);
        doctorIdBox.add(Box.createHorizontalStrut(100));

        box.add(Box.createVerticalStrut(30));
        box.add(userNameBox);
        box.add(Box.createVerticalStrut(30));
        box.add(genderBox);
        box.add(Box.createVerticalStrut(30));
        box.add(ageBox);
        box.add(Box.createVerticalStrut(30));
        box.add(addressBox);
        box.add(Box.createVerticalStrut(30));
        box.add(telBox);
        box.add(Box.createVerticalStrut(30));
        box.add(emailBox);
        box.add(Box.createVerticalStrut(30));
        box.add(symptomBox);
        box.add(Box.createVerticalStrut(30));
        box.add(userIdBox);
        box.add(Box.createVerticalStrut(30));
        box.add(doctorIdBox);
        box.add(Box.createVerticalStrut(30));
        box.add(submit);
        box.add(Box.createVerticalStrut(30));

        submit.addActionListener(e -> {
            User user = new User();
            user.setId(prop_user.getId());
            user.setUserName(userNameTextField.getText());
            user.setGender(genderTextField.getText());
            if (!ageTextField.getText().equals("")){
                user.setAge(Integer.parseInt(ageTextField.getText()));
            }else {
                user.setAge(0);
            }
            user.setAddress(addressTextField.getText());
            user.setTel(telTextField.getText());
            user.setEmail(emailTextField.getText());
            user.setSymptom(symptomTextField.getText());
            user.setUserId(userIdTextField.getText());
            if (!doctorIdTextField.getText().equals("")){
                user.setDoctorId(Integer.parseInt(doctorIdTextField.getText()));
            }else {
                user.setDoctorId(0);
            }
            if (    user.getUserName().equals("") ||
                    user.getGender().equals("") ||
                    user.getAge() == 0 ||
                    user.getAddress().equals("") ||
                    user.getTel().equals("") ||
                    user.getEmail().equals("") ||
                    user.getSymptom().equals("") ||
                    user.getUserId().equals("") ||
                    user.getDoctorId() == 0){
                System.out.println("请完善信息");
            }else {
                systemService.updateUser(user);
                this.setVisible(false);
                this.dispose();
            }
        });
        this.add(box);
        this.setVisible(true);
    }
}
