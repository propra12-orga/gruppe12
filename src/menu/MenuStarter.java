package menu;

import java.awt.Dimension;
import java.awt.Toolkit;

import tools.WindowQuitter;

/**
 * Klasse, um Menue zu starten.
 */

public class MenuStarter {
	public static MainMenu frame;

	/**
	 * Menue wird erzeugt, auf Vollbild gestellt und geoeffnet.
	 */

	public static void openmainmenu() {
		frame = new MainMenu(); // Erzeugt ein Objekt von StartMenu

		WindowQuitter wquit = new WindowQuitter(); // Erzeugt einen
													// WindowQuitter
		frame.addWindowListener(wquit); // weist dem "close Window"-Knopf einen
										// Listener namens "wquit" zu, siehe
										// Klasse "WindowQuitter"

		frame.setResizable(false);
		frame.setLocationByPlatform(true);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setBounds(0, 0, screenSize.width, screenSize.height);
		frame.setUndecorated(true);
		frame.setVisible(true); // Sichtbarmachen

	}

	public MainMenu getFrame() {
		return frame;
	}
}
