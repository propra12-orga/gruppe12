package bombe;

import spielfeld.Objekte;
import spielfeld.Spielflaeche;

//Bombe hat folgende Eigenschaften
//int: intBombeX, intBombeY
//Methoden: getX(), getY(), setX(int), setY(int), explodieren(int)

public class Bombe extends Objekte {
	int intBombeX, intBombeY;
	BombType bomb;

	public Bombe(int xPos, int yPos, int width, int height, BombType bomb) {
		super(xPos, yPos, width, height, bomb.picPath, bomb.type);
		this.bomb = bomb;
		intBombeX = xPos / getWidth();
		intBombeY = yPos / getHeight();
	}

	public void explodieren() {
		int radius = bomb.radius;
		boolean boolMauer = true;
		// zunächst warten bis die Bombe explodieren soll.
		long millisToWait = 2000;
		long millis = System.currentTimeMillis();
		while ((System.currentTimeMillis() - millis) < millisToWait) {
			// mache nichts
		}
		for (int i = 0; (i < radius) && (boolMauer); i++) {
			if (Spielflaeche.play.equalsMauer(intBombeX + i, intBombeY) == false) {
				Spielflaeche.play.fill(intBombeX + i, intBombeY, 0);
				// Animation von Bombe + Bomberman stirbt.
			} else
				boolMauer = false;

		}
		boolMauer = true;
		for (int i = 0; (i < radius) && (boolMauer); i++) {
			if (Spielflaeche.play.equalsMauer(intBombeX - i, intBombeY) == false) {
				Spielflaeche.play.fill(intBombeX - i, intBombeY, 0);
				// Animation von Bombe + Bomberman stirbt.

			} else
				boolMauer = false;

		}
		boolMauer = true;
		for (int i = 0; (i < radius) && (boolMauer); i++) {
			if (Spielflaeche.play.equalsMauer(intBombeX, intBombeY + i) == false) {
				Spielflaeche.play.fill(intBombeX, intBombeY + i, 0);
				// Animation von Bombe + Bomberman stirbt.

			} else
				boolMauer = false;

		}
		boolMauer = true;
		for (int i = 0; (i < radius) && (boolMauer); i++) {
			if (Spielflaeche.play.equalsMauer(intBombeX, intBombeY - i) == false) {
				Spielflaeche.play.fill(intBombeX, intBombeY - i, 0);
				// Animation von Bombe + Bomberman stirbt.
			} else
				boolMauer = false;

		}

	}
}
