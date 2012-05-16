package bombe;

import java.awt.Image;
import java.awt.Toolkit;

/*
 * Mutterklasse der verschiedenen Bombentypen.
 * Als Erbbeispiel NomalBomb.java betrachten!
 */

public class BombType {
	protected int radius;
	protected String picPath;
	protected String type;
	public Image pic;

	public BombType(int radius, String picPath, String type) {
		this.radius = radius;
		this.picPath = picPath;
		this.type = type;
		pic = Toolkit.getDefaultToolkit().getImage(
				getClass().getResource(picPath));
	}

	// get- und set-Methoden um Variablen abzurufen.

	public int getRadius() {
		return this.radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public String getPicPath() {
		return this.picPath;
	}

	public Image getPic() {
		return pic;
	}

	public void setPic(String picPath) {
		pic = Toolkit.getDefaultToolkit().getImage(
				getClass().getResource(picPath));
	}
}
