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
	protected int width;
	protected int height;
	protected String pic;
	private boolean bombPlanted = false;
	private BombType bomb = new NormalBomb();

	public boolean bombeLiegt;

	public Spielfigur() {
		addKeyListener(this);
	}

	private void addKeyListener(Spielfigur spielfigur) {
		// TODO Auto-generated method stub

	}

	public Spielfigur(int xPosition, int yPosition) {
		this.yPosition = yPosition;
		this.xPosition = xPosition;
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
			Spielflaeche.play.fill(xPosition, yPosition, Spielfeld.Bombe);
			bombPlanted = true;
			bombeLiegt = true;
		}
	}

	public void move(int x, int y) {
		if (Spielflaeche.play.equalsGras(xPosition + x, yPosition + y)) {

			Spielflaeche.play.fill(xPosition, yPosition, Spielfeld.Gras);
			if (Spielflaeche.bman.bombeLiegt) {
				Spielflaeche.play.fill(xPosition, yPosition, Spielfeld.Bombe);
				bombeLiegt = false;
			}
			xPosition = xPosition + x;
			yPosition = yPosition + y;
		} else if (Spielflaeche.play.equalsExit(xPosition + x, yPosition + y)) {
			xPosition = xPosition + x;
			yPosition = yPosition + y;
			Game.restartGame();
		} else if (Spielflaeche.play.equalsExplosion(xPosition + x, yPosition
				+ y)
				|| (Spielflaeche.play.equalsExplosion(xPosition, yPosition))) {
			Spielflaeche.play.fill(xPosition, yPosition, Spielfeld.Gras);
			xPosition = xPosition + x;
			yPosition = yPosition + y;
			System.out.println("DU TOT");
			// Label soll erstellt werden // Tot - wanna restart?

		}

	}

	public void move2(int x, int y) {
		if (Spielflaeche.play.equalsGras(xPosition + x, yPosition + y)) {

			Spielflaeche.play.fill(xPosition, yPosition, Spielfeld.Gras);
			if (Spielflaeche.bman2.bombeLiegt) {
				Spielflaeche.play.fill(xPosition, yPosition, Spielfeld.Bombe);
				bombeLiegt = false;
			}
			xPosition = xPosition + x;
			yPosition = yPosition + y;
		} else if (Spielflaeche.play.equalsExit(xPosition + x, yPosition + y)) {
			xPosition = xPosition + x;
			yPosition = yPosition + y;
			Game.restartGame();
		} else if (Spielflaeche.play.equalsExplosion(xPosition + x, yPosition
				+ y)) {
			Spielflaeche.play.fill(xPosition, yPosition, Spielfeld.Gras);
			xPosition = xPosition + x;
			yPosition = yPosition + y;
			System.out.println("DU TOT");
			// Label soll erstellt werden // Tot - wanna restart?

		}
	}
}