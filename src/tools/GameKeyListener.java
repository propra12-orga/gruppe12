package tools;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import spielfeld.Spielflaeche;

public class GameKeyListener implements KeyListener {

	@Override
	public void keyPressed(KeyEvent e) {
		// Fall: Hochtaste gedrückt ==> Hoch bewegen
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			Spielflaeche.bman.move(0, -1);
		}
		// Fall: Linkstaste gedrückt ==> Links bewegen
		else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			Spielflaeche.bman.move(-1, 0);
		}
		// Fall: Rechtstaste gedrückt ==> Rechts bewegen
		else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			Spielflaeche.bman.move(1, 0);
		}
		// Fall: Runtertaste gedrückt ==> Runter bewegen
		else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			Spielflaeche.bman.move(0, 1);
		}
		// Fall: Leertaste gedrückt ==> Bombe legen
		else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			Spielflaeche.bman.bombeLegen();

		}
		// same for player 2
		if (e.getKeyCode() == KeyEvent.VK_W) {
			Spielflaeche.bman2.move2(0, -1);
		} else if (e.getKeyCode() == KeyEvent.VK_S) {
			Spielflaeche.bman2.move2(0, 1);
		} else if (e.getKeyCode() == KeyEvent.VK_A) {
			Spielflaeche.bman2.move2(-1, 0);
		} else if (e.getKeyCode() == KeyEvent.VK_D) {
			Spielflaeche.bman2.move2(1, 0);
		} else if (e.getKeyCode() == KeyEvent.VK_G) {
			Spielflaeche.bman2.bombeLegen();
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
