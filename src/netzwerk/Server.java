package netzwerk;

import game.Game;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

import menu.MainMenu;
import spielfeld.Spielflaeche;

public class Server extends Thread {
	ServerSocket server;
	DataInputStream in;
	int port;
	public static boolean netHost, netClient;

	public Server(int port) throws IOException {
		server = new ServerSocket(3000);
		// server.setSoTimeout(10000);
	}

	public void run() {

		try {
			System.out.println("Warte auf Clients...");

			Socket client = server.accept();
			System.out.println("Client ist verbunden.");
			// Lesestroeme fuer eingehende - und verschickte Daten
			// output zum schreiben , input zum empfangen
			DataInputStream in = new DataInputStream(client.getInputStream());
			DataOutputStream dout = new DataOutputStream(
					client.getOutputStream());
			Spielflaeche.network = true;
			netHost = true;
			Game.go();
			// starte Spiel
			MainMenu.gamerunning = true;
			while (MainMenu.gamerunning) {
				System.out.println("bla");
				if (in.readUTF() != null) {
					Spielflaeche.bman2.setxPosition(Integer.parseInt(in
							.readUTF()));
					Spielflaeche.bman2.setyPosition(Integer.parseInt(in
							.readUTF()));

				}

				dout.writeUTF(String.valueOf(Spielflaeche.bman.xPosition));
				dout.writeUTF(String.valueOf(Spielflaeche.bman.yPosition));
			}

			server.close();

		} catch (SocketTimeoutException s) {
			System.out.println("Socket timed out!");

		} catch (IOException e) {
			System.out.println("I/O Exception ocurred1.");

		}

	}
}