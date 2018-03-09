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
		switch(this.spaces){
			case 1: return 1;
			case 2: return 2;
			case 3: return 4;
			case 4: return 7;
			case 5: return 10;
			case 6: return 15;
		}
		return 0;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
}
