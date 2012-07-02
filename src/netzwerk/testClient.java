package netzwerk;

import game.Game;
import game.LoadMap;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import spielfeld.Spielflaeche;

public class testClient {
	public static void main(String[] args) throws ClassNotFoundException {
		String serverName = "localHost";
		int port = 3000;
		try {

			Socket client = new Socket("192.168.0.100", 3000);

			// schreiben
			DataOutputStream out = new DataOutputStream(
					client.getOutputStream());
			// lesen
			ObjectInputStream in = new ObjectInputStream(
					client.getInputStream());

			Game.go();

			Spielflaeche.play
					.feldeinlesen(LoadMap.load((File) in.readObject()));

			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
