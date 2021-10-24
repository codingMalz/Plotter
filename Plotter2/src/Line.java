import java.awt.Color;

public class Line {
	Point point1;
	Point point2;
	Color color;
	
	public Line(Point point1, Point point2, Color color) {
		this.point1 = point1;
		this.point2 = point2;
		this.color = color;
		
		System.out.println(this);
	}

	public String toString() {
		return "Line [Point1 " + point1 + " | Point2 " + point2 + " | Color:" + color.toString() + "]";
	}
	
	public Point getPoint1() {
		return point1;
	}

	public void setPoint1(Point point1) {
		this.point1 = point1;
	}

	public Point getPoint2() {
		return point2;
	}

	public void setPoint2(Point point2) {
		this.point2 = point2;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

}
