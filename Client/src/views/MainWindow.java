package views;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.TitledBorder;

import controllers.Controller;
import controllers.MyActions;

public class MainWindow extends JFrame{

	private static final long serialVersionUID = 1L;
	private JSpinner sp;
	private JLabel lbText;
	private JButton btnAdd;
	
	public MainWindow(Controller controller) {
		setLocationRelativeTo(null);
		setSize(400, 200);
		SpinnerNumberModel model = new SpinnerNumberModel(0, 0, 15, 1);
		sp = new JSpinner(model);
		sp.setBorder(new TitledBorder("Select the number of phrases"));
		add(sp, BorderLayout.NORTH);
		
		lbText = new JLabel();
		add(lbText, BorderLayout.CENTER);
		
		btnAdd = new JButton("Accept");
		btnAdd.setActionCommand(MyActions.ADD.toString());
		btnAdd.addActionListener(controller);
		add(btnAdd, BorderLayout.SOUTH);
	}

	public void setText(String text) {
		lbText.setText(text);
		repaint();
	}
	
	public String getNumber() {
		return sp.getValue().toString();
	}

	public void hour(String read) {
		lbText.setBorder(new TitledBorder("The Hour is:"));
		lbText.setText(read);
		remove(sp);
	}

	public void file(String read) {
		lbText.setBorder(new TitledBorder("A new file has been created:"));
		lbText.setText(read);
		remove(sp);
	}
}
