package forms.depart;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.JTree;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeSelectionModel;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeSelectionEvent;

import com.tool.WindowUtil;

import dto.department.DepartDTO;
import forms.KeyValue;
import services.depart.Depart;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

import java.awt.Color;

import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JToolBar;
import javax.swing.border.CompoundBorder;

/**
 * 
 * @author 14713 部门管理界面，包括部门的增加，删除，修改。
 */
@SuppressWarnings("serial")
public class DepartMainForm extends JInternalFrame implements TreeSelectionListener {

	private final JPanel contentPanel = new JPanel();
	private JTree myTreeDep;
	// private JTextField txtSelectedDep;
	private String selectedDep;
	private JTable tblEmployee;
	private int rowCount;
	private DefaultTableModel tableModel;
	private DepartDTO departDTO = new DepartDTO();

	public String getSelectedDep() {
		return selectedDep;
	}

	public void setSelectedDep(String selectedDep) {
		this.selectedDep = selectedDep;
	}

	public static void initDepartTree(List<DepartDTO> list, String pid,
			DefaultMutableTreeNode parent) {
		for (DepartDTO depart : list) {
			if (depart.getUp_unit_id().equals(pid)) {
				DefaultMutableTreeNode other = new DefaultMutableTreeNode(
						new KeyValue(depart.getUnit_name(), depart));

				initDepartTree(list, depart.getUnit_id(), other);
				parent.add(other);
			}
		}

	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "static-access", "rawtypes", "unchecked" })
	public DepartMainForm(JTextField txtSelectedDep) {
		setResizable(false);
		setTitle("\u90E8\u95E8\u7BA1\u7406");
		setBounds(100, 100, 730, 427);
		getContentPane().setLayout(new BorderLayout(0, 0));
		contentPanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null,
				null, null, null));
		getContentPane().add(contentPanel);

		JTree treeDep = new JTree();
		treeDep.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		treeDep.setFont(new Font("楷体", Font.PLAIN, 16));
		myTreeDep = treeDep;
		treeDep.addTreeSelectionListener(this);
		treeDep.setVisibleRowCount(40);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			SwingUtilities.updateComponentTreeUI(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		setLocation(WindowUtil.setLocation(this.getWidth(), this.getHeight()));
		/*
		 * 初始部门树
		 */
		Depart departService = new Depart();
		DepartDTO depart = departService.getDepartById("001");
		// System.out.println("*****"+depart.getUnit_name());
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(new KeyValue(
				depart.getUnit_name(), depart));

		List<DepartDTO> list = departService.getAllDeparts();
		this.initDepartTree(list, "001", root);

		DefaultTreeModel model = new DefaultTreeModel(root);
		treeDep.setModel(model);
		treeDep.getSelectionModel().setSelectionMode(
				DefaultTreeSelectionModel.SINGLE_TREE_SELECTION);
		contentPanel.setLayout(new BorderLayout(0, 0));

		contentPanel.add(treeDep, BorderLayout.WEST);

		JPanel panel = new JPanel();
		panel.setBorder(new CompoundBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u90E8\u95E8\u4FE1\u606F", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), new BevelBorder(BevelBorder.LOWERED, null, null, null, null)));
		contentPanel.add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);
		tblEmployee = new JTable();
		tblEmployee.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String unit_id = (String) tblEmployee.getValueAt(
						tblEmployee.getSelectedRow(), 2);
				departDTO.setUnit_id(unit_id);
			}
		});
		List<DepartDTO> allDepartInfo = new Depart().getAllDeparts();   //从数据库加载部门信息显示在表中
		tblEmployee.setFont(new Font("楷体", Font.PLAIN, 16));
		tableModel = (DefaultTableModel) tblEmployee.getModel();
		tableModel.setColumnIdentifiers(new Object[] { "序号", "部门名称", "部门编号",
				"部门简称", "联系方式", "备注" });
		for (int i = 0; i < allDepartInfo.size(); i++) {
			DepartDTO departDTO = allDepartInfo.get(i);
			tableModel.addRow(new Object[] { i + 1, departDTO.getUnit_name(),
					departDTO.getUnit_id(), departDTO.getHeader(),
					departDTO.getTelephone(), departDTO.getRemark() });
		}
		rowCount = tableModel.getRowCount();
		tableModel.setRowCount(rowCount);
		RowSorter sorter = new TableRowSorter<DefaultTableModel>(tableModel);
		tblEmployee.setRowHeight(30);
		tblEmployee.setRowSorter(sorter);
		JTableHeader header = tblEmployee.getTableHeader();
		header.setFont(new Font("楷体", Font.PLAIN, 16));
		header.setPreferredSize(new Dimension(header.getWidth(), 40));
		TableColumnModel columnModel = tblEmployee.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(50);
		columnModel.getColumn(1).setPreferredWidth(100);
		columnModel.getColumn(2).setPreferredWidth(100);
		tblEmployee.setModel(tableModel);
		scrollPane.setViewportView(tblEmployee);

		JToolBar toolBar = new JToolBar();
		toolBar.setFont(new Font("楷体", Font.PLAIN, 18));
		toolBar.setForeground(Color.BLACK);
		contentPanel.add(toolBar, BorderLayout.NORTH);

		JButton Addbutton = new JButton("\u6DFB\u52A0");
		Addbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//DepartMainForm.this.setVisible(false);
				AddDepart addDepart = new AddDepart();
				addDepart.setVisible(true);
			}
		});
		Addbutton.setFont(new Font("楷体", Font.PLAIN, 18));
		toolBar.add(Addbutton);

		JButton Deletebutton = new JButton("\u5220\u9664");
		Deletebutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_button_1_action(e);
			}
		});
		Deletebutton.setFont(new Font("楷体", Font.PLAIN, 18));
		toolBar.add(Deletebutton);

		JButton Alterbutton = new JButton("\u4FEE\u6539");
		Alterbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_button_2_action(e);
			}
		});
		Alterbutton.setFont(new Font("楷体", Font.PLAIN, 18));
		toolBar.add(Alterbutton);
		
	}

	protected void do_button_2_action(ActionEvent e) {                    //修改一行部门信息
		int index = tblEmployee.getSelectedRow();
		if (index == -1) {
			JOptionPane.showMessageDialog(this, "请选择要修改的行", "",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		index++;
		int type = JOptionPane.showConfirmDialog(null, "您确定要修改第" + index
				+ " 行吗？", "确认修改", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);

		if (type == JOptionPane.YES_OPTION) {
			rowCount = rowCount - 1;
			List<DepartDTO> allDepartInfo = new Depart()
					.getDepartByIds(departDTO);
			AlterDepart alterDepart = new AlterDepart();
			alterDepart.alterDepart(allDepartInfo);
			alterDepart.setVisible(true);
		}

	}

	protected void do_button_1_action(ActionEvent e) {                    //删除一行部门信息
		DefaultTableModel model = (DefaultTableModel) tblEmployee.getModel();
		int index = tblEmployee.getSelectedRow();
		if (index == -1) {
			JOptionPane.showMessageDialog(this, "请选择要删除的行", "",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		index++;
		int type = JOptionPane.showConfirmDialog(null, "您将要删除第" + index
				+ " 行！删除后数据将不可恢复，你真的要删除吗？", "确认删除", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);

		if (type == JOptionPane.YES_OPTION) {
			model.removeRow(tblEmployee.getSelectedRow());
			rowCount = rowCount - 1;
			boolean result = new Depart().deleteDepart(departDTO);
			if (result) {
				JOptionPane.showMessageDialog(null, "删除成功");
			} else {
				JOptionPane.showMessageDialog(null, "删除失败");
			}
		}

	}

	public void valueChanged(TreeSelectionEvent e) {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) myTreeDep
				.getLastSelectedPathComponent();

		if (node == null)
			return;

		selectedDep = ((KeyValue) node.getUserObject()).getName();
		// System.out.println(selectedDep);
	}
}
