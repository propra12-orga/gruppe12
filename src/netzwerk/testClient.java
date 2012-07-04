package netzwerk;

import game.Game;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import menu.MainMenu;
import spielfeld.Spielflaeche;

public class testClient {
	public static void go(String ip, int port) throws ClassNotFoundException,
			InterruptedException {

		try {
			Spielflaeche.network = true;
			Server.netClient = true;
			MainMenu.gamerunning = true;
			Socket client = new Socket(ip, port);

			// schreiben
			DataOutputStream write = new DataOutputStream(
					client.getOutputStream());

			// lesen,
			DataInputStream read = new DataInputStream(client.getInputStream());

			Clientrefresh a = new Clientrefresh(read, write);

			Game.go();
			a.run();
			// System.out.println(map.getName());
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
