package netzwerk;

import java.io.IOException;

public class testClient {
	public static void go(String ip) throws IOException {
		Thread game = new Client(ip);
		game.start();
	}
}
