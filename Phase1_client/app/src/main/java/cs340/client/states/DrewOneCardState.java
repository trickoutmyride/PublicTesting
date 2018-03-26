package cs340.client.states;

import cs340.client.services.ClientFacade;
import cs340.shared.model.ClientModel;
import cs340.shared.model.Player;
import cs340.shared.model.TrainCard;
import cs340.shared.requests.DrawFaceupRequest;

public class DrewOneCardState extends TurnState {
	public TurnState claimRoute(Player player){
		this.fail();
		ClientFacade.getInstance().error("You can only draw another card!");
		return this;
	}
	public TurnState drawDestination(Player player){
		this.fail();
		ClientFacade.getInstance().error("You can only draw another card!");
		return this;
	}
	public TurnState discardDestination(Player player) {
		this.fail();
		ClientFacade.getInstance().error("You can only draw another card!");
		System.out.println("DrewOneCardState: discardDestination should not have been called here!");
		return this;
	}
	public TurnState drawTrainCard (Player player){
		return new NotMyTurnState(player);
	}
	public TurnState drawFaceupCard (Player player, DrawFaceupRequest request){
		TrainCard drawnCard = ClientModel.getInstance().getCurrentGame().getTrainFaceup().get(request.getIndex());
		if (drawnCard.getColor().equals("wild")) {
			this.fail();
			ClientFacade.getInstance().error("You can only draw a non-wild card!");
			return this;
		}
		else {
			return new NotMyTurnState(player);
		}
	}
}