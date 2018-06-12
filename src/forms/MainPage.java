package forms;

import com.tool.WindowUtil;
import forms.depart.MainDepartForm;
import forms.employee.MainForm;
import forms.trainplan.AddTrainingPlanForm;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.net.URL;

/**
 * 
 * @author 14713 主界面窗口，主要分为系统管理、培训计划管理、培训成绩管理、学员管理。
 */
@SuppressWarnings("serial")
public class MainPage extends JFrame {

	private JPanel contentPane;
	private JPanel panel_1;
	private JDesktopPane desktopPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPage frame = new MainPage();
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
	public MainPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 960, 562);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu Sysmenu = new JMenu("\u7CFB\u7EDF\u7BA1\u7406");
		Sysmenu.setFont(new Font("楷体", Font.PLAIN, 16));
		menuBar.add(Sysmenu);

		JMenuItem UsermenuItem = new JMenuItem("\u7528\u6237\u7BA1\u7406");
		UsermenuItem.setFont(new Font("楷体", Font.PLAIN, 16));
		Sysmenu.add(UsermenuItem);

		JMenu TraPlanmenu = new JMenu("\u57F9\u8BAD\u8BA1\u5212\u7BA1\u7406");

		TraPlanmenu.setFont(new Font("楷体", Font.PLAIN, 16));
		menuBar.add(TraPlanmenu);
		
		JMenuItem menuItem = new JMenuItem("\u67E5\u770B");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showTrainingPlanForm();
			}
		});
		menuItem.setFont(new Font("楷体", Font.PLAIN, 16));
		TraPlanmenu.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("\u521B\u5EFA");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainPage.this.setVisible(false);
				AddTrainingPlanForm addTrainingPlanForm=new AddTrainingPlanForm();
				addTrainingPlanForm.setVisible(true);
			}
		});
		menuItem_1.setFont(new Font("楷体", Font.PLAIN, 16));
		TraPlanmenu.add(menuItem_1);

		JMenu PraGrademenu = new JMenu("\u57F9\u8BAD\u6210\u7EE9\u7BA1\u7406");
		PraGrademenu.setFont(new Font("楷体", Font.PLAIN, 16));
		menuBar.add(PraGrademenu);

		JMenuItem AddmenuItem_1 = new JMenuItem("\u5F55\u5165");
		AddmenuItem_1.setFont(new Font("楷体", Font.PLAIN, 16));
		PraGrademenu.add(AddmenuItem_1);

		JMenuItem AltermenuItem_1 = new JMenuItem("\u4FEE\u6539");
		AltermenuItem_1.setFont(new Font("楷体", Font.PLAIN, 16));
		PraGrademenu.add(AltermenuItem_1);

		JMenuItem QuerymenuItem = new JMenuItem("\u67E5\u8BE2");
		QuerymenuItem.setFont(new Font("楷体", Font.PLAIN, 16));
		PraGrademenu.add(QuerymenuItem);

		JMenu Stmenu = new JMenu("\u5B66\u5458\u7BA1\u7406");
		Stmenu.setFont(new Font("楷体", Font.PLAIN, 16));
		menuBar.add(Stmenu);

		JMenuItem UnitmenuItem = new JMenuItem("\u90E8\u95E8\u7BA1\u7406");
		UnitmenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainPage.this.setVisible(false);
			    MainDepartForm mainDepartForm=new MainDepartForm();
			    mainDepartForm.setVisible(true);
			}
		});
		UnitmenuItem.setFont(new Font("楷体", Font.PLAIN, 16));
		Stmenu.add(UnitmenuItem);

		JMenuItem StmenuItem = new JMenuItem(
				"\u5B66\u5458\u57FA\u672C\u4FE1\u606F");
		StmenuItem.setFont(new Font("楷体", Font.PLAIN, 16));
		StmenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_main_action();
			}
		});
		Stmenu.add(StmenuItem);

		JMenuItem StrecordmenuItem = new JMenuItem(
				"\u5B66\u4E60\u8BB0\u5F55\u7BA1\u7406");
		StrecordmenuItem.setFont(new Font("楷体", Font.PLAIN, 16));
		Stmenu.add(StrecordmenuItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));

		JToolBar toolBar = new JToolBar();
		panel_1.add(toolBar, BorderLayout.NORTH);

		JButton AddButton = new JButton("\u5237\u65B0");
		AddButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showTrainingPlanForm();
			}
		});

		AddButton.setFont(new Font("楷体", Font.PLAIN, 16));
		toolBar.add(AddButton);

		JButton AlterButton = new JButton("\u4FEE\u6539");
		AlterButton.setFont(new Font("楷体", Font.PLAIN, 16));
		toolBar.add(AlterButton);

		JButton button = new JButton("\u5220\u9664");
		button.setFont(new Font("楷体", Font.PLAIN, 16));
		toolBar.add(button);

		desktopPane = new JDesktopPane();
		desktopPane
				.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.add(desktopPane, BorderLayout.CENTER);
		JLabel backLabel = new JLabel();

		URL resource = this.getClass().getResource("/images/background.jpg");

		ImageIcon imageIcon = new ImageIcon(resource);
		backLabel.setIcon(imageIcon);

		backLabel.setBounds(0, 0, imageIcon.getIconWidth(),
				imageIcon.getIconHeight());

		desktopPane.add(backLabel, BorderLayout.CENTER, new Integer(
				Integer.MIN_VALUE));

         /**
          * 设置UI感观及屏幕中间显示
          */
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			SwingUtilities.updateComponentTreeUI(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		setLocation(WindowUtil.setLocation(this.getWidth(), this.getHeight()));
	}

	protected void showTrainingPlanForm() {
		TrainingPlanForm trainingPlanForm = new TrainingPlanForm();

		desktopPane.add(trainingPlanForm);
		trainingPlanForm.setVisible(true);
		try {
			trainingPlanForm.setMaximum(true);
		} catch (PropertyVetoException e1) {
			e1.printStackTrace();
		}
	}

	protected void do_main_action() {
		MainPage.this.setVisible(false);
		new MainForm().setVisible(true);
	}

}
