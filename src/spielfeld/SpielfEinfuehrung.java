package spielfeld;

import java.awt.Graphics;
import java.awt.Image;

import spielfigur.Spielfigur;

public class SpielfEinfuehrung extends Spielflaeche {
	public static boolean tutorialMode = false;
	public static Spielfeld tutorial;
	public static Spielfigur bman;
	private Image gras, mauer, exit, bomb, man, kiste, explo;
	private static final long serialVersionUID = 1L;

	public SpielfEinfuehrung() {
		tutorialMode = true;
		tutorial = new Spielfeld(21, 21, 5);
		bman = new Spielfigur(10, 10, 3, 1);

		for (int y = 0; y < 21; y++) {
			tutorial.fill(0, y, 0, Spielfeld.festeMauer);
			tutorial.fill(y, 0, 0, Spielfeld.festeMauer);
			tutorial.fill(20, y, 0, Spielfeld.festeMauer);
			tutorial.fill(y, 20, 0, Spielfeld.festeMauer);

		}
		// tutorial.feldfuellen();

	}
	public void paint(Graphics g) {
		tutorial.fill(bman.xPosition, bman.yPosition, 3, Spielfeld.Bomberman1);

		// Anpassung der Spielfeldgröße an aktuelle Fenstergroesse
		arrayWidth = getWidth() / 21 + 1;
		arrayHeight = getHeight() / 21 + 1;

		// Laden der Bilder
		gras = tutorial.loadImg("/ressources/grafics/Floor2.gif");
		mauer = tutorial.loadImg("/ressources/grafics/unbreakable.gif");
		exit = tutorial.loadImg("/ressources/grafics/finish.png");
		bomb = tutorial.loadImg("/ressources/grafics/bomb1.gif");
		man = tutorial.loadImg("/ressources/grafics/bomberman.png");
		kiste = tutorial.loadImg("/ressources/grafics/Kiste2.gif");
		explo = tutorial.loadImg("/ressources/grafics/Explode12.png");
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

				if (tutorial.getObj(x, y, 0) == Spielfeld.festeMauer) {
					g.drawImage(mauer, x * arrayWidth, y * arrayHeight,
							arrayWidth, arrayHeight, null);

				}

				if (tutorial.getObj(x, y, 0) == Spielfeld.Gras) {
					g.drawImage(gras, x * arrayWidth, y * arrayHeight,
							arrayWidth, arrayHeight, null);
				}
				if (tutorial.getObj(x, y, 3) == Spielfeld.Bomberman1) {
					g.drawImage(man, x * arrayWidth, y * arrayHeight,
							arrayWidth, arrayHeight, null);
				}
				if (tutorial.getObj(x, y, 4) == Spielfeld.Bombe) {
					g.drawImage(bomb, x * arrayWidth, y * arrayHeight,
							arrayWidth, arrayHeight, null);
				}
				if (tutorial.getObj(x, y, 3) == Spielfeld.Explosion) {
					g.drawImage(explo, x * arrayWidth, y * arrayHeight,
							arrayWidth, arrayHeight, null);
				}

			}

		}
		repaint();
	}
}
