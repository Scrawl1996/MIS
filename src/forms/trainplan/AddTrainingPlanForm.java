package forms.trainplan;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ListSelectionModel;

import services.trainingplan.TrainingPlan;
import util.DateChooser;
import net.miginfocom.swing.MigLayout;

import com.tool.WindowUtil;

import dto.Trainingplan.TrainingPlanDTO;
import forms.MainPage;

/**
 * 
 * @author 14713 创建培训计划界面
 *
 */
@SuppressWarnings("serial")
public class AddTrainingPlanForm extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtPlanYear;
	private JTextField txtStaDate;
	private JTextField txtName;
	private JTextField txtEndDate;
	private DefaultTableModel tableModel;
	private int rowCount;
	private TrainingPlan trainingPlan = new TrainingPlan();
	private JTextField txtType;
	private JTextField txtFinal;

	/**
	 * Create the frame.
	 */
	public AddTrainingPlanForm() {
		setResizable(false);
		addWindowListener(new WindowAdapter() {

			public void windowActivated(WindowEvent e) {
				do_this_windowActivated(e);
			}
		});// 窗口监听器加载表格
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 660, 497);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"),
				"\u521B\u5EFA\u57F9\u8BAD\u8BA1\u5212", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(128, 128, 128)));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new MigLayout("", "[21.00px][][116.00,grow][72.00][64.00][116.00,grow][61.00]", "[26.00px][26.00][25.00][][]"));

		JButton ExitButton = new JButton("\u9000\u51FA");
		ExitButton.setFont(new Font("楷体", Font.PLAIN, 16));
		ExitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				MainPage mainPage = new MainPage();
				mainPage.setVisible(true);

			}
		});// 按钮监听器，调用主界面
		panel_1.add(ExitButton, "cell 6 0");

		JLabel PlYearlabel = new JLabel("\u5E74\u5EA6");
		PlYearlabel.setFont(new Font("楷体", Font.PLAIN, 16));
		panel_1.add(PlYearlabel, "cell 1 1,alignx trailing");

		txtPlanYear = new JTextField();
		txtPlanYear.setFont(new Font("楷体", Font.PLAIN, 16));
		panel_1.add(txtPlanYear, "cell 2 1,growx");
		txtPlanYear.setColumns(10);

		JLabel NameLabel = new JLabel("\u540D\u79F0");
		NameLabel.setFont(new Font("楷体", Font.PLAIN, 16));
		panel_1.add(NameLabel, "cell 4 1,alignx trailing");

		txtName = new JTextField();
		txtName.setFont(new Font("楷体", Font.PLAIN, 16));
		panel_1.add(txtName, "cell 5 1,growx");
		txtName.setColumns(10);

		JLabel StDatelabel = new JLabel("\u5F00\u59CB\u65F6\u95F4");
		StDatelabel.setFont(new Font("楷体", Font.PLAIN, 16));
		panel_1.add(StDatelabel, "cell 1 2,alignx trailing");

		txtStaDate = new JTextField();
		txtStaDate.setFont(new Font("楷体", Font.PLAIN, 16));
		DateChooser dateChooser1 = DateChooser.getInstance("yyyy-MM-dd");
		dateChooser1.register(txtStaDate);
		panel_1.add(txtStaDate, "cell 2 2,growx");
		txtStaDate.setColumns(10);

		JLabel EndDateLabel = new JLabel("\u7ED3\u675F\u65F6\u95F4");
		EndDateLabel.setFont(new Font("楷体", Font.PLAIN, 16));
		panel_1.add(EndDateLabel, "cell 4 2,alignx trailing");

		txtEndDate = new JTextField();
		txtEndDate.setFont(new Font("楷体", Font.PLAIN, 16));
		DateChooser dateChooser= DateChooser.getInstance("yyyy-MM-dd");
		dateChooser.register(txtEndDate);
		panel_1.add(txtEndDate, "cell 5 2,growx");
		txtEndDate.setColumns(10);

		final JButton Addbutton = new JButton("增加");
		Addbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == Addbutton) {
					do_button_action(e);
				}
			}
		});

		JLabel label = new JLabel("\u7C7B\u578B");
		label.setFont(new Font("楷体", Font.PLAIN, 16));
		panel_1.add(label, "cell 1 3,alignx trailing");

		txtType = new JTextField();
		txtType.setFont(new Font("楷体", Font.PLAIN, 16));
		txtType.setColumns(10);
		panel_1.add(txtType, "cell 2 3,growx");

		JLabel label_1 = new JLabel("\u5B8C\u6210\u60C5\u51B5");
		label_1.setFont(new Font("楷体", Font.PLAIN, 16));
		panel_1.add(label_1, "cell 4 3,alignx trailing");

		txtFinal = new JTextField();
		txtFinal.setFont(new Font("楷体", Font.PLAIN, 16));
		txtFinal.setColumns(10);
		panel_1.add(txtFinal, "cell 5 3,growx");
		Addbutton.setFont(new Font("楷体", Font.PLAIN, 16));
		panel_1.add(Addbutton, "cell 5 4");

		final JButton Deletebutton = new JButton("删除");
		Deletebutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == Deletebutton) {
					do_button1_action(e);
				}
			}
		});
		Deletebutton.setFont(new Font("楷体", Font.PLAIN, 16));
		panel_1.add(Deletebutton, "cell 6 4");

		JButton Savebutton = new JButton("\u4FDD\u5B58");
		Savebutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_save_action(e);
			}
		});
		Savebutton.setFont(new Font("楷体", Font.PLAIN, 16));
		panel_1.add(Savebutton, "cell 5 0");

		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.CENTER);
		JScrollPane scrollPane = new JScrollPane();
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3
						&& (e.getClickCount() == 2)) {
					AddTrainingPlanForm.this.setVisible(false);
					Plan_emp plan_emp=new Plan_emp();
					plan_emp.setVisible(true);
				}
			}
		});
		table.setCellSelectionEnabled(true);
		table.setFillsViewportHeight(true);
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.setFont(new Font("楷体", Font.PLAIN, 16));
		table.setRowHeight(40);
		JTableHeader header = table.getTableHeader();
		header.setFont(new Font("楷体", Font.PLAIN, 16));
		header.setPreferredSize(new Dimension(header.getWidth(), 40));
		panel_2.setLayout(new BorderLayout(0, 0));
		scrollPane.setViewportView(table);
		panel_2.add(scrollPane);

		JLabel BgLabel = new JLabel("");
		panel.add(BgLabel, BorderLayout.SOUTH);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			SwingUtilities.updateComponentTreeUI(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		setLocation(WindowUtil.setLocation(this.getWidth(), this.getHeight()));

	}

	protected void do_save_action(ActionEvent e) {

		TrainingPlanDTO trainingPlanDTO = new TrainingPlanDTO();
		trainingPlanDTO.setTrain_plan_name(txtName.getText());
		trainingPlanDTO.setTrain_plan_year(txtPlanYear.getText());
		trainingPlanDTO.setStart_time(txtStaDate.getText());
		trainingPlanDTO.setEnd_time(txtEndDate.getText());
		trainingPlanDTO.setTrain_plan_type(txtType.getText());
		trainingPlanDTO.setIs_finish(txtFinal.getText());
		for (int k = 0; k < table.getRowCount(); k++) {
				trainingPlanDTO.setZy_name((String) table.getValueAt(k, 0));
				trainingPlanDTO.setTrain_purpose((String) table
						.getValueAt(k, 1));
				trainingPlanDTO.setTrain_content((String) table
						.getValueAt(k, 2));
				trainingPlanDTO.setClass_count((String) table.getValueAt(k, 3));
				trainingPlanDTO.setTeacher((String) table.getValueAt(k, 4));


		}// 保存数据
		boolean flag = trainingPlan.saveTrain(trainingPlanDTO);

		if (flag) {
			if (txtName.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "添加失败");
			} else {
				JOptionPane.showMessageDialog(null, "添加成功");
			}
		} else {
			JOptionPane.showMessageDialog(null, "添加失败");
		}
	}// 将数据添加到数据库中

	protected void do_button_action(ActionEvent e) {
		tableModel.addRow(new Object[] { null, null, null, null, null, null });
		rowCount = rowCount + 1;
	}// 增加表的一行

	protected void do_button1_action(ActionEvent e) {
		int index = table.getSelectedRow();
		if (index == -1) {
			JOptionPane.showMessageDialog(this, "请选择要删除的行", "",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		tableModel.removeRow(table.getSelectedRow());
		rowCount = rowCount - 1;
		table.setModel(tableModel);
	}// 删除一行表

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void do_this_windowActivated(WindowEvent e) {
		tableModel = (DefaultTableModel) table.getModel();
		tableModel.setRowCount(rowCount);
		tableModel.setColumnIdentifiers(new Object[] { "专业", "培训目的", "培训内容",
				"课时", "授课人" });
		tableModel.addRow(new Object[] { null, null, null, null, null, null });
		table.setModel(tableModel);
		String[] states = { "电气", "汽机", "锅炉", "化水", "燃运" };
		JComboBox comboBox = new JComboBox(states);
		comboBox.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		DefaultCellEditor editor = new DefaultCellEditor(comboBox);
		TableColumnModel columnModel = table.getColumnModel();
		columnModel.getColumn(0).setCellEditor(editor);
		columnModel.getColumn(1).setPreferredWidth(150);
		columnModel.getColumn(2).setPreferredWidth(150);
	}// 初始化表

	public static void main(String[] args) {
		new AddTrainingPlanForm().setVisible(true);
	}
}
