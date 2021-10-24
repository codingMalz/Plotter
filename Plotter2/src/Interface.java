import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Interface extends JPanel{
	
	static float size = 30;
	static float zoom = 50;
	static float originX = Main.mainFrameWidth / 2;
	static float originY = Main.mainFrameHeight / 2;
	static ArrayList<Point> points = new ArrayList<Point>();
	static ArrayList<Line> lines = new ArrayList<Line>();
	static boolean showInfo = true;
	static Color currentColor = Color.black;
	static float[] m = new float[100];
	
	public void paint(Graphics g) {
		System.out.println(lines);
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
				
		// Oben querstriche
	    for (int i = 0; i < size; i++) {
	    	setLineLook(g2, 2.0f, Color.black);
		    g2.drawLine((int)(originX - ((i + 1) * zoom)), (int)(originY + 5), (int)(originX - ((i + 1) * zoom)), (int)(originY - 5));
		    
	    	setLineLook(g2, 1.0f, Color.gray);
			g2.drawLine(0, (int)(originY - (i * zoom)), Main.mainFrameWidth, (int)(originY - (i * zoom)));
		}
		
		// Unten querstriche
	    for (int i = 0; i < size * 2; i++) {
	    	setLineLook(g2,  2.0f, Color.black);
		    g2.drawLine((int)(originX + ((i + 1) * zoom)), (int)(originY + 5), (int)(originX + ((i + 1) * zoom)), (int)(originY - 5));
		  
	    	setLineLook(g2, 1.0f, Color.gray);
			g2.drawLine(0, (int)(originY + (i * zoom)), Main.mainFrameWidth, (int)(originY + (i * zoom)));
	    }
	    
	    // Rechts senkrecht
	    for (int i = 0; i < size * 2; i++) {
	    	setLineLook(g2,  2.0f, Color.black);
		    g2.drawLine((int)(originX + 5), (int)(originY + ((i + 1) * zoom)), (int)(originX - 5),(int)(originY + ((i + 1) * zoom)));
		    
	    	setLineLook(g2, 1.0f, Color.gray);
			g2.drawLine((int)(originX + (i * zoom)), 0, (int)(originX + (i * zoom)), Main.mainFrameHeight);
		}
		
	    /// Links senkrecht
	    for (int i = 0; i < size; i++) {
	    	setLineLook(g2,  2.0f, Color.black);
		    g2.drawLine((int)(originX + 5), (int)(originY - ((i + 1) * zoom)), (int)(originX - 5), (int)(originY - ((i + 1) * zoom)));
		    
	    	setLineLook(g2, 1.0f, Color.gray);
			g2.drawLine((int)(originX - (i * zoom)), 0, (int)(originX - (i * zoom)), Main.mainFrameHeight);
		}
	    
	    //Origin
    	setLineLook(g2, 2.0f, Color.black);
	    g2.setPaint(Color.black);
	    
	    g2.drawLine((int)originX, 0, (int)originX, Main.mainFrameWidth);
	    g2.drawLine(0, (int)originY, Main.mainFrameHeight, (int)originY);
	    
	    //Draw points
	    for (int i = 0; i < points.size(); i++) {
	    	setLineLook(g2, 3.0f, points.get(i).getColor());
	    	g2.drawLine((int)(originX + (points.get(i).getX() * zoom) +5), (int)(originY - (points.get(i).getY() * zoom) +5), (int)(originX + (points.get(i).getX() * zoom) -5), (int)(originY - (points.get(i).getY() * zoom) -5));
	    	g2.drawLine((int)(originX + (points.get(i).getX() * zoom) -5), (int)(originY - (points.get(i).getY() * zoom) +5), (int)(originX + (points.get(i).getX() * zoom) +5), (int)(originY - (points.get(i).getY() * zoom) -5));
		}
	    
	    //Draw line
	    //reCalculateMList();
		 for (int i = 0; i < lines.size(); i++) {
			 	g2.drawLine((int)originX + (int)((lines.get(i).getPoint1().getX() * zoom)), (int)originY +  (int)(((lines.get(i).getPoint1().getY() * zoom)*-1)),(int)originX + (int)(lines.get(i).getPoint2().getX() * zoom),(int)originY +  (int)((lines.get(i).getPoint2().getY() * zoom)*-1));
		 }
	    
	    
	    //Informations panel
	    if(showInfo) {
		    g2.setColor(Color.black);
		    g2.fillRect(0, 0, 250, 100);
		    g2.setColor(Color.white);
		    g2.drawString("origin X: " + Math.abs(originX), 10, 20);
		    g2.drawString("origin Y: " +  Math.abs(originY), 10, 35);
		    g2.drawString("Zoom: " + zoom, 10, 50);
		    g2.drawString("mouse X: " + MainFrame.mouseX + "(" + MainFrame.mouseCoordinatesX + ") | mouse Y: " + MainFrame.mouseY + "(" + MainFrame.mouseCoordinatesY + ")", 10, 80);
	    }
	    
	}
	
	void reCalculateMList() {
		 for (int i = 0; i < lines.size(); i++) {
			 m[i] = (lines.get(i).getPoint1().getY() - lines.get(i).getPoint2().getY()) / (lines.get(i).getPoint1().getX() * zoom - lines.get(i).getPoint2().getX());
		 }
	}
	
	float getNumSqr(float m){
		int i = 0;
		while(m*zoom*i < Main.mainFrameWidth + 100) {
			System.out.println("M" + m);
			System.out.println("Z" + zoom);
			System.out.println("I" + i);
			System.out.println("MZI" + m*zoom*i);
			i++;

		}
		return i;
	}
	
	private void setLineLook(Graphics2D g2, float stroke, Color color) {
			g2.setStroke(new BasicStroke(stroke));
		    g2.setPaint(color);
	}
	
}
