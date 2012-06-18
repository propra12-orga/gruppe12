package bombe;

public class NormalBomb extends BombType {

	private static int radius = 3;
	private static String picPath = "/ressources/grafics/Bombe.gif";
	private static String type = "NormalBomb";
	/**
	 * erzeugt eine einfache Bombe. Es werden noch verschiedene Bombentypen
	 * definiert.
	 */
	public NormalBomb() {
		super(radius, picPath, type);
		// TODO Auto-generated constructor stub
	}

}
