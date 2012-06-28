package game;

import java.awt.Color;
import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.JLabel;

import menu.MenuStarter;

public class zufallsKarte {

	public static void go() throws IOException {
		/*
		 * erzeugt eine Zufallskarte nach dem Prinzip von randomGen() aus
		 * Spielfeld.java und schreibt sie codiert in eine txt Datei Hinweis:
		 * Erläuterung zu Codierung der Level Dateien in Manual.txt
		 */
		LoadMap.randomMap();

		JDialog karteGeneriert = new JDialog(MenuStarter.frame, "Hinweis",
				false);
		karteGeneriert
				.add(new JLabel(
						"<html><body>Eine Zufallskarte wurde generiert.<br>Sie wurde unter random.lv gespeichert.<body><html>"));
		karteGeneriert.setBounds(300, 200, 400, 400);
		karteGeneriert.getContentPane().setBackground(Color.black);
		karteGeneriert.setVisible(true);
	}

};
