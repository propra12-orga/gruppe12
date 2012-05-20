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
	int hoehe, breite;
	public static int register[][];

	public Spielfeld(int breite, int hoehe) { // initialisiert Register
		register = new int[breite][hoehe];
		for (int i = 0; i < breite; i++) {
			for (int j = 0; j < hoehe; j++) {
				register[i][j] = 0;
			}
		}

	}

	public void feldfuellen() {// Feste Mauern, Kisten und Ausgang werden
		// bestimmt.
		for (int y = 0; y < 21; y++) {
			register[0][y] = 2;
			register[20][y] = 2;
			register[y][0] = 2;
			register[y][20] = 2;
		} // Raender mit Mauer befuellen
		for (int i = 2; i < 20; i = i + 2) {
			for (int j = 2; j < 20; j = j + 2) {
				register[i][j] = 2;
			}
		}
		// Zufallsgenerator deaktiviert.
		// randomGen(0.5);

	}// feldfuellen

	public void randomGen(double dichte) {
		// Feste Mauern einfuegen
		register[1][3] = 3;
		register[3][1] = 3;

		// alles was noch nicht Mauer ist wird eventuell eine kiste.
		for (int i = 3; i < 20; i = i + 2) {
			for (int j = 1; j < 20; j++) {
				int k = (int) (Math.random() + dichte);
				if (k == 1)
					register[i][j] = 3;
			}
		}
		for (int i = 3; i < 20; i = i + 2) {
			for (int j = 1; j < 20; j++) {
				int k = (int) (Math.random() + dichte);
				if (k == 1)
					register[j][i] = 3;
			}
		}

		for (int j = 3; j < 20; j++) {
			int k = (int) (Math.random() + dichte);
			if (k == 1)
				register[1][j] = 3;

			int l = (int) (Math.random() + dichte);
			if (l == 1)
				register[j][1] = 3;
		}
	}

	public void fill(int regX, int regY, int obj) { // Methode zum befuellen des
		// Arrays an der Stelle
		// RegX,RegY mit Objekt Obj
		register[regX][regY] = obj;
	}

	public int getObj(int regX, int regY) { // gibt das Objekt an der Stelle
		// RegX,RegY aus dem Register wieder
		return register[regX][regY];
	}

	public void draw(Image img, int x, int y, Graphics g) {

		g.drawImage(img, x * (getWidth() / 20), y * (getHeight() / 20),
				getWidth() / 20 + 19, (getHeight() / 20) + 19, null);
	}

	public Image loadImg(String pfad) {
		Image name1 = Toolkit.getDefaultToolkit().getImage(
				getClass().getResource(pfad));

		return name1;

	}

	public boolean equalsGras(int x, int y) {
		if (register[x][y] == 0)
			return true;
		else
			return false;
	}

	public boolean equalsMauer(int x, int y) {
		if (register[x][y] == 2)
			return true;
		else
			return false;
	}

	public boolean equalsExit(int x, int y) {
		if (register[x][y] == 1)
			return true;
		else
			return false;
	}

	public boolean equalsBomb(int x, int y) {
		// TODO Auto-generated method stub
		if (register[x][y] == 4)
			return true;
		else
			return false;

	}

	public boolean equalsMan(int x, int y) {
		// TODO Auto-generated method stub
		if (register[x][y] == 10)
			return true;
		else
			return false;

	}

	public boolean equalsKiste(int x, int y) {
		if (register[x][y] == 3)
			return true;
		else
			return false;
	}

	public boolean equalsExplosion(int x, int y) {
		if (register[x][y] == 5)
			return true;
		else
			return false;
	}

	/* Rest noch zu editieren */
}
