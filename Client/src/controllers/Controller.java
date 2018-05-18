package controllers;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import network.Client;
import views.MainWindow;

public class Controller implements ActionListener{
	private MainWindow mainWindow;
	private Client client;

	public Controller() {
		client = new Client();
		mainWindow = new MainWindow(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (MyActions.valueOf(e.getActionCommand())) {
		case ADD:
			try {
				add();
			} catch (HeadlessException | IOException e1) {
				e1.printStackTrace();
			}
			break;
		}
	}

	private void add() throws HeadlessException, IOException {
		client.write(mainWindow.getNumber());
		mainWindow.setText(client.read());
	}
	
	public static void main(String[] args) {
		new Controller();
	}
}
