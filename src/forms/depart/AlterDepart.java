package forms.depart;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import services.depart.Depart;

import com.tool.WindowUtil;

import dto.department.DepartDTO;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.ImageIcon;
/**
 * 
 * @author 14713
 * 修改部门信息界面
 */
@SuppressWarnings("serial")
public class AlterDepart extends JFrame {

	private JPanel contentPane;
	private JTextField txtUu_Id;
	private JTextField txtUnit;
	private JTextField txtU_Id;
	private JTextField txtHeader;
	private JTextArea txtremark;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AlterDepart frame = new AlterDepart();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AlterDepart() {
		setTitle("\u4FEE\u6539\u90E8\u95E8\u4FE1\u606F");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 540, 422);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JLabel Alter_UnitLabel = new JLabel("\u4FEE\u6539\u90E8\u95E8\u4FE1\u606F");
		Alter_UnitLabel.setForeground(Color.BLUE);
		Alter_UnitLabel.setFont(new Font("楷体", Font.PLAIN, 26));
		Alter_UnitLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(Alter_UnitLabel, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		panel.setBorder(new CompoundBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u90E8\u95E8\u4FE1\u606F", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), new EtchedBorder(EtchedBorder.LOWERED, null, null)));
		panel.setBackground(new Color(240, 248, 255));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel Uu_Idlabel = new JLabel("\u4E0A\u7EA7\u90E8\u95E8\u7F16\u53F7");
		Uu_Idlabel.setFont(new Font("楷体", Font.PLAIN, 16));
		Uu_Idlabel.setBounds(26, 77, 106, 31);
		panel.add(Uu_Idlabel);

		txtUu_Id = new JTextField();
		txtUu_Id.setFont(new Font("楷体", Font.PLAIN, 16));
		txtUu_Id.setEnabled(false);
		txtUu_Id.setColumns(20);
		txtUu_Id.setBounds(131, 80, 113, 28);
		panel.add(txtUu_Id);

		JLabel Unitlabel = new JLabel("\u90E8\u95E8\u540D\u79F0");
		Unitlabel.setFont(new Font("楷体", Font.PLAIN, 16));
		Unitlabel.setBounds(269, 77, 64, 31);
		panel.add(Unitlabel);

		txtUnit = new JTextField();
		txtUnit.setFont(new Font("楷体", Font.PLAIN, 16));
		txtUnit.setColumns(20);
		txtUnit.setBounds(353, 80, 118, 28);
		panel.add(txtUnit);

		JLabel U_Idlabel = new JLabel("\u90E8\u95E8\u7F16\u53F7");
		U_Idlabel.setFont(new Font("楷体", Font.PLAIN, 16));
		U_Idlabel.setBounds(26, 131, 113, 31);
		panel.add(U_Idlabel);

		JLabel Headerlabel = new JLabel("\u90E8\u95E8\u7B80\u79F0");
		Headerlabel.setFont(new Font("楷体", Font.PLAIN, 16));
		Headerlabel.setBounds(269, 131, 64, 31);
		panel.add(Headerlabel);

		JLabel Re_label = new JLabel("\u5907\u6CE8");
		Re_label.setFont(new Font("楷体", Font.PLAIN, 18));
		Re_label.setBounds(131, 204, 52, 31);
		panel.add(Re_label);

		txtU_Id = new JTextField();
		txtU_Id.setFont(new Font("楷体", Font.PLAIN, 16));
		txtU_Id.setEditable(false);
		
		txtU_Id.setColumns(20);
		txtU_Id.setBounds(131, 134, 113, 28);
		panel.add(txtU_Id);

		txtHeader = new JTextField();
		txtHeader.setFont(new Font("楷体", Font.PLAIN, 16));
		txtHeader.setColumns(20);
		txtHeader.setBounds(353, 134, 118, 28);
		panel.add(txtHeader);

		txtremark = new JTextArea();
		txtremark.setFont(new Font("Monospaced", Font.PLAIN, 16));
		txtremark.setBounds(208, 187, 263, 76);
		panel.add(txtremark);

		JButton AlterButton = new JButton("\u4FEE\u6539");
		AlterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnNewButton_action(e);
			}
		});
		AlterButton.setFont(new Font("楷体", Font.PLAIN, 16));
		AlterButton.setBounds(131, 288, 93, 31);
		panel.add(AlterButton);

		JButton Exitbutton = new JButton("\u9000\u51FA");
		Exitbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AlterDepart.this.setVisible(false);
				DepartMainForm mainDepart=new DepartMainForm(null);
				mainDepart.setVisible(true);
			}
		});
		Exitbutton.setFont(new Font("楷体", Font.PLAIN, 16));
		Exitbutton.setBounds(289, 288, 93, 31);
		panel.add(Exitbutton);
		
		JLabel BgLabel = new JLabel("New label");
		BgLabel.setIcon(new ImageIcon(AlterDepart.class.getResource("/images/background.jpg")));
		BgLabel.setBounds(-12, -67, 540, 423);
		panel.add(BgLabel);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			SwingUtilities.updateComponentTreeUI(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		setLocation(WindowUtil.setLocation(this.getWidth(), this.getHeight()));
	}

	protected void do_btnNewButton_action(ActionEvent e) {
		if(txtUu_Id.getText().length()==0){
			JOptionPane.showMessageDialog(null, "上级部门编号不可以为空");
			return;
		}
		if(txtUnit.getText().length()==0){
			JOptionPane.showMessageDialog(null, "部门不可以为空");
			return;
		}
		if(txtU_Id.getText().length()==0){
			JOptionPane.showMessageDialog(null, "部门编号不可以为空");
			return;
		}
		if(txtHeader.getText().length()==0){
			JOptionPane.showMessageDialog(null, "部门简称不可以为空");
			return;
		}
		Depart depart = new Depart();
		DepartDTO departDTO = new DepartDTO();
		departDTO.setUnit_id(txtUu_Id.getText()+txtU_Id.getText());
		departDTO.setUp_unit_id(txtUu_Id.getText());
		departDTO.setUnit_name(txtUnit.getText());
		departDTO.setHeader(txtHeader.getText());
		departDTO.setRemark(txtremark.getText());
		boolean flag = depart.updateDepart(departDTO);
		if (flag) {
			JOptionPane.showMessageDialog(null, "修改成功");
		} else {
			JOptionPane.showMessageDialog(null, "修改失败");
		}

	}

	public void alterDepart(List<DepartDTO> allDepartInfo) {
		for (int i = 0; i < allDepartInfo.size(); i++) {
			DepartDTO departDTO = allDepartInfo.get(i);
			txtU_Id.setText(departDTO.getUnit_id());
			txtHeader.setText( departDTO.getHeader());
			txtUu_Id.setText(departDTO.getUp_unit_id());
			txtUnit.setText(departDTO.getUnit_name());
			txtremark.setText(departDTO.getRemark());
		}
	}
}
