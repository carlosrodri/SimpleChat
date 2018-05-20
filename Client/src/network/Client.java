package network;

import java.awt.HeadlessException;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {

	private Socket socket;
	private DataInputStream dataInputStream;
	private DataOutputStream output;

	public Client() {
		try {
			System.out.println("Conexion iniciada");
			socket = new Socket("localhost", 2001);
			dataInputStream = new DataInputStream(socket.getInputStream());
			output = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void write(String option, String number) throws HeadlessException, IOException {
		System.out.println("enviando datos...");
		output.writeUTF(option+"#"+number);
	}

	public String read() throws IOException {
		String parts[] = dataInputStream.readUTF().split("#");
		switch (parts[0]) {
		case "0":
			return word(parts[1]);
		case "1":
			return hour(parts[1]);
		case "2":
			return file();
		}
		return null;
	}

	private String file() throws IOException {
		dataInputStream  = new DataInputStream(socket.getInputStream());
		String nameFile = dataInputStream.readUTF().toString();
		int tam = dataInputStream.readInt();
		FileOutputStream fos = new FileOutputStream(new File("src/datas/" + nameFile));
		@SuppressWarnings("resource")
		BufferedOutputStream out = new BufferedOutputStream(fos);
		BufferedInputStream in = new BufferedInputStream(socket.getInputStream());
		byte[] buffer = new byte[tam];
		for (int i = 0; i < buffer.length; i++) {
			buffer[i] = (byte) in.read();
		}
		out.write(buffer);
		out.flush();
		return nameFile;
	}

	private String hour(String hour) throws IOException {
		return hour;
	}

	private String word(String c) throws IOException {
		return c;
	}
}
