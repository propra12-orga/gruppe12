package menu.settings;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.xml.bind.JAXBException;

import menu.MenuButton;

/**
 * Settingsmenue wird erzeugt und verlinkt, um anschließend ins Hauptmenue
 * integriert zu werden.
 * 
 * @author garg
 * 
 */

public class SettingsFrame extends JFrame
		implements
			ActionListener,
			ChangeListener {

	private static final long serialVersionUID = 1L;

	JFrame sets = new JFrame("Settings");

	JCheckBox player2 = new JCheckBox("Zweiter Spieler");
	JCheckBox swap = new JCheckBox("Swap");
	JCheckBox bombSpawn = new JCheckBox("Zufallsbombe");

	JPanel mplayer = new JPanel();
	JPanel items = new JPanel();

	JPanel main = new JPanel();
	JPanel top = new JPanel();
	JPanel bot = new JPanel();
	JPanel titlepanel = new JPanel();
	JLabel title = new JLabel("Settings");

	JSlider bombRange = new JSlider(SwingConstants.HORIZONTAL, 1, 10, 1);
	JTextField bombRtext = new JTextField(4);
	JSlider maxBombs = new JSlider(SwingConstants.HORIZONTAL, 1, 10, 1);
	JTextField maxBtext = new JTextField(4);

	MenuButton save = new MenuButton("Speichern");
	MenuButton cancel = new MenuButton("Verwerfen");

	Settings settings;

	public SettingsFrame(Settings settings) {
		this.settings = settings;
	}

	public SettingsFrame() {
	}

	public void go() {
		blackCheckers();

		main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));

		sets.setResizable(false);
		sets.setLocationByPlatform(true);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		sets.setBounds(0, 0, screenSize.width, screenSize.height);
		sets.setUndecorated(true);
		sets.setBackground(Color.BLACK);

		titlepanel.setBackground(Color.BLACK);
		title.setForeground(Color.WHITE);
		titlepanel.add(title);

		mplayer.setLayout(new BoxLayout(mplayer, BoxLayout.Y_AXIS));
		mplayer.setBackground(Color.BLACK);
		mplayer.add(player2);

		items.setLayout(new BoxLayout(items, BoxLayout.Y_AXIS));
		items.setBackground(Color.BLACK);
		items.add(swap);
		items.add(bombSpawn);
		items.add(maxBombs);
		items.add(bombRange);
		items.add(slider(maxBtext, "maxBombs", "Maximale Bombenzahl", maxBombs));
		items.add(slider(bombRtext, "bombRange", "Reichweite der Bomben",
				bombRange));

		// top.add(new JLabel("Multiplayer"));
		// top.add(mplayer);
		top.add(new JLabel("Items"));
		top.add(items);
		top.setBackground(Color.BLACK);

		bot.add(save.getPanel());
		bot.add(cancel.getPanel());
		bot.setBackground(Color.BLACK);

		main.add(titlepanel);
		main.add(top);
		main.add(bot);

		sets.add(main);

		save.getButton().addActionListener(this);
		save.getButton().setActionCommand("save");

		cancel.getButton().addActionListener(this);
		cancel.getButton().setActionCommand("cancel");

		refresh();
		repaint();

		sets.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("cancel")) {
			sets.dispose();
		}

		if (e.getActionCommand().equals("save")) {
			save();
			sets.dispose();
		}
	}

	public void blackCheckers() {
		player2.setBackground(Color.BLACK);
		player2.setForeground(Color.WHITE);
		swap.setBackground(Color.BLACK);
		swap.setForeground(Color.WHITE);
		bombSpawn.setBackground(Color.BLACK);
		bombSpawn.setForeground(Color.WHITE);
	}

	public JPanel slider(JTextField tf, String name, String title, JSlider slide) {
		slide.setName(name);
		slide.setBackground(Color.BLACK);
		slide.setForeground(Color.WHITE);
		slide.setMinorTickSpacing(1);
		slide.setPaintTicks(true);
		slide.setPaintLabels(true);
		slide.setSnapToTicks(true);
		slide.addChangeListener(this);

		tf.setText(slide.getValue() + " ");

		JPanel jp = new JPanel();
		jp.setBackground(Color.BLACK);
		jp.add(new JLabel(title));
		jp.add(tf);
		jp.add(slide);
		return jp;
	}

	public void stateChanged(ChangeEvent evt) {
		JSlider source = (JSlider) evt.getSource();
		if (source.getName().equals("maxBombs"))
			maxBtext.setText(source.getValue() + " ");
		if (source.getName().equals("bombRange"))
			bombRtext.setText(source.getValue() + " ");
	}

	public void save() {
		Settings sets = new Settings();
		sets.setBombRange(bombRange.getValue());
		sets.setBombSpawn(bombSpawn.isSelected());
		sets.setMaxBombs(maxBombs.getValue());
		sets.setPlayer2(player2.isSelected());
		sets.setSwap(swap.isSelected());
		sets.setSavePath(null);
		try {
			new SaveSettings(sets);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void refresh() {
		player2.setSelected(settings.isPlayer2());
		swap.setSelected(settings.isSwap());
		bombSpawn.setSelected(settings.isBombSpawn());
		bombRange.setValue(settings.getBombRange());
		maxBombs.setValue(settings.getMaxBombs());
	}

}
