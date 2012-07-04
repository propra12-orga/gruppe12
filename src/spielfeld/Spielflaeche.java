package spielfeld;

import game.Game;
import game.LoadMap;
import game.Tutorial;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import spielfigur.Spielfigur;
import tools.Framebutton;

/* *** Update ***/
/*
 * zufälliger Ausgang implementiert.
 * Feldeinlesen implementiert
 * zum Testen entweder randomMap.java benutzen oder Level.txt einlesen.
 * wichtig:: bitte manual.txt lesen.
 */
/**
 * Initialisiert die Karte und fuehrt die Zeichnung durch. Das Prinzip einer
 * "state machine" wird benutzt. So aendert das Programm lediglich Zustaende und
 * uebergibt es der Zeichenmethode paint aus JavaGraphics.
 * 
 * @author tony
 * 
 */
public class Spielflaeche extends JPanel {
	/*
	 * Initialisierung der Variablen für das Spielfeld und die beiden
	 * Spielfiguren
	 */
	public static Spielfeld play;
	public static Spielfigur bman;
	public static Spielfigur bman2;
	public static boolean sollExplodieren[][];
	public static boolean network = false;

	private static final long serialVersionUID = 1L;
	public int arrayWidth, arrayHeight;
	private Image gras, mauer, exit, bomb, man, kiste, explo, man2, dummy,
			wechsler;

	/*
	 * Erzeugt eine neue Spielflaeche. intern wird das als Array der Größe
	 * [21][21][5] realisiert Dabei ist die Größe 21x21 und die Anzahl der
	 * Dimensionen 5 Bomberman wird oben links platiert.
	 * 
	 * Das Spielfeld füllt sich direkt mit der ersten Methode feldfuellen() Es
	 * werden Gras,Mauer,Kiste,Ausgang und Items generiert. wahlweise kann ein
	 * Feld eingelesen werden mit feldlesen()
	 */
	/**
	 * Der Konstruktor dieser Klasse plaziert die Spielfigur und erzeugt ein
	 * noch zu befuellendes Spielfeld. Falls keine Datei eingelesen wurde wird
	 * ein Zufallsgenerator aktiviert,andernfalls die Karte eingelesen und
	 * geoeffnet.
	 */
	public Spielflaeche() {
		play = new Spielfeld(21, 21, 5);

		// sollExplodieren ist die Kommunikationsplattform der bomben-Threads.
		// Ist hier ein Wert true, so sollen bomben auf diesem feld explodieren
		sollExplodieren = new boolean[21][21];
		for (int i = 0; i < 21; i++) {
			for (int j = 0; j < 21; j++) {
				sollExplodieren[i][j] = false;// bombe soll anfangen zu
				// explodieren

			}
		}

		/*
		 * Routine um Tutorials zu starten
		 */

		if (network) {
			bman = new Spielfigur(1, 1, 2, 1);

			bman2 = new Spielfigur(19, 19, 2, 2);
			play.fill(4, 1, 2, Spielfeld.Kiste);
			for (int y = 0; y < 21; y++) {
				play.fill(0, y, 0, Spielfeld.festeMauer);
				play.fill(20, y, 0, Spielfeld.festeMauer);
				play.fill(y, 0, 0, Spielfeld.festeMauer);
				play.fill(y, 20, 0, Spielfeld.festeMauer);
			}
			for (int i = 2; i < 20; i = i + 2) {
				for (int j = 2; j < 20; j = j + 2) {
					play.fill(i, j, 0, Spielfeld.festeMauer);
				}
			}
		}

		if (network == false) {
			if (Tutorial.tutorialMode) {
				play.newTutorial();
				play.fill(15, 15, 1, Spielfeld.DummyItem);
				play.fill(15, 15, 2, Spielfeld.Kiste);
				play.fill(16, 16, 1, Spielfeld.DummyItem);
				play.fill(16, 16, 2, Spielfeld.Kiste);
				play.fill(17, 17, 1, Spielfeld.DummyItem);
				play.fill(17, 17, 2, Spielfeld.Kiste);

				bman = new Spielfigur(10, 10, 2, 1);

			}

			/*
			 * falls keine Datei geladen wurde isLoadText()==false dann
			 * ursprüngliche füllMethode per Zufall ansonsten liest er eine
			 * Karte ein über LoadMap.load()
			 */

			if (LoadMap.isLoadtext() == false && Tutorial.tutorialMode == false) {
				play.feldfuellen();
				bman = new Spielfigur(1, 1, 2, 1);

				bman2 = new Spielfigur(19, 19, 2, 2);

			} else if (Tutorial.tutorialMode == false) {
				/*
				 * wenn nicht gespeichert wurde, werden die Spielfiguren
				 * explizit gesetzt beide male wird einfach eine Karte
				 * eingelesen. prueft ob die Karte gueltig ist und laedt sie
				 * auch nur genau dann, wenn alle Bedingungen erfuellt sind. a)
				 * fair b) Ausgang existiert/zugaenglich
				 */

				// Routine zum Karten einlesen und pruefen
				if (Spielfeld.booleanSave == false) {
					bman = new Spielfigur(2, 1, 2, 1);

					bman2 = new Spielfigur(18, 19, 2, 2);

					if (LoadMap.consisCheck(LoadMap.map, Spielflaeche.bman,
							Spielflaeche.bman2)) {

						play.feldeinlesen(LoadMap.map);

					}

					else {
						JDialog Fehler = new JDialog(Game.gameFrame);
						Fehler.getContentPane().setBackground(Color.black);
						Fehler.setBounds(500, 150, 300, 300);
						Fehler.add(new JLabel(
								"<html><body> Die Karte ist fehlerhaft.<br> Entweder war ein Spieler bevorteilt oder kein Ausgang existend.<body><html>"));
						Fehler.setVisible(true);
					}
					/*
					 * lese gespeicherte Datei ein.Mit der Bomberman Position
					 * aus dem vorherigen Spiel
					 */
				} else {
					play.feldeinlesen(LoadMap.map);
					bman = new Spielfigur(Spielfeld.xPos, Spielfeld.yPos, 2, 1);
				}
			}
		}
	}// Konstruktor Ende
	/**
	 * paint() zeichnet dynamisch das ganze Register. Die Zeichenschleife
	 * implementiert eine bestimmte Prioritaetenliste fuer die Dimensionen. Die
	 * Mehrschichtigkeit wird wie folgt realisiert: Eine Ebene kann nur dann
	 * gezeichnet werden, wenn die Ebene ueber ihr leer ist. Die Ausnahme ist
	 * bei Gras zu machen, denn da wird gezeichnet falls sich ein Objekt (!=
	 * Kiste) darueber befindet.
	 * 
	 */
	public void paint(Graphics g) {
		// Figur dort gezeichnet wo objekt bman ist
		Spielfeld.register[bman.xPosition][bman.yPosition][3] = Spielfeld.Bomberman1;

		if (Tutorial.tutorialMode == false) {
			Spielfeld.register[bman2.xPosition][bman2.yPosition][3] = Spielfeld.Bomberman2;
		}
		// Anpassung der Spielfeldgröße an aktuelle Fenstergroesse
		arrayWidth = getWidth() / 21 + 1;
		arrayHeight = getHeight() / 21 + 1;

		if (Framebutton.zaehler == 3) {
			play.fill(1, 1, 1, Spielfeld.Ausgang);
		}

		// Laden der Bilder
		gras = play.loadImg("/ressources/grafics/Floor2.gif");
		mauer = play.loadImg("/ressources/grafics/unbreakable.gif");
		exit = play.loadImg("/ressources/grafics/finish.png");
		bomb = play.loadImg("/ressources/grafics/bomb1.gif");
		man = play.loadImg("/ressources/grafics/bomberman.png");
		man2 = play.loadImg("/ressources/grafics/bomberman2.png");
		kiste = play.loadImg("/ressources/grafics/Kiste2.gif");
		explo = play.loadImg("/ressources/grafics/Explode12.png");
		dummy = play.loadImg("/ressources/grafics/item_star.png");
		wechsler = play.loadImg("/ressources/grafics/Wechsla.png");
		/*
		 * Zeichenschleife. sie implementiert eine gewisse Prioritätenliste. Die
		 * Objekte der Dimension 1 werden nur gezeichnet: a) falls sie
		 * existieren und b) falls kein Objekt der Dimension 2 existiert (auf
		 * den selben Koordinaten.). Wichtig: unter die Objekte(sprich zeitlich
		 * davor!) muss Gras gesetzt werden sonst wird ein falscher Untergrund
		 * implementiert.
		 */
		for (int x = 0; x < 21; x++) {
			for (int y = 0; y < 21; y++) {

				if (play.getObj(x, y, 1) == null
						&& play.getObj(x, y, 2) == null) {
					/*
					 * Wenn das Feld frei ist an der Stelle zeichne lediglich
					 * Gras (sofern da auch welches ist) merke: Jede Stelle die
					 * nicht gerade festeMauer beeinhaltet wird erstmal zu gras
					 * und die anderen Ebenen null falls nicht explizit gefüllt
					 */
					if (play.equalsGras(x, y)) {
						g.drawImage(gras, x * arrayWidth, y * arrayHeight,
								arrayWidth, arrayHeight, null);
					}
				} else if (play.getObj(x, y, 1) != null) {
					/*
					 * Wenn Ebene 1 nicht leer ist und nichts über ihr liegt
					 * zeichne Ebene 1. wichtig: vorher Untergrund editieren zu
					 * Gras.
					 */
					if (play.equalsExit(x, y, 1)
							&& play.getObj(x, y, 2) == null) {
						g.drawImage(gras, x * arrayWidth, y * arrayHeight,
								arrayWidth, arrayHeight, null);
						g.drawImage(exit, x * arrayWidth, y * arrayHeight,
								arrayWidth, arrayHeight, null);
					} else if (play.equalsDummyItem(x, y, 1)
							&& play.getObj(x, y, 2) == null) {

						g.drawImage(gras, x * arrayWidth, y * arrayHeight,
								arrayWidth, arrayHeight, null);
						g.drawImage(dummy, x * arrayWidth, y * arrayHeight,
								arrayWidth, arrayHeight, null);
					} else if (play.equalsWechsler(x, y, 1)
							&& play.getObj(x, y, 2) == null) {
						g.drawImage(gras, x * arrayWidth, y * arrayHeight,
								arrayWidth, arrayHeight, null);
						g.drawImage(wechsler, x * arrayWidth, y * arrayHeight,
								arrayWidth, arrayHeight, null);
					}
				}

				/*
				 * über Mauer Position liegt nichts. zeichnet Mauer
				 */
				if (play.equalsMauer(x, y)) {
					g.drawImage(mauer, x * arrayWidth, y * arrayHeight,
							arrayWidth, arrayHeight, null);
				}

				/*
				 * zeichnet Bombe auf Dimension 4 Die Bombe hat eine eigene
				 * Dimension damit Bomberman beim legen der Bombe über ihr
				 * stehen kann.
				 */
				else if (play.equalsBomb(x, y, 4)) {
					g.drawImage(bomb, x * arrayWidth, y * arrayHeight,
							arrayWidth, arrayHeight, null);
				}
				// zeichnet Spielfigur
				if (play.equalsMan(x, y, 3)) {
					g.drawImage(man, x * arrayWidth, y * arrayHeight,
							arrayWidth, arrayHeight, null);
				}
				// zeichnet Spielfigur 2
				if (play.equalsMan2(x, y, 3)) {
					g.drawImage(man2, x * arrayWidth, y * arrayHeight,
							arrayWidth, arrayHeight, null);
				}

				// zeichnet Kiste
				if (play.equalsKiste(x, y, 2)) {
					g.drawImage(kiste, x * arrayWidth, y * arrayHeight,
							arrayWidth, arrayHeight, null);
				}
				// zeichnet Explosion
				if (play.equalsExplosion(x, y, 3)) {
					g.drawImage(explo, x * arrayWidth, y * arrayHeight,
							arrayWidth, arrayHeight, null);
				}
			}
		}
		repaint();

		// GameKeyListener.boolSave = false;
		// GameKeyListener.boolSave = false;

	}

}
