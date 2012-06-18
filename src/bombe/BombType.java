package bombe;

import java.awt.Image;
import java.awt.Toolkit;

/**
 * Mutterklasse der verschiedenen Bombentypen. Als Erbbeispiel NomalBomb.java
 * betrachten!
 */

public class BombType {
	protected int radius;
	protected String picPath;
	protected String type;
	public Image pic;
	/**
	 * Erzeugt eine Bombe mit gegebenen Attributen
	 * 
	 * @param radius
	 *            bestimmt den Radius der Bombe
	 * @param picPath
	 *            gibt den Pfad zum Verzeichnis fuer das Bild der Bombe
	 * @param type
	 *            Stichwort fuer den Bombentyp
	 */
	public BombType(int radius, String picPath, String type) {
		this.radius = radius;
		this.picPath = picPath;
		this.type = type;
		pic = Toolkit.getDefaultToolkit().getImage(
				getClass().getResource(picPath));
	}

	// get- und set-Methoden um Variablen abzurufen.
	/**
	 * 
	 * @return gibt den radius der Bombe zurueck
	 */
	public int getRadius() {
		return this.radius;
	}
	/**
	 * legt den Radius fest
	 * 
	 * @param radius
	 *            nimmt einen Int-Wert entgegen der den Umfang der Bombe
	 *            darstellt.
	 */

	public void setRadius(int radius) {
		this.radius = radius;
	}
	/**
	 * 
	 * @return gibt den Pfad zum Bild als String wieder
	 */
	public String getPicPath() {
		return this.picPath;
	}

	public Image getPic() {
		return pic;
	}
	/**
	 * Ordnet einem Bild eine Visualisierung zu.
	 * 
	 * @param picPath
	 *            Pfad zum Bild
	 */
	public void setPic(String picPath) {
		pic = Toolkit.getDefaultToolkit().getImage(
				getClass().getResource(picPath));
	}

}
