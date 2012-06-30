package menu.mapChoose;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class LvFilter extends FileFilter {
	/**
	 * FileFilter, um für FileChooser alles außer Ordner und *.lv-Dateien aus zu
	 * filtern.
	 */
	@Override
	public boolean accept(File f) {
		if (f.isDirectory()) { // Ordner sollen angezeigt werden
			return true;
		}

		String ext = null; // Datei-
		String fname = f.getName(); // -Endung
		int i = fname.lastIndexOf('.'); // wird

		if (i > 0 && i < fname.length() - 1) { // gelesen
			ext = fname.substring(i + 1).toLowerCase(); //
		}

		if (ext.equals("lv")) { // Datei soll angezeigt werden, wenn die Endung
								// ".txt" lautet.
			return true;
		}
		return false;
	}

	@Override
	public String getDescription() {
		String d = ".lv";
		return d;
	}
}