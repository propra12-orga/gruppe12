package netzwerk;

import game.Game;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

import spielfeld.Spielfeld;
import spielfeld.Spielflaeche;

public class Server extends Thread {
	ServerSocket server;
	DataInputStream in;
	int port;
	public Server(int port) throws IOException {
		server = new ServerSocket(3000);
		server.setSoTimeout(10000);
	}

	public void run() {

		try {
			System.out.println("Warte auf Clients...");

			Socket client = server.accept();
			// Lesestroeme fuer eingehende - und verschickte Daten
			// output zum schreiben , input zum empfangen
			ObjectInputStream in = new ObjectInputStream(
					client.getInputStream());
			OutputStream out = client.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(out);
			// starte Spiel

			Game.go();
			Spielflaeche.network = true;
			oos.writeObject(Spielfeld.save(Spielflaeche.play));

			server.close();

			// System.out.println("Vom Client erhaltene Nummer: " + test);
		} catch (SocketTimeoutException s) {
			System.out.println("Socket timed out!");

		} catch (IOException e) {
			System.out.println("I/O Exception ocurred1.");

		}

	}

}