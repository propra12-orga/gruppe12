package spielfeld;

//Bombe hat folgende Eigenschaften
//int: intBombeX, intBombeY
//Methoden: getX(), getY(), setX(int), setY(int), explodieren(int)

public class Bombe extends Objekte {
	int intBombeX, intBombeY;

	public Bombe(int xPos, int yPos, int width, int height, String pic,
			String type) {
		super(xPos, yPos, width, height, pic, type);
	}

	public void explodieren(int radius) {

	}
}
