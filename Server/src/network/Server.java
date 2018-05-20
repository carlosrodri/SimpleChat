package network;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.GregorianCalendar;

public class Server {

	private static final String FILE_PATH = "src/datas/file.txt";
	private ServerSocket serverSocket;
	private DataOutputStream dataOutputStream;

	public Server() throws IOException {
		serverSocket = new ServerSocket(2001);
		System.out.println("Server create at port 2001");
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
		String parts[] = input.readUTF().split("#");
		switch (parts[0]) {
		case "0":
			words(clientSocket, parts[1]);
			break;
		case "1":
			hour(clientSocket);
			break;
		case "2":
			file(clientSocket);
			break;
		}
	}

	private void file(Socket clientSocket) {
		try {
			File file = new File(FILE_PATH);
			int fileSize = (int) file.length();
			dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
			dataOutputStream.writeUTF("2#" + fileSize);
			dataOutputStream.writeUTF(file.getName());
			dataOutputStream.writeInt(fileSize);
			FileInputStream filInp = new FileInputStream(file);
			@SuppressWarnings("resource")
			BufferedInputStream bis = new BufferedInputStream(filInp);
			BufferedOutputStream bos = new BufferedOutputStream(clientSocket.getOutputStream());
			byte[] buffer = new byte[fileSize];
			bis.read(buffer);
			for (int i = 0; i < buffer.length; i++) {
				bos.write(buffer[i]);
			}
			bos.flush();
		} catch (IOException e) {
			System.out.println("Error al crear canal de salida en el servidor.");
			e.printStackTrace();
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	private void hour(Socket clientSocket) throws IOException {
		Date c = new GregorianCalendar().getTime();
		dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
		dataOutputStream.writeUTF("1#"+String.valueOf(c.getHours()+" : "+c.getMinutes()+" : "+c.getSeconds()));
	}

	private void words(Socket clientSocket, String string) throws IOException {
		dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
		String line = "";
		for (int i = 0; i < Integer.parseInt(string); i++) {
			line += random(i)+" ";
		}
		dataOutputStream.writeUTF("0#"+line);
	}

	public String random(int i) {
		String[] s = {"hola","como","estas","bien","?","y","tu","jaja","bueno"};
		return s[i];
	}

	public static void main(String[] args) {
		try {
			new Server();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}