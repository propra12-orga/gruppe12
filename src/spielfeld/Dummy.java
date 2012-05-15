import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import spielfeld.Spielflaeche;
import tools.GameQuitter;
// Diese Klasse erzeugt ein Spielfeld und benutzt einen Thread zum darstellen
// wahrscheinlich fixt das das Problem, es ist bei mir nichtmehr aufgetreten

public class Dummy extends Thread {
	public Dummy(){   // Konstruktor erzeugt Frame mit Spielfeld
		final JFrame opener = new JFrame("Graphicstester");
		GameQuitter gQuit = new GameQuitter(opener); // Erzeugt WindowListener		
		opener.add(new Spielflaeche());
		opener.setLocation(400, 25);
		opener.setSize(800,600);
		opener.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		opener.addWindowListener(gQuit);
		
		
		
		
		Runnable gui=new Runnable(){

			@Override
			public void run() {			  // der Thread zeichnet durchgehend neu 
				opener.repaint();
				opener.setVisible(true);
			}
			
		};
		SwingUtilities.invokeLater(gui); // wartet bis ausgefuehrt
			
		
		
		
		
		
	}

}
