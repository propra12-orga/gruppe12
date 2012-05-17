package spielfeld;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class Spielfeld extends JPanel {

	// Register- Zahlen:
	/*
	 * siehe Felder.txt
	 */

	int Hoehe, Breite;
	int Register[][];

	public Spielfeld(int Breite, int Hoehe) { // initialisiert Register
		Register = new int[Breite][Hoehe];
		for (int i = 0; i < Breite; i++) {
			for (int j = 0; j < Hoehe; j++) {
				Register[i][j] = 0;
			}
		}

	}

	public void Randfuellen() {
		for (int y = 0; y < 20; y++) {
			Register[0][y] = 2;
		} // Raender mit Mauer befuellen
		for (int y = 0; y < 20; y++) {
			Register[19][y] = 2;
		}
		for (int x = 0; x < 20; x++) {
			Register[x][0] = 2;
		}
		for (int x = 0; x < 20; x++) {
			Register[x][19] = 2;
		}

	}

	public void fill(int RegX, int RegY, int Obj) { // Methode zum befuellen des
													// Arrays an der Stelle
													// RegX,RegY mit Objekt Obj
		Register[RegX][RegY] = Obj;
	}

	public int getObj(int RegX, int RegY) { // gibt das Objekt an der Stelle
											// RegX,RegY aus dem Register wieder
		return Register[RegX][RegY];
	}

	public void draw(Image img, int x, int y, Graphics g) {

		g.drawImage(img, x * (getWidth() / 20), y * (getHeight() / 20),
				getWidth() / 20 + 19, (getHeight() / 20) + 19, null);
	}

	public Image loadImg(String Pfad) {
		Image Name1 = Toolkit.getDefaultToolkit().getImage(
				getClass().getResource(Pfad));

		return Name1;

	}

	public boolean equalsGras(int x, int y) {
		if (Register[x][y] == 0)
			return true;
		else
			return false;
	}

	public boolean equalsMauer(int x, int y) {
		if (Register[x][y] == 2)
			return true;
		else
			return false;
	}

	public boolean equalsExit(int x, int y) {
		if (Register[x][y] == 1)
			return true;
		else
			return false;
	}

	public boolean equalsBomb(int x, int y) {
		// TODO Auto-generated method stub
		if (Register[x][y] == 4)
			return true;
		else
			return false;

	}

	/* Rest noch zu editieren */
}
