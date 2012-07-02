package netzwerk;

import java.io.IOException;

public class testServer {
	public static void main(String[] args) throws IOException {
		Thread game = new Server(3000);
		game.run();
	}
}
