package cs340.client.results;

import java.util.ArrayList;

import cs340.shared.model.Player;

public class DiscardDestinationResult {
	private Player p;
	private ArrayList<String> auths;
	private int discarded;
	
	public DiscardDestinationResult(Player p, int discarded, ArrayList<String> auths) {
		this.p = p;
		this.discarded = discarded;
		this.auths = auths;
	}
	public Player getP() {
		return p;
	}
	public void setP(Player p) {
		this.p = p;
	}
	public ArrayList<String> getAuths() {
		return auths;
	}
	public void setAuths(ArrayList<String> auths) {
		this.auths = auths;
	}
	public int getDiscarded() {
		return discarded;
	}
	public void setDiscarded(int discarded) {
		this.discarded = discarded;
	}
}
