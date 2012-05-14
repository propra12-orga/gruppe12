package spielfigur;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import spielfeld.Bombe;

public class Spielfigur implements KeyListener {

	// Dekleration von Variabeln

	public int xPosition;
	public int yPosition;
	protected int width;
	protected int height;
	protected String pic;

	public Spielfigur() {
		addKeyListener(this);
	}

	public Spielfigur(int xPosition, int yPosition) {
		this.yPosition = yPosition;
		this.xPosition = xPosition;
	}

	// get und set methoden

	private int getxPosition() {
		return xPosition;
	}

	private void setxPosition(int xPosition) {
		this.xPosition = xPosition;
	}

	private int getyPosition() {
		return yPosition;
	}

	private void setyPosition(int yPosition) {
		this.yPosition = yPosition;
	}

	// Bombe

	public void bombeLegen() {
		Bombe b = new Bombe(this.xPosition, this.yPosition);

	}

	private void addKeyListener(Spielfigur Spielfigur) {

	}

	// Hier werden die Bewegungen mit der Tastatur geregelt

	public void keyTyped(KeyEvent e) {

	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {

		if (key.getKeyCode() == Key.VK_UP) {
			this.yPosition = yPosition - 1;
		}

		if (Key.getKeyCode() == Key.VK_LEFT) {

		}

		if (Key.getKeyCode() == Key.VK_RIGHT) {
			;
		}

		if (Key.getKeyCode() == Key.VK_DOWN) {

		}

	}

}
