package forms.trainplan;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.JTree;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeSelectionModel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeSelectionEvent;

import com.tool.WindowUtil;

import dto.department.DepartDTO;
import dto.user.UserDTO;
import forms.KeyValue;
import services.depart.Depart;
import services.trainingplan.TrainingPlan;
import services.user.User;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 安排学员主界面
 * 
 * @author 14713
 *
 */
@SuppressWarnings("serial")
public class Plan_emp extends JFrame implements TreeSelectionListener {

	private final JPanel contentPanel = new JPanel();
	private JTree myTreeDep;
	private String selectedDep;
	private JTable tblEmployee;
	private DefaultTableModel tableModel_1;
	private JTextField txtDep;
	private JTable tblEmployee_planed;
	private JScrollPane scrollPane_1;
	private DefaultTableModel tableModel_2;
	private JTextField txtMun;
	private JPanel panel_planed;
	private int rowCount;
	private String train_plan_id;

	public void setSelectedDep(String selectedDep) {
		this.selectedDep = selectedDep;
	}

	public String getSelectedDep() {
		return selectedDep;
	}

	public void getTrain_base(String train_plan_id) {
		this.train_plan_id = train_plan_id;
	}

	public void setTrain_plan_id(String train_plan_id) {
		this.train_plan_id = train_plan_id;
	}

	public String getTrain_plan_id() {
		return train_plan_id;
	}

	/**
	 * Create the frame.
	 */

	@SuppressWarnings("static-access")
	public Plan_emp() {
		setExtendedState(JFrame.MAXIMIZED_BOTH); // 设置窗口最大化
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 关闭当前窗口
		setTitle("\u90E8\u95E8\u7BA1\u7406");
		setBounds(100, 100, 860, 672);
		getContentPane().setLayout(new BorderLayout(0, 0));
		contentPanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null,
				null, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		JTree treeDep = new JTree(); // 新建树
		treeDep.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null,
				null, null));
		treeDep.setFont(new Font("楷体", Font.PLAIN, 16));
		myTreeDep = treeDep;
		treeDep.addTreeSelectionListener(this);
		treeDep.setVisibleRowCount(40);

		/*
		 * 初始部门树
		 */

		Depart departService = new Depart();
		DepartDTO depart = departService.getDepartById("001");
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(new KeyValue(
				depart.getUnit_name(), depart));

		List<DepartDTO> list = departService.getAllDeparts(); // 从数据库获取所有的部门信息储存在List中
		this.initDepartTree(list, "001", root); // 调用初始化部门树

		DefaultTreeModel model = new DefaultTreeModel(root);
		treeDep.setModel(model);
		treeDep.getSelectionModel().setSelectionMode(
				DefaultTreeSelectionModel.SINGLE_TREE_SELECTION);
		contentPanel.setLayout(new BorderLayout(0, 0));
		contentPanel.add(treeDep, BorderLayout.WEST);

		JPanel panel_plan = new JPanel();
		panel_plan.setBorder(new CompoundBorder());
		contentPanel.add(panel_plan, BorderLayout.CENTER);
		panel_plan.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel_plan.add(scrollPane);

		tblEmployee = new JTable(); // 创建一个表格
		scrollPane.setViewportView(tblEmployee);

		JPanel panelDep = new JPanel();
		panel_plan.add(panelDep, BorderLayout.NORTH);
		panelDep.setLayout(new BoxLayout(panelDep, BoxLayout.X_AXIS));

		JLabel labDep = new JLabel("\u90E8\u95E8");
		labDep.setHorizontalAlignment(SwingConstants.TRAILING);
		labDep.setVerticalAlignment(SwingConstants.TOP);
		labDep.setFont(new Font("楷体", Font.PLAIN, 18));
		panelDep.add(labDep);

		txtDep = new JTextField();
		txtDep.setHorizontalAlignment(SwingConstants.CENTER);
		txtDep.setFont(new Font("楷体", Font.PLAIN, 16));
		txtDep.setColumns(10);
		panelDep.add(txtDep);

		JButton btnSelete = new JButton("\u9009\u62E9");
		btnSelete.addActionListener(new ActionListener() { // 调用选择按钮监听事件
					public void actionPerformed(ActionEvent e) {
						do_btnSel_action(e);
					}
				});

		btnSelete.setFont(new Font("楷体", Font.PLAIN, 18));
		btnSelete.setHorizontalAlignment(SwingConstants.RIGHT);
		panelDep.add(btnSelete);

		JPanel panel = new JPanel();
		contentPanel.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));

		panel_planed = new JPanel();
		panel.add(panel_planed, BorderLayout.SOUTH);
		panel_planed.setLayout(new BorderLayout(0, 0));

		scrollPane_1 = new JScrollPane();
		panel_planed.add(scrollPane_1);

		tblEmployee_planed = new JTable(); // 创建另一个表格
		tblEmployee_planed.setShowGrid(true);
		scrollPane_1.setViewportView(tblEmployee_planed);

		JPanel panel_Mun = new JPanel();
		panel.add(panel_Mun);
		panel_Mun.setLayout(new BoxLayout(panel_Mun, BoxLayout.X_AXIS));

		JLabel lab_planed = new JLabel(
				"\u5DF2\u5B89\u6392\u7684\u4EBA\u6570     ");
		lab_planed.setFont(new Font("楷体", Font.PLAIN, 18));
		panel_Mun.add(lab_planed);

		txtMun = new JTextField();
		txtMun.setHorizontalAlignment(SwingConstants.CENTER);
		txtMun.setEditable(false);
		txtMun.setFont(new Font("楷体", Font.PLAIN, 16));
		txtMun.setColumns(10);
		panel_Mun.add(txtMun);

		JButton btnDelete = new JButton("\u5220\u9664");
		btnDelete.addActionListener(new ActionListener() { // 调用删除按钮监听事件
					public void actionPerformed(ActionEvent e) {
						do_btnDelete_action(e);
					}
				});
		btnDelete.setFont(new Font("楷体", Font.PLAIN, 18));
		panel_Mun.add(btnDelete);

		JButton btnSave = new JButton("\u4FDD\u5B58");
		btnSave.addActionListener(new ActionListener() { // 调用保存按钮监听事件
			public void actionPerformed(ActionEvent e) {
				do_btnSave_action(e);
			}
		});
		btnSave.setFont(new Font("楷体", Font.PLAIN, 18));
		panel_Mun.add(btnSave);

		JLabel lab_Employee = new JLabel("\u5B66\u5458\u5B89\u6392");
		lab_Employee.setVerticalAlignment(SwingConstants.TOP);
		lab_Employee.setHorizontalAlignment(SwingConstants.CENTER);
		lab_Employee.setFont(new Font("楷体", Font.PLAIN, 24));
		contentPanel.add(lab_Employee, BorderLayout.NORTH);

		do_tblEmployee_planed_windowActivated(null); // 调用表tblEmployee_planed的实现窗口动作的事件
		do_tblEmployee_windowActivated(); // 调用表tblEmployee的实现窗口动作的事件

		/**
		 * 设置UI感观及使窗口显示在屏幕正中央
		 */
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			SwingUtilities.updateComponentTreeUI(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		setLocation(WindowUtil.setLocation(this.getWidth(), this.getHeight()));
	}

	public static void initDepartTree(List<DepartDTO> list, String pid,
			DefaultMutableTreeNode parent) { // 初始化树
		for (DepartDTO depart : list) {
			if (depart.getUp_unit_id().equals(pid)) {
				DefaultMutableTreeNode other = new DefaultMutableTreeNode(
						new KeyValue(depart.getUnit_name(), depart));

				initDepartTree(list, depart.getUnit_id(), other);
				parent.add(other);
			}
		}
	}

	protected void do_btnDelete_action(ActionEvent e) { // 删除安排学员事件
		int index = tblEmployee_planed.getSelectedRow();
		if (index == -1) {
			JOptionPane.showMessageDialog(null, "请选择要删除的学员", "",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		tableModel_2.removeRow(tblEmployee_planed.getSelectedRow()); // 调用删除表的一行的事件
		rowCount = rowCount - 1;
		tblEmployee_planed.setModel(tableModel_2);
	}

	protected void do_btnSave_action(ActionEvent e) { // 安排学员事件
		boolean flag = false;
		for (int i = 0; i < tblEmployee_planed.getRowCount(); i++) {
			TrainingPlan trainingPlan = new TrainingPlan();
			flag = trainingPlan.saveTrain_plan(this.train_plan_id,
					(String) tblEmployee_planed.getValueAt(i, 0));
		}

		User user = new User();
		user.countStudent_sum(this.train_plan_id,
				(String) tblEmployee_planed.getValueAt(0, 0));

		if (flag) {
			JOptionPane.showMessageDialog(null, "保存成功");
		} else {
			JOptionPane.showMessageDialog(null, "保存失败");
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void do_tblEmployee_windowActivated() { // 表tblEmployee的窗口动作
		tblEmployee.setFont(new Font("楷体", Font.PLAIN, 16));
		tblEmployee.setRowHeight(30);
		tableModel_1 = (DefaultTableModel) tblEmployee.getModel(); // 获取表的一个Model

		RowSorter sorter = new TableRowSorter<DefaultTableModel>(tableModel_1); // 调用表的自动排序事件
		tblEmployee.setRowSorter(sorter);

		tableModel_1.setColumnIdentifiers(new Object[] { "全选", "序号", "姓名",
				"职务", "部门", "入厂时间", "岗位" }); // 创建表头
		JTableHeader header = tblEmployee.getTableHeader();
		header.setFont(new Font("楷体", Font.PLAIN, 16));
		header.setPreferredSize(new Dimension(header.getWidth(), 40));

		TableColumnModel columnModel = tblEmployee.getColumnModel(); // 设置表列的长度
		columnModel.getColumn(0).setPreferredWidth(50);
		columnModel.getColumn(1).setPreferredWidth(100);
		columnModel.getColumn(2).setPreferredWidth(100);

		List<UserDTO> allUsertInfo = new User().getAllusers(); // 从数据库加载部门信息显示在表中
		for (int i = 0; i < allUsertInfo.size(); i++) {
			UserDTO userDTO = allUsertInfo.get(i);
			tableModel_1.addRow(new Object[] { false, userDTO.getUser_id(),
					userDTO.getName(), userDTO.getDuty(),
					userDTO.getUnit_name() });
		}
		hide_TableColumn(); // 调用隐藏列的事件
		tblEmployee.setModel(tableModel_1);
	}

	private void hide_TableColumn() { // 隐藏列的事件
		TableColumn Column = tblEmployee.getColumnModel().getColumn(0);
		Column.setCellEditor(tblEmployee.getDefaultEditor(Boolean.class));
		Column.setCellRenderer(tblEmployee.getDefaultRenderer(Boolean.class));
		TableColumn Column_1 = tblEmployee.getColumnModel().getColumn(1);
		Column_1.setMinWidth(0);
		Column_1.setMaxWidth(0);
		TableColumn Column_3 = tblEmployee.getColumnModel().getColumn(4);
		Column_3.setMinWidth(0);
		Column_3.setMaxWidth(0);
	}

	private void do_tblEmployee_planed_windowActivated(List<Object[]> list2) { // 表tblEmployee_planed的窗口动作事件
		tblEmployee_planed.setFont(new Font("楷体", Font.PLAIN, 16));
		tblEmployee_planed.setRowHeight(30);
		tableModel_2 = (DefaultTableModel) tblEmployee_planed.getModel(); // 获取表的一个Model
		tableModel_2.setRowCount(tblEmployee_planed.getRowCount());
		tableModel_2.setColumnIdentifiers(new Object[] { "学员编号", "姓名", "部门" });    //创建表头
		JTableHeader header = tblEmployee_planed.getTableHeader();
		header.setFont(new Font("楷体", Font.PLAIN, 16));
		header.setPreferredSize(new Dimension(header.getWidth(), 40));
		TableColumn aColumn_2 = tblEmployee_planed.getColumnModel()
				.getColumn(0);
		aColumn_2.setMinWidth(0);
		aColumn_2.setMaxWidth(0);
		if (list2 != null) {
			for (Object[] model : list2) {
				tableModel_2.addRow(model);
			}
		}
		tableModel_2.addTableModelListener(new TableModelListener() {          //监听表的改变事件
			public void tableChanged(TableModelEvent e) {
				for (int i = 0; i < tblEmployee_planed.getRowCount(); i++) {
					for (int j = i + 1; j < tblEmployee_planed.getRowCount(); j++) {
						if (tblEmployee_planed.getValueAt(i, 0).equals(
								tblEmployee_planed.getValueAt(j, 0)))
							tableModel_2.removeRow(i);
					}
				}
				txtMun.setText(tblEmployee_planed.getRowCount() + "");
			}
		});
	}

	protected void do_btnSel_action(ActionEvent e) {       //安排学员事件
		/**
		 * 对象Object装换为boolean型
		 */
		List<Object[]> list = new ArrayList<Object[]>();
		for (int i = 0; i < tblEmployee.getRowCount(); i++) {
			if (Boolean.parseBoolean( (String) tblEmployee.getValueAt(i, 0)) == true) {
				Object[] data = new Object[3];
				data[0] = tblEmployee.getValueAt(i, 1);
				data[1] = tblEmployee.getValueAt(i, 2);
				data[2] = tblEmployee.getValueAt(i, 4);
				list.add(data);
			}
		}
		do_tblEmployee_planed_windowActivated(list);
	}

	public void valueChanged(TreeSelectionEvent e) {              //监听树值改变的事件
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) myTreeDep
				.getLastSelectedPathComponent();

		if (node == null)
			return;

		selectedDep = ((KeyValue) node.getUserObject()).getName();
		txtDep.setText(selectedDep);
		do_Updata_windowActivated(selectedDep);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void do_Updata_windowActivated(String selectedDep) {
		tableModel_1.setRowCount(0);
		tableModel_1 = (DefaultTableModel) tblEmployee.getModel();

		RowSorter sorter = new TableRowSorter<DefaultTableModel>(tableModel_1);
		tblEmployee.setRowSorter(sorter);

		tableModel_1.setColumnIdentifiers(new Object[] { "全选", "序号", "姓名",
				"职务", "部门", "入厂时间", "岗位" });
		JTableHeader header = tblEmployee.getTableHeader();
		header.setFont(new Font("楷体", Font.PLAIN, 16));
		header.setPreferredSize(new Dimension(header.getWidth(), 40));

		TableColumnModel columnModel = tblEmployee.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(50);
		columnModel.getColumn(1).setPreferredWidth(100);
		List<UserDTO> allUsertInfo = new User()
				.getAllusersBydepart(selectedDep); // 从数据库加载部门信息显示在表中
		for (int i = 0; i < allUsertInfo.size(); i++) {
			UserDTO userDTO = allUsertInfo.get(i);
			tableModel_1.addRow(new Object[] { false, userDTO.getUser_id(),
					userDTO.getName(), userDTO.getDuty(),
					userDTO.getUnit_name() });
		}
		hide_TableColumn();
		tblEmployee.setModel(tableModel_1);
	}

	public static void main(String[] args) {
		new Plan_emp().setVisible(true);
	}
}
