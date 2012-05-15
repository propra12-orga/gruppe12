package game;

import javax.swing.JFrame;

import menu.MainMenu;



public class TestRun {
	public static void go() {
		
		
														// vom Typ GameQuitter
		Dummy a=new Dummy();	 // erstellt das Spielfeld
		
		
		try{
		Thread.sleep(1000);}  // wartet explizit auf a.start() welches die Anzeige eroeffnet
		
		catch(InterruptedException e){}
		
		a.start();
		

		MainMenu.gamerunning = false; // teilt StartMenu mit, dass das Spiel
										// läuft.
	}

}
