package cs340.client.states;

import cs340.shared.model.Player;
import cs340.shared.requests.DrawFaceupRequest;

/**
 * Created by Mark on 3/22/2018.
 */

public class DrewDestinationCardState extends TurnState {
	public TurnState claimRoute(Player player){
		this.fail();
		return this;
	}
	public TurnState drawDestination(Player player){
		this.fail();
		return this;
	}
	public TurnState discardDestination(Player player) {
		return new NotMyTurnState(player);
	}
	public TurnState drawTrainCard (Player player){
		this.fail();
		return this;
	}
	public TurnState drawFaceupCard (Player player, DrawFaceupRequest request){
		this.fail();
		return this;
	}
}
