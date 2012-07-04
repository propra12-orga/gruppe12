package netzwerk;

import game.Game;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import menu.MainMenu;
import spielfeld.Spielflaeche;

/**
 * Thread startet Clienten.
 * 
 * @author garg
 * 
 */

public class Client extends Thread {

	public String ip = "";

	public Client(String ip) {
		this.ip = ip;
	}

	public void run() {

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

			while (client.isConnected() && !Spielflaeche.bman.dead
					&& !Spielflaeche.bman2.dead) {

			}
			client.close();
			System.out.println("Verbindung getrennt.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
