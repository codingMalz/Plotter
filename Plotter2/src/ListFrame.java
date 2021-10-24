import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ListFrame extends JFrame{
	
	static Interface inter = new Interface();
	static JPanel panel = new JPanel();
	static ArrayList<Element> elements = new ArrayList<Element>();
	
	public ListFrame(int width, int height) {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((dim.width / 2) - (Main.mainFrameWidth / 2) - 300, (dim.height / 2) - (height /2), width, height);
		setLayout(null);
		setVisible(true);		
		
		
		panel = new JPanel();
		panel.setBounds(0, 0, width, height);
		panel.setLayout(null);
		add(panel);
	}
	
	public void update() {
		int numElements = 0;
		elements.clear();
		for (int i = 0; i < inter.points.size(); i++) {
			elements.add(new Element(panel, 5, 10 + (numElements * 50), Main.listFrameWidth - 25, 40, inter.points.get(i).getX(), inter.points.get(i).getY()));
			numElements++;
		}
		
		for (int i = 0; i < inter.lines.size(); i++) {
			elements.add(new Element(panel, 5, 10 + (numElements * 50), Main.listFrameWidth - 25, 40, inter.lines.get(i).getPoint1().getX(), inter.lines.get(i).getPoint1().getY(), inter.lines.get(i).getPoint2().getX() , inter.lines.get(i).getPoint2().getY()));
			numElements++;
		}
		repaint();
	}
}
