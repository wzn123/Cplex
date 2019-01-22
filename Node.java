import java.awt.Point;

public class Node{
	private final Point loca;
	private final double demand;
	public Node(int x,int y, double demand) {
		this.loca = new Point(x,y);
		this.demand=demand;
	}
	public double getX() {
		return loca.getX();
	}
	public double getY() {
		return loca.getY();
	}
	public double getDemand() {
		return demand;
	}
	public Point getPoint(){
		return loca.getLocation();
	}
	
	public String toString() {
		return loca.toString();
	}
}