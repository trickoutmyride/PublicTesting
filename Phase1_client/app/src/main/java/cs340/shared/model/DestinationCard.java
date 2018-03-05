package cs340.shared.model;

public class DestinationCard {
	private String startPoint;
	private String endPoint;
	private int points;
	
	public DestinationCard(String startPoint, String endPoint, int points) {
		this.startPoint = startPoint;
		this.endPoint = endPoint;
		this.points = points;
	}
	
	public String getStartPoint() {
		return startPoint;
	}
	public void setStartPoint(String startPoint) {
		this.startPoint = startPoint;
	}
	public String getEndPoint() {
		return endPoint;
	}
	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
}
