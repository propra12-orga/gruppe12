
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Spielfeld extends JPanel {


	@Override
	protected void paintComponent(Graphics g) // ueberschreiben der
												// paintComponent Methode
	{

		Graphics2D g2d = (Graphics2D) g;

		int[][] Register = new int[20][20]; // Register fuer versch. Objekte

		for (int x = 0; x < 20; x++) {
			for (int y = 0; y < 20; y++) {
				Register[x][y] = 1; // leeres Feld entspricht int 1
			}
		}
		Register[3][0] = 2;
		for (int y = 0; y < 20; y++) {
			for (int x = 0; x < 20; x++) {
				if (Register[x][y] == 1) { // if-Abfrage: welches Objekt soll
					g2d.drawRect(x * 20, y * 20, 20, 20); // gezeichnet werden?

				}
			}

		}
		if (Register[3][0] == 2) {
			g2d.fillRect(3 * 20, 0 * 20, 20, 20);
		}

	}

}
