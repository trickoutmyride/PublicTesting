package cs340.client.states;

import cs340.client.services.ClientFacade;
import cs340.shared.model.ClientModel;
import cs340.shared.model.Player;
import cs340.shared.model.TrainCard;
import cs340.shared.requests.DrawFaceupRequest;

public class MyTurnState extends TurnState {
	public TurnState claimRoute(Player player){
		return new NotMyTurnState(player);
	}
	public TurnState drawDestination(Player player){
		return new NotMyTurnState(player);
	}
	public TurnState discardDestination(Player player) {
		this.fail();
		ClientFacade.getInstance().error("Invalid Destination Discard");
		System.out.println("MyTurnState: discardDestination should not have been called here!");
		return this;
	}
	public TurnState drawTrainCard (Player player){
		return new DrewOneCardState();
	}
	public TurnState drawFaceupCard (Player player, DrawFaceupRequest request){
		TrainCard drawnCard = ClientModel.getInstance().getCurrentGame().getTrainFaceup().get(request.getIndex());
		System.out.println("MyTurnState.drawFaceupCard: " + drawnCard.getColor());
		if (drawnCard.getColor().equals("wild")) {
			return new NotMyTurnState(player);
		}
		else {
			return new DrewOneCardState();
		}
	}
}
