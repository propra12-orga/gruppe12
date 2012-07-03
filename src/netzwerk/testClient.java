package netzwerk;

import game.Game;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import menu.MainMenu;
import spielfeld.Spielflaeche;

public class testClient {
	public static void main(String[] args) throws ClassNotFoundException,
			InterruptedException {

		try {
			Spielflaeche.network = true;
			Server.netClient = true;
			Socket client = new Socket("192.168.2.104", 3000);

			// schreiben
			DataOutputStream out = new DataOutputStream(
					client.getOutputStream());
			// lesen,
			ObjectInputStream in = new ObjectInputStream(
					client.getInputStream());

			// Starte Netzwerkspiel
			Game.go();
			MainMenu.gamerunning = true;

			while (MainMenu.gamerunning) {
				if (in.readUTF() != null) {
					Spielflaeche.bman.setxPosition(Integer.parseInt(in
							.readUTF()));
					Spielflaeche.bman.setyPosition(Integer.parseInt(in
							.readUTF()));

				}

				out.writeUTF(String.valueOf(Spielflaeche.bman2.xPosition));
				out.writeUTF(String.valueOf(Spielflaeche.bman2.yPosition));
			}

			// System.out.println(map.getName());
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
