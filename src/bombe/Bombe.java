package bombe;

import spielfeld.Objekte;

//Bombe hat folgende Eigenschaften
//int: intBombeX, intBombeY
//Methoden: getX(), getY(), setX(int), setY(int), explodieren(int)

public class Bombe extends Objekte {
	int intBombeX, intBombeY;
	BombType bomb;

	public Bombe(int xPos, int yPos, int width, int height, BombType bomb) {
		super(xPos, yPos, width, height, bomb.picPath, bomb.type);
		this.bomb = bomb;
	}

	public void explodieren() {
		// radius = bomb.radius

	}
}
