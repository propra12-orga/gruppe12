package spielfeld;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class Spielfeld extends JPanel {

	/*
	 * wichtig:die einzelnen Ebenen mit kurzer Erläuterung finden Sie in
	 * "dimension.txt".
	 */

	private static final long serialVersionUID = 1L;

	int hoehe, breite;
	public static Objekte register[][][];

	// erstellen der Objekte
	public static Objekte Gras = new Objekte("Gras", 0);
	public static Objekte festeMauer = new Objekte("festeMauer", 0);
	public static Objekte Ausgang = new Objekte("Ausgang", 1);
	public static Objekte Bombe = new Objekte("Bombe", 4);
	public static Objekte Explosion = new Objekte("Explosion", 3);
	public static Objekte Kiste = new Objekte("Kiste", 2);
	public static Objekte Bomberman1 = new Objekte("Bomberman1", 2);
	public static Objekte Bomberman2 = new Objekte("Bomberman2", 2);
	public static Objekte DummyItem = new Objekte("DummyItem", 1);
	/*
	 * 
	 * Konstruktor intialisiert den Register. Befüllt die Ebene 0 mit Gras Die
	 * restlichen Ebenen werden explizit null gesetzt um Störungen der If
	 * Abfrage in Spielflaeche.java zu vermeiden So werden wirklich nur die
	 * Plätze belegt die auch beschrieben werden.
	 */

	public Spielfeld(int breite, int hoehe, int dimension) {

		register = new Objekte[breite][hoehe][dimension];
		for (int i = 0; i < breite; i++) {
			for (int j = 0; j < hoehe; j++) {
				register[i][j][0] = Gras;
				register[i][j][1] = null;
				register[i][j][2] = null;
				register[i][j][3] = null;

			}
		}

	}

	/*
	 * Feste Mauern, Kisten und Ausgang werden
	 * 
	 * ins Register geschrieben
	 */

	public void feldfuellen() {
		for (int y = 0; y < 21; y++) {
			register[0][y][0] = festeMauer;
			register[20][y][0] = festeMauer;
			register[y][0][0] = festeMauer;
			register[y][20][0] = festeMauer;
		} // Raender mit Mauer befuellen
		for (int i = 2; i < 20; i = i + 2) {
			for (int j = 2; j < 20; j = j + 2) {
				register[i][j][0] = festeMauer;
			}
		}
		// Zufallsgenerator deaktiviert.
		randomGen(0.1);

	}// feldfuellen

	public void randomGen(double dichte) {
		// Feste Mauern einfuegen
		register[1][3][2] = Kiste;
		register[3][1][2] = Kiste;

		// alles was noch nicht Mauer ist wird eventuell eine kiste.
		for (int i = 3; i < 20; i = i + 2) {
			for (int j = 1; j < 20; j++) {
				int k = (int) (Math.random() + dichte);
				if (k == 1)
					register[i][j][2] = Kiste;
			}
		}
		for (int i = 3; i < 20; i = i + 2) {
			for (int j = 1; j < 20; j++) {
				int k = (int) (Math.random() + dichte);
				if (k == 1)
					register[j][i][2] = Kiste;
			}
		}

		for (int j = 3; j < 20; j++) {
			int k = (int) (Math.random() + dichte);
			if (k == 1)
				register[1][j][2] = Kiste;

			int l = (int) (Math.random() + dichte);
			if (l == 1)
				register[j][1][2] = Kiste;
		}
	}

	/*
	 * Methode um explizit Stellen des Arrays zu befüllen Sie nimmt X,Y
	 * Koordinaten,ein Objekt und eine Ebene an
	 * 
	 * so wird eine Mehrschichtigkeit realisiert
	 */
	public void fill(int regX, int regY, int ebene, Objekte obj) {
		register[regX][regY][ebene] = obj;
	}

	public void destroy(int regX, int regY, int dimension) {
		register[regX][regY][dimension] = null;
	}

	public Objekte getObj(int regX, int regY, int ebene) { // gibt das Objekt an
															// der Stelle
		// RegX,RegY aus dem Register wieder
		return register[regX][regY][ebene];
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
		if (register[x][y][0] == Gras)
			return true;
		else
			return false;
	}
	public boolean equalsMauer(int x, int y) {
		if (register[x][y][0] == festeMauer)
			return true;
		else
			return false;
	}

	public boolean equalsExit(int x, int y, int dimension) {
		if (register[x][y][dimension] == Ausgang)
			return true;
		else
			return false;
	}
	public boolean equalsDummyItem(int x, int y, int dimension) {

		if (register[x][y][dimension] == DummyItem)
			return true;
		else
			return false;

	}

	public boolean equalsBomb(int x, int y, int dimension) {
		if (register[x][y][dimension] == Bombe)
			return true;
		else
			return false;

	}

	public boolean equalsMan(int x, int y, int dimension) {

		if (register[x][y][dimension] == Bomberman1)
			return true;
		else
			return false;
	}
	public boolean equalsMan2(int x, int y, int dimension) {

		if (register[x][y][dimension] == Bomberman2)
			return true;
		else
			return false;

	}

	public boolean equalsKiste(int x, int y, int dimension) {
		if (register[x][y][dimension] == Kiste)
			return true;
		else
			return false;
	}

	public boolean equalsExplosion(int x, int y, int dimension) {
		if (register[x][y][dimension] == Explosion)
			return true;
		else
			return false;
	}

	/* Rest noch zu editieren */
}
