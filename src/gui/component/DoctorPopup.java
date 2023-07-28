package gui.component;
import entity.Doctor;
import gui.service.SystemService;
import javax.swing.*;
import java.awt.*;

/**
 * 对医生添加和修改的弹出界面
 */
public class DoctorPopup extends JFrame {
    /**
     * 新增
     * @param systemService //传入业务类便于调用业务方法
     * @throws Exception
     */
    public DoctorPopup(SystemService systemService) throws Exception{
        this.setSize(new Dimension(500,300));         //设置窗口大小
        this.setTitle("添加医生");       //设置窗口标题
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);          //设置窗口关闭方式
        this.setResizable(false);          //设置窗口尺寸再调整
        this.setLocationRelativeTo(null);       //设置窗口位置居中

        Box box = Box.createVerticalBox();
        Box doctorNameBox = Box.createHorizontalBox();
        Box genderBox = Box.createHorizontalBox();
        Box telBox = Box.createHorizontalBox();

        JLabel doctorNameLabel = new JLabel("医生姓名");
        doctorNameLabel.setFont(new Font("24pxFont",1,12));
        doctorNameLabel.setForeground(Color.BLACK);
        JLabel genderLabel = new JLabel("性别");
        genderLabel.setFont(new Font("24pxFont",1,12));
        genderLabel.setForeground(Color.BLACK);
        JLabel telLabel = new JLabel("电话");
        telLabel.setFont(new Font("24pxFont",1,12));
        telLabel.setForeground(Color.BLACK);

        JTextField doctorNameTextField = new JTextField(10);
        JTextField genderTextField = new JTextField(10);
        JTextField telTextField = new JTextField(10);

        JButton submit = new JButton("提交");

        doctorNameBox.add(Box.createHorizontalStrut(100));
        doctorNameBox.add(doctorNameLabel);
        doctorNameBox.add(Box.createHorizontalStrut(30));
        doctorNameBox.add(doctorNameTextField);
        doctorNameBox.add(Box.createHorizontalStrut(100));

        genderBox.add(Box.createHorizontalStrut(100));
        genderBox.add(genderLabel);
        genderBox.add(Box.createHorizontalStrut(30));
        genderBox.add(genderTextField);
        genderBox.add(Box.createHorizontalStrut(100));

        telBox.add(Box.createHorizontalStrut(100));
        telBox.add(telLabel);
        telBox.add(Box.createHorizontalStrut(30));
        telBox.add(telTextField);
        telBox.add(Box.createHorizontalStrut(100));

        box.add(Box.createVerticalStrut(30));
        box.add(doctorNameBox);
        box.add(Box.createVerticalStrut(30));
        box.add(genderBox);
        box.add(Box.createVerticalStrut(30));
        box.add(telBox);
        box.add(Box.createVerticalStrut(30));
        box.add(submit);
        box.add(Box.createVerticalStrut(30));

        submit.addActionListener(e -> {
            Doctor doctor = new Doctor();
            doctor.setDoctorName(doctorNameTextField.getText());
            doctor.setGender(genderTextField.getText());
            doctor.setTel(telTextField.getText());

            if (    doctor.getDoctorName().equals("") ||
                    doctor.getGender().equals("") ||
                    doctor.getTel().equals("")){
                System.out.println("请完善信息");
            }else {
                systemService.addDoctor(doctor);
                this.setVisible(false);
                this.dispose();
            }
        });
        this.add(box);
        this.setVisible(true);
    }

    /**
     * 修改
     * @param systemService //传入业务类便于调用业务方法
     * @param prop_doctor   //传入列表中被选中行的数据,并渲染到弹出框中,便于修改
     * @throws Exception
     */
    public DoctorPopup(SystemService systemService, Doctor prop_doctor) throws Exception{
        this.setSize(new Dimension(500,300));         //设置窗口大小
        this.setTitle("修改医生信息");       //设置窗口标题
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);          //设置窗口关闭方式
        this.setResizable(false);          //设置窗口尺寸再调整
        this.setLocationRelativeTo(null);       //设置窗口位置居中

        Box box = Box.createVerticalBox();
        Box doctorNameBox = Box.createHorizontalBox();
        Box genderBox = Box.createHorizontalBox();
        Box telBox = Box.createHorizontalBox();

        JLabel doctorNameLabel = new JLabel("医生姓名");
        doctorNameLabel.setFont(new Font("24pxFont",1,12));
        doctorNameLabel.setForeground(Color.BLACK);
        JLabel genderLabel = new JLabel("性别");
        genderLabel.setFont(new Font("24pxFont",1,12));
        genderLabel.setForeground(Color.BLACK);
        JLabel telLabel = new JLabel("电话");
        telLabel.setFont(new Font("24pxFont",1,12));
        telLabel.setForeground(Color.BLACK);

        JTextField doctorNameTextField = new JTextField(10);
        doctorNameTextField.setText(prop_doctor.getDoctorName());
        JTextField genderTextField = new JTextField(10);
        genderTextField.setText(prop_doctor.getGender());
        JTextField telTextField = new JTextField(10);
        telTextField.setText(prop_doctor.getTel());

        JButton submit = new JButton("提交");

        doctorNameBox.add(Box.createHorizontalStrut(100));
        doctorNameBox.add(doctorNameLabel);
        doctorNameBox.add(Box.createHorizontalStrut(30));
        doctorNameBox.add(doctorNameTextField);
        doctorNameBox.add(Box.createHorizontalStrut(100));

        genderBox.add(Box.createHorizontalStrut(100));
        genderBox.add(genderLabel);
        genderBox.add(Box.createHorizontalStrut(30));
        genderBox.add(genderTextField);
        genderBox.add(Box.createHorizontalStrut(100));

        telBox.add(Box.createHorizontalStrut(100));
        telBox.add(telLabel);
        telBox.add(Box.createHorizontalStrut(30));
        telBox.add(telTextField);
        telBox.add(Box.createHorizontalStrut(100));

        box.add(Box.createVerticalStrut(30));
        box.add(doctorNameBox);
        box.add(Box.createVerticalStrut(30));
        box.add(genderBox);
        box.add(Box.createVerticalStrut(30));
        box.add(telBox);
        box.add(Box.createVerticalStrut(30));
        box.add(submit);
        box.add(Box.createVerticalStrut(30));

        submit.addActionListener(e -> {
            Doctor doctor = new Doctor();
            doctor.setId(prop_doctor.getId());
            doctor.setDoctorName(doctorNameTextField.getText());
            doctor.setGender(genderTextField.getText());
            doctor.setTel(telTextField.getText());

            if (    doctor.getDoctorName().equals("") ||
                    doctor.getGender().equals("") ||
                    doctor.getTel().equals("")){
                System.out.println("请完善信息");
            }else {
                systemService.updateDoctor(doctor);
                this.setVisible(false);
                this.dispose();
            }
        });
        this.add(box);
        this.setVisible(true);
    }
}
