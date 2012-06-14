package spielfeld;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;
/*
 * Klasse um Spielfelder zu erzeugen.
 */
public class Spielfeld extends JPanel {

	/*
	 * Das ganze Spielfeld befindet sich auf mehreren Ebenen und wird als 3d
	 * Array register[][][] realisiert. wichtig:die einzelnen Ebenen mit kurzer
	 * Erläuterung finden Sie in "dimension.txt".
	 */

	private static final long serialVersionUID = 1L;

	int hoehe, breite;
	public static Objekte register[][][];
	public static char map[][][];

	/*
	 * Erstelle Objekte die statisch genutzt werden können in allen Klassen.
	 * Macht das Programm deutlich übersichtlicher und lesbarer. Jedem Objekt
	 * wird ein String der seinen Typ definiert und eine Dimension mitgegeben.
	 */
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
	 * Konstruktor intialisiert den Register. Befüllt die Ebene 0 mit Gras Die
	 * restlichen Ebenen werden explizit null gesetzt um Störungen der If
	 * Abfrage in Spielflaeche.java zu vermeiden So werden wirklich nur die
	 * Plätze belegt die auch beschrieben werden.
	 * 
	 * @param breite bestimmt die Breite des Feldes(Anzahl Blöcke in X Richtung)
	 * 
	 * @param hoehe bestimmt die Höhe des Feldes (Anzahl Blöcke in Y Richtung)
	 * 
	 * @param dimension legt die Anzahl der Ebenen fest
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
	 * void feldfuellen(): Feste Mauern, Kisten und Ausgang werden ins Register
	 * geschrieben.Dabei haben wir die festeMauer an den Rand verlegt und einen
	 * Zufallsgenerator mit randomGen(double Dichte) implementiert, der Kisten
	 * verteilt,Items und Ausgang verteilt.
	 */

	public void feldfuellen() {

		for (int y = 0; y < 21; y++) {
			register[0][y][0] = festeMauer;
			register[20][y][0] = festeMauer;
			register[y][0][0] = festeMauer;
			register[y][20][0] = festeMauer;
		}
		for (int i = 2; i < 20; i = i + 2) {
			for (int j = 2; j < 20; j = j + 2) {
				register[i][j][0] = festeMauer;
			}
		}
		// Zufallsgenerator deaktiviert.
		randomGen(0.1);

	}// feldfuellen

	/*
	 * ueberladen der Methode feldfuellen() um ein Feld aus einer .txt Datei
	 * auszulesen
	 * 
	 * @param map bekommt von der Methode loadmap() aus LoadMap.java eine Karte
	 * geliefert zum parsen
	 */
	public void feldeinlesen(char[][][] map) {
		for (int y = 0; y < 21; y++) {
			register[0][y][0] = festeMauer;
			register[20][y][0] = festeMauer;
			register[y][0][0] = festeMauer;
			register[y][20][0] = festeMauer;
		}
		for (int i = 2; i < 20; i = i + 2) {
			for (int j = 2; j < 20; j = j + 2) {
				register[i][j][0] = festeMauer;
			}
		}
		/*
		 * hier wird aus der geladenen Datei, die in map[][][] gespeichert wird
		 * übersetzt. anhand if abfragen wird aus 0 oder 1 ein Objekt auf dem
		 * Spielfeld
		 */
		for (int x = 0; x < 21; x++) {
			for (int y = 0; y < 21; y++) {
				if (map[x][y][1] == '1') {
					register[x][y][1] = DummyItem;
				} else if (map[x][y][1] == '2') {
					register[x][y][1] = Ausgang;
				} else if (map[x][y][1] == '0') {
					register[x][y][1] = null;
				}
			}
		}
		for (int x = 0; x < 20; x++) {
			for (int y = 0; y < 20; y++) {
				if (map[x][y][2] == '1') {
					register[x][y][2] = Kiste;
				} else if (map[x][y][2] == '0') {
					register[x][y][2] = null;
				}
			}
		}

	}
	public void randomGen(double dichte) {
		boolean existsExit = false;

		register[1][3][2] = Kiste;
		register[3][1][2] = Kiste;

		// Zufallsgenerator erweitert um die Moeglichkeit
		// Items zu platzieren
		for (int i = 3; i < 20; i = i + 2) {
			for (int j = 1; j < 20; j++) {
				int k = (int) (Math.random() + dichte);
				int h = (int) (Math.random() + dichte);
				int l = (int) (Math.random() + dichte);

				if (h == 1) {
					register[i][j][2] = Kiste;
					register[i][j][1] = DummyItem;
				} else if (k == 1) {
					register[i][j][2] = Kiste;
				} else if (l == 1 && existsExit == false) {
					register[i][j][1] = Ausgang;
					register[i][j][2] = Kiste;
					existsExit = true;
				}
			}
		}
		for (int i = 3; i < 20; i = i + 2) {
			for (int j = 1; j < 20; j++) {
				int k = (int) (Math.random() + dichte);
				int h = (int) (Math.random() + dichte / 2);
				int l = (int) (Math.random() + 0.15);
				if (h == 1) {
					register[j][i][2] = Kiste;
					register[j][i][1] = DummyItem;
				} else if (k == 1) {
					register[j][i][2] = Kiste;
				} else if (l == 1 && existsExit == false) {
					register[i][j][1] = Ausgang;
					register[i][j][2] = Kiste;
					existsExit = true;
				}
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

		for (int i = 3; i < 20; i = i + 2) {
			for (int j = 1; j < 20; j++) {
				if (existsExit == false) {
					int k = (int) (Math.random() + 0.5);
					if (k == 1) {
						register[i][j][1] = Ausgang;
						register[i][j][2] = Kiste;
						existsExit = true;
					}
				}
			}
		}
	}
	// randomGen

	/*
	 * Methode um explizit Stellen des Arrays zu befüllen.An angegebener Stelle
	 * 
	 * @param regX X Koordinate im Register
	 * 
	 * @param regY Y Koordinate im Register
	 * 
	 * @param ebene Legt die Schicht des Objekts fest,so wird eine
	 * Mehrschichtigkeit realisiert
	 * 
	 * @param obj Das Objekt welches in diese Stelle kommen soll
	 */
	public void fill(int regX, int regY, int ebene, Objekte obj) {
		register[regX][regY][ebene] = obj;
	}
	/*
	 * void destroy(): Zerstört das Objekt an der Position(+Ebene). Setzt das
	 * Register an dieser Stelle null und leert es damit.
	 * 
	 * @param regX X Koordinate des zu zerst. Objekts
	 * 
	 * @param regY Y Koordinate des zu zerst. Objekts
	 * 
	 * @param dimension wichtig: nur die richtige Schicht löschen, untere
	 * Schichten können hiermit freigelegt sein!
	 */

	public void destroy(int regX, int regY, int dimension) {
		register[regX][regY][dimension] = null;
	}
	/*
	 * Objekte getObj():Methode um das Objekt zu bestimmen an bestimmter Stelle
	 * Nützlich für Kollisionsabfragen bezgl. Bombe,unbegehbares Gelände und
	 * Ausgang.
	 * 
	 * @param regX Die X Koordinate
	 * 
	 * @param regY Die Y Koordinate
	 * 
	 * @param ebene Die Schicht auf der das Objekt liegt
	 * 
	 * @return gibt das Objekt an genau dieser Stelle auf dieser Ebene wieder
	 * nützlich für Kollisionsabfragen
	 */
	public Objekte getObj(int regX, int regY, int ebene) {
		return register[regX][regY][ebene];
	}
	/*
	 * void draw(): nimmt ein Bild,Koordinaten und ein Graphics-Objekt entgegen
	 * im Prinzip nur eine verkürzte Fassung von Graphics.drawImage um die
	 * lesbarkeit zu steigern.Intern skaliert die Methode die Bilder bei
	 * Veränderung der Fenstergröße mit.
	 */
	public void draw(Image img, int x, int y, Graphics g) {
		g.drawImage(img, x * (getWidth() / 20), y * (getHeight() / 20),
				getWidth() / 20 + 19, (getHeight() / 20) + 19, null);
	}
	/*
	 * Image loadImg(): dient ebenfalls der Lesbarkeit und liest ein Bild ein
	 * nach gegebenem String.
	 * 
	 * @param pfad nimmt den Pfad des Bildes entgegen. Steigert deutlich die
	 * lesbarkeit.Denn nur noch dieser String kann gesehen werden von außen
	 */
	public Image loadImg(String pfad) {
		Image name1 = Toolkit.getDefaultToolkit().getImage(
				getClass().getResource(pfad));
		return name1;

	}
	/*
	 * Für jedes Objekt, welches im Register stehen kann gibt es eine Abfrage ob
	 * an der gewählten Stelle X,Y(bzw. auch in dieser Dimension) dieses Objekt
	 * liegt. Alle Methoden vom typ boolean. return true wenn Objekt sich dort
	 * befindet ansonsten false.
	 */
	/*
	 * @return gibt true zurück falls an dieser Stelle Gras ist.
	 */
	public boolean equalsGras(int x, int y) {
		if (register[x][y][0] == Gras)
			return true;
		else
			return false;
	}
	/*
	 * @return gibt true zurück wenn sich an der x und y Koordinate eine
	 * festeMauer befindet
	 */
	public boolean equalsMauer(int x, int y) {
		if (register[x][y][0] == festeMauer)
			return true;
		else
			return false;
	}
	/*
	 * @return gibt true zurück wenn auf dieser Schicht sich ein Ausgang
	 * befindet
	 */
	public boolean equalsExit(int x, int y, int dimension) {
		if (register[x][y][dimension] == Ausgang)
			return true;
		else
			return false;
	}/*
	 * @return return true wenn Item auf dieser Ebene an X/Y Stelle
	 */
	public boolean equalsDummyItem(int x, int y, int dimension) {

		if (register[x][y][dimension] == DummyItem)
			return true;
		else
			return false;

	}
	/*
	 * @return gibt true zurück wenn eine Bombe liegt an dieser Stelle/Ebene
	 */
	public boolean equalsBomb(int x, int y, int dimension) {
		if (register[x][y][dimension] == Bombe)
			return true;
		else
			return false;

	}
	/*
	 * @return gibt true zurück wenn Bomberman dort steht
	 */
	public boolean equalsMan(int x, int y, int dimension) {

		if (register[x][y][dimension] == Bomberman1)
			return true;
		else
			return false;
	}/*
	 * @return analog zu equalsMan()
	 */
	public boolean equalsMan2(int x, int y, int dimension) {

		if (register[x][y][dimension] == Bomberman2)
			return true;
		else
			return false;

	}
	/*
	 * @return gibt true zurück falls register an dieser Stelle eine Kiste
	 * beinhaltet
	 */
	public boolean equalsKiste(int x, int y, int dimension) {
		if (register[x][y][dimension] == Kiste)
			return true;
		else
			return false;
	}
	/*
	 * @return true wenn sich eine Explosion dort befindet.
	 */
	public boolean equalsExplosion(int x, int y, int dimension) {
		if (register[x][y][dimension] == Explosion)
			return true;
		else
			return false;
	}

}
