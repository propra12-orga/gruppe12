package game;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import spielfeld.Spielflaeche;
import tools.Framebutton;
import tools.GameKeyListener;
import tools.GameQuitter;

public class Tutorial extends Thread {
	public static Spielflaeche tut; // neue "leere" Spieflaeche
	public static JFrame gameFrame; // neues "leeres" Fenster
	public static GameKeyListener gkl;
	public static boolean tutorialMode = false, moved1 = false, moved2 = false,
			moved3 = false, moved4 = false, bombeplan = false, move;
	public static JDialog dialog;
	public static JPanel begrueßung, steuerung, bombe, ausgang;
	public static JButton ok;

	public Tutorial() { // Konstruktor erzeugt Frame mit Spielfeld
		go();
	}

	private static void go() {// Methode zum Spielstart
		tutorialMode = true;

		gameFrame = new JFrame("Tutorial"); // Deklariert gameFrame zu
		// neuem Fenster
		GameQuitter gQuit = new GameQuitter(gameFrame); // Erzeugt
		// WindowListener gQuit
		// und übergibt den
		// Frame "gameFrame"
		tut = new Spielflaeche(); // Deklariert game zu NEUER Spielflaeche
									// (ein
		// JPanel)

		gameFrame.add(tut); // Fügt game zum Fenster zu
		gameFrame.setLocation(550, 20); // Stellt Position des Fensters ein
		gameFrame.setSize(720, 720); // .. und die Größe
		gameFrame.addWindowListener(gQuit); // fügt WindowListener gQuit dem
		// Frame bei

		gkl = new GameKeyListener(); // erzeugt GameKeyListener
		// "gkl"
		gameFrame.addKeyListener(gkl); // fügt GameKeyListener "gkl" dem Frame
		// bei

		/*
		 * Tutorial-Fenster
		 */
		dialog = new JDialog(gameFrame, "Hinweise", false);
		ok = new JButton("OK");
		ok.addActionListener(new Framebutton());
		ok.setBackground(Color.red);

		dialog.add(ok, BorderLayout.SOUTH);
		dialog.setSize(400, 400);
		dialog.setVisible(true);

		begrueßung = new JPanel();
		begrueßung.setLayout(new BorderLayout());
		begrueßung.setBackground(Color.black);
		begrueßung
				.add(new JLabel(
						"<html><body>Willkommen in der Welt von Bomberman!<br>Das Tutorial zeigt dir wie man spielt<br> Wir wuenschen eine gute Unterhaltung.<br>Beginnen wir nun mit Lektion 1.<br> Drücke OK um fortzufahren<body><html>"));

		dialog.add(begrueßung);

		steuerung = new JPanel();
		steuerung.setLayout(new BorderLayout());
		steuerung.setBackground(Color.black);
		steuerung
				.add(new JLabel(
						"<html><body>Das Spiel wird mithilfe der Pfeiltasten gesteuert.<br>Probier es aus,danach kannst du weiter<body><html>"));
		bombe = new JPanel();
		bombe.setLayout(new BorderLayout());
		bombe.setBackground(Color.black);
		bombe.add(new JLabel(
				"<html><body>Mit der Leertaste kannst du Bomben legen.<br>Nachdem du eine Bombe gelegt hast <br> kannst du fortfahren mit 'OK' <br> Vorsicht,explosiv!<body><html>"));

		ausgang = new JPanel();
		ausgang.setLayout(new BorderLayout());
		ausgang.setBackground(Color.black);
		ausgang.add(new JLabel("Gehe durch den Ausgang!"));

		gameFrame.setVisible(true);

		/*
		 * Label wird auf Panel platziert.
		 */

		Runnable gui = new Runnable() {
			@Override
			public void run() { // der Thread zeichnet durchgehend neu

				gameFrame.repaint();

			}

		};
		SwingUtilities.invokeLater(gui); // wartet bis ausgefuehrt
	}
	public static void restartTut() { // Methode zum Spielneustart
		gameFrame.dispose(); // Frame wird geschlossen
		Framebutton.zaehler = 0; // Spiel wird gestartet
		JFrame sieg = new JFrame("Du hast gewonnen!");
		sieg.setSize(400, 400);
		sieg.setLocation(600, 200);
		sieg.getContentPane().setBackground(Color.black);
		sieg.add(new JLabel(
				"<html><body>Wahnsinn du hast es geschafft!<br>Viel Spaß weiterhin.<body><html>"));
		sieg.setVisible(true);

	}
}
