package spielfeld;

//Bombe hat folgende Eigenschaften
//int: intBombeX, intBombeY
//Methoden: getX(), getY(), setX(int), setY(int), explodieren(int)

public class Bombe implements Objekte {
	int intBombeX, intBombeY;

	public Bombe(int x, int y) {
		this.setX(x);
		this.setY(y);
	}

	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return intBombeX;
	}

	@Override
	public int getY() {
		return intBombeY;
		// TODO Auto-generated method stub

	}

	@Override
	public void setX(int x) {
		// TODO Auto-generated method stub
		this.intBombeX = x;
	}

	@Override
	public void setY(int y) {
		// TODO Auto-generated method stub
		this.intBombeY = y;
	}

	public void explodieren(int radius) {

	}
}
