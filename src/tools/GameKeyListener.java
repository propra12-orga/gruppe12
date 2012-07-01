package tools;

import game.Tutorial;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import spielfeld.Spielfeld;
import spielfeld.Spielflaeche;

public class GameKeyListener implements KeyListener {
	public static boolean sichtbar, boolSave;

	/**
	 * Die Funktion dieser Klasse ist die Bedienung des Programms mit Hilfe der
	 * Tastatur. Auf die einzelnen Tasten wurde jeweils die move bzw. move2
	 * Methoden gelegt.
	 * 
	 * Tastaturbelegung:
	 * 
	 * Player 1:
	 * 
	 * Pfeiltaste hoch = bman geht hoch Pfeiltaste runter = bman geht runter
	 * Pfeiltaste rechts = bman geht rechts Pfeiltaste links = bman geht links
	 * Space = bman legt Bombe
	 * 
	 * Player 2:
	 * 
	 * w = bman2 geht hoch s = bman2 geht runter a = bman2 geht links d = bman2
	 * geht rechts g = bman2 legt Bombe
	 */

	@Override
	public void keyPressed(KeyEvent e) {

		// Fall: Hochtaste gedrückt ==> Hoch bewegen
		if (e.getKeyCode() == KeyEvent.VK_UP) {

			Spielflaeche.bman.move(0, -1);
			if (Tutorial.tutorialMode && Framebutton.zaehler == 1) {
				Tutorial.moved1 = true;
			}
		}
		// Fall: Linkstaste gedrückt ==> Links bewegen
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {

			Spielflaeche.bman.move(-1, 0);
			if (Tutorial.tutorialMode && Framebutton.zaehler == 1) {
				Tutorial.moved2 = true;
			}
		}
		// Fall: Rechtstaste gedrückt ==> Rechts bewegen
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			Spielflaeche.bman.move(1, 0);
			if (Tutorial.tutorialMode && Framebutton.zaehler == 1) {
				Tutorial.moved3 = true;
			}
		}
		// Fall: Runtertaste gedrückt ==> Runter bewegen
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			Spielflaeche.bman.move(0, 1);
			if (Tutorial.tutorialMode && Framebutton.zaehler == 1) {
				Tutorial.moved4 = true;
			}
		}
		// Fall: Leertaste gedrückt ==> Bombe legen
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			Spielflaeche.bman.bombeLegen();
			if (Tutorial.tutorialMode && Framebutton.zaehler == 2) {
				Tutorial.bombeplan = true;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_F5) {
			try {
				boolSave = true;
				Spielfeld.save(Spielflaeche.play);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
