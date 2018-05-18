package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	private ServerSocket serverSocket;
	
	public Server() throws IOException {
		serverSocket = new ServerSocket(2001);
		System.out.println("Server create at port 2000");
		waitForNewConnection();
	}
	
	public void waitForNewConnection() throws IOException {
		while (true) {
			Socket socket = serverSocket.accept();
			System.out.println("New connection!");
			DataInputStream input = new DataInputStream(socket.getInputStream());
			connection(input, socket);
			input.close();
		}
	}
	
	private void connection(DataInputStream input, Socket clientSocket) throws NumberFormatException, IOException {
		int n = Integer.parseInt(input.readUTF());
		System.out.println(n +"      utffffff");
		DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
		String line = "";
		for (int i = 0; i < n; i++) {
			line += "hola ";
		}
		dataOutputStream.writeUTF(line);
	}

	public static void main(String[] args) {
		try {
			new Server();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}