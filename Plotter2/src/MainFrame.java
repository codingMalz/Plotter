import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

public class MainFrame extends JFrame implements MouseMotionListener, MouseListener{

	Interface inter = new Interface();
	
	// List to store currently pressed keys;
    private final ArrayList<Character> pressedKeys = new ArrayList<Character>();
    
    // Moving speed
    int speed = 5;
    
    // Mouse position
    static float mouseX, mouseY;
    
    // Mouse position in coordinates
    static int mouseCoordinatesX, mouseCoordinatesY;
    
	public MainFrame(int width, int height) {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((dim.width / 2) - width /2 , (dim.height / 2) - height /2, width, height);
		setVisible(true);
		getContentPane().add(inter);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addMouseMotionListener(this);
		addMouseListener(this);   
		
		getContentPane().setPreferredSize(new Dimension(1000, 1000));
		
		addKeyListener(new KeyListener() {
		    public void keyPressed(KeyEvent e) {
		    	pressedKeys.add(e.getKeyChar());
		    	for (int i = 0; i < pressedKeys.size(); i++) {
		    		if(inter.originX < inter.zoom * inter.size) {
			    		if(inter.originY < inter.zoom * inter.size) {
				    		if(inter.originX > (inter.zoom * inter.size) *-1) {
					    		if(inter.originY > (inter.zoom * inter.size) *-1) {
						    		if(pressedKeys.get(i) ==  'w') inter.originY += speed;
							    	else if(pressedKeys.get(i) == 'a') inter.originX += speed;
							    	else if(pressedKeys.get(i) == 's') inter.originY -= speed;
							    	else if(pressedKeys.get(i) == 'd') inter.originX -= speed;	
							    	else if(pressedKeys.get(i) == 'w') inter.originX -= speed;
							    	else if(pressedKeys.get(i) == 'w'&& pressedKeys.get(i) == 'a') {
							    		inter.originY += speed;
							    		inter.originX += speed;
							    	}else if(pressedKeys.get(i) == 'w' && pressedKeys.get(i) == 'd') {
							    		inter.originY += speed;
							    		inter.originX -= speed;
							    	}else if(pressedKeys.get(i) == 'a' && pressedKeys.get(i) == 's') {
							    		inter.originY -= speed;
							    		inter.originX += speed;
							    	}else if(pressedKeys.get(i) == 's' && pressedKeys.get(i) == 'd') {
							    		inter.originY -= speed;
							    		inter.originX -= speed;
							    	}else if(pressedKeys.get(i) == 'i') {
							    		if(inter.showInfo == true) inter.showInfo = false;
							    		else inter.showInfo = true;
							    	}
					    		}else inter.originY= ((inter.zoom * inter.size) -1) *-1;
				    		}else inter.originX= ((inter.zoom * inter.size) -1) *-1;
			    		}else inter.originY= (inter.zoom * inter.size) -1;
		    		}else inter.originX = (inter.zoom * inter.size) -1;
					    		
				}
	    		inter.repaint();
		    }
		    
		    public void keyReleased(KeyEvent e) {
		    	pressedKeys.clear();
		    }

		    public void keyTyped(KeyEvent e) { /* ... */ }
		});
		
		addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				if (e.getWheelRotation() < 0) {
					if (inter.zoom <= 230) {
						inter.zoom -= e.getWheelRotation();
						inter.repaint();
					}
						
				} else {
					if (inter.zoom >= 30) {
						inter.zoom -= e.getWheelRotation();
						inter.repaint();
					}
				}
			}
		});
	}

	@Override
	public void mouseDragged(MouseEvent e) { /* ... */ }

	float x, y;
	boolean drawLine = false;
	
	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = (MouseInfo.getPointerInfo().getLocation().x - getLocation().x) - 8;
		mouseY = (MouseInfo.getPointerInfo().getLocation().y - getLocation().y) - 32;
		
		x = (mouseX - inter.originX) / inter.zoom;
		y = ((mouseY - inter.originY) / inter.zoom)*-1;
		
		mouseCoordinatesX = (int)Math.round(x);
		mouseCoordinatesY = (int)Math.round(y);
		
		if(drawLine) {
			if(Tool.FIXED.isActive() == true) {
				inter.lines.get(inter.lines.size() -1).getPoint2().setX(mouseCoordinatesX);
				inter.lines.get(inter.lines.size() -1).getPoint2().setY(mouseCoordinatesY);
			}else {
				inter.lines.get(inter.lines.size() -1).getPoint2().setX(x);
				inter.lines.get(inter.lines.size() -1).getPoint2().setY(y);
			}
		}

		inter.repaint();
		
	}

	@Override
	public void mouseClicked(MouseEvent e) { /* ... */ }
	
	@Override
	public void mousePressed(MouseEvent e) {
		boolean pointAlreadyExists = false;
		for (int i = 0; i < inter.points.size(); i++) if(inter.points.get(i).getX() == (int)Math.round(x) && inter.points.get(i).getY() ==  (int)Math.round(y)) pointAlreadyExists = true;
		
		Point point = null;
		
		if(pointAlreadyExists == false) {
				if(Tool.FIXED.isActive()) point = new Point((int)Math.round(x), (int)Math.round(y), inter.currentColor);
				else point = new Point(x, y, inter.currentColor);
				inter.repaint();
		}
		
		inter.points.add(point);
		
		if(drawLine != true) {
			if(Tool.CREATELINE.isActive()) {
				inter.lines.add(new Line(point, new Point(point.getX(), point.getY(), inter.currentColor), inter.currentColor));
				drawLine = true;
			}
		}else {
			//System.out.println(inter.getNumSqr(inter.m[0]));
			drawLine = false;
		}
		 Main.listFrame.update();
	}

	@Override
	public void mouseReleased(MouseEvent e) { /* ... */ }

	@Override
	public void mouseEntered(MouseEvent e) { /* ... */}

	@Override
	public void mouseExited(MouseEvent e) { /* ... */ }
}
