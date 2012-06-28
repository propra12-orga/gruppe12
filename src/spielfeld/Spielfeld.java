package spielfeld;

import game.LoadMap;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JPanel;
/*
 * Klasse um Spielfelder zu erzeugen.
 */
public class Spielfeld extends JPanel {

	/**
	 * Diese Klasse beinhaltet alle Methoden zum erzeugen von
	 * Spielfeldern,Objekten und zum Einlesen von Karten.
	 */
	/*
	 * Das ganze Spielfeld befindet sich auf mehreren Ebenen und wird als 3d
	 * Array register[][][] realisiert. wichtig:die einzelnen Ebenen mit kurzer
	 * Erläuterung finden Sie in "dimension.txt".
	 */

	private static final long serialVersionUID = 1L;

	int hoehe, breite;
	public static Objekte register[][][];
	public static char map[][][];
	public static boolean booleanSave = false;
	public static int xPos, yPos;
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
	public static Objekte Wechsler = new Objekte("Wechsler", 1);
	/**
	 * intialisiert den Register. Das Register ist ein 3d Array. So werden X/Y
	 * Koordinaten und eine Ebene erfasst fuer jedes Objekt .Befuellt die Ebene
	 * 0 mit Gras. Die restlichen Ebenen werden explizit null gesetzt um
	 * Stoerungen der If Abfrage in Spielflaeche.java zu vermeiden So werden
	 * wirklich nur die Plaetze belegt die auch beschrieben werden. Bei
	 * Erstellung eines Feldes wird es direkt befuellt damit.
	 * 
	 * @param breite
	 *            bestimmt die Breite des Feldes(Anzahl Bloecke in X Richtung)
	 * 
	 * @param hoehe
	 *            bestimmt die Höhe des Feldes (Anzahl Bloecke in Y Richtung)
	 * 
	 * @param dimension
	 *            legt die Anzahl der Ebenen fest
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

	/**
	 * Feste Mauern werden explizit und Kisten/Items/Ausgang per Zufall ins
	 * Register geschrieben. Zufallsgenerator wird mit double 0.1 gestartet.
	 * Startmethode, die mehrere Aktionen zusammenfasst.
	 * 
	 * @see randomGen()
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

	public void newTutorial() {
		for (int y = 0; y < 21; y++) {
			register[0][y][0] = festeMauer;
			register[20][y][0] = festeMauer;
			register[y][0][0] = festeMauer;
			register[y][20][0] = festeMauer;
		}

	}
	/**
	 * Liest eine gegebene Karte ein und uebersetzt sie.
	 * 
	 * @param map
	 *            bekommt von der Methode loadmap() aus LoadMap.java eine Karte
	 *            geliefert zum uebertragen in Register. Anhand des char Arrays
	 *            wird das Register an den bestimten Stellen mit Objekten
	 *            befuellt.
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
	public void tutorial() {
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

	}
	/**
	 * Zufallsgenerator der bei jeder Start-Ausfuehrung ein anderes Feld
	 * generiert.Kisten mit Items,Kisten ohne Items und ein Ausgang werden
	 * erzeugt.
	 * 
	 * @param dichte
	 *            double Wert zwischen 0 und 1. Je hoeher desto groeßer die
	 *            Anzahl der Kisten
	 */
	public void randomGen(double dichte) {
		int posX = LoadMap.randomInt();
		int posY = LoadMap.randomInt();
		register[1][3][2] = Kiste;
		register[3][1][2] = Kiste;
		register[posX][posY][2] = Kiste;
		register[posX][posY][1] = Ausgang;

		// Zufallsgenerator erweitert um die Moeglichkeit
		// Items zu platzieren
		for (int i = 3; i < 20; i = i + 2) {
			for (int j = 1; j < 20; j++) {
				int k = (int) (Math.random() + dichte);
				int h = (int) (Math.random() + dichte);
				int w = (int) (Math.random() + dichte);

				if (h == 1) {
					register[i][j][2] = Kiste;
					register[i][j][1] = DummyItem;
				} else if (k == 1) {
					register[i][j][2] = Kiste;
				} else if (w == 1) {
					register[i][j][2] = Kiste;
					register[i][j][1] = Wechsler;
				}
			}
		}
		for (int i = 3; i < 20; i = i + 2) {
			for (int j = 1; j < 20; j++) {
				int k = (int) (Math.random() + dichte);
				int h = (int) (Math.random() + dichte / 2);

				if (h == 1) {
					register[j][i][2] = Kiste;
					register[j][i][1] = DummyItem;
				} else if (k == 1) {
					register[j][i][2] = Kiste;
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
		register[19][19][1] = null;
		register[19][19][2] = null;
		register[19][17][1] = null;
		register[19][17][2] = null;
		register[18][19][1] = null;
		register[18][19][2] = null;
		register[19][18][1] = null;
		register[19][18][2] = null;

	}
	// randomGen

	/**
	 * Methode um explizit Stellen des Arrays zu befuellen.An angegebener
	 * Stelle.
	 * 
	 * @param regX
	 *            X Koordinate im Register
	 * 
	 * @param regY
	 *            Y Koordinate im Register
	 * 
	 * @param ebene
	 *            Legt die Schicht des Objekts fest,so wird eine
	 *            Mehrschichtigkeit realisiert
	 * 
	 * @param obj
	 *            Das Objekt welches in diese Stelle kommen soll
	 */
	public void fill(int regX, int regY, int ebene, Objekte obj) {
		register[regX][regY][ebene] = obj;
	}
	/**
	 * Zerstoert das Objekt an der Position(+Ebene). Setzt das Register an
	 * dieser Stelle null und leert es damit.Wichtig fuer Bombenimplementierung.
	 * 
	 * @param regX
	 *            X Koordinate des zu zerst. Objekts
	 * 
	 * @param regY
	 *            Y Koordinate des zu zerst. Objekts
	 * 
	 * @param dimension
	 *            wichtig: nur die richtige Schicht loeschen, untere Schichten
	 *            koennen hiermit freigelegt sein!
	 */

	public void destroy(int regX, int regY, int dimension) {
		register[regX][regY][dimension] = null;
	}
	/**
	 * Objekte getObj():Methode um das Objekt zu bestimmen an bestimmter Stelle
	 * Nuetzlich für Kollisionsabfragen bezgl. Bombe,unbegehbares Gelaende und
	 * Ausgang.
	 * 
	 * @param regX
	 *            Die X Koordinate
	 * 
	 * @param regY
	 *            Die Y Koordinate
	 * 
	 * @param ebene
	 *            Die Schicht auf der das Objekt liegt
	 * 
	 * @return gibt das Objekt an genau dieser Stelle auf dieser Ebene wieder
	 *         nützlich für Kollisionsabfragen
	 */
	public Objekte getObj(int regX, int regY, int ebene) {
		return register[regX][regY][ebene];
	}
	/**
	 * nimmt ein Bild,Koordinaten und ein Graphics-Objekt entgegen im Prinzip
	 * nur eine verkuerzte Fassung von Graphics.drawImage um die lesbarkeit zu
	 * steigern.Intern skaliert die Methode die Bilder bei Veraenderung der
	 * Fenstergroesse mit.
	 */
	public void draw(Image img, int x, int y, Graphics g) {
		g.drawImage(img, x * (getWidth() / 20), y * (getHeight() / 20),
				getWidth() / 20 + 19, (getHeight() / 20) + 19, null);
	}
	/**
	 * Image loadImg(): dient ebenfalls der Lesbarkeit und liest ein Bild ein
	 * nach gegebenem String.
	 * 
	 * @param pfad
	 *            nimmt den Pfad des Bildes entgegen. Steigert deutlich die
	 *            lesbarkeit.Denn von außen kann nur noch dieser String gesehen
	 *            werden
	 */
	public Image loadImg(String pfad) {
		Image name1 = Toolkit.getDefaultToolkit().getImage(
				getClass().getResource(pfad));
		return name1;

	}
	public static void save(Spielfeld game) throws IOException {
		booleanSave = true;
		xPos = Spielflaeche.bman.getxPosition();
		yPos = Spielflaeche.bman.getyPosition();
		char[][][] map = new char[21][21][5];
		map[0][0][3] = (char) xPos;
		map[1][1][3] = (char) yPos;
		for (int y = 0; y < 21; y++) {
			map[0][y][0] = '1';
			map[20][y][0] = '1';
			map[y][0][0] = '1';
			map[y][20][0] = '1';
		}
		for (int i = 2; i < 20; i = i + 2) {
			for (int j = 2; j < 20; j = j + 2) {
				map[i][j][0] = '1';
			}
		}
		/*
		 * speichere Dimension 1.
		 */
		for (int x = 0; x < 21; x++) {
			for (int y = 0; y < 21; y++) {
				if (game.getObj(y, x, 1) == DummyItem) {
					map[y][x][1] = '1';
				} else if (game.getObj(y, x, 1) == Ausgang) {
					map[y][x][1] = '2';
				} else if (game.getObj(y, x, 1) == null) {
					map[y][x][1] = '0';
				}
			}
		}
		/*
		 * speichere Dimension 2
		 */
		for (int x = 0; x < 20; x++) {
			for (int y = 0; y < 20; y++) {
				if (game.getObj(y, x, 2) == Kiste) {
					map[y][x][2] = '1';
				} else if (game.getObj(y, x, 2) == null) {
					map[y][x][2] = '0';
				}
			}
		}

		/*
		 * schreibe nun das Save-Game in eine txt Datei
		 */

		/*
		 * Dimension 1 wird gespeichert.
		 */
		File saveGame = new File("save.txt");
		FileWriter speichern = new FileWriter(saveGame);
		speichern.write(map[0][0][3]);
		speichern.write("\n");
		for (int i = 0; i < 20; i++) {

			for (int j = 0; j < 20; j++) {

				speichern.write(String.valueOf(map[j][i][1]));
				if (j == 19) {
					speichern.write('\n');
				}
			}

		}
		for (int i = 0; i < 20; i++) {

			for (int j = 0; j < 20; j++) {

				speichern.write(String.valueOf(map[j][i][2]));
				if (j == 19) {
					speichern.write('\n');
				}
			}
		}
		speichern.flush();
		speichern.close();
		System.out.println("Das Spiel wurde gespeichert.");
	}
	/*
	 * Für jedes Objekt, welches im Register stehen kann gibt es eine Abfrage ob
	 * an der gewaehlten Stelle X,Y(bzw. auch in dieser Dimension) dieses Objekt
	 * liegt. Alle Methoden vom typ boolean. return true wenn Objekt sich dort
	 * befindet ansonsten false.
	 */
	/**
	 * pruefe ob Gras an der Stelle.
	 * 
	 * @return gibt true zurueck falls an dieser Stelle Gras ist.
	 */
	public boolean equalsGras(int x, int y) {
		if (register[x][y][0] == Gras)
			return true;
		else
			return false;
	}
	/**
	 * teste ob an dieser Stelle eine Mauer ist.
	 * 
	 * @return gibt true zurueck wenn sich an der x und y Koordinate eine
	 *         festeMauer befindet
	 */
	public boolean equalsMauer(int x, int y) {
		if (register[x][y][0] == festeMauer)
			return true;
		else
			return false;
	}
	/**
	 * teste ob Eingang an X/Y/Ebene Position.
	 * 
	 * @return gibt true zurueck wenn auf dieser Schicht sich ein Ausgang
	 *         befindet
	 */
	public boolean equalsExit(int x, int y, int dimension) {
		if (register[x][y][dimension] == Ausgang)
			return true;
		else
			return false;
	}
	/**
	 * Teste ob Item an dieser Stelle liegt.
	 * 
	 * @return gibt true zurueck wenn Item auf dieser Ebene an X/Y Stelle
	 */
	public boolean equalsWechsler(int x, int y, int dimension) {

		if (register[x][y][dimension] == Wechsler)
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
	/**
	 * teste ob die Bombe an dieser Stelle ist.
	 * 
	 * @return gibt true zurueck wenn eine Bombe liegt an dieser Stelle/Ebene
	 */
	public boolean equalsBomb(int x, int y, int dimension) {
		if (register[x][y][dimension] == Bombe)
			return true;
		else
			return false;

	}
	/**
	 * teste ob Bomberman sich dort befindet.
	 * 
	 * @return gibt true zurueck wenn Bomberman dort steht
	 */
	public boolean equalsMan(int x, int y, int dimension) {

		if (register[x][y][dimension] == Bomberman1)
			return true;
		else
			return false;
	}
	/**
	 * analog zu eqalsMan()
	 * 
	 * @return analog zu equalsMan()
	 */
	public boolean equalsMan2(int x, int y, int dimension) {

		if (register[x][y][dimension] == Bomberman2)
			return true;
		else
			return false;

	}
	/**
	 * prueft ob eine Kiste auf diesem Feld liegt.
	 * 
	 * @return gibt true zurueck falls register an dieser Stelle eine Kiste
	 *         beinhaltet
	 */
	public boolean equalsKiste(int x, int y, int dimension) {
		if (register[x][y][dimension] == Kiste)
			return true;
		else
			return false;
	}
	/**
	 * prueft ob die Explosion da ist.
	 * 
	 * @return true wenn sich eine Explosion dort befindet.
	 */
	public boolean equalsExplosion(int x, int y, int dimension) {
		if (register[x][y][dimension] == Explosion)
			return true;
		else
			return false;
	}

}
