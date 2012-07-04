package netzwerk;

import java.io.IOException;

/**
 * Startet Thread, der Server refresht.
 * 
 * @author garg
 * 
 */

public class testServer {
	public static void go() throws IOException {
		Thread game = new Server();
		game.start();
	}
}
