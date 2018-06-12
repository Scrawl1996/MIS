package forms;

import com.tool.WindowUtil;
import services.user.User;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * 实现电厂职工培训信息管理系统的登录功能，本实验连接Sql Server数据库，实现登录功能
 * @author 14713
 *
 */
@SuppressWarnings("serial")
public class LoginForm extends JFrame {
	@SuppressWarnings("unused")
	private JTextField textField_4;
	private JTextField txtUname;
	private JPasswordField passwordField;
	private JTextField txtSe_code;

	/**
	 * Create the frame.
	 */

	public LoginForm() {
		JPanel panel = new JPanel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 869, 502);

		panel.setForeground(Color.CYAN);
		panel.setToolTipText("");
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel.setBounds(457, 61, 253, 293);
		getContentPane().add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(248, 248, 255));
		panel_1.setForeground(Color.GREEN);
		panel_1.setBounds(569, 63, 247, 329);
		panel_1.setBorder(new CompoundBorder(new LineBorder(new Color(0, 255,
				255)), new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 255,
				255))));
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel loginLabel = new JLabel(new ImageIcon(LoginForm.class.getResource("/images/login.png")));
		loginLabel.setBounds(77, 10, 76, 33);
		panel_1.add(loginLabel);
		loginLabel.setVerticalAlignment(SwingConstants.BOTTOM);

		txtUname = new JTextField();
		txtUname.setBounds(101, 57, 111, 33);
		panel_1.add(txtUname);
		txtUname.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(101, 112, 111, 33);
		panel_1.add(passwordField);

		JLabel se_codeLabel = new JLabel("验证码：");
		se_codeLabel.setFont(new Font("楷体", Font.PLAIN, 20));
		se_codeLabel.setBounds(3, 173, 88, 33);
		panel_1.add(se_codeLabel);

		JLabel pic_Label = new JLabel(new ImageIcon(LoginForm.class.getResource("/images/Ind.png")));
		pic_Label.setBounds(146, 173, 91, 33);
		panel_1.add(pic_Label);
		pic_Label.setVerticalAlignment(SwingConstants.BOTTOM);

		JLabel unamelabel = new JLabel("用户名：");
		unamelabel.setFont(new Font("楷体", Font.PLAIN, 20));
		unamelabel.setBounds(3, 55, 88, 33);
		panel_1.add(unamelabel);

		JLabel passwordlabel = new JLabel("密码：");
		passwordlabel.setFont(new Font("楷体", Font.PLAIN, 20));
		passwordlabel.setBounds(3, 112, 88, 33);
		panel_1.add(passwordlabel);

		final JButton LoginButton = new JButton("\u767B\u5F55");
		LoginButton.setBounds(77, 286, 93, 33);
		panel_1.add(LoginButton);
		LoginButton.setFont(new Font("楷体", Font.PLAIN, 18));
		LoginButton.setForeground(Color.CYAN);

		txtSe_code = new JTextField();
		txtSe_code.setBounds(72, 173, 66, 33);
		panel_1.add(txtSe_code);
		txtSe_code.setColumns(10);

		JCheckBox CheckBox = new JCheckBox("记住登录");
		CheckBox.setBounds(6, 227, 117, 23);
		panel_1.add(CheckBox);
		CheckBox.setFont(new Font("楷体", Font.PLAIN, 16));
		LoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == LoginButton) {
					do_login_action(e);
				}
			}
		});

		JLabel Label_head = new JLabel(new ImageIcon("F:\\git\\ManageSystem\\MIS\\images\\7.png"));
		Label_head.setBounds(504, 0, 336, 23);
		Label_head.setForeground(Color.WHITE);
		Label_head.setVerticalAlignment(SwingConstants.BOTTOM);
		Label_head.setToolTipText("");
		Label_head.setText("");
		panel.add(Label_head);

		JLabel Label = new JLabel("电厂职工培训信息管理系统");
		Label.setForeground(Color.CYAN);
		Label.setFont(new Font("楷体", Font.PLAIN, 30));
		Label.setBounds(121, 37, 368, 45);
		panel.add(Label);

		JLabel lblCopyright = new JLabel(
				"Copyright © 2015-2016 Tencent. All Rights Reserved.    电厂职工培训管理系统      技术支持联系方式：0351-6998011");
		lblCopyright.setBounds(62, 417, 675, 23);
		panel.add(lblCopyright);

		JLabel lblicp = new JLabel("版权所有 备案证号：晋ICP备050024564号");
		lblicp.setBounds(280, 438, 263, 23);
		panel.add(lblicp);
		JLabel Label_bg = new JLabel(new ImageIcon(LoginForm.class.getResource("/images/bg.jpg")));
		Label_bg.setBounds(0, 0, 863, 475);
		panel.add(Label_bg);
		/**
		 * 设置UI
		 */
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			SwingUtilities.updateComponentTreeUI(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		setLocation(WindowUtil.setLocation(this.getWidth(), this.getHeight()));
		// frame.setVisible(true);
	}

	protected void do_login_action(ActionEvent e) {             //登录检测用户名与密码是否正确，正确登录成功进入主界面，失败提示登录出错
		String username = txtUname.getText().trim();
		String password = new String(passwordField.getPassword());

		if ("".equals(username) || "".equals(password)) {
			JOptionPane.showMessageDialog(null, "输入的用户名或密码不能为空!");
		} else {
			User user=new User();
			boolean flag =user.findUser(username, password);
			if (flag) {
				LoginForm.this.setVisible(false);
				MainPage mainPage = new MainPage();
				mainPage.setVisible(true);

			} else {
				JOptionPane.showMessageDialog(null, "输入的用户名或密码不正确!");
			}
		}
	}
}