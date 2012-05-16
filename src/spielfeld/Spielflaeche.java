package spielfeld;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

/* *** Update ***/
/* neue Version mit dynamischer Vergroeßerung der Bilder */

public class Spielflaeche extends JPanel {

	public static Spielfeld play;

	private static final long serialVersionUID = 1L;

	public void paint(Graphics g) {
		play = new Spielfeld(20, 20); // intern wird das spielfeld als
										// array implementiert und direkt
										// befuellt mit 0

		Image gras = play.loadImg("/ressources/grafics/gras.jpg");
		Image mauer = play.loadImg("/ressources/grafics/brick.jpg");
		Image exit = play.loadImg("/ressources/grafics/ausgang.jpg");

		play.Randfuellen(); // Befuellt die Raender mit Mauer
		play.fill(18, 18, 1); // Ausgang

		for (int x = 0; x < 20; x++) {
			for (int y = 0; y < 20; y++) {
				int a = getWidth() / 20;
				int b = getHeight() / 20;

				if (play.equalsGras(x, y)) {

					g.drawImage(gras, x * a, y * b, a + 1, b + 1, null); // zeichnet
																			// Gras

				}
				if (play.equalsMauer(x, y)) {

					g.drawImage(mauer, x * a, y * b, a + 19, b + 19, null); // zeichnet
																			// Mauer

				}
				if (play.equalsExit(x, y)) {
					g.drawImage(exit, x * a, y * b, a + 19, b + 19, null); // zeichnet
																			// Ausgang
				}
			}
		}
		repaint();
	}

}
