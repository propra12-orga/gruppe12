package spielfigur;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import spielfeld.Bombe;

public class Spielfigur implements KeyListener {

	private int xpos;
	private int ypos;

	public Spielfigur() {
		addKeyListener(this);
	}

	public Spielfigur(int xpos, int ypos) {
		this.ypos = ypos;
		this.xpos = xpos;
	}

	private int getxPosition() {
		return xpos;
	}

	private void setxPosition(int xpos) {
		this.xpos = xpos;
	}

	private int getyPosition() {
		return ypos;
	}

	private void setyPosition(int ypos) {
		this.xpos = ypos;
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
