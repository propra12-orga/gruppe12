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

/**
 * Thread startet Server.
 * 
 * @author garg
 * 
 */

public class Server extends Thread {
	ServerSocket server;
	int port;
	public static boolean netHost, netClient;

	public Server() throws IOException {
		server = new ServerSocket(3000);
	}

	public void run() {

		try {
			System.out.println("Warte auf Clients...");

			Socket client = server.accept();
			System.out.println("Client ist verbunden.");
			// Lesestroeme fuer eingehende - und verschickte Daten
			// output zum schreiben , input zum empfangen
			DataInputStream in = new DataInputStream(client.getInputStream());
			DataOutputStream out = new DataOutputStream(
					client.getOutputStream());
			ServerRefresh servRef = new ServerRefresh(in, out);

			MainMenu.gamerunning = true;
			Spielflaeche.network = true;
			Server.netHost = true;

			Game.go();
			servRef.start();

			while (client.isConnected() && !Spielflaeche.bman.dead
					&& !Spielflaeche.bman2.dead) {

			}
			server.close();
			System.out.println("Server geschlossen.");

		} catch (SocketTimeoutException s) {
			System.out.println("Socket timed out!");

		} catch (IOException e) {
			System.out.println("I/O Exception ocurred1.");

		}

	}
}