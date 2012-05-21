package menu;

import game.RunGame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainMenu extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Wahrheitswert sagt aus, ob bereits ein Spiel läuft
	public static boolean gamerunning = false;

	// Panels und zugehoerige Buttons initialisieren
	MenuButton start = new MenuButton("Start");
	MenuButton test = new MenuButton("Test");
	MenuButton sets = new MenuButton("Sets");
	MenuButton exit = new MenuButton("Exit");

	// ...ebenso das Titelpanel
	JPanel titlepanel = new JPanel();
	JLabel title = new JLabel("Ganz schön dolles Spiel");

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
		getContentPane().add(test.getPanel());
		getContentPane().add(sets.getPanel());
		getContentPane().add(exit.getPanel());

		// Buttons beim ActionListener registrieren
		start.getButton().addActionListener(this);
		start.getButton().setActionCommand("start");

		test.getButton().addActionListener(this);
		test.getButton().setActionCommand("test");

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
		// Startet den Tests (falls implementiert
		if (evt.getActionCommand().equals("test") && gamerunning == false) {

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
