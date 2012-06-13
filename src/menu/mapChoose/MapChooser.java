package menu.mapChoose;

import game.LoadMap;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;

public class MapChooser extends JFileChooser {
	/**
	 * Die Methode ruft einen JFileChooser auf, nachdem er mit den gewünschten
	 * Filtern und weiteren Optionen versorgt wurde.
	 */
	private static final long serialVersionUID = 1L;

	private static MapChooser fc = new MapChooser();

	public static void go() throws IOException {
		TxtFilter filter = new TxtFilter(); // neues TxtFilter-Objekt angelegt
		fc.setFileFilter(filter); // und als FileFilter dem MapChooser fc
									// übergeben
		fc.setMultiSelectionEnabled(false); // keine mehrfache Selektion
		fc.showDialog(fc, "Laden"); // statt "Öffnen" steht im Dialog "Laden"
		File f = fc.getSelectedFile();// prüft...
		String name = f.getName().toLowerCase();
		/*
		 * Überprüfe ob eine Datei ausgewählt wurde und ob es die richtige
		 * Endung hat hier : .txt edit: nicht sicher bei manueller Änderung des
		 * Dateinamens, da lediglich der String der Datei geprüft wird
		 */
		if (f != null && name.endsWith(".txt")) {
			LoadMap.load(f); // startet Methode zur Map-Berechnung
		} else {
			/*
			 * Bitte Label hinzufügen Fehlerbehandlung falls der File nicht eine
			 * .txt Datei beinhaltet
			 */
		}
	}
}