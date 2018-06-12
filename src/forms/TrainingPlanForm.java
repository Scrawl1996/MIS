package forms;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JToolBar;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.RowSorter;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JPanel;

import services.trainingplan.TrainingPlan;

import java.util.Vector;
import java.awt.Font;

import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

import dto.Trainingplan.TrainingPlanDTO;
import forms.trainplan.TrainingPlan_base;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class TrainingPlanForm extends JInternalFrame {
	private JTable tblEmployee;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TrainingPlanForm frame = new TrainingPlanForm();
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
	public TrainingPlanForm() {
		super();
		setClosable(true);
		setBounds(100, 100, 978, 679);

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);

		tblEmployee = new JTable();
		Vector<Vector<Object>> tableDatas = new TrainingPlan()
				.getAllTrainingPlans();

		Vector<String> colums = new Vector<String>();
		colums.add("序号");
		colums.add("培训编号");
		colums.add("年度");
		colums.add("培训计划名称");
		colums.add("培训类型");
		colums.add("培训专业");
		colums.add("培训时间");
		colums.add("完成情况");
		colums.add("培训人数");

		DefaultTableModel tableModel = new DefaultTableModel(tableDatas, colums);
		
		tblEmployee = new JTable(tableModel) {                          //将表设置成不可编辑
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tblEmployee.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1
						&& (e.getClickCount() == 2)) {
					TrainingPlanDTO trainingPlanDTO = new TrainingPlanDTO();
					String id = tblEmployee.getValueAt(
							tblEmployee.getSelectedRow(), 1).toString();
					trainingPlanDTO.setTrain_plan_id(id);
					TrainingPlanForm.this.setVisible(false);
					int index = tblEmployee.getSelectedRow();
					Vector<Vector<Object>> tableDatas = new TrainingPlan()
							.getBaseTrainingPlans(trainingPlanDTO);
					TrainingPlan_base trainingPlan_base = new TrainingPlan_base();
					trainingPlan_base.base_info(tableDatas, index);
					trainingPlan_base.setVisible(true);
				}
			}
		});
		tblEmployee.setFont(new Font("楷体", Font.PLAIN, 16));
		RowSorter sorter = new TableRowSorter<DefaultTableModel>(tableModel);
		tblEmployee.setRowHeight(30);
		tblEmployee.setRowSorter(sorter);
		tblEmployee.setModel(tableModel);

		scrollPane.setViewportView(tblEmployee);
		JTableHeader header = tblEmployee.getTableHeader();
		header.setFont(new Font("楷体", Font.PLAIN, 16));
		header.setPreferredSize(new Dimension(header.getWidth(), 40));
		TableColumn aColumn = tblEmployee.getColumnModel().getColumn(1);
		aColumn.setMinWidth(0);
		aColumn.setMaxWidth(0);
		TableColumnModel columnModel = tblEmployee.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(50);
		columnModel.getColumn(2).setPreferredWidth(50);
		columnModel.getColumn(3).setPreferredWidth(100);
		columnModel.getColumn(4).setPreferredWidth(100);
		columnModel.getColumn(5).setPreferredWidth(150);
		// scrollPane.setColumnHeaderView(tblEmployee);

		JToolBar toolBar_1 = new JToolBar();
		panel.add(toolBar_1, BorderLayout.NORTH);

		JPanel panel_1 = new JPanel();
		panel_1.setToolTipText("wwfd");
		panel_1.setBorder(new TitledBorder(UIManager

		.getBorder("TitledBorder.border"), "\u67E5\u8BE2\u6761\u4EF6",

		TitledBorder.LEADING, TitledBorder.TOP, null, null));
		toolBar_1.add(panel_1);

		JLabel label = new JLabel("\u57F9\u8BAD\u8BA1\u5212\u5E74\u5EA6\uFF1A");
		label.setFont(new Font("楷体", Font.PLAIN, 16));
		panel_1.add(label);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "2015",
				"2016", "2017" }));
		comboBox.setFont(new Font("楷体", Font.PLAIN, 16));
		panel_1.add(comboBox);

		JLabel label_1 = new JLabel("\u57F9\u8BAD\u4E13\u4E1A\uFF1A");
		label_1.setFont(new Font("楷体", Font.PLAIN, 16));
		panel_1.add(label_1);

		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {
				"\u7535\u6C14", "\u6C7D\u673A", "\u9505\u7089" }));
		comboBox_1.setFont(new Font("楷体", Font.PLAIN, 16));
		panel_1.add(comboBox_1);

		JLabel label_2 = new JLabel(
				"\u57F9\u8BAD\u8BA1\u5212\u7C7B\u578B\uFF1A");
		label_2.setFont(new Font("楷体", Font.PLAIN, 16));
		panel_1.add(label_2);

		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {
				"\u65B0\u5458\u5DE5\u5165\u5382\u57F9\u8BAD",
				"\u4E2D\u5C42\u7BA1\u7406\u4EBA\u5458\u57F9\u8BAD",
				"\u73ED\u7EC4\u957F\u57F9\u8BAD" }));
		comboBox_2.setFont(new Font("楷体", Font.PLAIN, 16));
		panel_1.add(comboBox_2);

		JLabel label_3 = new JLabel(
				"\u5B8C\u6210\u60C5\u51B5\u57F9\u8BAD\uFF1A");
		label_3.setFont(new Font("楷体", Font.PLAIN, 16));
		panel_1.add(label_3);

		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] {
				"\u672A\u57F9\u8BAD", "\u57F9\u8BAD\u4E2D",
				"\u5DF2\u57F9\u8BAD" }));
		comboBox_3.setFont(new Font("楷体", Font.PLAIN, 16));
		panel_1.add(comboBox_3);

		JButton button = new JButton("\u67E5\u8BE2");
		button.setFont(new Font("楷体", Font.PLAIN, 16));
		panel_1.add(button);

	}

}
