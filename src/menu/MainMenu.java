package menu;

import game.RunGame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import menu.mapChoose.MapChooser;

public class MainMenu extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Wahrheitswert sagt aus, ob bereits ein Spiel läuft
	public static boolean gamerunning = false;

	// Panels und zugehoerige Buttons initialisieren
	MenuButton start = new MenuButton("Start");
	MenuButton dummy = new MenuButton("Dummy");
	MenuButton loadMap = new MenuButton("Lade Karte");
	MenuButton sets = new MenuButton("Sets");
	MenuButton exit = new MenuButton("Exit");

	// ...ebenso das Titelpanel
	JPanel titlepanel = new JPanel();
	JLabel title = new JLabel("Ganz dolles Spiel");

	// Konstruktor
	public MainMenu() {

		getContentPane().setLayout(
				new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		getContentPane().setBackground(Color.BLACK);

		// Buttons den Panels hinzufuegen
		titlepanel.setBackground(null);
		title.setForeground(Color.WHITE);
		titlepanel.add(title);

		// Panels dem Oberpanel hinzufuegen
		getContentPane().add(titlepanel);
		getContentPane().add(start.getPanel());
		getContentPane().add(dummy.getPanel());
		getContentPane().add(loadMap.getPanel());
		getContentPane().add(sets.getPanel());
		getContentPane().add(exit.getPanel());

		// Buttons beim ActionListener registrieren
		start.getButton().addActionListener(this);
		start.getButton().setActionCommand("start");

		dummy.getButton().addActionListener(this);
		dummy.getButton().setActionCommand("dummy");

		loadMap.getButton().addActionListener(this);
		loadMap.getButton().setActionCommand("loadMap");

		sets.getButton().addActionListener(this);
		sets.getButton().setActionCommand("sets");

		exit.getButton().addActionListener(this);
		exit.getButton().setActionCommand("exit");
	}

	// ActionLister: Hier werden den Buttones Aktionen zugeordnet.
	public void actionPerformed(ActionEvent evt) {
		// Startet das Spiel
		if (evt.getActionCommand().equals("start") && gamerunning == false) {
			RunGame.go();
			gamerunning = true;
		}
		// Startet den Tests (falls implementiert)
		if (evt.getActionCommand().equals("dummy")) {

		}
		// Startet Spiel mit vorgegebener Karte
		if (evt.getActionCommand().equals("loadMap") && gamerunning == false) {
			MapChooser.go();
		}
		// Startet die Settings (2do)
		if (evt.getActionCommand().equals("sets")) {
		}
		// Beendet das Programm
		if (evt.getActionCommand().equals("exit")) { // Beendet das Programm
			System.exit(0);
		}

	}
}
