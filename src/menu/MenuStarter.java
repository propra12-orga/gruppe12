package menu;

import tools.WindowQuitter;

public class MenuStarter {

	public static void openmainmenu() {
		MainMenu frame = new MainMenu(); // Erzeugt ein Objekt von StartMenu

		WindowQuitter wquit = new WindowQuitter(); // Erzeugt einen
													// WindowQuitter
		frame.addWindowListener(wquit); // weist dem "close Window"-Knopf einen
										// Listener namens "wquit" zu, siehe
										// Klasse "WindowQuitter"

		frame.setSize(400, 225); // Einstellung der Fenstergröße
		frame.setResizable(false);
		frame.setLocationByPlatform(true);
		frame.setVisible(true); // Sichtbarmachen
	}
}
