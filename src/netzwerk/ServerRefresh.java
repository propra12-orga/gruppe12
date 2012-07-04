package netzwerk;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;

import spielfeld.Spielfeld;
import spielfeld.Spielflaeche;
import tools.GameKeyListener;
import bombe.Bombe;

/**
 * Thread, um staendig Clienten nach Information zu befragen.
 * 
 * @author garg
 * 
 */
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
		int bombX;
		int bombY;
		int bombX2;
		int bombY2;

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

				bombX = 0;
				bombY = 0;
				if (GameKeyListener.bomb) {
					bombX = posX;
					bombY = posY;
					GameKeyListener.bomb = false;
				}
				bombX2 = Integer.parseInt(in.readUTF());
				bombY2 = Integer.parseInt(in.readUTF());

				out.writeUTF(String.valueOf(bombX));
				out.writeUTF(String.valueOf(bombY));

				Spielflaeche.play.fill(bombX2, bombY2, 4, Spielfeld.Bombe);
				new Bombe(bombX2, bombY2, 2, 2,
						Spielflaeche.bman.getBombType(), 1, 3).start();
				Spielflaeche.play.destroy(0, 0, 4);

			} catch (EOFException e) {
				// TODO Auto-generated catch block
				System.out.print("");
				break;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.print("");
				break;
			}
		}
	}
	public void moved(int oldX, int oldY, int newX, int newY) {
		int x = newX - oldX;
		int y = newY - oldY;
		Spielflaeche.bman2.move(x, y);
	}
}
