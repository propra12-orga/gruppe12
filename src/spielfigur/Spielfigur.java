package spielfigur;

import game.Game;
import spielfeld.Spielfeld;
import spielfeld.Spielflaeche;
import bombe.BombType;
import bombe.Bombe;
import bombe.NormalBomb;

public class Spielfigur {

	// Initialisierung von Variabeln

	public int xPosition; // aktuelle Position auf der "x-Achse" der
							// Spielfläche
	public int yPosition; // aktuelle Position auf der "y-Achse" der
							// Spielfläche
	public int dimension;
	protected int width;
	protected int height;
	protected String pic;
	private boolean bombPlanted = false;
	private BombType bomb = new NormalBomb();

	public boolean bombeLiegt;

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
	/*
	 * Die Methode move bzw. move2 regeln die Bewegungen der Figuren "man" und
	 * "man2. Benutzt werden diese Methoden vom gamekeylistener. Die Bewegung
	 * wird durch Abfragen , der in der jeweiligen Bewegungsrichtung vorhandenen
	 * Objekte realisiert. Es wird abgefragt, was sich auf dem jeweiligen Feld
	 * befindet und falls Bewegung logisch erscheint, wird die jeweilige Figur
	 * auf dieses Feld bewegt. (Änderung der Variablen xPosition und yPosition
	 * des jeweiligen Spielfigurenobjektes
	 */
	public void move(int x, int y) {

		/*
		 * Das Spiel wird neu gestartet wenn Bomberman und Ausgang sich auf den
		 * selben Koordinaten befinden.
		 */

		if (Spielflaeche.play.getObj(xPosition + x, yPosition + y, 1) == Spielfeld.Ausgang
				&& Spielflaeche.play.getObj(xPosition + x, yPosition + y, 2) != Spielfeld.Kiste) {
			xPosition = xPosition + x;
			yPosition = yPosition + y;
			Game.restartGame();
		}

		/*
		 * wenn das angepeilte Feld eine Explosion ist, dann wird die Figur
		 * dorthin bewegt. Allerdings stirbt diese dann --> Spieler2 hat
		 * gewonnen --> Spiel startet neu
		 */

		else if (Spielflaeche.play.equalsExplosion(xPosition + x,
				yPosition + y, 3)
				|| (Spielflaeche.play.equalsExplosion(xPosition, yPosition, 3))) {
			Spielflaeche.play.fill(xPosition, yPosition, 3, Spielfeld.Gras);
			xPosition = xPosition + x;
			yPosition = yPosition + y;
			System.out.println("Player 2 siegt");
			// Label soll erstellt werden // Tot - wanna restart?

		}

		/*
		 * Es wird festgestellt, ob das angepeilte Feld bereits mit einem
		 * Objekt, das unpassierbarist, belegt ist. Wenn es passierbar ist geht
		 * die Abfrage weiter.
		 */
		else if (Spielflaeche.play.getObj(xPosition + x, yPosition + y, 2) == null
				&& Spielflaeche.play.getObj(xPosition + x, yPosition + y, 4) == null
				&& Spielflaeche.play.equalsMauer(xPosition + x, yPosition + y) == false) {
			/*
			 * Es wird abgefragt ob eine Bombe gelegt wurde. Wenn eine Bombe
			 * gelegt wurde, dann wird auf den Variablen xPostítion und
			 * yPosition des Spielfigurenobjektes eine Bombe gezeichnet.
			 */
			Spielflaeche.play.fill(xPosition, yPosition, 3, Spielfeld.Gras);
			if (Spielflaeche.bman.bombeLiegt) {
				Spielflaeche.play
						.fill(xPosition, yPosition, 4, Spielfeld.Bombe);
				bombeLiegt = false; // Noch eine Bedingung ( Wenn Explo --> Bman
									// auf explo
				// sieht verkohlt aus

			}
			/*
			 * Bewegung der Figur
			 */

			xPosition = xPosition + x;
			yPosition = yPosition + y;
		}

	}// move

	public void move2(int x, int y) {
		/*
		 * 
		 * Diese Methode funktioniert analog zu move ausser ,dass hierbei nicht
		 * das Objekt"man" angesprochen wird sondern "man2"
		 */
		if (Spielflaeche.play.getObj(xPosition + x, yPosition + y, 1) == Spielfeld.Ausgang
				&& Spielflaeche.play.getObj(xPosition + x, yPosition + y, 2) != Spielfeld.Kiste) {
			xPosition = xPosition + x;
			yPosition = yPosition + y;
			Game.restartGame();

		}
		/*
		 * Checkt ob Spieler 2 in eine Explo rennt
		 */

		else if (Spielflaeche.play.equalsExplosion(xPosition + x,
				yPosition + y, 3)) {
			Spielflaeche.play.fill(xPosition, yPosition, 3, Spielfeld.Gras);
			xPosition = xPosition + x;
			yPosition = yPosition + y;
			System.out.println("Player1 siegt");
		}

		// Label soll erstellt werden // Tot - wanna restart?

		/*
		 * 
		 * Checkt ob das Feld auf das Player 2 rennen soll leer ist
		 */
		else if (Spielflaeche.play.getObj(xPosition + x, yPosition + y, 2) == null
				&& Spielflaeche.play.getObj(xPosition + x, yPosition + y, 4) == null
				&& Spielflaeche.play.equalsMauer(xPosition + x, yPosition + y) == false) {

			Spielflaeche.play.fill(xPosition, yPosition, 3, Spielfeld.Gras);
			if (Spielflaeche.bman2.bombeLiegt) {
				Spielflaeche.play
						.fill(xPosition, yPosition, 4, Spielfeld.Bombe);
				bombeLiegt = false;
			}
			xPosition = xPosition + x;
			yPosition = yPosition + y;
		}
	}
}// move2
