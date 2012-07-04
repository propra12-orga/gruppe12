package menu;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Klasse, um allgemein Buttons fuer die Menues konstruieren zu koennen.
 * 
 * @author garg
 * 
 */

public class MenuButton extends JPanel {
	// initialisiere Variablen
	JButton button;
	JPanel buttonPanel;
	String title;

	private static final long serialVersionUID = 1L;

	// Konstruktor
	public MenuButton(String title) {
		this.title = title; // deklariere title mit übergebenem title
		button = new JButton(title); // deklariere button als neuen JButton
		buttonPanel = new JPanel(); // deklariere buttonPanel als neues JPanel
		buttonPanel.setBackground(null); // stelle Hintergrund vom buttonPanel
											// durchsichtig
		buttonPanel.add(button); // füge button dem buttonPanel bei
	}

	// Methode zur JPanel-Rückgabe
	public JPanel getPanel() {
		return buttonPanel;
	}

	// Methode zur JButton-Rückgabe
	public JButton getButton() {
		return button;
	}
}
