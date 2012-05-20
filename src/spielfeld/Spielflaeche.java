package spielfeld;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import spielfigur.Spielfigur;

/* *** Update ***/
/* neue Version mit dynamischer Vergroeßerung der Bilder */

public class Spielflaeche extends JPanel implements KeyListener {

	public static Spielfeld play;
	public static Spielfigur bman;

	private static final long serialVersionUID = 1L;

	public Spielflaeche() {
		play = new Spielfeld(21, 21);
		bman = new Spielfigur(1, 1);

		play.feldfuellen(); // Befuellt die Raender mit Mauer
		play.fill(19, 19, 1); // Ausgang
		play.fill(1, 2, 4); // Testbombe
		play.fill(5, 5, 10); // Testfigur

	}

	public void paint(Graphics g) {
		int arrayWidth = getWidth() / 21 + 1;
		int arrayHeight = getHeight() / 21 + 1;
		Image gras = play.loadImg("/ressources/grafics/gras.gif");
		Image mauer = play.loadImg("/ressources/grafics/brick.jpg");
		Image exit = play.loadImg("/ressources/grafics/ausgang.jpg");
		Image bomb = play.loadImg("/ressources/grafics/Bombe.gif");
		Image man = play.loadImg("/ressources/grafics/e.gif");
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

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

		if (e.getKeyCode() == KeyEvent.VK_UP) {
			System.out.println("hoch");
			Spielflaeche.play.fill(bman.xPosition, bman.yPosition, 0);
			Spielflaeche.play.fill(bman.xPosition, bman.yPosition + 1, 10);
			bman.xPosition = bman.xPosition;
			bman.yPosition = bman.yPosition + 1;

			// objekt bewegen
		}

		else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			Spielflaeche.play.fill(bman.xPosition, bman.yPosition, 0);
			Spielflaeche.play.fill(bman.xPosition - 1, bman.yPosition, 10);
			bman.xPosition = bman.xPosition - 1;
			bman.yPosition = bman.yPosition;

		}

		else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			Spielflaeche.play.fill(bman.xPosition, bman.yPosition, 0);
			Spielflaeche.play.fill(bman.xPosition + 1, bman.yPosition, 10);
			bman.xPosition = bman.xPosition + 1;
			bman.yPosition = bman.yPosition;

		}

		else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			Spielflaeche.play.fill(bman.xPosition, bman.yPosition, 0);
			Spielflaeche.play.fill(bman.xPosition, bman.yPosition - 1, 10);
			bman.xPosition = bman.xPosition;
			bman.yPosition = bman.yPosition - 1;

		}

		else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			bman.bombeLegen();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			System.out.println("hoch");
			Spielflaeche.play.fill(bman.xPosition, bman.yPosition, 0);
			Spielflaeche.play.fill(bman.xPosition, bman.yPosition + 1, 10);
			bman.xPosition = bman.xPosition;
			bman.yPosition = bman.yPosition + 1;
		}
	}

}
