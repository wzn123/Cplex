public class Vehicle{
	private final int id;
	private final double capacity;
	private final double cost;
	public Vehicle(int id, double capacity, double cost) {
		this.id = id;
		this.capacity=capacity;
		this.cost=cost;
	}
	public int getId(){
		return id;
	}
	public double getCapacity(){
		return capacity;
	}
	public double getCost(){
		return cost;
	}
}