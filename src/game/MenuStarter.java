package game;

public class MenuStarter {

	public static void main(String[] args) {
		StartMenu frame = new StartMenu(); // Erzeugt ein Objekt von StartMenu

		WindowQuitter wquit = new WindowQuitter(); // Erzeugt einen
													// WindowQuitter
		frame.addWindowListener(wquit); // weist dem "close Window"-Knopf einen
										// Listener namens "wquit" zu, siehe
										// Klasse "WindowQuitter"

		frame.setSize(400, 225); // Einstellung der Fenstergröße
		frame.setVisible(true); // Sichtbarmachen
	}
}
