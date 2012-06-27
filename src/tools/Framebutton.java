package tools;

import game.Tutorial;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

public class Framebutton implements ActionListener {
	public static int zaehler = 0;

	public void actionPerformed(ActionEvent a) {

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
			System.out.println(zaehler);
		}
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
			System.out.println(zaehler);
		}

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
