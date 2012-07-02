package netzwerk;

import game.Game;
import game.LoadMap;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import spielfeld.Spielflaeche;

public class testClient {
	public static void main(String[] args) throws ClassNotFoundException,
			InterruptedException {
		try {
			Socket client = new Socket("192.168.0.100", 3000);
			Spielflaeche.network = true;
			// schreiben
			DataOutputStream out = new DataOutputStream(
					client.getOutputStream());
			// lesen,
			ObjectInputStream in = new ObjectInputStream(
					client.getInputStream());

			File map = (File) in.readObject();
			FileReader f = new FileReader(map);
			BufferedReader bitte = new BufferedReader(f);
			File ausgabe = new File("plz.lv");
			FileWriter bla = new FileWriter(ausgabe);
			bla.write("HalloWelt");
			System.out.println(ausgabe.getName());

			Game.go();
			Spielflaeche.play.feldeinlesen(LoadMap.load(ausgabe));

			// System.out.println(map.getName());
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
