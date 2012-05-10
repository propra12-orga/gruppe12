package spielfigur;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import spielfeld.Bombe;

public class Spielfigur implements KeyListener {

	public Spielfigur() {
		addKeyListener(this);
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
	}

}
