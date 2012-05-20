package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import spielfeld.Spielflaeche;

public class gameKeyListener implements KeyListener {

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

		if (e.getKeyCode() == KeyEvent.VK_UP) {
			Spielflaeche.play.fill(Spielflaeche.bman.xPosition,
					Spielflaeche.bman.yPosition, 0);
			Spielflaeche.bman.xPosition = Spielflaeche.bman.xPosition;
			Spielflaeche.bman.yPosition = Spielflaeche.bman.yPosition - 1;

		}

		else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			Spielflaeche.play.fill(Spielflaeche.bman.xPosition,
					Spielflaeche.bman.yPosition, 0);
			Spielflaeche.bman.xPosition = Spielflaeche.bman.xPosition - 1;
			Spielflaeche.bman.yPosition = Spielflaeche.bman.yPosition;

		}

		else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

			Spielflaeche.play.fill(Spielflaeche.bman.xPosition,
					Spielflaeche.bman.yPosition, 0);
			Spielflaeche.bman.xPosition = Spielflaeche.bman.xPosition + 1;
			Spielflaeche.bman.yPosition = Spielflaeche.bman.yPosition;

		}

		else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			Spielflaeche.play.fill(Spielflaeche.bman.xPosition,
					Spielflaeche.bman.yPosition, 0);
			Spielflaeche.bman.xPosition = Spielflaeche.bman.xPosition;
			Spielflaeche.bman.yPosition = Spielflaeche.bman.yPosition + 1;

		}

		else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			Spielflaeche.bman.bombeLegen();
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
