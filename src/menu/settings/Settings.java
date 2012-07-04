package menu.settings;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Klasse, um diverse Einstellungen abspeichern und abrufen zu können.
 * 
 * @author garg
 * 
 */
@XmlRootElement
public class Settings {

	// Multiplayer:
	boolean player2;

	// Bomben:
	int maxBombs;
	int bombRange;

	// Items:
	boolean swap;
	boolean bombSpawn;

	// Sonstiges:
	String savePath;

	// Get- und Setmethoden:
	public boolean isPlayer2() {
		return player2;
	}
	public void setPlayer2(boolean player2) {
		this.player2 = player2;
	}
	public int getMaxBombs() {
		return maxBombs;
	}
	public void setMaxBombs(int maxBombs) {
		this.maxBombs = maxBombs;
	}
	public int getBombRange() {
		return bombRange;
	}
	public void setBombRange(int bombRange) {
		this.bombRange = bombRange;
	}
	public boolean isSwap() {
		return swap;
	}
	public void setSwap(boolean swap) {
		this.swap = swap;
	}
	public boolean isBombSpawn() {
		return bombSpawn;
	}
	public void setBombSpawn(boolean bombSpawn) {
		this.bombSpawn = bombSpawn;
	}
	public String getSavePath() {
		return savePath;
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
}
