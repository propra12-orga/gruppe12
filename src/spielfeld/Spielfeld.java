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
	public static Objekte register[][];

	public static Objekte Gras = new Objekte("Gras");
	public static Objekte festeMauer = new Objekte("festeMauer");
	public static Objekte zerstMauer = new Objekte("zerstMauer");
	public static Objekte Ausgang = new Objekte("Ausgang");
	public static Objekte Bombe = new Objekte("Bombe");
	public static Objekte Explosion = new Objekte("Explosion");
	public static Objekte Kiste = new Objekte("Kiste");
	public static Objekte Bomberman1 = new Objekte("Bomberman1");
	public static Objekte Bomberman2 = new Objekte("Bomberman2");
	// Konstruktor
	public Spielfeld(int breite, int hoehe) { // initialisiert Register
		register = new Objekte[breite][hoehe];
		for (int i = 0; i < breite; i++) {
			for (int j = 0; j < hoehe; j++) {
				register[i][j] = Gras;
			}
		}

	}

	public void feldfuellen() {// Feste Mauern, Kisten und Ausgang werden
		// bestimmt.
		for (int y = 0; y < 21; y++) {
			register[0][y] = festeMauer;
			register[20][y] = festeMauer;
			register[y][0] = festeMauer;
			register[y][20] = festeMauer;
		} // Raender mit Mauer befuellen
		for (int i = 2; i < 20; i = i + 2) {
			for (int j = 2; j < 20; j = j + 2) {
				register[i][j] = festeMauer;
			}
		}
		// Zufallsgenerator deaktiviert.
		randomGen(0.1);

	}// feldfuellen

	public void randomGen(double dichte) {
		// Feste Mauern einfuegen
		register[1][3] = zerstMauer;
		register[3][1] = zerstMauer;

		// alles was noch nicht Mauer ist wird eventuell eine kiste.
		for (int i = 3; i < 20; i = i + 2) {
			for (int j = 1; j < 20; j++) {
				int k = (int) (Math.random() + dichte);
				if (k == 1)
					register[i][j] = Kiste;
			}
		}
		for (int i = 3; i < 20; i = i + 2) {
			for (int j = 1; j < 20; j++) {
				int k = (int) (Math.random() + dichte);
				if (k == 1)
					register[j][i] = Kiste;
			}
		}

		for (int j = 3; j < 20; j++) {
			int k = (int) (Math.random() + dichte);
			if (k == 1)
				register[1][j] = Kiste;

			int l = (int) (Math.random() + dichte);
			if (l == 1)
				register[j][1] = Kiste;
		}
	}
	public void fill(int regX, int regY, Objekte obj) { // Methode zum befuellen
														// des
		// Arrays an der Stelle
		// RegX,RegY mit Objekt Obj
		register[regX][regY] = obj;
	}

	public Objekte getObj(int regX, int regY) { // gibt das Objekt an der Stelle
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
		if (register[x][y] == Gras)
			return true;
		else
			return false;
	}
	public boolean equalsMauer(int x, int y) {
		if (register[x][y] == festeMauer)
			return true;
		else
			return false;
	}

	public boolean equalsExit(int x, int y) {
		if (register[x][y] == Ausgang)
			return true;
		else
			return false;
	}

	public boolean equalsBomb(int x, int y) {
		// TODO Auto-generated method stub
		if (register[x][y] == Bombe)
			return true;
		else
			return false;

	}

	public boolean equalsMan(int x, int y) {
		// TODO Auto-generated method stub
		if (register[x][y] == Bomberman1)
			return true;
		else
			return false;
	}
	public boolean equalsMan2(int x, int y) {
		// TODO Auto-generated method stub
		if (register[x][y] == Bomberman1)
			return true;
		else
			return false;

	}

	public boolean equalsKiste(int x, int y) {
		if (register[x][y] == Kiste)
			return true;
		else
			return false;
	}

	public boolean equalsExplosion(int x, int y) {
		if (register[x][y] == Explosion)
			return true;
		else
			return false;
	}

	/* Rest noch zu editieren */
}
