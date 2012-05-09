package game;

import javax.swing.JFrame;

import spielfeld.Spielfeld;

public class TestRun {
	public static void go() {
		JFrame Bomb = new JFrame("Spielfeld Dummy");

		Bomb.setSize(500, 500); // 500x500 Pixel groß
		Bomb.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Bomb.setVisible(true);
		Bomb.add(new Spielfeld());
	}
}
