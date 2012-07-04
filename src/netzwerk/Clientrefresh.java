package netzwerk;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import spielfeld.Spielflaeche;

public class Clientrefresh extends Thread {
	public Socket client;
	public DataInputStream in;
	public DataOutputStream out;

	public Clientrefresh(DataInputStream in, DataOutputStream out) {
		this.in = in;
		this.out = out;
	}
	public void moved(int oldX, int oldY, int newX, int newY) {
		int x = newX - oldX;
		int y = newY - oldY;
		Spielflaeche.bman.move(x, y);
	}
	public void run() {
		int newX = 1, newY = 1, oldX = 0, oldY = 0, posX, posY;
		while (true) {
			try {
				// System.out.println("bla");
				posX = Spielflaeche.bman2.xPosition;
				posY = Spielflaeche.bman2.yPosition;
				out.writeUTF(String.valueOf(posX));
				out.writeUTF(String.valueOf(posY));

				oldX = newX;
				oldY = newY;

				newX = Integer.parseInt(in.readUTF());

				newY = Integer.parseInt(in.readUTF());
				moved(oldX, oldY, newX, newY);

			} catch (IOException e) {

				e.printStackTrace();
			}
		}
	}
}
