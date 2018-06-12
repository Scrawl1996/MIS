package forms.depart;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyVetoException;
import java.net.URL;

import javax.swing.JLabel;

import com.tool.WindowUtil;

import forms.MainPage;

import java.awt.Font;
import java.awt.Color;

import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class MainDepartForm extends JFrame {

	private JPanel contentPane;
	private JDesktopPane desktopPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainDepartForm frame = new MainDepartForm();
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
	public MainDepartForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 714, 611);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnEmployee = new JMenu("\u90E8\u95E8\u7BA1\u7406");
		mnEmployee.setFont(new Font("楷体", Font.PLAIN, 16));
		menuBar.add(mnEmployee);

		JMenuItem mntmAddEmployee = new JMenuItem("\u6DFB\u52A0\u90E8\u95E8");
		mntmAddEmployee.setHorizontalAlignment(SwingConstants.CENTER);
		mntmAddEmployee.setFont(new Font("楷体", Font.PLAIN, 16));

		mnEmployee.add(mntmAddEmployee);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JToolBar toolBar = new JToolBar();
		contentPane.add(toolBar, BorderLayout.NORTH);

		JButton btnAddEmployee = new JButton("\u5237\u65B0");
		btnAddEmployee.setFont(new Font("楷体", Font.PLAIN, 16));
		btnAddEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showEmployeeMainForm();
			}
		});
		toolBar.add(btnAddEmployee);

		JButton btnNewButton_1 = new JButton("\u9000\u51FA");
		btnNewButton_1.setFont(new Font("楷体", Font.PLAIN, 16));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainDepartForm.this.setVisible(false);
				MainPage mainPage = new MainPage();
				mainPage.setVisible(true);

			}
		});

		JLabel lblNewLabel_1 = new JLabel("   ");
		toolBar.add(lblNewLabel_1);
		toolBar.add(btnNewButton_1);

		desktopPane = new JDesktopPane();
		contentPane.add(desktopPane, BorderLayout.CENTER);
		JLabel backLabel = new JLabel();

		URL resource = this.getClass().getResource("/images/background.jpg");

		ImageIcon imageIcon = new ImageIcon(resource);
		backLabel.setIcon(imageIcon);

		backLabel.setBounds(0, 0, imageIcon.getIconWidth(),
				imageIcon.getIconHeight());

		desktopPane.add(backLabel, BorderLayout.CENTER, new Integer(
				Integer.MIN_VALUE));

		//setBounds(0, 0, imageIcon.getIconWidth(), imageIcon.getIconHeight());

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);

		JLabel lblNewLabel = new JLabel(
				"\u2605\u592A\u539F\u79D1\u6280\u5927\u5B66  \u8BA1\u7B97\u673A\u5B66\u9662\u2605");
		lblNewLabel.setForeground(Color.GRAY);
		lblNewLabel.setFont(new Font("华文隶书", Font.PLAIN, 13));
		panel.add(lblNewLabel);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			SwingUtilities.updateComponentTreeUI(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		setLocation(WindowUtil.setLocation(this.getWidth(), this.getHeight()));

		mntmAddEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showEmployeeMainForm();
			}
		});
	}

	private void showEmployeeMainForm() {
		DepartMainForm departMainForm=new DepartMainForm(null);
		desktopPane.add(departMainForm);
		departMainForm.setVisible(true);
		try {
			departMainForm.setMaximum(true);
		} catch (PropertyVetoException e1) {
			e1.printStackTrace();
		}
	}
}
