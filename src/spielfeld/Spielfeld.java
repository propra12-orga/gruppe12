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

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int Hoehe, Breite;
	public static int Register[][];

	public Spielfeld(int Breite, int Hoehe) { // initialisiert Register
		Register = new int[Breite][Hoehe];
		for (int i = 0; i < Breite; i++) {
			for (int j = 0; j < Hoehe; j++) {
				Register[i][j] = 0;
			}
		}

	}

	public void feldfuellen() {// Feste Mauern, Kisten und Ausgang werden
		// bestimmt.
		for (int y = 0; y < 21; y++) {
			Register[0][y] = 2;
			Register[20][y] = 2;
			Register[y][0] = 2;
			Register[y][20] = 2;
		} // Raender mit Mauer befuellen
		for (int i = 2; i < 20; i = i + 2) {
			for (int j = 2; j < 20; j = j + 2) {
				Register[i][j] = 2;
			}
		}// Feste Mauern einf�gen
		// Register[1][3] = 3;
		// Register[3][1] = 3;

		// alles was noch nicht Mauer ist wird eventuell eine kiste.
		// for (int i = 3; i < 20; i = i + 2) {
		// for (int j = 1; j < 20; j++) {
		// int k = (int) (Math.random() + 0.5);
		// if (k == 1)
		// Register[i][j] = 3;
		// }
		// }
		// for (int i = 3; i < 20; i = i + 2) {
		// for (int j = 1; j < 20; j++) {
		// int k = (int) (Math.random() + 0.5);
		// if (k == 1)
		// Register[j][i] = 3;
		// }
		// }
		//
		// for (int j = 3; j < 20; j++) {
		// int k = (int) (Math.random() + 0.5);
		// if (k == 1)
		// Register[1][j] = 3;
		//
		// int l = (int) (Math.random() + 0.5);
		// if (l == 1)
		// Register[j][1] = 3;
		// }

	}// feldfuellen

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

	public boolean equalsMan(int x, int y) {
		// TODO Auto-generated method stub
		if (Register[x][y] == 10)
			return true;
		else
			return false;

	}

	public boolean equalsKiste(int x, int y) {
		if (Register[x][y] == 3)
			return true;
		else
			return false;
	}

	/* Rest noch zu editieren */
}
