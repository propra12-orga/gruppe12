package game;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import menu.MainMenu;
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

		tut = new Spielflaeche();

		/*
		 * Dem Fenster fuer das Tutorial gameFrame wird das Spiel tut
		 * hinzugefuegt. Seine Position auf 550,20 und seine Groeße auf 720x720
		 * gesetzt. Der Windowlistener gQuit macht das Fenster schließbar
		 */
		gameFrame.add(tut);
		gameFrame.setLocation(550, 20);
		gameFrame.setSize(720, 720);
		gameFrame.addWindowListener(gQuit);

		/*
		 * die Steuerung wird hinzugefuegt mit einem GameKeyListener
		 */
		gkl = new GameKeyListener();
		gameFrame.addKeyListener(gkl);

		/*
		 * Erstellen der Tutorial-Fenster
		 */

		/*
		 * dialog ist ein JDialog und auf dem werden wir die einzelnen Panel
		 * setzen. dialog ist quasi das Fenster was neben dem Spielfenster
		 * aufspringt.Und die einzelnen Anweisungen werden als Panel realisiert.
		 */

		dialog = new JDialog(gameFrame, "Hinweise", false);

		/*
		 * fuege dem Fenster einen Knopf dazu. Auf dem Knopf steht der String
		 * "OK" er wird mittig und unten auf dem Fenster zu sehen sein. Sein
		 * Actionlistener wechselt (nach einem klick + bestimmter Handlung des
		 * Spielers) zwischen den Dialogen.
		 */
		ok = new JButton("OK");
		ok.addActionListener(new Framebutton());
		ok.setBackground(Color.red);

		dialog.add(ok, BorderLayout.SOUTH);
		dialog.setSize(400, 400);
		dialog.setVisible(true);

		/*
		 * Start-Bildschirm des Tutorials.
		 */
		begrueßung = new JPanel();
		begrueßung.setLayout(new BorderLayout());
		begrueßung.setBackground(Color.black);
		begrueßung
				.add(new JLabel(
						"<html><body>Willkommen in der Welt von Bomberman!<br>Das Tutorial zeigt dir wie man spielt<br> Wir wuenschen eine gute Unterhaltung.<br>Beginnen wir nun mit Lektion 1.<br> Drücke OK um fortzufahren<body><html>"));
		// kann man direkt hinzufuegen um die Ausgabe fuer den Benutzer zu
		// haben.
		dialog.add(begrueßung);
		/*
		 * 2. Punkt des Tutorials, die Steuerung wird erklaert.
		 */
		steuerung = new JPanel();
		steuerung.setLayout(new BorderLayout());
		steuerung.setBackground(Color.black);
		steuerung
				.add(new JLabel(
						"<html><body>Das Spiel wird mithilfe der Pfeiltasten gesteuert.<br>Probier es aus,danach kannst du weiter<body><html>"));
		/*
		 * 3. Punkt des Tutorials, Erklaerung der Bombe.
		 */
		bombe = new JPanel();
		bombe.setLayout(new BorderLayout());
		bombe.setBackground(Color.black);
		bombe.add(new JLabel(
				"<html><body>Mit der Leertaste kannst du Bomben legen.<br>Nachdem du eine Bombe gelegt hast <br> kannst du fortfahren mit 'OK' <br> Vorsicht,explosiv!<body><html>"));
		/*
		 * letzter Punkt, der Spieler soll zum Ausgang gehen
		 */
		ausgang = new JPanel();
		ausgang.setLayout(new BorderLayout());
		ausgang.setBackground(Color.black);
		ausgang.add(new JLabel("Gehe durch den Ausgang!"));

		gameFrame.setVisible(true);

		Runnable gui = new Runnable() {
			@Override
			public void run() { // der Thread zeichnet durchgehend neu

				gameFrame.repaint();

			}

		};
		SwingUtilities.invokeLater(gui); // wartet bis ausgefuehrt
	}
	/*
	 * Routine fuer das Erreichen des Ausganges. Das Tutorial ist erfolgreich
	 * absolviert. ES kommt eine Meldung und das Tutorial wird
	 * beendet/zurueckgesetzt.
	 */
	public static void restartTut() {
		gameFrame.dispose();
		Framebutton.zaehler = 0;
		JFrame sieg = new JFrame("Du hast gewonnen!");
		sieg.setSize(400, 400);
		sieg.setLocation(600, 200);
		sieg.getContentPane().setBackground(Color.black);
		sieg.add(new JLabel(
				"<html><body>Wahnsinn du hast es geschafft!<br>Viel Spaß weiterhin.<body><html>"));
		sieg.setVisible(true);
		tutorialMode = false;
		MainMenu.gamerunning = false;
	}
}
