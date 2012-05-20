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
		int nachUnten, nachOben, nachRechts, nachLinks;
		// zunächst warten bis die Bombe explodieren soll.
		warten(2000);

		// in alle 4 richtungen explodieren bis zu einer mauer, kiste oder
		// rechts
		for (int i = 0; (i < radius) && (boolMauer); i++) {
			if (Spielflaeche.play.equalsMauer(intBombeX + i, intBombeY) == false) {// keine
																					// Mauer
				if (Spielflaeche.play.equalsKiste(intBombeX + i, intBombeY) == false) {// keine
																						// Kiste

					Spielflaeche.play.fill(intBombeX + i, intBombeY, 5);// explosion
																		// anzeigen
					// Animation von Bombe + Bomberman stirbt + kiste geht
					// kaputt
				} else {// wird eine Kiste getroffen soll diese zerstört werden
						// und es soll nicht weiter explodieren

					boolMauer = false;
					Spielflaeche.play.fill(intBombeX + i, intBombeY, 5);
					nachRechts = i;
				}
			} else {
				// Mauer=> explosion in diese richtung hört auf.
				boolMauer = false;
				nachRechts = i;
			}
		}
		boolMauer = true;
		// links
		for (int i = 0; (i < radius) && (boolMauer); i++) {
			if (Spielflaeche.play.equalsMauer(intBombeX - i, intBombeY) == false) {// keine
																					// Mauer
				if (Spielflaeche.play.equalsKiste(intBombeX - i, intBombeY) == false) {// keine
																						// Kiste

					Spielflaeche.play.fill(intBombeX - i, intBombeY, 5);// explosion
																		// anzeigen
					// Animation von Bombe + Bomberman stirbt + kiste geht
					// kaputt
				} else {// wird eine Kiste getroffen soll diese zerstört werden
						// und es soll nicht weiter explodieren

					boolMauer = false;
					Spielflaeche.play.fill(intBombeX - i, intBombeY, 5);
					nachLinks = i;
				}
			} else {
				// Mauer=> explosion in diese richtung hört auf.
				boolMauer = false;
				nachLinks = i;
			}
		}
		boolMauer = true;

		// oben
		for (int i = 0; (i < radius) && (boolMauer); i++) {
			if (Spielflaeche.play.equalsMauer(intBombeX, intBombeY + i) == false) {// keine
																					// Mauer
				if (Spielflaeche.play.equalsKiste(intBombeX, intBombeY + i) == false) {// keine
																						// Kiste

					Spielflaeche.play.fill(intBombeX, intBombeY + i, 5);// explosion
																		// anzeigen
					// Animation von Bombe + Bomberman stirbt + kiste geht
					// kaputt
				} else {// wird eine Kiste getroffen soll diese zerstört werden
						// und es soll nicht weiter explodieren

					boolMauer = false;
					Spielflaeche.play.fill(intBombeX, intBombeY + i, 5);
					nachOben = i;
				}
			} else {
				// Mauer=> explosion in diese richtung hört auf.
				boolMauer = false;
				nachOben = i;
			}
		}
		boolMauer = true;

		// unten
		for (int i = 0; (i < radius) && (boolMauer); i++) {
			if (Spielflaeche.play.equalsMauer(intBombeX, intBombeY - i) == false) {// keine
																					// Mauer
				if (Spielflaeche.play.equalsKiste(intBombeX, intBombeY - i) == false) {// keine
																						// Kiste

					Spielflaeche.play.fill(intBombeX, intBombeY - i, 5);// explosion
																		// anzeigen
					// Animation von Bombe + Bomberman stirbt + kiste geht
					// kaputt
				} else {// wird eine Kiste getroffen soll diese zerstört werden
						// und es soll nicht weiter explodieren

					boolMauer = false;
					Spielflaeche.play.fill(intBombeX, intBombeY - i, 5);
					nachUnten = i;
				}
			} else {
				// Mauer=> explosion in diese richtung hört auf.
				boolMauer = false;
				nachUnten = i;
			}
		}
		boolMauer = true;

	}

}
