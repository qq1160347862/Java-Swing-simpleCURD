package gui.component;
import entity.Vaccine;
import gui.service.SystemService;
import javax.swing.*;
import java.awt.*;

/**
 * 对疫苗添加和修改的弹出界面
 */
public class VaccinePopup extends JFrame {
    /**
     *
     * @param systemService //传入业务类便于调用业务方法
     * @throws Exception
     */
    public VaccinePopup(SystemService systemService) throws Exception{
        this.setSize(new Dimension(500,240));         //设置窗口大小
        this.setTitle("添加疫苗");       //设置窗口标题
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);          //设置窗口关闭方式
        this.setResizable(false);          //设置窗口尺寸再调整
        this.setLocationRelativeTo(null);       //设置窗口位置居中

        Box box = Box.createVerticalBox();
        Box vaccineNameBox = Box.createHorizontalBox();
        Box companyBox = Box.createHorizontalBox();

        JLabel vaccineNameLabel = new JLabel("疫苗名称");
        vaccineNameLabel.setFont(new Font("24pxFont",1,12));
        vaccineNameLabel.setForeground(Color.BLACK);
        JLabel companyLabel = new JLabel("公司");
        companyLabel.setFont(new Font("24pxFont",1,12));
        companyLabel.setForeground(Color.BLACK);

        JTextField vaccineNameTextField = new JTextField(10);
        JTextField companyTextField = new JTextField(10);

        JButton submit = new JButton("提交");

        vaccineNameBox.add(Box.createHorizontalStrut(100));
        vaccineNameBox.add(vaccineNameLabel);
        vaccineNameBox.add(Box.createHorizontalStrut(30));
        vaccineNameBox.add(vaccineNameTextField);
        vaccineNameBox.add(Box.createHorizontalStrut(100));

        companyBox.add(Box.createHorizontalStrut(100));
        companyBox.add(companyLabel);
        companyBox.add(Box.createHorizontalStrut(30));
        companyBox.add(companyTextField);
        companyBox.add(Box.createHorizontalStrut(100));

        box.add(Box.createVerticalStrut(30));
        box.add(vaccineNameBox);
        box.add(Box.createVerticalStrut(30));
        box.add(companyBox);
        box.add(Box.createVerticalStrut(30));
        box.add(submit);
        box.add(Box.createVerticalStrut(30));

        submit.addActionListener(e -> {
            Vaccine vaccine = new Vaccine();
            vaccine.setVaccineName(vaccineNameTextField.getText());
            vaccine.setCompany(companyTextField.getText());

            if (    vaccine.getVaccineName().equals("") ||
                    vaccine.getCompany().equals("")){
                System.out.println("请完善信息");
            }else {
                systemService.addVaccine(vaccine);
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
     * @param prop_vaccine  //传入列表中被选中行的数据,并渲染到弹出框中,便于修改
     * @throws Exception
     */
    public VaccinePopup(SystemService systemService,Vaccine prop_vaccine) throws Exception{
        this.setSize(new Dimension(500,240));         //设置窗口大小
        this.setTitle("修改疫苗信息");       //设置窗口标题
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);          //设置窗口关闭方式
        this.setResizable(false);          //设置窗口尺寸再调整
        this.setLocationRelativeTo(null);       //设置窗口位置居中

        Box box = Box.createVerticalBox();
        Box vaccineNameBox = Box.createHorizontalBox();
        Box companyBox = Box.createHorizontalBox();

        JLabel vaccineNameLabel = new JLabel("疫苗名称");
        vaccineNameLabel.setFont(new Font("24pxFont",1,12));
        vaccineNameLabel.setForeground(Color.BLACK);
        JLabel companyLabel = new JLabel("公司");
        companyLabel.setFont(new Font("24pxFont",1,12));
        companyLabel.setForeground(Color.BLACK);

        JTextField vaccineNameTextField = new JTextField(10);
        vaccineNameTextField.setText(prop_vaccine.getVaccineName());
        JTextField companyTextField = new JTextField(10);
        companyTextField.setText(prop_vaccine.getCompany());

        JButton submit = new JButton("提交");

        vaccineNameBox.add(Box.createHorizontalStrut(100));
        vaccineNameBox.add(vaccineNameLabel);
        vaccineNameBox.add(Box.createHorizontalStrut(30));
        vaccineNameBox.add(vaccineNameTextField);
        vaccineNameBox.add(Box.createHorizontalStrut(100));

        companyBox.add(Box.createHorizontalStrut(100));
        companyBox.add(companyLabel);
        companyBox.add(Box.createHorizontalStrut(30));
        companyBox.add(companyTextField);
        companyBox.add(Box.createHorizontalStrut(100));

        box.add(Box.createVerticalStrut(30));
        box.add(vaccineNameBox);
        box.add(Box.createVerticalStrut(30));
        box.add(companyBox);
        box.add(Box.createVerticalStrut(30));
        box.add(submit);
        box.add(Box.createVerticalStrut(30));

        submit.addActionListener(e -> {
            Vaccine vaccine = new Vaccine();
            vaccine.setId(prop_vaccine.getId());
            vaccine.setVaccineName(vaccineNameTextField.getText());
            vaccine.setCompany(companyTextField.getText());

            if (    vaccine.getVaccineName().equals("") ||
                    vaccine.getCompany().equals("")){
                System.out.println("请完善信息");
            }else {
                systemService.updateVaccine(vaccine);
                this.setVisible(false);
                this.dispose();
            }
        });

        this.add(box);
        this.setVisible(true);
    }
}
