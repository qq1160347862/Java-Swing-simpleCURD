package gui.component;
import entity.Records;
import gui.service.SystemService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 对接种记录添加和修改的弹出界面
 */
public class RecordPopup extends JFrame {
    /**
     *
     * @param systemService //传入业务类便于调用业务方法
     * @throws Exception
     */
    public RecordPopup(SystemService systemService) throws Exception{
        this.setSize(new Dimension(500,300));         //设置窗口大小
        this.setTitle("添加接种记录");       //设置窗口标题
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);          //设置窗口关闭方式
        this.setResizable(false);          //设置窗口尺寸再调整
        this.setLocationRelativeTo(null);       //设置窗口位置居中

        Box box = Box.createVerticalBox();
        Box userIdBox = Box.createHorizontalBox();
        Box vaccineIdBox = Box.createHorizontalBox();
        Box vaccinateDateBox = Box.createHorizontalBox();

        JLabel userIdLabel = new JLabel("患者id");
        userIdLabel.setFont(new Font("24pxFont",1,12));
        userIdLabel.setForeground(Color.BLACK);
        JLabel vaccineIdLabel = new JLabel("疫苗id");
        vaccineIdLabel.setFont(new Font("24pxFont",1,12));
        vaccineIdLabel.setForeground(Color.BLACK);
        JLabel vaccinateDateLabel = new JLabel("接种日期");
        vaccinateDateLabel.setFont(new Font("24pxFont",1,12));
        vaccinateDateLabel.setForeground(Color.BLACK);

        JTextField userIdTextField = new JTextField(10);
        userIdTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                int keyChar = e.getKeyChar();
                if ((keyChar > KeyEvent.VK_9 || keyChar < KeyEvent.VK_0)) {
                    e.consume();    // 屏蔽输入，限制输入为0-9
                }
            }
        });
        JTextField vaccineIdTextField = new JTextField(10);
        vaccineIdTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                int keyChar = e.getKeyChar();
                if ((keyChar > KeyEvent.VK_9 || keyChar < KeyEvent.VK_0)) {
                    e.consume();    // 屏蔽输入，限制输入输入为0-9
                }
            }
        });
        JTextField vaccinateDateTextField = new JTextField(10);
        vaccinateDateTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                int keyChar = e.getKeyChar();
                if ((keyChar>=KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9) || keyChar==45) {

                }else {
                    e.consume();    // 屏蔽输入，限制输入只能为数字和-
                }
            }
        });

        JButton submit = new JButton("提交");

        userIdBox.add(Box.createHorizontalStrut(100));
        userIdBox.add(userIdLabel);
        userIdBox.add(Box.createHorizontalStrut(30));
        userIdBox.add(userIdTextField);
        userIdBox.add(Box.createHorizontalStrut(100));

        vaccineIdBox.add(Box.createHorizontalStrut(100));
        vaccineIdBox.add(vaccineIdLabel);
        vaccineIdBox.add(Box.createHorizontalStrut(30));
        vaccineIdBox.add(vaccineIdTextField);
        vaccineIdBox.add(Box.createHorizontalStrut(100));

        vaccinateDateBox.add(Box.createHorizontalStrut(100));
        vaccinateDateBox.add(vaccinateDateLabel);
        vaccinateDateBox.add(Box.createHorizontalStrut(30));
        vaccinateDateBox.add(vaccinateDateTextField);
        vaccinateDateBox.add(Box.createHorizontalStrut(100));

        box.add(Box.createVerticalStrut(30));
        box.add(userIdBox);
        box.add(Box.createVerticalStrut(30));
        box.add(vaccineIdBox);
        box.add(Box.createVerticalStrut(30));
        box.add(vaccinateDateBox);
        box.add(Box.createVerticalStrut(30));
        box.add(submit);
        box.add(Box.createVerticalStrut(30));

        submit.addActionListener(e -> {
            Records records = new Records();
            if (!userIdTextField.getText().equals("")){
                records.setUserId(Integer.parseInt(userIdTextField.getText()));
            }else {
                records.setUserId(0);
            }
            if (!vaccineIdTextField.getText().equals("")){
                records.setVaccineId(Integer.parseInt(vaccineIdTextField.getText()));
            }else {
                records.setVaccineId(0);
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date = dateFormat.parse(vaccinateDateTextField.getText());
                records.setVaccinateDate(date);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            if (    records.getUserId() == 0 ||
                    records.getVaccineId() == 0 ||
                    records.getVaccinateDate().equals("")){
                System.out.println("请完善信息");
            }else {
                systemService.addRecord(records);
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
     * @param prop_records  //传入列表中被选中行的数据,并渲染到弹出框中,便于修改
     * @throws Exception
     */
    public RecordPopup(SystemService systemService, Records prop_records) throws Exception {
        this.setSize(new Dimension(500,300));         //设置窗口大小
        this.setTitle("修改接种记录");       //设置窗口标题
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);          //设置窗口关闭方式
        this.setResizable(false);          //设置窗口尺寸再调整
        this.setLocationRelativeTo(null);       //设置窗口位置居中

        Box box = Box.createVerticalBox();
        Box userIdBox = Box.createHorizontalBox();
        Box vaccineIdBox = Box.createHorizontalBox();
        Box vaccinateDateBox = Box.createHorizontalBox();

        JLabel userIdLabel = new JLabel("患者id");
        userIdLabel.setFont(new Font("24pxFont",1,12));
        userIdLabel.setForeground(Color.BLACK);
        JLabel vaccineIdLabel = new JLabel("疫苗id");
        vaccineIdLabel.setFont(new Font("24pxFont",1,12));
        vaccineIdLabel.setForeground(Color.BLACK);
        JLabel vaccinateDateLabel = new JLabel("接种日期");
        vaccinateDateLabel.setFont(new Font("24pxFont",1,12));
        vaccinateDateLabel.setForeground(Color.BLACK);

        JTextField userIdTextField = new JTextField(10);
        userIdTextField.setText(Integer.toString(prop_records.getUserId()));
        userIdTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                int keyChar = e.getKeyChar();
                if ((keyChar > KeyEvent.VK_9 || keyChar < KeyEvent.VK_0)) {
                    e.consume();    // 屏蔽输入，限制输入为0-9
                }
            }
        });
        JTextField vaccineIdTextField = new JTextField(10);
        vaccineIdTextField.setText(Integer.toString(prop_records.getVaccineId()));
        vaccineIdTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                int keyChar = e.getKeyChar();
                if ((keyChar > KeyEvent.VK_9 || keyChar < KeyEvent.VK_0)) {
                    e.consume();    // 屏蔽输入，限制输入输入为0-9
                }
            }
        });
        JTextField vaccinateDateTextField = new JTextField(10);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        vaccinateDateTextField.setText(dateFormat.format(prop_records.getVaccinateDate()));
        vaccinateDateTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                int keyChar = e.getKeyChar();
                if ((keyChar>=KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9) || keyChar==45) {

                }else {
                    e.consume();    // 屏蔽输入，限制输入只能为数字和-
                }
            }
        });

        JButton submit = new JButton("提交");

        userIdBox.add(Box.createHorizontalStrut(100));
        userIdBox.add(userIdLabel);
        userIdBox.add(Box.createHorizontalStrut(30));
        userIdBox.add(userIdTextField);
        userIdBox.add(Box.createHorizontalStrut(100));

        vaccineIdBox.add(Box.createHorizontalStrut(100));
        vaccineIdBox.add(vaccineIdLabel);
        vaccineIdBox.add(Box.createHorizontalStrut(30));
        vaccineIdBox.add(vaccineIdTextField);
        vaccineIdBox.add(Box.createHorizontalStrut(100));

        vaccinateDateBox.add(Box.createHorizontalStrut(100));
        vaccinateDateBox.add(vaccinateDateLabel);
        vaccinateDateBox.add(Box.createHorizontalStrut(30));
        vaccinateDateBox.add(vaccinateDateTextField);
        vaccinateDateBox.add(Box.createHorizontalStrut(100));

        box.add(Box.createVerticalStrut(30));
        box.add(userIdBox);
        box.add(Box.createVerticalStrut(30));
        box.add(vaccineIdBox);
        box.add(Box.createVerticalStrut(30));
        box.add(vaccinateDateBox);
        box.add(Box.createVerticalStrut(30));
        box.add(submit);
        box.add(Box.createVerticalStrut(30));

        submit.addActionListener(e -> {
            Records records = new Records();
            records.setId(prop_records.getId());
            if (!userIdTextField.getText().equals("")){
                records.setUserId(Integer.parseInt(userIdTextField.getText()));
            }else {
                records.setUserId(0);
            }
            if (!vaccineIdTextField.getText().equals("")){
                records.setVaccineId(Integer.parseInt(vaccineIdTextField.getText()));
            }else {
                records.setVaccineId(0);
            }
            try {
                Date date = dateFormat.parse(vaccinateDateTextField.getText());
                records.setVaccinateDate(date);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            if (    records.getUserId() == 0 ||
                    records.getVaccineId() == 0 ||
                    records.getVaccinateDate().equals("")){
                System.out.println("请完善信息");
            }else {
                systemService.updateRecord(records);
                this.setVisible(false);
                this.dispose();
            }

        });
        this.add(box);
        this.setVisible(true);
    }
}
