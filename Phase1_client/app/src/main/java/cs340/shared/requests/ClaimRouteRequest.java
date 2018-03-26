package cs340.shared.requests;

import java.util.ArrayList;

import cs340.shared.model.Player;
import cs340.shared.model.Route;
import cs340.shared.model.TrainCard;

public class ClaimRouteRequest {
	private Player player;
	private Route route;
	private ArrayList<TrainCard> selectedCards;
	
	public ClaimRouteRequest(Player player, Route route, ArrayList<TrainCard> selected) {
		this.player = player;
		this.route = route;
		this.selectedCards = selected;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public ArrayList<TrainCard> getSelectedCards() {
		return selectedCards;
	}

	public void setSelectedCards(ArrayList<TrainCard> selectedCards) {
		this.selectedCards = selectedCards;
	}
}
