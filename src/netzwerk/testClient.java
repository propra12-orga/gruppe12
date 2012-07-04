package netzwerk;

import game.Game;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import menu.MainMenu;
import spielfeld.Spielflaeche;

public class testClient {
	public static void go(String ip) throws ClassNotFoundException,
			InterruptedException {

		try {
			Socket client = new Socket(ip, 3000);
			System.out.println("Verbunden zu Server: "
					+ client.getInetAddress().getHostAddress());

			// schreiben
			DataOutputStream write = new DataOutputStream(
					client.getOutputStream());

			// lesen,
			DataInputStream read = new DataInputStream(client.getInputStream());

			Clientrefresh clientRef = new Clientrefresh(read, write);

			MainMenu.gamerunning = true;
			Spielflaeche.network = true;
			Server.netClient = true;

			Game.go();
			clientRef.start();

			while (client.isConnected()) {

			}
			client.close();
			System.out.println("Verbindung getrennt.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
