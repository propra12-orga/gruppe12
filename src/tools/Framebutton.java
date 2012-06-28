package tools;

import game.Tutorial;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

public class Framebutton implements ActionListener {
	// Zaehler um mit dem selben Knopf OK mehrere Ereignisse bearbeiten zu
	// koennen.
	public static int zaehler = 0;
	/*
	 * Hier wird der Actionlistener fuer das Dialogfeld im Tutorial
	 * implementiert. verbunden mit dem OK - Knopf wechselt er bei bestimmten
	 * Handlungen die Textfelder aus.
	 */
	public void actionPerformed(ActionEvent a) {
		/*
		 * beim ersten druecken des Knopfes wurde noch die Begrueßung angezeigt.
		 * keine besondere Bedingung, Spieler kann direkt weiter. ersetze
		 * begrueßung durch Hinweise zur Steuerung
		 */
		if (zaehler == 0) {

			Tutorial.dialog.setVisible(false);
			Tutorial.dialog = new JDialog(Tutorial.gameFrame, "Steuerung",
					false);
			Tutorial.dialog.remove(Tutorial.begrueßung);
			Tutorial.dialog.add(Tutorial.steuerung);
			// Tutorial.dialog.add(ok, BorderLayout.SOUTH);
			Tutorial.dialog.setSize(400, 400);
			Tutorial.dialog.setVisible(true);
			Tutorial.dialog.add(Tutorial.ok, BorderLayout.SOUTH);
			zaehler++;

		}
		/*
		 * nachdem 1x gedrueckt worden ist, liegt der Zaehler bei 1. Der Spieler
		 * kann nur fortfahren wenn er sich in jede Richtung einmal bewegt hat.
		 * ersetze Steuerung durch Hinweise zur Bombe
		 */
		if (zaehler == 1 && Tutorial.moved1 == true && Tutorial.moved2 == true
				&& Tutorial.moved3 == true && Tutorial.moved4 == true) {

			Tutorial.dialog.setVisible(false);
			Tutorial.dialog = new JDialog(Tutorial.gameFrame, "Bombe", false);
			Tutorial.dialog.remove(Tutorial.steuerung);
			Tutorial.dialog.add(Tutorial.bombe);
			Tutorial.dialog.setSize(400, 400);
			Tutorial.dialog.setVisible(true);
			Tutorial.dialog.add(Tutorial.ok, BorderLayout.SOUTH);
			zaehler++;

		}
		/*
		 * der Spieler gelangt zum Hinweis er solle doch den Ausgang betreten
		 * nachdem er auf OK geklickt hat und eine Bombe gelegt hat.
		 */
		if (zaehler == 2 && Tutorial.bombeplan == true) {
			Tutorial.dialog.setVisible(false);
			Tutorial.dialog = new JDialog(Tutorial.gameFrame, "Ausgang", false);
			Tutorial.dialog.remove(Tutorial.bombe);
			Tutorial.dialog.add(Tutorial.ausgang);
			Tutorial.dialog.setSize(400, 400);
			Tutorial.dialog.setVisible(true);
			Tutorial.dialog.add(Tutorial.ok, BorderLayout.SOUTH);
			zaehler++;
		}
	}

}
