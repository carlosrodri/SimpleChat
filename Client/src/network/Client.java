package network;

import java.awt.HeadlessException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {

	private Socket socket;

	public Client() {
		try {
			System.out.println("Conexion iniciada");
			socket = new Socket("localhost", 2001);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void write(int number) throws HeadlessException, IOException {
		DataOutputStream output = new DataOutputStream(socket.getOutputStream());
		System.out.println("enviando datos...");
		output.writeUTF(String.valueOf(number));
	}

	public String read() throws IOException {
		DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
		return dataInputStream.readUTF();
	}
}
