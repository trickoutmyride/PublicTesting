package cs340.client.states;

import cs340.client.communication.ServerProxy;
import cs340.shared.model.Player;
import cs340.shared.requests.DrawFaceupRequest;
import cs340.shared.requests.EndTurnRequest;

/**
 * Created by Mark on 3/22/2018.
 */

public class NotMyTurnState extends TurnState {
	// This constructor is called only when the game is beginning for non-game creators.
	public NotMyTurnState () {}

	// This constructor sends a command to the server announcing the end of this players turn.
	NotMyTurnState(Player player) {
		new ServerProxy().endTurn(new EndTurnRequest(player));
	}
	public TurnState claimRoute(Player player){
		this.fail();
		return this;
	}
	public TurnState drawDestination(Player player){
		this.fail();
		return this;
	}
	public TurnState discardDestination(Player player) {
		this.fail();
		System.out.println("NotMyTurnState: discardDestination should not have been called here!");
		return this;
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
