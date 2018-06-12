package forms.employee;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JToolBar;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.RowSorter;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

import dto.user.UserDTO;
import services.user.User;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.util.Vector;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

@SuppressWarnings("serial")
public class EmployeeMainForm extends JInternalFrame {
	private JTextField txtEmployeeName;
	private JTable tblEmployee;
	private String sex;
	private int rowCount;
	private UserDTO userDTO = new UserDTO();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeMainForm frame = new EmployeeMainForm();
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public EmployeeMainForm() {
		setClosable(true);
		setBounds(100, 100, 978, 679);

		JToolBar toolBar = new JToolBar();
		getContentPane().add(toolBar, BorderLayout.NORTH);

		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		toolBar.add(panel_1);

		JLabel lblNewLabel = new JLabel("\u59D3\u540D\uFF1A");
		lblNewLabel.setFont(new Font("楷体", Font.PLAIN, 16));
		panel_1.add(lblNewLabel);

		txtEmployeeName = new JTextField();
		panel_1.add(txtEmployeeName);
		txtEmployeeName.setHorizontalAlignment(SwingConstants.LEFT);
		txtEmployeeName.setColumns(20);

		JLabel lblNewLabel_1 = new JLabel("\u6027\u522B\uFF1A");
		lblNewLabel_1.setFont(new Font("楷体", Font.PLAIN, 16));
		panel_1.add(lblNewLabel_1);

		final JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("楷体", Font.PLAIN, 16));

		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedItem().equals("男")) {
					sex = "男";
				} else {
					sex = "女";
				}
			}
		});
		panel_1.add(comboBox);
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "\u7537",
				"\u5973" }));

		JButton btnNewButton = new JButton("\u67E5\u8BE2");
		btnNewButton.setFont(new Font("楷体", Font.PLAIN, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnNewButton_action(e);
			}
		});
		panel_1.add(btnNewButton);

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);

		tblEmployee = new JTable();
		tblEmployee.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String id = (String) tblEmployee.getValueAt(
						tblEmployee.getSelectedRow(), 0);
				userDTO.setUser_id(id);
			}
		});
		Vector<Vector<Object>> tableDatas = new User().getAllUsers();
		Vector<String> colums = new Vector<String>();
		tblEmployee.setFont(new Font("楷体", Font.PLAIN, 16));
		colums.add("ID");
		colums.add("部门");
		colums.add("姓名");
		colums.add("出生日期");
		colums.add("身份证");
		colums.add("职务");
		DefaultTableModel tableModel = new DefaultTableModel(tableDatas, colums);
		rowCount = tableModel.getRowCount();
		tableModel.setRowCount(rowCount);
		RowSorter sorter = new TableRowSorter<DefaultTableModel>(tableModel);
		tblEmployee.setRowHeight(30);
		tblEmployee.setRowSorter(sorter);
		tblEmployee.setModel(tableModel);
		JTableHeader header = tblEmployee.getTableHeader();
		header.setFont(new Font("楷体", Font.PLAIN, 16));
		header.setPreferredSize(new Dimension(header.getWidth(),35));
		TableColumnModel columnModel = tblEmployee.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(100);
		columnModel.getColumn(1).setPreferredWidth(50);
		columnModel.getColumn(2).setPreferredWidth(50);
		columnModel.getColumn(4).setPreferredWidth(100);
		scrollPane.setViewportView(tblEmployee);
		JToolBar toolBar_1 = new JToolBar();
		panel.add(toolBar_1, BorderLayout.NORTH);

		JButton btnNewButton_1 = new JButton("\u6DFB\u52A0");
		btnNewButton_1.setFont(new Font("楷体", Font.PLAIN, 16));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddEmployee addEmp = new AddEmployee();
				addEmp.setVisible(true);
			}
		});
		toolBar_1.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("\u5220\u9664");
		btnNewButton_2.setFont(new Font("楷体", Font.PLAIN, 16));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnNewButton_2_action(e);
			}
		});
		toolBar_1.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("\u4FEE\u6539");
		btnNewButton_3.setFont(new Font("楷体", Font.PLAIN, 16));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnNewButton_3_action(e);
			}
		});
		btnNewButton_3.setHorizontalAlignment(SwingConstants.LEFT);
		toolBar_1.add(btnNewButton_3);

	}

	protected void do_btnNewButton_3_action(ActionEvent e) {
		//DefaultTableModel model = (DefaultTableModel) tblEmployee.getModel();
		int index = tblEmployee.getSelectedRow();
		if (index == -1) {
			JOptionPane.showMessageDialog(this, "请选择要修改的行", "",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		
	
		index++;
		int type = JOptionPane.showConfirmDialog(null,
				"您确定要修改第" + index
						+ " 行吗？", "确认修改",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

		if (type == JOptionPane.YES_OPTION) {
			rowCount = rowCount - 1;
			Vector<Vector<Object>> tableDatas = new User().getalterUsers(userDTO);
			AlterEmployee alterEmployee=new AlterEmployee();
			alterEmployee.alteremployee(tableDatas, index);
			alterEmployee.setVisible(true);
		}
		

	}

	protected void do_btnNewButton_2_action(ActionEvent e) {
		DefaultTableModel model = (DefaultTableModel) tblEmployee.getModel();
		int index = tblEmployee.getSelectedRow();
		if (index == -1) {
			JOptionPane.showMessageDialog(this, "请选择要删除的行", "",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		index++;
		int type = JOptionPane.showConfirmDialog(null,
				"您将要删除第" + index
						+ " 行！删除后数据将不可恢复，你真的要删除吗？", "确认删除",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

		if (type == JOptionPane.YES_OPTION) {
			model.removeRow(tblEmployee.getSelectedRow());
			rowCount = rowCount - 1;
			boolean result = new User().deleteUser(userDTO);
			if (result) {
				JOptionPane.showMessageDialog(null, "删除成功");
			} else {
				JOptionPane.showMessageDialog(null, "删除失败");
			}
		}

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void do_btnNewButton_action(ActionEvent e) {
		UserDTO userDTO = new UserDTO();
		userDTO.setName(txtEmployeeName.getText());
		userDTO.setSex(sex);
		Vector<Vector<Object>> tableDatas = new User().getQueryUsers(userDTO);
		Vector<String> colums = new Vector<String>();
		colums.add("id");
		colums.add("部门");
		colums.add("姓名");
		colums.add("出生日期");
		colums.add("身份证");
		colums.add("职务");
		DefaultTableModel tableModel = new DefaultTableModel(tableDatas, colums);
		RowSorter sorter = new TableRowSorter<DefaultTableModel>(tableModel);
		tblEmployee.setRowSorter(sorter);
		tblEmployee.setModel(tableModel);
	}

}
