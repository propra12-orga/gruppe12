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
		intBombeX = xPos;
		intBombeY = yPos;
	}

	public void run() {// explodieren der bombe als thread.
		int radius = bomb.radius;

		boolean boolMauer = true;
		int nachUnten = 0, nachOben = 0, nachRechts = 0, nachLinks = 0;
		// zunaechst warten bis die Bombe explodieren soll.
		warten(2000);

		// in alle 4 richtungen explodieren bis zu einer mauer, kiste oder
		// rechts
		for (int i = 0; (i < radius) && (boolMauer); i++) {
			if (Spielflaeche.play.equalsMauer(intBombeX + i, intBombeY) == false) {// keine
				// Mauer
				if (Spielflaeche.play.equalsKiste(intBombeX + i, intBombeY) == false) {// keine
					// Kiste
					nachRechts++;
					Spielflaeche.play.fill(intBombeX + i, intBombeY, 5);// explosion
					// anzeigen
					// Animation von Bombe + Bomberman stirbt + kiste geht
					// kaputt
				} else {// wird eine Kiste getroffen soll diese zerst�rt
						// werden
					// und es soll nicht weiter explodieren

					boolMauer = false;
					Spielflaeche.play.fill(intBombeX + i, intBombeY, 5);
					nachRechts++;
					// nachRechts++;

				}
			} else {
				// Mauer=> explosion in diese richtung h�rt auf.
				boolMauer = false;

			}

		}
		boolMauer = true;
		// links
		for (int i = 0; (i < radius) && (boolMauer); i++) {
			if (Spielflaeche.play.equalsMauer(intBombeX - i, intBombeY) == false) {// keine
				// Mauer
				if (Spielflaeche.play.equalsKiste(intBombeX - i, intBombeY) == false) {// keine
					// Kiste
					nachLinks++;
					Spielflaeche.play.fill(intBombeX - i, intBombeY, 5);
					// explosion
					// anzeigen
					// Animation von Bombe + Bomberman stirbt + kiste geht
					// kaputt
				} else {// wird eine Kiste getroffen soll diese zerst�rt
						// werden
					// und es soll nicht weiter explodieren

					boolMauer = false;
					Spielflaeche.play.fill(intBombeX - i, intBombeY, 5);
					nachLinks++;

				}
			} else {
				// Mauer=> explosion in diese richtung h�rt auf.
				boolMauer = false;

			}

		}
		boolMauer = true;

		// oben
		for (int i = 0; (i < radius) && (boolMauer); i++) {
			if (Spielflaeche.play.equalsMauer(intBombeX, intBombeY + i) == false) {// keine
				// Mauer
				if (Spielflaeche.play.equalsKiste(intBombeX, intBombeY + i) == false) {// keine
					// Kiste
					nachOben++;
					Spielflaeche.play.fill(intBombeX, intBombeY + i, 5);// explosion
					// anzeigen
					// Animation von Bombe + Bomberman stirbt + kiste geht
					// kaputt
				} else {// wird eine Kiste getroffen soll diese zerst�rt
						// werden
					// und es soll nicht weiter explodieren

					boolMauer = false;
					Spielflaeche.play.fill(intBombeX, intBombeY + i, 5);
					nachOben++;
				}
			} else {
				// Mauer=> explosion in diese richtung h�rt auf.
				boolMauer = false;
			}

		}
		boolMauer = true;

		// unten
		for (int i = 0; (i < radius) && (boolMauer); i++) {
			if (Spielflaeche.play.equalsMauer(intBombeX, intBombeY - i) == false) {// keine
				// Mauer
				if (Spielflaeche.play.equalsKiste(intBombeX, intBombeY - i) == false) {// keine
					// Kiste
					nachUnten++;
					Spielflaeche.play.fill(intBombeX, intBombeY - i, 5);// explosion
					// anzeigen
					// Animation von Bombe + Bomberman stirbt + kiste geht
					// kaputt
				} else {// wird eine Kiste getroffen soll diese zerst�rt
						// werden
					// und es soll nicht weiter explodieren

					boolMauer = false;
					Spielflaeche.play.fill(intBombeX, intBombeY - i, 5);
					nachUnten++;

				}
			} else {
				// Mauer=> explosion in diese richtung h�rt auf.
				boolMauer = false;

			}

		}

		boolMauer = true;
		warten(750);
		for (int i = 0; i < nachUnten; i++) {
			Spielflaeche.play.fill(intBombeX, intBombeY - i, 0);
		}
		for (int i = 0; i < nachOben; i++) {
			Spielflaeche.play.fill(intBombeX, intBombeY + i, 0);
		}
		for (int i = 0; i < nachLinks; i++) {
			Spielflaeche.play.fill(intBombeX - i, intBombeY, 0);
		}
		for (int i = 0; i < nachRechts; i++) {
			Spielflaeche.play.fill(intBombeX + i, intBombeY, 0);
		}
		Spielflaeche.bman.setBombPlanted(false);
	}// bombeexplodieren
}
