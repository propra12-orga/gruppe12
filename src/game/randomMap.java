package game;

import java.io.IOException;

public class randomMap {
	public static void main(String[] args) throws IOException {
		/*
		 * erzeugt eine Zufallskarte nach dem Prinzip von randomGen() aus
		 * Spielfeld.java und schreibt sie codiert in eine txt Datei Hinweis:
		 * Erläuterung zu Codierung der Level Dateien in Manual.txt
		 */
		LoadMap.randomMap();
		System.out
				.println("Karte wurde erzeugt und unter Tester.txt im Verzeichnis gruppe12 gespeichert.");
	}
}
