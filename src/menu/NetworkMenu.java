package menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import netzwerk.testClient;
import netzwerk.testServer;

/**
 * Netzwerk-Menue wird konstruiert, und entsprechend gelinkt um die
 * Funktionsfähigkeit zu garantieren, anschließend wird es als Untermenue vom
 * Hauptmenue integriert.
 * 
 * @author garg
 * 
 */
public class NetworkMenu extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JPanel hostP = new JPanel();
	private JPanel joinP = new JPanel();

	private JTextField getIPTF;
	private JTextField hostPortTF;
	private JTextField joinIPTF;
	private JTextField joinPortTF;
	private boolean netRunning;

	public static NetworkMenu nFrame;

	public static void openFrame() {
		nFrame = new NetworkMenu();
		nFrame.setResizable(false);
		nFrame.setLocationByPlatform(true);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		nFrame.setBounds(0, 0, screenSize.width, screenSize.height);
		nFrame.setUndecorated(true);

		JPanel main = new JPanel();

		nFrame.construct(main);
		nFrame.add(main);

		nFrame.repaint();
		nFrame.setVisible(true);
	}
	public void construct(JPanel main) {
		main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
		JPanel top = new JPanel();
		top.setBackground(Color.BLACK);
		JPanel mid = new JPanel();
		mid.setBackground(Color.BLACK);
		JPanel bot = new JPanel();
		bot.setBackground(Color.BLACK);

		MenuButton backB = new MenuButton("Zurück");
		backB.getButton().addActionListener(this);
		backB.getButton().setActionCommand("back");

		JLabel titleL = new JLabel("Netzwerkspiel - Einstellungen:");
		titleL.setForeground(Color.WHITE);

		JPanel getIPP = new JPanel();
		getIPP.setBackground(Color.BLACK);
		MenuButton getIPB = new MenuButton("eigene IP");
		getIPB.getButton().addActionListener(this);
		getIPB.getButton().setActionCommand("getIP");
		getIPTF = new JTextField();
		getIPTF.setColumns(9);

		getIPP.add(getIPB.getPanel());
		getIPP.add(getIPTF);

		top.add(titleL);

		mid.add(hostPanel());
		// mid.add(getIPP);
		mid.add(joinPanel());

		bot.add(backB.getPanel());

		main.add(top);
		main.add(mid);
		main.add(bot);
	}

	public JPanel hostPanel() {
		hostP.setLayout(new BoxLayout(hostP, BoxLayout.Y_AXIS));

		JPanel top = new JPanel();
		top.setBackground(Color.BLACK);
		JPanel mid = new JPanel();
		mid.setBackground(Color.BLACK);
		JPanel bot = new JPanel();
		bot.setBackground(Color.BLACK);

		JLabel hostL = new JLabel("Host");
		hostL.setForeground(Color.WHITE);

		JPanel portP = new JPanel();
		portP.setBackground(Color.BLACK);
		JLabel portL = new JLabel("Port:");
		portL.setForeground(Color.WHITE);
		hostPortTF = new JTextField();
		hostPortTF.setColumns(4);

		MenuButton hostB = new MenuButton("Hoste");
		hostB.getButton().addActionListener(this);
		hostB.getButton().setActionCommand("host");

		portP.add(portL);
		portP.add(hostPortTF);

		top.add(hostL);

		mid.add(portP);

		bot.add(hostB.getPanel());

		hostP.add(top);
		hostP.add(mid);
		hostP.add(bot);

		return hostP;
	}

	public JPanel joinPanel() {
		joinP.setLayout(new BoxLayout(joinP, BoxLayout.Y_AXIS));

		JPanel top = new JPanel();
		top.setBackground(Color.BLACK);
		JPanel mid = new JPanel();
		mid.setBackground(Color.BLACK);
		JPanel bot = new JPanel();
		bot.setBackground(Color.BLACK);

		JLabel joinL = new JLabel("Client-Einstellungen:");
		joinL.setForeground(Color.WHITE);

		JLabel joinIPL = new JLabel("IP:");
		joinIPL.setForeground(Color.WHITE);
		joinIPTF = new JTextField();
		joinIPTF.setColumns(9);

		JLabel joinPortL = new JLabel("Port");
		joinPortL.setForeground(Color.WHITE);
		joinPortTF = new JTextField();
		joinPortTF.setColumns(4);

		MenuButton joinB = new MenuButton("Verbinde");
		joinB.getButton().addActionListener(this);
		joinB.getButton().setActionCommand("join");

		top.add(joinL);

		mid.add(joinIPL);
		mid.add(joinIPTF);
		mid.add(joinPortL);
		mid.add(joinPortTF);

		bot.add(joinB.getPanel());

		joinP.add(top);
		joinP.add(mid);
		joinP.add(bot);

		return joinP;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("back")) {
			if (!netRunning)
				nFrame.dispose();
		}

		if (e.getActionCommand().equals("join")) {
			netRunning = true;
			String ip = joinIPTF.getText();
			if (ip.equals("") != true) {
				try {
					testClient.go(ip);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					netRunning = false;
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					netRunning = false;
				}
			}
		}

		// if (e.getActionCommand().equals("getIP")) {
		//
		// }

		if (e.getActionCommand().equals("host")) {
			netRunning = true;
			try {
				testServer.go();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				netRunning = false;
			}
		}
	}
}
