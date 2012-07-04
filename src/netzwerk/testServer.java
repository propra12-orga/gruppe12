package netzwerk;

import java.io.IOException;

import menu.MainMenu;

public class testServer {
	public static void go() throws IOException {
		Thread game = new Server(3000);
		game.run();
		MainMenu.gamerunning = true;

	}
}
