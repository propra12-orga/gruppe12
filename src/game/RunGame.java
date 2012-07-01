package game;

import javax.xml.bind.JAXBException;

import menu.settings.LoadSettings;
import menu.settings.Settings;

public class RunGame {
	public static void go() throws JAXBException {
		Settings settings;
		settings = LoadSettings.load();

		Game a = new Game(); // erstellt das Spielfeld
		a.run();
		a.start();
	}

}
