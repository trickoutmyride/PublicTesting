package cs340.shared.model;

import java.util.ArrayList;

public class Route {
	private ArrayList<String> endpoints;
	private int spaces;
	private String color;
	
	public Route(String start, String end, int spaces, String color) {
		this.endpoints = endpoints;
		this.spaces = spaces;
		this.color = color;
	}

	public ArrayList<String> getEndpoints() {
		return endpoints;
	}

	public void setEndpoints(ArrayList<String> endpoints) {
		this.endpoints = endpoints;
	}

	public int getSpaces() {
		return spaces;
	}

	public void setSpaces(int spaces) {
		this.spaces = spaces;
	}
	
	public int getPointValue(){
		return 0; //Stub
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
}
