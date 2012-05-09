package game;

import javax.swing.JFrame;

import spielfeld.Spielflaeche;

public class RunTester {
	public static void go() {
		JFrame opener = new JFrame("Graphicstester");

		opener.setLocation(400, 25);
		opener.setVisible(true);
		opener.setSize(822, 849);
		opener.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		opener.add(new Spielflaeche());

	}

}
