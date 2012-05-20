package game;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import spielfeld.Spielflaeche;
import tools.GameQuitter;

public class Dummy extends Thread {
	public static Spielflaeche game;
	public static JFrame opener;

	public Dummy() { // Konstruktor erzeugt Frame mit Spielfeld
		opener = new JFrame("Graphicstester");
		GameQuitter gQuit = new GameQuitter(opener); // Erzeugt WindowListener
		game = new Spielflaeche();
		opener.add(game);
		opener.setLocation(400, 25);
		opener.setSize(600, 600);
		opener.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		opener.addWindowListener(gQuit);

		gameKeyListener gkl = new gameKeyListener();
		opener.addKeyListener(gkl);

		Runnable gui = new Runnable() {

			@Override
			public void run() { // der Thread zeichnet durchgehend neu
				// game.paint();
				opener.repaint();
				opener.setVisible(true);
			}

		};
		SwingUtilities.invokeLater(gui); // wartet bis ausgefuehrt

	}
}
