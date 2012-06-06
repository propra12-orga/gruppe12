package spielfeld;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import spielfigur.Spielfigur;

/* *** Update ***/
/* Objekte können sich nun unter anderen verstecken.Kommentare hinsichtlich
 * JavaDoc hinzugefügt */

public class Spielflaeche extends JPanel {
	/*
	 * Initialisierung der Variablen für das Spielfeld und die beiden
	 * Spielfiguren
	 */
	public static Spielfeld play;
	public static Spielfigur bman;
	public static Spielfigur bman2;

	private static final long serialVersionUID = 1L;
	public int arrayWidth, arrayHeight;
	private Image gras, mauer, exit, bomb, man, kiste, explo, man2, dummy;

	/*
	 * Erzeugt eine neue Spielflaeche. intern wird das als Array der Größe
	 * [21][21][5] realisiert Dabei ist die Größe 21x21 und die Anzahl der
	 * Dimensionen 5 Bomberman wird oben links platiert.
	 * 
	 * Das Spielfeld füllt sich direkt mit der ersten Methode feldfuellen() Es
	 * werden Gras,Mauer,Kiste,Ausgang und Items generiert.
	 */
	public Spielflaeche() {
		play = new Spielfeld(21, 21, 5);
		bman = new Spielfigur(2, 1, 2);
		bman2 = new Spielfigur(18, 19, 2);

		play.feldfuellen(); // Befuellt die Raender mit Mauer
		play.fill(19, 19, 1, Spielfeld.Ausgang); // Ausgang
		play.fill(1, 1, 2, Spielfeld.Kiste);
		play.fill(1, 1, 1, Spielfeld.Ausgang);

	}

	public void paint(Graphics g) {
		// Figur dort gezeichnet wo objekt bman ist
		Spielfeld.register[bman.xPosition][bman.yPosition][3] = Spielfeld.Bomberman1;
		Spielfeld.register[bman2.xPosition][bman2.yPosition][3] = Spielfeld.Bomberman2;

		// Anpassung der Spielfeldgröße an aktuelle Fenstergroesse
		arrayWidth = getWidth() / 21 + 1;
		arrayHeight = getHeight() / 21 + 1;

		// Laden der Bilder
		gras = play.loadImg("/ressources/grafics/gras.gif");
		mauer = play.loadImg("/ressources/grafics/brick.jpg");
		exit = play.loadImg("/ressources/grafics/ausgang.jpg");
		bomb = play.loadImg("/ressources/grafics/Bombe.gif");
		man = play.loadImg("/ressources/grafics/bomberman.png");
		man2 = play.loadImg("/ressources/grafics/bomberman2.png");
		kiste = play.loadImg("/ressources/grafics/kiste.png");
		explo = play.loadImg("/ressources/grafics/expl.gif");
		dummy = play.loadImg("/ressources/grafics/Gem.png");
		/*
		 * abgeaenderte Zeichenschleife. sie implementiert eine gewisse
		 * Prioritätenliste. Die Objekte der Dimension 1 werden nur gezeichnet
		 * a) falls sie existieren und b) falls kein Objekt der Dimension 2
		 * existiert (auf den selben Koordinaten.) Das Gras wird nur genau dann
		 * gezeichnet wenn Dimension 1 und 2 leer sind wichtig: unter die
		 * Objekte(sprich zeitlich davor!) muss Gras gesetzt werden sonst wird
		 * ein falscher Untergrund implementiert.
		 */
		for (int x = 0; x < 21; x++) {
			for (int y = 0; y < 21; y++) {

				if (play.getObj(x, y, 1) == null
						&& play.getObj(x, y, 2) == null) {
					if (play.equalsGras(x, y)) {
						g.drawImage(gras, x * arrayWidth, y * arrayHeight,
								arrayWidth, arrayHeight, null);
					}
				} else if (play.getObj(x, y, 1) != null) {

					if (play.equalsExit(x, y, 1)
							&& play.register[x][y][2] == null) {
						g.drawImage(gras, x * arrayWidth, y * arrayHeight,
								arrayWidth, arrayHeight, null);
						g.drawImage(exit, x * arrayWidth, y * arrayHeight,
								arrayWidth, arrayHeight, null);
					} else if (play.equalsDummyItem(x, y, 1)
							&& play.register[x][y][2] == null) {

						g.drawImage(gras, x * arrayWidth, y * arrayHeight,
								arrayWidth, arrayHeight, null);
						g.drawImage(dummy, x * arrayWidth, y * arrayHeight,
								arrayWidth, arrayHeight, null);
					}
				}

				/*
				 * über Mauer Position liegt eh nichts. zeichnet Mauer
				 */
				if (play.equalsMauer(x, y)) {
					g.drawImage(mauer, x * arrayWidth, y * arrayHeight,
							arrayWidth, arrayHeight, null);
				}

				// zeichnet Bombe auf Dimension 4
				else if (play.equalsBomb(x, y, 4)) {
					g.drawImage(bomb, x * arrayWidth, y * arrayHeight,
							arrayWidth, arrayHeight, null);
				}
				// zeichnet Spielfigur
				if (play.equalsMan(x, y, 3)) {
					g.drawImage(man, x * arrayWidth, y * arrayHeight,
							arrayWidth, arrayHeight, null);
				}

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

	}
}
