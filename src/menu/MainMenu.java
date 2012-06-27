package menu;

import game.RunGame;
import game.zufallsKarte;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

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
	MenuButton randomMap = new MenuButton("Zufallskarte");
	MenuButton sets = new MenuButton("Sets");
	MenuButton exit = new MenuButton("Exit");

	// ...ebenso das Titelpanel
	JPanel titlepanel = new JPanel();
	JPanel buttons = new JPanel();
	JPanel bottom = new JPanel();
	JLabel title = new JLabel("Ganz dolles Spiel");

	// Konstruktor
	public MainMenu() {

		getContentPane().setLayout(
				new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		getContentPane().setBackground(Color.BLACK);

		// Titel dem Titelpanel hinzufuegen
		titlepanel.setBackground(null);
		title.setForeground(Color.WHITE);
		titlepanel.add(title);

		// Buttons dem Buttonpanel hinzufuegen
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));
		buttons.setBackground(null);
		buttons.add(start.getPanel());
		buttons.add(dummy.getPanel());
		buttons.add(loadMap.getPanel());
		buttons.add(randomMap.getPanel());
		buttons.add(sets.getPanel());
		buttons.add(exit.getPanel());

		// Bottompanel:
		bottom.setBackground(null);

		// Panels dem Oberpanel hinzufuegen
		getContentPane().add(titlepanel);
		getContentPane().add(buttons);
		getContentPane().add(bottom);

		// Buttons beim ActionListener registrieren
		start.getButton().addActionListener(this);
		start.getButton().setActionCommand("start");

		dummy.getButton().addActionListener(this);
		dummy.getButton().setActionCommand("dummy");

		loadMap.getButton().addActionListener(this);
		loadMap.getButton().setActionCommand("loadMap");

		randomMap.getButton().addActionListener(this);
		randomMap.getButton().setActionCommand("randomMap");

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
			try {
				MapChooser.go();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
		if (evt.getActionCommand().equals("randomMap") && gamerunning == false) {
			try {
				zufallsKarte.go();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
