package spielfeld;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import spielfigur.Spielfigur;

/* *** Update ***/
/* neue Version mit dynamischer Vergroeßerung der Bilder */

public class Spielflaeche extends JPanel {
	// initialisiere Variablen
	public static Spielfeld play;
	public static Spielfigur bman;
	public static Spielfigur bman2;

	private static final long serialVersionUID = 1L;
	public int arrayWidth, arrayHeight;
	private Image gras, mauer, exit, bomb, man, kiste, explo, man2;

	// Konstruktor
	public Spielflaeche() {
		play = new Spielfeld(21, 21);
		bman = new Spielfigur(2, 1);
		bman2 = new Spielfigur(18, 19);

		play.feldfuellen(); // Befuellt die Raender mit Mauer
		play.fill(19, 19, Spielfeld.Ausgang); // Ausgang

	}

	public void paint(Graphics g) {
		// Figur dort gezeichnet wo objekt bman ist
		Spielfeld.register[bman.xPosition][bman.yPosition] = Spielfeld.Bomberman1;
		Spielfeld.register[bman2.xPosition][bman2.yPosition] = Spielfeld.Bomberman2;

		// Anpassung der Spielfeldgröße an aktuelle Fenstergroesse
		arrayWidth = getWidth() / 21 + 1;
		arrayHeight = getHeight() / 21 + 1;

		// Laden der Bilder
		gras = play.loadImg("/ressources/grafics/gras.gif");
		mauer = play.loadImg("/ressources/grafics/brick.jpg");
		exit = play.loadImg("/ressources/grafics/ausgang.jpg");
		bomb = play.loadImg("/ressources/grafics/Bombe.gif");
		man = play.loadImg("/ressources/grafics/e.gif");
		man2 = play.loadImg("/ressources/grafics/1015-1-kroco-gems.jpg");
		kiste = play.loadImg("/ressources/grafics/kiste.png");
		explo = play.loadImg("/ressources/grafics/expl.gif");

		// Zeichnenschleife
		for (int x = 0; x < 21; x++) {
			for (int y = 0; y < 21; y++) {
				// zeichnet Gras
				if (play.equalsGras(x, y)) {
					g.drawImage(gras, x * arrayWidth, y * arrayHeight,
							arrayWidth, arrayHeight, null);
				}
				// zeichnet Mauer
				if (play.equalsMauer(x, y)) {
					g.drawImage(mauer, x * arrayWidth, y * arrayHeight,
							arrayWidth, arrayHeight, null);
				}
				// zeichnet Exit
				if (play.equalsExit(x, y)) {
					g.drawImage(exit, x * arrayWidth, y * arrayHeight,
							arrayWidth, arrayHeight, null);
				}
				// zeichnet Bombe
				if (play.equalsBomb(x, y)) {
					g.drawImage(bomb, x * arrayWidth, y * arrayHeight,
							arrayWidth, arrayHeight, null);
				}
				// zeichnet Spielfigur
				if (play.equalsMan(x, y)) {
					g.drawImage(man, x * arrayWidth, y * arrayHeight,
							arrayWidth, arrayHeight, null);
				}

				if (play.equalsMan2(x, y)) {
					g.drawImage(man2, x * arrayWidth, y * arrayHeight,
							arrayWidth, arrayHeight, null);
				}
				// zeichnet Kiste
				if (play.equalsKiste(x, y)) {
					g.drawImage(kiste, x * arrayWidth, y * arrayHeight,
							arrayWidth, arrayHeight, null);
				}
				// zeichnet Explosion
				if (play.equalsExplosion(x, y)) {
					g.drawImage(explo, x * arrayWidth, y * arrayHeight,
							arrayWidth, arrayHeight, null);
				}
			}
		}
		repaint();

	}
}
