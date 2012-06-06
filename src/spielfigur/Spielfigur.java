package spielfigur;

import game.Game;
import spielfeld.Spielfeld;
import spielfeld.Spielflaeche;
import bombe.BombType;
import bombe.Bombe;
import bombe.NormalBomb;

public class Spielfigur {

	// Initialisierung von Variabeln

	public int xPosition; // aktuelle Position auf der "x-Achse" der Spielfläche
	public int yPosition; // aktuelle Position auf der "y-Achse" der Spielfläche
	public int dimension;
	protected int width;
	protected int height;
	protected String pic;
	private boolean bombPlanted = false;
	private BombType bomb = new NormalBomb();

	public boolean bombeLiegt;

	private void addKeyListener(Spielfigur spielfigur) {
		// TODO Auto-generated method stub

	}

	public Spielfigur(int xPosition, int yPosition, int dimension) {
		this.yPosition = yPosition;
		this.xPosition = xPosition;
		this.dimension = dimension;
	}

	// get und set methoden

	public int getxPosition() {
		return xPosition;
	}

	public void setxPosition(int xPosition) {
		this.xPosition = xPosition;
	}

	public int getyPosition() {
		return yPosition;
	}

	public void setyPosition(int yPosition) {
		this.yPosition = yPosition;
	}

	public BombType getBombType() {
		return bomb;
	}

	public void setBombType(BombType bomb) {
		this.bomb = bomb;
	}

	public void setBombPlanted(boolean b) {
		bombPlanted = b;
	}

	// Bombe

	public void bombeLegen() {
		if (bombPlanted == false) {
			new Bombe(xPosition, yPosition, width, height, bomb).start();
			Spielflaeche.play.fill(xPosition, yPosition, 4, Spielfeld.Bombe);
			bombPlanted = true;
			bombeLiegt = true;
		}
	}

	public void move(int x, int y) {
		/*
		 * Abfrage ob schon ein Objekt bzw. Mauer in Bewegungsrichtung vorhanden
		 * ist
		 */
		if (Spielflaeche.play.getObj(xPosition + x, yPosition + y, 2) == null
				&& Spielflaeche.play.getObj(xPosition + x, yPosition + y, 4) == null
				&& Spielflaeche.play.equalsMauer(xPosition + x, yPosition + y) == false) {
			/*
			 * Checkt ob Bombe gelegt wurde, falls ja wird Bombengrafik auf den
			 * Feld hinterlassen
			 */
			Spielflaeche.play.fill(xPosition, yPosition, 3, Spielfeld.Gras);
			if (Spielflaeche.bman.bombeLiegt) {
				Spielflaeche.play
						.fill(xPosition, yPosition, 4, Spielfeld.Bombe);
				bombeLiegt = false; // Noch eine Bedingung ( Wenn Explo --> Bman
									// auf explo
				// sieht verkohlt aus

			}
			xPosition = xPosition + x;
			yPosition = yPosition + y;
		}
		/*
		 * Das Spiel wird neu gestartet wenn Bomberman in Ausgang
		 */

		else if (Spielflaeche.play.equalsExit(xPosition + x, yPosition + y, 1)
				&& Spielflaeche.play.getObj(xPosition + x, yPosition + y, 2) != Spielfeld.Kiste) {
			xPosition = xPosition + x;
			yPosition = yPosition + y;
			Game.restartGame();
		}
		/*
		 * Abfrage: bomberman kann in eine Explosion gehen: stirbt allerdings.
		 */

		else if (Spielflaeche.play.equalsExplosion(xPosition + x,
				yPosition + y, 3)
				|| (Spielflaeche.play.equalsExplosion(xPosition, yPosition, 3))) {
			Spielflaeche.play.fill(xPosition, yPosition, 3, Spielfeld.Gras);
			xPosition = xPosition + x;
			yPosition = yPosition + y;
			System.out.println("DU TOT");
			// Label soll erstellt werden // Tot - wanna restart?

		}

	}// move

	public void move2(int x, int y) {
		if (Spielflaeche.play.equalsGras(xPosition + x, yPosition + y)) {

			Spielflaeche.play.fill(xPosition, yPosition, 3, Spielfeld.Gras);
			if (Spielflaeche.bman2.bombeLiegt) {
				Spielflaeche.play
						.fill(xPosition, yPosition, 4, Spielfeld.Bombe);
				bombeLiegt = false;
			}
			xPosition = xPosition + x;
			yPosition = yPosition + y;
		} else if (Spielflaeche.play
				.equalsExit(xPosition + x, yPosition + y, 1)) {
			xPosition = xPosition + x;
			yPosition = yPosition + y;
			Game.restartGame();
		} else if (Spielflaeche.play.equalsExplosion(xPosition + x, yPosition
				+ y, 3)) {
			Spielflaeche.play.fill(xPosition, yPosition, 3, Spielfeld.Gras);
			xPosition = xPosition + x;
			yPosition = yPosition + y;
			System.out.println("DU TOT");
			// Label soll erstellt werden // Tot - wanna restart?

		}
	}// move2
}