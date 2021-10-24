import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ToolsFrame extends JFrame{
	
	String path = "C:\\Users\\JerBa\\eclipse-workspace\\Plotter\\res";
	
	public ToolsFrame(int width, int height) {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((dim.width / 2) - (width / 2) +10 , (dim.height / 2) - (Main.mainFrameWidth /2) - 70, width, height);
		setLayout(null);
		dispose();
		setUndecorated(true);
		setVisible(true);

		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, width, height);
		panel.setLayout(null);
		add(panel);
		
		ToolsButton.create(panel, Tool.CREATEPOINT, 5, 5, 50, 50, new ImageIcon(path + "\\point_icon_32px.png"), true);
		ToolsButton.create(panel, Tool.CREATELINE, 80, 5, 50, 50, new ImageIcon(path + "\\line_icon_32px.png"), true);
		ToolsButton.create(panel, Tool.FIXED, 155, 5, 50, 50, new ImageIcon(path + "\\fixed_icon_32px.png"), true);
		ToolsButton.create(panel, Tool.COLOR, 230, 5, 50, 50, new ImageIcon(path + "\\color_icon_32px.png"), true);
		ToolsButton.create(panel, Tool.TRASH, width - 80, 5, 50, 50, new ImageIcon(path + "\\trash_icon_32px.png"), true);
		ToolsButton.setButtonColors();

		panel.repaint();
	}
}
