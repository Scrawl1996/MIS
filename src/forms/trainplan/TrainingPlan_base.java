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
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import javax.swing.table.TableColumn;
import javax.swing.ListSelectionModel;

import util.DateChooser;
import net.miginfocom.swing.MigLayout;

import com.tool.WindowUtil;

/**
 * 
 * @author 14713 创建培训计划界面
 *
 */
@SuppressWarnings("serial")
public class TrainingPlan_base extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtPlanYear;
	private JTextField txtStaDate;
	private JTextField txtName;
	private JTextField txtEndDate;
	private DefaultTableModel tableModel;

	/**
	 * Create the frame.
	 */
	public TrainingPlan_base() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 582, 408);
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
		panel_1.setLayout(new MigLayout("",
				"[21.00px][][116.00,grow][72.00][64.00][116.00,grow][61.00]",
				"[26.00][25.00]"));

		JLabel PlYearlabel = new JLabel("\u5E74\u5EA6");
		PlYearlabel.setFont(new Font("楷体", Font.PLAIN, 16));
		panel_1.add(PlYearlabel, "cell 1 0,alignx trailing");

		txtPlanYear = new JTextField();
		txtPlanYear.setFont(new Font("楷体", Font.PLAIN, 16));
		panel_1.add(txtPlanYear, "cell 2 0,growx");
		txtPlanYear.setColumns(10);

		JLabel NameLabel = new JLabel("\u540D\u79F0");
		NameLabel.setFont(new Font("楷体", Font.PLAIN, 16));
		panel_1.add(NameLabel, "cell 4 0,alignx trailing");

		txtName = new JTextField();
		txtName.setFont(new Font("楷体", Font.PLAIN, 16));
		panel_1.add(txtName, "cell 5 0,growx");
		txtName.setColumns(10);

		JLabel StDatelabel = new JLabel("\u5F00\u59CB\u65F6\u95F4");
		StDatelabel.setFont(new Font("楷体", Font.PLAIN, 16));
		panel_1.add(StDatelabel, "cell 1 1,alignx trailing");

		txtStaDate = new JTextField();
		txtStaDate.setFont(new Font("楷体", Font.PLAIN, 16));
		DateChooser dateChooser1 = DateChooser.getInstance("yyyy-MM-dd");
		dateChooser1.register(txtStaDate);
		panel_1.add(txtStaDate, "cell 2 1,growx");
		txtStaDate.setColumns(10);

		JLabel EndDateLabel = new JLabel("\u7ED3\u675F\u65F6\u95F4");
		EndDateLabel.setFont(new Font("楷体", Font.PLAIN, 16));
		panel_1.add(EndDateLabel, "cell 4 1,alignx trailing");

		txtEndDate = new JTextField();
		txtEndDate.setFont(new Font("楷体", Font.PLAIN, 16));
		DateChooser dateChooser = DateChooser.getInstance("yyyy-MM-dd");
		dateChooser.register(txtEndDate);
		panel_1.add(txtEndDate, "cell 5 1,growx");
		txtEndDate.setColumns(10);

		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.CENTER);
		JScrollPane scrollPane = new JScrollPane();
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3
						&& (e.getClickCount() == 2)) {
					String train_plan_id = table.getValueAt(
							table.getSelectedRow(), 1).toString();
					TrainingPlan_base.this.setVisible(false);
					Plan_emp plan_emp = new Plan_emp();
					plan_emp.getTrain_base(train_plan_id);
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

	public static void main(String[] args) {
		new TrainingPlan_base().setVisible(true);
	}

	public void base_info(Vector<Vector<Object>> tableDatas, int index) {
		Vector<Object> trainPlanDTo = tableDatas.get(0);
		tableModel = (DefaultTableModel) table.getModel();
		tableModel.setColumnIdentifiers(new Object[] { "专业", "培训编号", "培训目的",
				"培训内容", "课时", "授课人", "操作" });
		tableModel.addRow(new Object[] { (String) trainPlanDTo.elementAt(5),
				(String) trainPlanDTo.elementAt(4),
				(String) trainPlanDTo.elementAt(6),
				(String) trainPlanDTo.elementAt(7),
				(String) trainPlanDTo.elementAt(8),
				(String) trainPlanDTo.elementAt(9), "安排学员" });
		txtPlanYear.setText((String) trainPlanDTo.elementAt(0));
		txtName.setText((String) trainPlanDTo.elementAt(1));
		txtStaDate.setText((String) trainPlanDTo.elementAt(2));
		txtEndDate.setText((String) trainPlanDTo.elementAt(3));
		TableColumn aColumn = table.getColumnModel().getColumn(1);
		aColumn.setMinWidth(0);
		aColumn.setMaxWidth(0);
		table.setModel(tableModel);
	}
}
