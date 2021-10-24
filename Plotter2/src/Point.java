import java.awt.Color;

public class Point {
	float x;
	float y;
	Color color;

	public Point(float x, float y, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
		System.out.println(this);
	}
	
	public String toString() {
		return "Point [X:" + x + " | Y: " + y + "]"; 
	}
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

}
