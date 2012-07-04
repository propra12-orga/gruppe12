package netzwerk;

import java.io.IOException;

public class testServer {
	public static void go() throws IOException {
		Thread game = new Server();
		game.start();
	}
}
