package tools;

import game.LoadMap;
import game.Tutorial;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class GameQuitter extends WindowAdapter // WindowListener
{
	JFrame GamePan;

	// Konstruktor
	public GameQuitter(JFrame frame) {
		GamePan = frame;
	}

	public void windowClosing(WindowEvent e) {
		menu.MainMenu.gamerunning = false;// teilt StartMenu mit, dass das
		LoadMap.setLoadtext(false); // Spiel geschlossen ist.
		GamePan.dispose(); // schließt Fenster
		if (Tutorial.tutorialMode) {
			Tutorial.tutorialMode = false;
		}
	}
}