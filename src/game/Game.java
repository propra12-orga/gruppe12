package game;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import menu.MainMenu;
import spielfeld.Spielflaeche;
import tools.GameKeyListener;
import tools.GameQuitter;

/**
 * Klasse zum Spielstart.
 */

public class Game extends Thread {
	// initialisiere Variablen
	public static Spielflaeche game; // neue "leere" Spieflaeche
	public static JFrame gameFrame; // neues "leeres" Fenster
	public static GameKeyListener gkl;

	public Game() { // Konstruktor erzeugt Frame mit Spielfeld
		go();

	}

	/**
	 * Methode zum Spielstart, es wird ein Fenster erzeugt und ein Thread der es
	 * durchgehend neu zeichnet.
	 */
	public static void go() { // Methode zum Spielstart

		gameFrame = new JFrame("Bomberman"); // Deklariert gameFrame zu
		// neuem Fenster
		GameQuitter gQuit = new GameQuitter(gameFrame); // Erzeugt
		// WindowListener gQuit
		// und übergibt den
		// Frame "gameFrame"
		game = new Spielflaeche(); // Deklariert game zu NEUER Spielflaeche (ein
		// JPanel)
		if (Spielflaeche.network) {

		}

		gameFrame.add(game); // Fügt game zum Fenster zu
		gameFrame.setLocation(350, 20); // Stellt Position des Fensters ein
		gameFrame.setSize(720, 720); // .. und die Größe
		gameFrame.addWindowListener(gQuit); // fügt WindowListener gQuit dem
		// Frame bei

		gkl = new GameKeyListener(); // erzeugt GameKeyListener
		// "gkl"
		gameFrame.addKeyListener(gkl); // fügt GameKeyListener "gkl" dem Frame
		// bei

		Runnable gui = new Runnable() {
			@Override
			public void run() { // der Thread zeichnet durchgehend neu
				MainMenu.gamerunning = true;
				gameFrame.repaint();
				gameFrame.setVisible(true);
			}

		};
		SwingUtilities.invokeLater(gui); // wartet bis ausgefuehrt
	}

	public static synchronized void restartGame(int sieger) { // Methode zum
																// Spielneustart
		gameFrame.dispose(); // Frame wird geschlossen
		JFrame EndFrame = new JFrame("Ende");// Ende Fenster
		EndFrame.setLocation(350, 20); // Stellt Position des Fensters ein

		EndFrame.setSize(400, 300); // .. und die Größe
		EndFrame.getContentPane().setBackground(Color.black);

		GameQuitter gQuit = new GameQuitter(gameFrame); // Erzeugt
		// WindowListener gQuit
		// und übergibt den
		// Frame "gameFrame"
		EndFrame.addWindowListener(gQuit); // fügt WindowListener gQuit dem
		// Frame bei
		// ImageIcon i = new ImageIcon("/ressources/grafics/winner.png");
		JLabel j = new JLabel("Spieler " + sieger + " gewinnt.");
		j.setForeground(Color.WHITE);

		EndFrame.add(j);
		EndFrame.setVisible(true);

	}
}
