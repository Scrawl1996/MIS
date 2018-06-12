package forms.depart;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeSelectionModel;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeSelectionEvent;

import dto.department.DepartDTO;
import forms.KeyValue;
import services.depart.Depart;

/**
 * 
 * @author 14713 树形加载部门信息
 */
@SuppressWarnings("serial")
public class SelectDepForm_1 extends JDialog implements ActionListener,
		TreeSelectionListener {

	private final JPanel contentPanel = new JPanel();
	private JTree myTreeDep;
	private JTextField txtSelectedDep;
	private String selectedDep;
	private JTextField txtSelecteID;
	private String selectedID;
	private JTextField txtUnit_id;
	private String Unit_id;
	private Depart departService;
	private List<DepartDTO> list;

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
				// System.out.println(depart.getUp_unit_id());
				parent.add(other);
			}
		}

	}

	public static String getUnit_id(List<DepartDTO> list, String pid) {
		String Maxunit_id = null;
		for (DepartDTO departDTO : list) {
			if (departDTO.getUp_unit_id().equals(pid)) {
				Maxunit_id = departDTO.getUnit_id();
			}
		}
		return Maxunit_id;

	}

	public static String unit_id(List<DepartDTO> list, String name) {
		String up_id = null;
		for (DepartDTO depart : list) {
			if (depart.getUnit_name().equals(name)) {
				// System.out.println(depart.getUp_unit_id());
				up_id = depart.getUnit_id();
			}
		}
		return up_id;
	}

	/**
	 * Create the Dialog.
	 */
	@SuppressWarnings("static-access")
	public SelectDepForm_1(JTextField txtSelectedDep, JTextField txtSelecteID,
			JTextField txtUnit_id) {
		this.txtSelectedDep = txtSelectedDep;
		this.txtSelecteID = txtSelecteID;
		this.txtUnit_id = txtUnit_id;
		setModal(true);
		setTitle("\u9009\u62E9\u90E8\u95E8");
		setBounds(100, 100, 212, 401);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		JTree treeDep = new JTree();
		treeDep.setBounds(10, 10, 176, 310);
		myTreeDep = treeDep;
		treeDep.addTreeSelectionListener(this);
		contentPanel.setLayout(null);
		treeDep.setVisibleRowCount(40);
		/*
		 * 初始部门树
		 */
		departService = new Depart();
		DepartDTO depart = departService.getDepartById("001");
		// System.out.println("*****"+depart.getUnit_name());
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(new KeyValue(
				depart.getUnit_name(), depart));

		list = departService.getAllDeparts();
		this.initDepartTree(list, "001", root);

		DefaultTreeModel model = new DefaultTreeModel(root);
		treeDep.setModel(model);
		treeDep.getSelectionModel().setSelectionMode(
				DefaultTreeSelectionModel.SINGLE_TREE_SELECTION);

		contentPanel.add(treeDep);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		{
			JButton okButton = new JButton("OK");
			okButton.addActionListener(this);
			okButton.setActionCommand("OK");
			buttonPane.add(okButton);
			getRootPane().setDefaultButton(okButton);
		}
		{
			JButton cancelButton = new JButton("Cancel");
			cancelButton.setActionCommand("Cancel");
			buttonPane.add(cancelButton);
		}
	}

	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();
		if (btn.getActionCommand() == "OK") {
			String id = unit_id(list, this.selectedDep);
			String k = getUnit_id(list, id);
			int i = Integer.parseInt(k);
			int j = i + 1;
			String unit_id = Integer.toString(j);
			String s = unit_id
					.substring(unit_id.length() - 3, unit_id.length());
			txtUnit_id.setText(s);
			txtSelectedDep.setText(this.selectedDep);
			txtSelecteID.setText(id);
			this.setVisible(false);
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

	public String getSelectedID() {
		return selectedID;
	}

	public void setSelectedID(String selectedID) {
		this.selectedID = selectedID;
	}

	public String getUnit_id() {
		return Unit_id;
	}

	public void setUnit_id(String unit_id) {
		Unit_id = unit_id;
	}
}
