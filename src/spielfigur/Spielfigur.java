package spielfigur;

import spielfeld.Spielflaeche;
import bombe.BombType;
import bombe.Bombe;
import bombe.NormalBomb;

public class Spielfigur {

	// Dekleration von Variabeln

	public int xPosition;
	public int yPosition;
	protected int width = 200;
	protected int height = 200;
	protected String pic;
	private boolean bombPlanted = false;
	private BombType bomb = new NormalBomb();

	public boolean rechts = false;
	public boolean links = false;
	public boolean runter = false;
	public boolean hoch = false;

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

	// Bombe

	public void bombeLegen() {
		if (bombPlanted == false) {
			new Bombe(xPosition, yPosition, width, height, bomb).explodieren();
			Spielflaeche.play.fill(xPosition, yPosition, 4);
			bombPlanted = true;

		}

	}

}
