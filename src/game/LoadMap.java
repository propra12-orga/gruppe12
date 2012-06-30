package game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import spielfeld.Spielfeld;
import spielfeld.Spielflaeche;
import spielfigur.Spielfigur;
/**
 * Klasse, die eine Datei bekommt diese in ein Array schreibt wird in
 * Spielfeld.java feldfuellen() übergeben um zu übersetzen. boolean loadtext
 * gibt Auskunft darüber ob eine Datei geladen wurde. Das Programm füllt sich
 * mit der eigenen randomGen() Methode falls sie false ist. Ansonsten wird das
 * Feld mit dem Dateiinhalt befüllt.
 * 
 */
public class LoadMap {
	private static boolean loadtext = false;
	public static char[][][] map;
	/**
	 * LoadMap liest eine Datei ein und schreibt damit eine Karte (char [][][]
	 * map). Es ist eine Datei zu uebergeben, welche ein char-Array beinhaltet.
	 * Diese Ziffernkombination wird in map reingeschrieben. Die Dimension 0 der
	 * Uebersetzer-Karte wird explizit mit Gras und Mauer befuellt. Die
	 * Dimensionen 1 und 2 jeweils getrennt von einander eingelesen.
	 * 
	 * 
	 * @param f
	 *            Die Methode nimmt File f entgegen. Das ist die Datei aus der
	 *            das Feld gelesen wird.Typischerweise eine .txt Datei welche
	 *            ein char Array beinhaltet
	 * @throws IOException
	 *             Wirft eine Fehlermeldung falls die einzulesende Datei nicht
	 *             existiert.
	 */

	public static void load(File f) throws IOException {
		setLoadtext(true);
		if (Spielfeld.booleanSave == true) {
			Spielflaeche.bman.setxPosition(Spielfeld.xPos);
			Spielflaeche.bman.setyPosition(Spielfeld.yPos);
		}
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
		// hole Header runter von Datei
		br.readLine();
		// fange an zu lesen
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

	}
	public static int randomInt() {
		int zufallsZahl = 0, a;
		a = (int) (Math.random() * 6) + 1;

		if (a == 1 || a == 2) {
			zufallsZahl = 9;
		} else if (a == 3 || a == 4) {
			zufallsZahl = 10;
		} else if (a == 5) {
			zufallsZahl = 11;
		} else
			zufallsZahl = 12;

		return zufallsZahl;
	}

	/**
	 * Mit dieser Methode hat der Spieler die Moeglichkeit, selber Karten zu
	 * erstellen nach dem Zufallsprinzip aus randomGen(). Die Karte wird
	 * erstellt und in einer .txt Datei gespeichert. Sie kann danach wie gewohnt
	 * geladen und gespielt werden.
	 */
	public static char[][][] randomMap() throws IOException {

		char[][][] randomMap = new char[21][21][3];

		/*
		 * fülle Array zufällig mit zerstörbaren Kisten und Items.
		 * Zufallsgenerator aus randomGen() wird genutzt. wichtig: Alles was
		 * nicht zu zerst. Mauer oder Item wird wird "geleert" also 0 gesetzt.
		 */
		for (int i = 0; i < 21; i++) {
			for (int j = 0; j < 21; j++) {
				randomMap[i][j][1] = '0';
				randomMap[i][j][2] = '0';
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

				/*
				 * wichtig: 2 bedingte Fälle: a) Kiste ohne Item b) Kiste mit
				 * Item <=> Item ohne Kiste gibt es nicht. merken:0 heißt Feld
				 * leer, 1 heißt Feld belegt
				 */
				if (h == 1) {
					randomMap[i][j][1] = '1'; // Ebene 1 == 1 <=> Item
					randomMap[i][j][2] = '1'; // Ebene 2 == 1 <=> zerst. Mauer
				} else if (k == 1) {
					randomMap[i][j][2] = '1'; // zerst. Kiste ohne Item

				}
			}
		}
		for (int i = 3; i < 21; i = i + 2) {
			for (int j = 1; j < 21; j++) {
				int k = (int) (Math.random() + 0.4);
				int h = (int) (Math.random() + 0.2 / 2);

				if (h == 1) {
					randomMap[j][i][1] = '1'; // analog zu der ersten Befüllung
					randomMap[j][i][2] = '1';
				} else if (k == 1) {
					randomMap[j][i][2] = '1';
				}
			}
		}

		for (int j = 3; j < 21; j++) {
			int k = (int) (Math.random() + 0.2);
			if (k == 1)
				randomMap[1][j][2] = '1';

			int l = (int) (Math.random() + 0.3);
			if (l == 1) {
				randomMap[j][1][2] = '1';
			}

		}

		// schreibe den Ausgang an einer zufaelligen Position. Liegt zwischen 9
		// und 12(mittig)
		int randomX = randomInt();
		int randomY = randomInt();
		randomMap[randomX][randomY][1] = '2';
		randomMap[randomX][randomY][2] = '1';
		// leere die Felder um Bomberman2 eplizit.fairness
		randomMap[19][19][1] = '0';
		randomMap[19][19][2] = '0';
		randomMap[19][17][1] = '0';
		randomMap[19][17][2] = '0';
		randomMap[18][19][1] = '0';
		randomMap[18][19][2] = '0';
		randomMap[19][18][1] = '0';
		randomMap[19][18][2] = '0';

		/*
		 * ab hier beginnt die Ausgabe die in die Datei "Level.txt" umgelenkt
		 * wird. Jede Dimension wird getrennt von einander in Blöcke geschrieben
		 * realisiert durch ein 3d Array vom Typ char. Jede Dimension ist ein
		 * 20x20 Block.Und wird von 0 umrahmt. 0 steht hierbei für ein leeres
		 * Feld im Endeffekt liegt da dann allerdings die Ebene 0 was festeMauer
		 * ist
		 */

		File ausgabe = new File("random.lv");
		FileWriter Schreiberling = new FileWriter(ausgabe);

		/*
		 * schreibe Dimension 1 in Zeile 1-20 auf. Blockweise.
		 */
		Schreiberling.write("LEVELDATEI\n");
		for (int i = 0; i < 20; i++) {

			for (int j = 0; j < 20; j++) {

				Schreiberling.write(String.valueOf(randomMap[i][j][1]));
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

				Schreiberling.write(String.valueOf(randomMap[i][j][2]));
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
		return randomMap;
	}
	// randomMap
	/*
	 * 
	 * Methode um die Karte zu ueberpruefen. Ist der Ausgang fair?Gibt es einen
	 * erreichbaren Ausgang?
	 */
	public static boolean consisCheck(char[][][] game, Spielfigur man1,
			Spielfigur man2) {
		boolean checkExit = false;
		boolean checkFair = false;
		int exitX = 0, exitY = 0;
		double pruefung;
		/*
		 * suche Ausgang und speichere in exitX und exitY (X/Y Koordinaten)
		 */
		for (int i = 0; i < 21; i++) {
			for (int j = 0; j < 21; j++) {
				if (game[i][j][1] == '2') {
					exitX = i;
					exitY = j;
				}
			}
		}
		/*
		 * pruefe ob Ausgang erreichbar. sind die X bzw Y Koordinaten unter 20
		 * so ist der Ausgang erreichbar fuer die Spieler.
		 */

		if (exitX < 20 && exitY < 20 && exitX != 0 && exitY != 0) {
			checkExit = true;
		}

		/*
		 * pruefe ob der Ausgang in fairerweise fuer beide Spieler erreichbar
		 * ist.
		 */
		/*
		 * berechne Abstand von den Bomberman-Positionen zu dem Ausgang. Die
		 * Punkte werden als Vektoren aufgefasst. es gilt:Abstand von v1(x,y)
		 * und v2(x1,y1) ist gleich: sqrt((x1-x)² +(y1-y)²)
		 */

		/*
		 * berechne Abstand Bomberman 1 zu Ausgang
		 */
		int x = man1.xPosition - exitX;
		int y = man1.yPosition - exitY;
		int radikand = y * y + x * x;
		double distMan1 = Math.sqrt(radikand);

		/*
		 * berechne Abstand Bomberman 2 zu Ausgang
		 */

		x = man2.xPosition - exitX;
		y = man2.yPosition - exitY;
		radikand = y * y + x * x;
		double distMan2 = Math.sqrt(radikand);
		/*
		 * Laengenunterschied der beiden Abstaende
		 */
		pruefung = (int) distMan1 - distMan2;

		/*
		 * Der Abstand der Spielfiguren zum Ausgang wurde in distMan1/2
		 * gespeichert. Die Variable pruefung hat den Laengenunterschied der
		 * Abstaende gespeichert. Ist der Unterschied kleiner oder gleich 2 (Das
		 * ist eine Toleranz) ist der Ausgang gueltig.
		 */
		if (Math.abs(pruefung) <= 5) {
			checkFair = true;
		}
		/*
		 * Wenn beide Pruefungen positiv abgeschlossen wurden gibt die
		 * Konsistenzpruefung zurueck dass das Feld gueltig ist. checkExit=wahr
		 * und checkFair=wahr
		 */
		if (checkExit == true && checkFair == true) {

			return true;

		}

		else
			return false;

	}

	public static boolean isLoadtext() {
		return loadtext;
	}
	/**
	 * Gibt an ob eine Karte geladen wurde. Kann benutzt werden, um zu
	 * unterscheiden ob der Computer eine Zufallskarte erstellen soll oder die
	 * eingelesene Karte realisiert.
	 * 
	 * @param loadtext
	 *            Wenn man dieser Methode true uebergibt weiß das Programm, dass
	 *            eine Karte eingelesen wurde und reagiert dementsprechend.
	 */
	public static void setLoadtext(boolean loadtext) {
		LoadMap.loadtext = loadtext;
	}
}