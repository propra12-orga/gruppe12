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
			Spielflaeche.network = true;
			Server.netClient = true;
			Socket client = new Socket(ip, 3000);

			// schreiben
			DataOutputStream write = new DataOutputStream(
					client.getOutputStream());

			// lesen,
			DataInputStream read = new DataInputStream(client.getInputStream());

			Clientrefresh a = new Clientrefresh(read, write);

			Game.go();
			a.run();
			MainMenu.gamerunning = true;

			// System.out.println(map.getName());
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
