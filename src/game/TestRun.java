package game;

import javax.swing.JFrame;

import menu.MainMenu;

import spielfeld.Spielflaeche;
import tools.GameQuitter;

public class TestRun {
	public static void go() {
		JFrame opener = new JFrame("Graphicstester");
		GameQuitter gQuit = new GameQuitter(opener); // Erzeugt WindowListener
														// vom Typ GameQuitter

		opener.setLocation(400, 25);
		opener.setSize(822, 849);

		opener.addWindowListener(gQuit); // gQuit wird hinzugefügt.

		opener.add(new Spielflaeche());
		opener.setVisible(true);
		MainMenu.gamerunning = false; // teilt StartMenu mit, dass das Spiel
										// läuft.
	}

}