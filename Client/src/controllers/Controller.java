package controllers;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import network.Client;
import views.DialogInit;
import views.MainWindow;

public class Controller implements ActionListener{
	private MainWindow mainWindow;
	private Client client;
	private DialogInit dialogInit;

	public Controller() {
		client = new Client();
		mainWindow = new MainWindow(this);
		dialogInit = new DialogInit(this);
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
		case ACCEPT:
			accept();
			break;
		}
	}

	private void accept() {
		dialogInit.option();
		switch (dialogInit.getOption()) {
		case "0":
			dialogInit.setVisible(false);
			mainWindow.setVisible(true);
			break;
		case "1":
			dialogInit.setVisible(false);
			hour();
			break;
		case "2":
			dialogInit.setVisible(false);
			file();
			break;
		}
	}

	private void file() {
		try {
			client.write("2", "");
			mainWindow.file(client.read());
			mainWindow.setVisible(true);
		} catch (HeadlessException | IOException e) {
			e.printStackTrace();
		}
	}

	private void hour() {
		try {
			client.write("1", "");
			mainWindow.hour(client.read());
			mainWindow.setVisible(true);
		} catch (HeadlessException | IOException e) {
			e.printStackTrace();
		}
	}

	private void add() throws HeadlessException, IOException {
		client.write(dialogInit.getOption(), mainWindow.getNumber());
		mainWindow.setText(client.read());
	}

	public static void main(String[] args) {
		new Controller();
	}
}
