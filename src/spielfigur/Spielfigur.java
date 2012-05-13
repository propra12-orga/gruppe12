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

	public void bombeLegen() {
		Bombe b = new Bombe(1, 2);// statt 1,2 müssen hier x und y position vom
									// bomberman übergeben werden.

	}

	private void addKeyListener(Spielfigur Spielfigur) {

	}

	public void keyTyped(KeyEvent e) {

	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {

		if (key.getKeyCode() == Key.VK_UP) {

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
