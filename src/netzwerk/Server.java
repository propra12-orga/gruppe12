package netzwerk;

import game.Game;
import game.LoadMap;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

import menu.MainMenu;
import spielfeld.Spielflaeche;

public class Server extends Thread {
	ServerSocket server;
	DataInputStream in;
	int port;

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
			OutputStream out = client.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(out);
			DataOutputStream dout = new DataOutputStream(
					client.getOutputStream());

			// starte Spiel

			File f = LoadMap.randomMap();
			// File f = new File("haumichtod.de");
			oos.writeObject(f);

			Spielflaeche.network = true;
			Game.go();
			Spielflaeche.play.feldeinlesen(LoadMap.load(f));
			MainMenu.gamerunning = true;

			// oos.writeObject(Spielfeld.save(Spielflaeche.play));
			// dout.writeUTF("level_loaded");
			//
			// while (MainMenu.gamerunning) {
			// oos.writeObject(Spielfeld.save(Spielflaeche.play));
			// }

			server.close();

		} catch (SocketTimeoutException s) {
			System.out.println("Socket timed out!");

		} catch (IOException e) {
			System.out.println("I/O Exception ocurred1.");

		}

	}
}