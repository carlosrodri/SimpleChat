package views;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.border.TitledBorder;
import controllers.Controller;
import controllers.MyActions;

public class DialogInit extends JDialog{

	private static final Dimension DIMENSION = new Dimension(350, 110);
	private static final String ACCEPT = "Accept";
	private static final long serialVersionUID = 1L;
	private static final String[] options = {"Number of words", "Actual hour", "file"};
	private JComboBox<String> cbOptions;
	private JButton btnAccept;
	private String option;
	
	public DialogInit(Controller controller) {
		setLocationRelativeTo(null);
		setSize(DIMENSION);
		
		cbOptions = new JComboBox<>(options);
		cbOptions.setBorder(new TitledBorder("Select any option"));
		add(cbOptions, BorderLayout.NORTH);
		
		btnAccept = new JButton(ACCEPT);
		btnAccept.setActionCommand(MyActions.ACCEPT.toString());
		btnAccept.addActionListener(controller);
		add(btnAccept, BorderLayout.SOUTH);
		
		setVisible(true);
	}
	
	public void option() {
		option = String.valueOf(cbOptions.getSelectedIndex());
	}

	public String getOption() {
		return option;
	}
}
