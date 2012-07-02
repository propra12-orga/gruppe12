package netzwerk;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class testClient {
	public static void main(String[] args) {
		String serverName = "localHost";
		int port = 3000;
		try {

			Socket client = new Socket(serverName, port);

			DataOutputStream out = new DataOutputStream(
					client.getOutputStream());

			DataInputStream in = new DataInputStream(client.getInputStream());
			out.writeUTF("5");

			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
