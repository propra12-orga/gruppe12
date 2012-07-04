package tools;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * WindowListener, um fehlerfrei das Programm schließen zu koennen.
 * 
 * @author garg
 * 
 */

public class WindowQuitter extends WindowAdapter // WindowListener
{
	public void windowClosing(WindowEvent e) {
		System.exit(0); // Beendet Programm
	}
}