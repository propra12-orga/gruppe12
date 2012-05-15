package game;


public class TestRun {
	public static void go() {

		Dummy a = new Dummy(); // erstellt das Spielfeld
		a.run();

		try {
			Thread.sleep(1000);
		} // wartet explizit auf a.start() welches die Anzeige eroeffnet

		catch (InterruptedException e) {

		}

		a.start();
	}

}
