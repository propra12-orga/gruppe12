package game;
/*
 * Klasse zum ausfuehren von Tutorial. Mit dem gleichnamigen Knopf verbunden im Hauptmenu
 */
public class RunTutorial {
	public static void go() {
		Tutorial a = new Tutorial();

		a.run();
		a.start();

	}
}
