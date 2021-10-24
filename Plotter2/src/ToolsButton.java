import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JPanel;

public class ToolsButton {
	static JButton[] iconButtons = new JButton[100];
	
	static int i = 0;
	static Interface inter = new Interface();
	
	public static void create(JPanel panel, Tool tool, int x, int y, int width, int height, ImageIcon icon, boolean borderPainted) {
		iconButtons[i] = new JButton();
		iconButtons[i].setIcon(icon);
		iconButtons[i].setBounds(x, y, width, height);
		iconButtons[i].setOpaque(true);
		iconButtons[i].setContentAreaFilled(true);
		iconButtons[i].setBackground(Color.black);
		iconButtons[i].setBorderPainted(borderPainted);
		iconButtons[i].setFocusable(false);
		iconButtons[i].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	switch(tool) {
            		case CREATEPOINT:
            			setToolActive(tool);
            			break;

            		case CREATELINE:
            			setToolActive(tool);
            			break;
            			
            		case FIXED:
            			if(tool.isActive() == true) tool.setActive(false);
            			else tool.setActive(true);
            			break;
            			
            		case COLOR:
            			 Interface.currentColor = JColorChooser.showDialog(null, "Choose color", null);
            			 break;
            		
            		case TRASH:
            			inter.points.clear();
            			inter.lines.clear();
            			inter.repaint();
            			Main.listFrame.update();
            			break;
            	}
            	setButtonColors();
            }
        });
		panel.add(iconButtons[i]);
		i++;
	}

	
	public static void setButtonColors() {
		for (int i = 0; i < Tool.values().length; i++) {
			if(Tool.values()[i].isActive()) iconButtons[Tool.values()[i].getI()].setBackground(Color.decode("#636363"));
			else iconButtons[Tool.values()[i].getI()].setBackground(Color.black);
		}
	}
	
	private static void setToolActive(Tool tool) {
		for (int i = 0; i < Tool.values().length; i++) {
			if(Tool.values()[i] != tool) Tool.values()[i].setActive(false);
			else Tool.values()[i].setActive(true);
		}
		
		System.out.println("Tool " + tool + " is " + tool.isActive());
	}
}

enum Tool{
	CREATEPOINT(0, true),
	CREATELINE(1, false), 
	FIXED(2, false),
	COLOR(3),
	TRASH(4);
	
		
	int i;
	boolean active;

	private Tool(int i, boolean active) {
		this.i = i;
		this.active = active;
	}
	
	private Tool(int i) {
		this.i = i;
		this.active = false;
	}
	
	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
