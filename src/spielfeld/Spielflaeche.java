package spielfeld;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import spielfigur.Spielfigur;

/* *** Update ***/
/* neue Version mit dynamischer Vergroeßerung der Bilder */

public class Spielflaeche extends JPanel {

	public static Spielfeld play;
	public static Spielfigur bman = new Spielfigur();

	private static final long serialVersionUID = 1L;

	public Spielflaeche() {
		play = new Spielfeld(21, 21);

		play.feldfuellen(); // Befuellt die Raender mit Mauer
		play.fill(19, 19, 1); // Ausgang
		play.fill(1, 2, 4); // Testbombe
		play.fill(1, 1, 10); // Testfigur
	}

	public void paint(Graphics g) {
		int arrayWidth = getWidth() / 21 + 1;
		int arrayHeight = getHeight() / 21 + 1;
		Image gras = play.loadImg("/ressources/grafics/gras.gif");
		Image mauer = play.loadImg("/ressources/grafics/brick.jpg");
		Image exit = play.loadImg("/ressources/grafics/ausgang.jpg");
		Image bomb = play.loadImg("/ressources/grafics/Bombe.gif");
		Image man = play.loadImg("/ressources/grafics/gras.jpg");
		Image kiste = play.loadImg("/ressources/grafics/kiste.png");

		for (int x = 0; x < 21; x++) {
			for (int y = 0; y < 21; y++) {

				if (play.equalsGras(x, y)) {

					g.drawImage(gras, x * arrayWidth, y * arrayHeight,
							arrayWidth, arrayHeight, null);
					// zeichnet
					// Gras

				}
				if (play.equalsMauer(x, y)) {

					g.drawImage(mauer, x * arrayWidth, y * arrayHeight,
							arrayWidth, arrayHeight, null); // zeichnet
					// Mauer

				}
				if (play.equalsExit(x, y)) {
					g.drawImage(exit, x * arrayWidth, y * arrayHeight,
							arrayWidth, arrayHeight, null); // zeichnet
					// Ausgang
				}
				if (play.equalsBomb(x, y)) {
					g.drawImage(bomb, x * arrayWidth, y * arrayHeight,
							arrayWidth, arrayHeight, null);// Zeichnet
				}
				// Bombe

				if (play.equalsMan(x, y)) {
					g.drawImage(man, x * arrayWidth, y * arrayHeight,
							arrayWidth, arrayHeight, null);// Zeichnet
					// Spielfigur
				}
				if (play.equalsKiste(x, y)) {
					g.drawImage(kiste, x * arrayWidth, y * arrayHeight,
							arrayWidth, arrayHeight, null);// Zeichnet
					// Spielfigur
				}

			}
		}
		repaint();
	}
}
