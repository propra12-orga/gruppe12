package netzwerk;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import spielfeld.Spielflaeche;

public class ServerRefresh extends Thread {

	DataInputStream in;
	DataOutputStream out;

	public ServerRefresh(DataInputStream in, DataOutputStream out) {
		this.in = in;
		this.out = out;
	}

	public void run() {
		int posX = 1;
		int newX = 19;
		int newY = 19;
		int oldX = 0;
		int oldY = 0;
		int posY = 2;

		while (true) {
			try {
				oldX = newX;
				oldY = newY;
				newX = Integer.parseInt(in.readUTF());
				newY = Integer.parseInt(in.readUTF());
				moved(oldX, oldY, newX, newY);

				posX = Spielflaeche.bman.xPosition;
				posY = Spielflaeche.bman.yPosition;
				out.writeUTF(String.valueOf(posX));
				out.writeUTF(String.valueOf(posY));

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void moved(int oldX, int oldY, int newX, int newY) {
		int x = newX - oldX;
		int y = newY - oldY;
		Spielflaeche.bman2.move(x, y);
	}
}
