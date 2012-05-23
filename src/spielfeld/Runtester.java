package spielfeld;

// das ist die starter version mithilfe eines Threads. sie wartet 1000 ms bevor etwas dargestellt wird 

import game.Dummy;

import javax.swing.JPanel;

public class Runtester extends JPanel {

	public static void main(String[] args) {
		Dummy a = new Dummy(); // erstellt das Spielfeld

		try {
			Thread.sleep(1000);
		} // wartet explizit auf a.start() welches die Anzeige eroeffnet

		catch (InterruptedException e) {
		}

		a.start();
	}

}
