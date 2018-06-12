package forms.depart;

import com.tool.WindowUtil;
import dto.department.DepartDTO;
import services.depart.Depart;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 
 * @author 14713 ��Ӳ�����Ϣ����
 */
@SuppressWarnings("serial")
public class AddDepart extends JFrame {

	private JPanel contentPane;
	private JTextField txtU_unit;
	private JTextField txtU_unit_id;
	private JTextField txtUnit;
	private JTextField txtUnit_id;
	private JTextField txtHeader;
	private JTextArea txtRemark;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddDepart frame = new AddDepart();
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
	public AddDepart() {
		setTitle("\u6DFB\u52A0\u90E8\u95E8\u4FE1\u606F");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 540, 422);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JLabel Add_UnitLabel = new JLabel(
				"\u6DFB\u52A0\u90E8\u95E8\u4FE1\u606F");
		Add_UnitLabel.setForeground(Color.BLUE);
		Add_UnitLabel.setFont(new Font("����", Font.PLAIN, 26));
		Add_UnitLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(Add_UnitLabel, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		panel.setBorder(new CompoundBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u90E8\u95E8\u4FE1\u606F", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), new EtchedBorder(EtchedBorder.LOWERED, null, null)));
		panel.setBackground(new Color(240, 248, 255));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel U_UnitLabel = new JLabel("\u4E0A\u7EA7\u90E8\u95E8");
		U_UnitLabel.setFont(new Font("����", Font.PLAIN, 16));
		U_UnitLabel.setBounds(47, 39, 69, 28);
		panel.add(U_UnitLabel);

		txtU_unit = new JTextField();
		txtU_unit.setEnabled(false);
		txtU_unit.addMouseListener(new MouseAdapter() { // ����������������ı���textField��ʱ�����ѡ���ŵĽ��棬���ݽ����ϵĲ��ţ�ѡ����
					@SuppressWarnings("unused")
					@Override
					public void mouseClicked(MouseEvent e) {
						String selectedDep;
						SelectDepForm_1 selectDepForm = new SelectDepForm_1(
								txtU_unit, txtU_unit_id, txtUnit_id);
						selectDepForm.setVisible(true);
					}
				});
		txtU_unit.setBounds(126, 41, 126, 28);
		panel.add(txtU_unit);
		txtU_unit.setColumns(20);

		JLabel U_unit_idlabel = new JLabel(
				"\u4E0A\u7EA7\u90E8\u95E8\u7F16\u53F7");
		U_unit_idlabel.setFont(new Font("����", Font.PLAIN, 16));
		U_unit_idlabel.setBounds(268, 38, 96, 31);
		panel.add(U_unit_idlabel);

		txtU_unit_id = new JTextField();
		txtU_unit_id.setEnabled(false);
		txtU_unit_id.setColumns(20);
		txtU_unit_id.setBounds(374, 41, 120, 28);
		panel.add(txtU_unit_id);

		JLabel unitlabel = new JLabel("\u90E8\u95E8\u540D\u79F0");
		unitlabel.setFont(new Font("����", Font.PLAIN, 16));
		unitlabel.setBounds(45, 108, 113, 31);
		panel.add(unitlabel);

		txtUnit = new JTextField();
		txtUnit.setColumns(20);
		txtUnit.setBounds(126, 111, 126, 28);
		panel.add(txtUnit);

		JLabel Unit_idlabel = new JLabel("\u90E8\u95E8\u7F16\u53F7");
		Unit_idlabel.setFont(new Font("����", Font.PLAIN, 16));
		Unit_idlabel.setBounds(269, 108, 69, 31);
		panel.add(Unit_idlabel);

		JLabel Hlabel = new JLabel("\u90E8\u95E8\u7B80\u79F0");
		Hlabel.setFont(new Font("����", Font.PLAIN, 16));
		Hlabel.setBounds(45, 169, 71, 31);
		panel.add(Hlabel);

		JLabel Rlabel = new JLabel("\u5907\u6CE8");
		Rlabel.setFont(new Font("����", Font.PLAIN, 18));
		Rlabel.setBounds(240, 217, 52, 31);
		panel.add(Rlabel);

		txtUnit_id = new JTextField();
		txtUnit_id.setColumns(20);
		txtUnit_id.setBounds(374, 111, 120, 28);
		panel.add(txtUnit_id);

		txtHeader = new JTextField();
		txtHeader.setColumns(20);
		txtHeader.setBounds(126, 172, 126, 28);
		panel.add(txtHeader);

		txtRemark = new JTextArea();
		txtRemark.setFont(new Font("����", Font.PLAIN, 16));
		txtRemark.setBounds(291, 169, 203, 102);
		panel.add(txtRemark);

		JButton SaveButton = new JButton("\u4FDD\u5B58");
		SaveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnNewButton_action(e);
			}
		});
		SaveButton.setFont(new Font("����", Font.PLAIN, 16));
		SaveButton.setBounds(126, 283, 93, 38);
		panel.add(SaveButton);

		JButton Exitbutton = new JButton("\u9000\u51FA");
		Exitbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddDepart.this.setVisible(false);
				DepartMainForm mainDepart = new DepartMainForm(null);
				mainDepart.setVisible(true);
			}
		});
		Exitbutton.setFont(new Font("����", Font.PLAIN, 16));
		Exitbutton.setBounds(289, 283, 93, 38);
		panel.add(Exitbutton);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(AddDepart.class.getResource("/images/background.jpg")));
		lblNewLabel.setBounds(-16, -37, 544, 392);
		panel.add(lblNewLabel);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			SwingUtilities.updateComponentTreeUI(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		setLocation(WindowUtil.setLocation(this.getWidth(), this.getHeight()));
	}

	protected void do_btnNewButton_action(ActionEvent e) { // ������Ϣ���
		if(txtU_unit.getText().length()==0){
			JOptionPane.showMessageDialog(null, "�ϼ����Ų�����Ϊ��");
			return;
		}
		if(txtU_unit_id.getText().length()==0){
			JOptionPane.showMessageDialog(null, "�ϼ����ű�Ų�����Ϊ��");
			return;
		}
		if(txtUnit.getText().length()==0){
			JOptionPane.showMessageDialog(null, "���Ų�����Ϊ��");
			return;
		}
		if(txtUnit_id.getText().length()==0){
			JOptionPane.showMessageDialog(null, "���ű�Ų�����Ϊ��");
			return;
		}
		if(txtHeader.getText().length()==0){
			JOptionPane.showMessageDialog(null, "���ż�Ʋ�����Ϊ��");
			return;
		}
		Depart depart = new Depart();
		DepartDTO departDTO = new DepartDTO();
		departDTO.setUnit_id(txtU_unit_id.getText()+txtUnit_id.getText());
		departDTO.setUp_unit_id(txtU_unit_id.getText());
		departDTO.setUnit_name(txtUnit.getText());
		departDTO.setHeader(txtHeader.getText());
		departDTO.setRemark(txtRemark.getText());
		boolean flag = depart.getDepart(departDTO);
		if (flag) {
			JOptionPane.showMessageDialog(null, "��ӳɹ�");
		} else {
			JOptionPane.showMessageDialog(null, "���ʧ��");
		}

	}
}
