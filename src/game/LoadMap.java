package game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
/*
 * Klasse, die eine Datei bekommt diese in ein Array schreibt 
 * wird in Spielfeld.java feldfuellen() übergeben um zu
 * übersetzen.
 * boolean loadtext gibt Auskunft darüber ob eine Datei geladen wurde.
 * Das Programm füllt sich mit der eigenen randomGen() Methode falls sie false ist.
 * Ansonsten wird das Feld mit dem Dateiinhalt befüllt.
 *
 */
public class LoadMap {
	private static boolean loadtext = false;
	public static char[][][] map;
	public static void load(File f) throws IOException {
		setLoadtext(true);
		/*
		 * in den ersten Zeilen wird ein 3d Array für die Übersetzung ans
		 * Spielfeld initialisiert. Ein Stream öffnet sich (BufferedReader). Er
		 * liest aus der generierten Text Datei Char Werte ein in das Array auf
		 * mehreren Ebenen.
		 */
		map = new char[21][21][5];
		FileReader einlesen = new FileReader(f);
		BufferedReader br = new BufferedReader(einlesen);
		/*
		 * schreibe auf Ebene 0 überall Gras also eine 0
		 */
		for (int i = 0; i < 21; i++) {
			for (int j = 0; j < 21; j++) {
				map[i][j][0] = '0';
			}
		}
		/*
		 * schreibe Mauern in Array Mauern müssen sich am Rand befinden auch bei
		 * Zufallsmap
		 */

		for (int i = 0; i < 21; i++) {
			map[0][i][0] = '1';
			map[i][0][0] = '1';
			map[i][19][0] = '1';
			map[19][i][0] = '1';
		}
		for (int i = 2; i < 21; i = i + 2) {
			for (int j = 2; j < 21; j = j + 2) {
				map[i][j][0] = '1';
			}
		} // Mauern schreiben ende
		/*
		 * Lese Zeilenweise die Daten aus der txt Datei aus. Zeilen werden
		 * zwischengespeichert in char[] c da sie durch readline() nur als
		 * gesamte Strings da sind. Einzelne werte werden in Array geschrieben
		 * sichere DIMENSION 1
		 */

		for (int i = 0; i < 20; i++) {
			char[] c = br.readLine().toCharArray();
			for (int j = 0; j < c.length; j++) {
				map[j][i][1] = c[j];

			}
		}
		/*
		 * zwischen den Blöcken liegen exakt 5 Zeilenumbrüche zur Trennung
		 * welche mit readline() übersprungen werden (nichts wird gespeichert
		 * und durch den gargabe collector abgeholt)
		 */

		/*
		 * lese Zeilenweise den Block aus der Datei aus analog zu Dimension 1
		 * 
		 * lese DIMENSION 2
		 */

		for (int i = 0; i < 20; i++) {
			char[] c = br.readLine().toCharArray();
			for (int j = 0; j < c.length; j++) {
				map[j][i][2] = c[j];

			}
		}

	}/*
	 * erzeugt eine Zufallskarte und schreibt sie in txt Datei namens Tester.txt
	 */
	public static void randomMap() throws IOException {
		boolean existsExit = false;
		char[][][] random = new char[21][21][3];

		/*
		 * fülle Array zufällig mit zerstörbaren Kisten und Items.
		 * Zufallsgenerator aus randomGen() wird genutzt. wichtig: Alles was
		 * nicht zu zerst. Mauer oder Item wird wird "geleert" also 0 gesetzt.
		 */
		for (int i = 0; i < 21; i++) {
			for (int j = 0; j < 21; j++) {
				random[i][j][1] = '0';
				random[i][j][2] = '0';
			}

		}
		/*
		 * Start der zufälligen Befüllung der Ebenen 1 und 2 die Ebene 0 wird
		 * explizit gefüllt mit Gras und Mauer in der Mitte und Mauer als
		 * Rand-Abgrenzung Der Ausgang wird einmalig und zufällig unter einer
		 * Kiste platziert.
		 */

		for (int i = 3; i < 21; i = i + 2) {
			for (int j = 1; j < 21; j++) {
				int k = (int) (Math.random() + 0.2);
				int h = (int) (Math.random() + 0.2);
				int l = (int) (Math.random() + 0.3);
				/*
				 * wichtig: 2 bedingte Fälle: a) Kiste ohne Item b) Kiste mit
				 * Item <=> Item ohne Kiste gibt es nicht. merken:0 heißt Feld
				 * leer, 1 heißt Feld belegt
				 */
				if (h == 1) {
					random[i][j][1] = '1'; // Ebene 1 == 1 <=> Item
					random[i][j][2] = '1'; // Ebene 2 == 1 <=> zerst. Mauer
				} else if (k == 1) {
					random[i][j][2] = '1'; // zerst. Kiste ohne Item

				} else if (l == 1 && existsExit == false) {
					random[i][j][1] = '2'; // Ebene 2 == 2 <=> Ausgang
					random[i][j][2] = '1';
					existsExit = true;
				}
			}
		}
		for (int i = 3; i < 21; i = i + 2) {
			for (int j = 1; j < 21; j++) {
				int k = (int) (Math.random() + 0.4);
				int h = (int) (Math.random() + 0.2 / 2);
				int l = (int) (Math.random() + 0.3);
				if (h == 1) {
					random[j][i][1] = '1'; // analog zu der ersten Befüllung
					random[j][i][2] = '1';
				} else if (k == 1) {
					random[j][i][2] = '1';
				} else if (l == 1 && existsExit == false) {
					random[i][j][1] = '2'; // Ebene 2 == 2 <=> Ausgang
					random[i][j][2] = '1';
					existsExit = true;
				}
			}
		}

		for (int j = 3; j < 21; j++) {
			int k = (int) (Math.random() + 0.2);
			if (k == 1)
				random[1][j][2] = '1';

			int l = (int) (Math.random() + 0.3);
			if (l == 1) {
				random[j][1][2] = '1';
			}

		}

		/*
		 * ab hier beginnt die Ausgabe die in die Datei "Level.txt" umgelenkt
		 * wird. Jede Dimension wird getrennt von einander in Blöcke geschrieben
		 * realisiert durch ein 3d Array vom Typ char. Jede Dimension ist ein
		 * 20x20 Block.Und wird von 0 umrahmt. 0 steht hierbei für ein leeres
		 * Feld im Endeffekt liegt da dann allerdings die Ebene 0 was festeMauer
		 * ist
		 */

		File ausgabe = new File("Tester.txt");
		FileWriter Schreiberling = new FileWriter(ausgabe);

		/*
		 * schreibe Dimension 1 in Zeile 1-20 auf. Blockweise.
		 */

		for (int i = 0; i < 20; i++) {

			for (int j = 0; j < 20; j++) {

				Schreiberling.write(String.valueOf(random[i][j][1]));
				if (j == 19) {
					Schreiberling.write('\n');
				}
			}

		}

		/*
		 * schreibe Dimension 2 in Zeile 21-40.Blockweise
		 */
		for (int i = 0; i < 20; i++) {

			for (int j = 0; j < 20; j++) {

				Schreiberling.write(String.valueOf(random[i][j][2]));
				if (j == 19) {
					Schreiberling.write('\n');
				}
			}
		}

		/*
		 * leere Schreibstream mit .flush() und schließe selbigen mit .close()
		 * mehr Sicherheit und Gewissheit in der speicherverwaltung
		 */

		Schreiberling.flush();
		Schreiberling.close();
	}

	// randomMap
	public static boolean isLoadtext() {
		return loadtext;
	}

	public static void setLoadtext(boolean loadtext) {
		LoadMap.loadtext = loadtext;
	}
}