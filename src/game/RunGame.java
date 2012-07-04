package game;

import javax.xml.bind.JAXBException;

import menu.settings.LoadSettings;
import menu.settings.Settings;
import spielfeld.Spielflaeche;

/**
 * Ladet Settings, startet das Spiel und nimmt Einstellungen an dem Spiel vor.
 * 
 * @author garg
 * 
 */

public class RunGame {
	public static void go() throws JAXBException {
		Settings settings;
		settings = LoadSettings.load();

		Game a = new Game(); // erstellt das Spielfeld
		a.run();
		a.start();
		Spielflaeche.bman.setBombPlanted(settings.getMaxBombs());
		Spielflaeche.bman2.setBombPlanted(settings.getMaxBombs());
		Spielflaeche.bman.setRadius(settings.getBombRange());
		Spielflaeche.bman2.setRadius(settings.getBombRange());
		Spielflaeche.bman.setWechsler(settings.isSwap());
		Spielflaeche.bman2.setWechsler(settings.isSwap());
		Spielflaeche.bman.setZfbombe(settings.isBombSpawn());
		Spielflaeche.bman2.setZfbombe(settings.isBombSpawn());
	}

}
