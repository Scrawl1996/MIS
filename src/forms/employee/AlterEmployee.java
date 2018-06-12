package forms.employee;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;

import util.DateChooser;
import util.IdcardUtils;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import com.tool.WindowUtil;

import dto.user.UserDTO;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Vector;

import services.depart.Depart;
import services.enumeration.Duty;
import services.enumeration.Education;
import services.user.User;

import java.awt.Font;

import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class AlterEmployee extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtName;
	private JTextField txtBirthday;
	private JTextField textField_3;
	private JTextField txtDep;
	private JTextField txtTel;
	private JComboBox<String> cmbDuty;
	private JComboBox<String> cmbEdu;
	private UserDTO userDTO = new UserDTO();
	 
	private String sex;
	private JRadioButton rdbtnSexMale;
	private JRadioButton rdbtnSexFemale;
	private JTextField textID;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AlterEmployee dialog = new AlterEmployee();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public AlterEmployee() {
		setType(Type.UTILITY);
		setTitle("\u4FEE\u6539\u5B66\u5458\u4FE1\u606F\r\n");
		
		setModal(true);
		setBounds(100, 100, 582, 346);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("\u59D3\u540D\uFF1A");
		lblNewLabel.setBounds(45, 72, 54, 15);
		contentPanel.add(lblNewLabel);

		txtName = new JTextField();
		txtName.setBounds(107, 63, 173, 33);
		contentPanel.add(txtName);
		txtName.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("\u6027\u522B\uFF1A");
		lblNewLabel_1.setBounds(301, 72, 54, 15);
		contentPanel.add(lblNewLabel_1);

		JLabel label = new JLabel("\u51FA\u751F\u65E5\u671F\uFF1A");
		label.setBounds(45, 115, 79, 15);
		contentPanel.add(label);

		txtBirthday = new JTextField();
		txtBirthday.setColumns(10);
		txtBirthday.setBounds(107, 106, 173, 33);
		DateChooser dateChooser1 = DateChooser.getInstance("yyyy-MM-dd");
		dateChooser1.register(txtBirthday);
		contentPanel.add(txtBirthday);

		JLabel label_1 = new JLabel("\u8EAB\u4EFD\u8BC1\uFF1A");
		label_1.setBounds(301, 115, 54, 15);
		contentPanel.add(label_1);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(365, 103, 155, 36);
		contentPanel.add(textField_3);

		JLabel label_2 = new JLabel("\u90E8\u95E8\uFF1A");
		label_2.setBounds(45, 162, 54, 15);
		contentPanel.add(label_2);

		txtDep = new JTextField();
		txtDep.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("unused")
			@Override
			public void mouseClicked(MouseEvent e) {
				String selectedDep;
				SelectDepForm selectDepForm = new SelectDepForm(txtDep);
				selectDepForm.setVisible(true);
			}
		});
		txtDep.setColumns(10);
		txtDep.setBounds(107, 153, 173, 33);
		contentPanel.add(txtDep);

		JLabel label_3 = new JLabel("\u8054\u7CFB\u7535\u8BDD\uFF1A");
		label_3.setBounds(301, 162, 72, 15);
		contentPanel.add(label_3);

		txtTel = new JTextField();
		txtTel.setColumns(10);
		txtTel.setBounds(365, 149, 155, 34);
		contentPanel.add(txtTel);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			SwingUtilities.updateComponentTreeUI(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		setLocation(WindowUtil.setLocation(this.getWidth(), this.getHeight()));

		 rdbtnSexMale = new JRadioButton("\u7537");
		rdbtnSexMale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnSexMale.isSelected()) {
					sex = " 男";
					rdbtnSexFemale.setSelected(false);
				}
				rdbtnSexMale.setSelected(true);
			}
		});
		rdbtnSexMale.setBounds(364, 68, 59, 23);
		contentPanel.add(rdbtnSexMale);

		 rdbtnSexFemale = new JRadioButton("\u5973");
		rdbtnSexFemale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnSexFemale.isSelected()) {
					sex = "女";
					rdbtnSexMale.setSelected(false);
				}
				rdbtnSexFemale.setSelected(true);
			}
		});
		rdbtnSexFemale.setBounds(425, 68, 59, 23);
		contentPanel.add(rdbtnSexFemale);
		/*ButtonGroup sexRdbtn = new ButtonGroup();
		sexRdbtn.add(rdbtnSexMale);
		sexRdbtn.add(rdbtnSexFemale);*/

		JLabel label_4 = new JLabel("\u804C\u52A1\uFF1A");
		label_4.setBounds(45, 213, 54, 15);
		contentPanel.add(label_4);

		cmbDuty = new JComboBox();

		/*cmbDuty.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					String selDuty = (String) e.getItem();
					System.out.println("Selected item is "
							+ Duty.getIndex(selDuty));
				}
			}
		});*/
		cmbDuty.setBackground(Color.WHITE);
		cmbDuty.setForeground(Color.BLACK);
		cmbDuty.setModel(new DefaultComboBoxModel<String>(Duty.getNames()));
		cmbDuty.setBounds(105, 202, 175, 36);
		contentPanel.add(cmbDuty);

		cmbEdu = new JComboBox();
		/*cmbEdu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});*/
		cmbEdu.setModel(new DefaultComboBoxModel<String>(Education.getNames()));
		cmbEdu.setForeground(Color.BLACK);
		cmbEdu.setBackground(Color.WHITE);
		cmbEdu.setBounds(365, 202, 155, 36);
		contentPanel.add(cmbEdu);

		JLabel label_5 = new JLabel("\u5B66\u5386\uFF1A");
		label_5.setBounds(301, 213, 54, 15);
		contentPanel.add(label_5);
		
		JLabel txtID = new JLabel("ID");
		txtID.setFont(new Font("楷体", Font.PLAIN, 16));
		txtID.setBounds(207, 25, 22, 23);
		contentPanel.add(txtID);
		
		textID = new JTextField();
		textID.setEnabled(false);
		textID.setEditable(false);
		textID.setBounds(239, 22, 173, 31);
		contentPanel.add(textID);
		textID.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon(AlterEmployee.class.getResource("/images/background.jpg")));
		lblNewLabel_2.setBounds(0, 0, 566, 310);
		contentPanel.add(lblNewLabel_2);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("\u4FEE\u6539");
				okButton.setFont(new Font("楷体", Font.PLAIN, 16));
				okButton.addActionListener(this);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("\u9000\u51FA");
				cancelButton.setFont(new Font("楷体", Font.PLAIN, 16));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (txtName.getText().length() == 0) {
			JOptionPane.showMessageDialog(null, "名字不可以为空");
			return;
		}
		if(!IdcardUtils.isValidName(txtName.getText())){
			JOptionPane.showMessageDialog(null, "名字不可以有数字");
			return;
		}
		if (txtName.getText().trim().length()<2) {
			JOptionPane.showMessageDialog(null, "名字必须为两个字以上");
			return;
		}
		if (!rdbtnSexMale.isSelected()) {
			if (!rdbtnSexFemale.isSelected()) {
				JOptionPane.showMessageDialog(null, "请选择性别");
				return;
			}
		}
		if(textField_3.getText().length()==0){
			JOptionPane.showMessageDialog(null, "身份证不可以为空");
			return;
		}
		if(!IdcardUtils.validateCard(textField_3.getText().toString())){
			JOptionPane.showMessageDialog(null, "请输入正确的身份证号码");
			return;
		}
		if(txtBirthday.getText().length()==0){
			JOptionPane.showMessageDialog(null, "出生日期不可以为空");
			return;
		}

		
		if(txtDep.getText().length()==0){
			JOptionPane.showMessageDialog(null, "部门不可以为空");
			return;
		}
		if(txtTel.getText().length()==0){
			JOptionPane.showMessageDialog(null, "电话号码不可以为空");
			return;
		}
		if(!IdcardUtils.isValidMobileNo(txtTel.getText())){
			JOptionPane.showMessageDialog(null, "请输入正确的电话号码");
			return;
		}
		
		userDTO.setUser_id(textID.getText());
		userDTO.setName(txtName.getText());
		userDTO.setSex(sex);
		userDTO.setBirthday(txtBirthday.getText());
		userDTO.setCard_id(textField_3.getText());
		Depart depart = new Depart();
		userDTO.setUnit_id(depart.getUnit_idByname(txtDep.getText())
				.getUnit_id());
		userDTO.setTelephone(txtTel.getText());
		userDTO.setDuty((String) cmbDuty.getSelectedItem());
		userDTO.setTecduty((String) cmbEdu.getSelectedItem());
		boolean flag=new User().updateUser(userDTO);
		if (flag) {
			JOptionPane.showMessageDialog(null, "修改成功");
		} else {
			JOptionPane.showMessageDialog(null, "修改失败");
		}
	}

	public void alteremployee(Vector<Vector<Object>> tableDatas, int index) {
		Vector<Object> userDTo = tableDatas.get(0);
		txtName.setText((String) userDTo.elementAt(1));
		textField_3.setText((String) userDTo.elementAt(4));
		sex = (String) userDTo.elementAt(2);
		if ("男".equals(sex.trim())) {
          rdbtnSexMale.setSelected(true);
		} else {
         rdbtnSexFemale.setSelected(true);
		}
		txtBirthday.setText((String) userDTo.elementAt(3));
		txtTel.setText((String) userDTo.elementAt(6));
		txtDep.setText((String) userDTo.elementAt(5));
		cmbDuty.setSelectedItem((String) userDTo.elementAt(7));
		cmbEdu.setSelectedItem((String) userDTo.elementAt(8));
		textID.setText((String) userDTo.elementAt(0));
	}
}
