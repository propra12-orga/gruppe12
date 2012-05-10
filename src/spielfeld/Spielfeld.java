package spielfeld;

public class Spielfeld implements Objekte {

	// Register- Zahlen:
	/*
	 * 0 = Startfeld (nicht implementiert) 1 = begehbares Gras 2 =
	 * unzerstoerbare Mauer 3 = zerstoerbare Mauer (nicht implementiert) 4 =
	 * Ausgang (nicht implementiert)
	 */

	int Hoehe, Breite;
	int Register[][];

	public Spielfeld(int Breite, int Hoehe) { // initialisiert Register
		Register = new int[Breite][Hoehe];
	}

	public void fill(int RegX, int RegY, int Obj) { // Methode zum befuellen des
													// Arrays an der Stelle
													// RegX,RegY mit Objekt Obj
		Register[RegX][RegY] = Obj;
	}

	public int getObj(int RegX, int RegY) { // gibt das Objekt an der Stelle
											// RegX,RegY aus dem Register wieder
		return Register[RegX][RegY];
	}

	/* Rest noch zu editieren */

	@Override
	public int getX() {
		return 0;
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setX(int x) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setY(int y) {
		// TODO Auto-generated method stub

	}

}