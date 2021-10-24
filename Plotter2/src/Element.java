import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class Element extends JButton{
	public Element(JPanel panel, int x, int y, int width, int height, float pointX, float pointY) {
		setBounds(x, y, width, height);
		setVisible(true);
		setFocusable(false);
		setOpaque(false);
		setBackground(Color.decode("#e6e6e6"));
		setBorder(new RoundedBorder(10));
		
		JLabel label = new JLabel("Point [X: " + pointX + " Y: " + pointY + "]");
		label.setBounds(10, 5, width, height);
		add(label);
		
		panel.add(this);
	}
	
	public Element(JPanel panel, int x, int y, int width, int height, float pointX1, float pointY1, float pointX2, float pointY2) {
		setBounds(x, y, width, height);
		setVisible(true);
		setFocusable(false);
		setOpaque(false);
		setBackground(Color.decode("#e6e6e6"));
		setBorder(new RoundedBorder(10));
		
		JLabel label = new JLabel("Line [X1: " + pointX1 + " Y1: " + pointY1 + " X2: " + pointY2 + " Y2: " + pointY2 + "]");
		label.setBounds(10, 5, width, height);
		add(label);
		
		panel.add(this);
	}
	
	private static class RoundedBorder implements Border {

	    private int radius;


	    RoundedBorder(int radius) {
	        this.radius = radius;
	    }


	    public Insets getBorderInsets(Component c) {
	        return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
	    }


	    public boolean isBorderOpaque() {
	        return true;
	    }


	    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
	        g.setColor(Color.decode("#d6d6d6"));
	    	g.fillRoundRect(x, y, width-1, height-1, radius, radius);
	    }
	}
}
