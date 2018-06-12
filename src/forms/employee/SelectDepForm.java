package forms.employee;

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

import java.awt.Font;

@SuppressWarnings("serial")
public class SelectDepForm extends JDialog implements ActionListener,
		TreeSelectionListener {

	private final JPanel contentPanel = new JPanel();
	private JTree myTreeDep;
	private JTextField txtSelectedDep;
	private String selectedDep;

	public String getSelectedDep() {
		return selectedDep;
	}

	public void setSelectedDep(String selectedDep) {
		this.selectedDep = selectedDep;
	}

	/**
	 * Launch the application.
	 */
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		
		List<DepartDTO> allDeparts = new Depart().getAllDeparts();
		String format = "Depart：%s";
		for(DepartDTO d: allDeparts){
			System.out.println(format.format(format, d.getUnit_name()));
		}
		
		try {

			SelectDepForm dialog = new SelectDepForm(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void initDepartTree(List<DepartDTO> list, String pid, DefaultMutableTreeNode parent){
		for(DepartDTO depart : list){
			if(depart.getUp_unit_id().equals(pid)){
				DefaultMutableTreeNode other =  new DefaultMutableTreeNode(
						new KeyValue(depart.getUnit_name(),depart));
				
				initDepartTree(list,depart.getUnit_id(),other);
				parent.add(other);
			}
		}
		
	}
	
	
	/**
	 * Create the dialog.
	 */
	@SuppressWarnings("static-access")
	public SelectDepForm(JTextField txtSelectedDep) {
		this.txtSelectedDep = txtSelectedDep;
		setModal(true);
		setTitle("\u9009\u62E9\u90E8\u95E8");
		setBounds(100, 100, 212, 401);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		JTree treeDep = new JTree();
		treeDep.setFont(new Font("楷体", Font.PLAIN, 16));
		treeDep.setBounds(10, 10, 176, 310);
		myTreeDep = treeDep;
		treeDep.addTreeSelectionListener(this);
		contentPanel.setLayout(null);
		treeDep.setVisibleRowCount(40);
		/*
		 * 初始部门树
		 */
		Depart departService = new Depart();
		DepartDTO depart = departService.getDepartById("001");
		//System.out.println("*****"+depart.getUnit_name());
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(new KeyValue(  
				depart.getUnit_name(), depart));
		
		List<DepartDTO> list = departService.getAllDeparts();
		this.initDepartTree(list, "001", root);
		
		DefaultTreeModel model = new DefaultTreeModel(root);  
		treeDep.setModel(model);  
		treeDep.getSelectionModel().setSelectionMode(  
                DefaultTreeSelectionModel.SINGLE_TREE_SELECTION);  
        
        
//		treeDep.setModel(new DefaultTreeModel(
//			new DefaultMutableTreeNode("\u90E8\u95E8") {
//				{
//					add(new DefaultMutableTreeNode("\u90E8\u95E81"));
//					add(new DefaultMutableTreeNode("\u90E8\u95E82"));
//					add(new DefaultMutableTreeNode("\u90E8\u95E83"));
//				}
//			}
//		));
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
			// selectedDep = (String)node.getUserObject();
			txtSelectedDep.setText(this.selectedDep);
			this.setVisible(false);
		}
	}

	public void valueChanged(TreeSelectionEvent e) {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) myTreeDep
				.getLastSelectedPathComponent();

		if (node == null)
			return;
		
		selectedDep = ((KeyValue)node.getUserObject()).getName();
	//	System.out.println(selectedDep);
	}
}
