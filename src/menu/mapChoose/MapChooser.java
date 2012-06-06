package menu.mapChoose;

import game.LoadMap;

import java.io.File;

import javax.swing.JFileChooser;

public class MapChooser extends JFileChooser {
	/**
	 * Die Methode ruft einen JFileChooser auf, nachdem er mit den gewünschten
	 * Filtern und weiteren Optionen versorgt wurde.
	 */
	private static final long serialVersionUID = 1L;

	private static MapChooser fc = new MapChooser();

	public static void go() {
		TxtFilter filter = new TxtFilter(); // neues TxtFilter-Objekt angelegt
		fc.setFileFilter(filter); // und als FileFilter dem MapChooser fc
									// übergeben
		fc.setMultiSelectionEnabled(false); // keine mehrfache Selektion
		fc.showDialog(fc, "Laden"); // statt "Öffnen" steht im Dialog "Laden"
		File f = fc.getSelectedFile(); // prüft...
		if (f != null) { // ...ob Datei ausgewählt wurde
			LoadMap.load(f); // startet Methode zur Map-Berechnung
		}
	}
}