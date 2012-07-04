package netzwerk;

import java.io.IOException;

public class testServer {
	public static void go(int port) throws IOException {
		Thread game = new Server(port);
		game.run();
	}
}
