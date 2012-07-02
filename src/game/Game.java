package game;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import spielfeld.Spielflaeche;
import tools.GameKeyListener;
import tools.GameQuitter;

public class Game extends Thread {
	// initialisiere Variablen
	public static Spielflaeche game; // neue "leere" Spieflaeche
	public static JFrame gameFrame; // neues "leeres" Fenster
	public static GameKeyListener gkl;

	public Game() { // Konstruktor erzeugt Frame mit Spielfeld
		go();
	}

	private static void go() { // Methode zum Spielstart
		gameFrame = new JFrame("Bomberman"); // Deklariert gameFrame zu
		// neuem Fenster
		GameQuitter gQuit = new GameQuitter(gameFrame); // Erzeugt
		// WindowListener gQuit
		// und übergibt den
		// Frame "gameFrame"
		game = new Spielflaeche(); // Deklariert game zu NEUER Spielflaeche (ein
		// JPanel)
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
				gameFrame.repaint();
				gameFrame.setVisible(true);
			}

		};
		SwingUtilities.invokeLater(gui); // wartet bis ausgefuehrt
	}

	public static synchronized void restartGame(int sieger) { // Methode zum
																// Spielneustart
		gameFrame.dispose(); // Frame wird geschlossen
		gameFrame = new JFrame("Ende");// Ende Fenster
		gameFrame.setLocation(350, 20); // Stellt Position des Fensters ein
		gameFrame.setSize(200, 150); // .. und die Größe
		GameQuitter gQuit = new GameQuitter(gameFrame); // Erzeugt
		// WindowListener gQuit
		// und übergibt den
		// Frame "gameFrame"
		gameFrame.addWindowListener(gQuit); // fügt WindowListener gQuit dem
		// Frame bei
		// ImageIcon i = new ImageIcon("/ressources/grafics/winner.png");
		JLabel j = new JLabel("Spieler " + sieger + " gewinnt.");

		gameFrame.add(j);
		gameFrame.setVisible(true);

	}
}
