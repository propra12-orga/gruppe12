
import javax.swing.*;



public class TestRun extends JPanel {
	public static void main(String[] args){
		JFrame Bomb=new JFrame("Spielfeld Dummy");
		Bomb.setSize(500,500);  // 500x500 Pixel groß
		Bomb.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Bomb.setVisible(true);
		Bomb.add(new Spielfeld());
	}
}
