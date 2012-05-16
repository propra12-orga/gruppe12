package spielfigur;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import spielfeld.Spielflaeche;
import bombe.BombType;
import bombe.Bombe;
import bombe.NormalBomb;

public class Spielfigur implements KeyListener {

	// Dekleration von Variabeln

	public int xPosition;
	public int yPosition;
	protected int width;
	protected int height;
	protected String pic;
	private boolean bombPlanted = false;
	private BombType bomb = new NormalBomb();

	public Spielfigur() {
		addKeyListener(this);
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
			Bombe b = new Bombe(xPosition, yPosition, width, height, bomb);
			Spielflaeche.play.fill(xPosition, yPosition, 4);
			bombPlanted = true;
		}

	}

	private void addKeyListener(Spielfigur Spielfigur) {

	}

	// Hier werden die Bewegungen mit der Tastatur geregelt

	public void keyTyped(KeyEvent e) {

	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == e.VK_UP) {
			this.yPosition = yPosition - 1;
		}

		if (e.getKeyCode() == e.VK_LEFT) {

		}

		if (e.getKeyCode() == e.VK_RIGHT) {
			;
		}

		if (e.getKeyCode() == e.VK_DOWN) {

		}

	}

}
