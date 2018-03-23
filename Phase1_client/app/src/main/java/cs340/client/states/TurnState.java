package cs340.client.states;

import cs340.shared.model.Player;
import cs340.shared.requests.DrawFaceupRequest;

public abstract class TurnState {
	private boolean success = true;
	/**
	 * Each method returns the new Turn State (which can be itself).
	 */
	public abstract TurnState claimRoute(Player player);
	public abstract TurnState drawDestination(Player player);
	public abstract TurnState discardDestination(Player player);
	public abstract TurnState drawTrainCard (Player player);
	public abstract TurnState drawFaceupCard (Player player, DrawFaceupRequest request);
	/**
	 *
	 * @returns true if the last call was made successfully
	 */
	public boolean isSuccess() {
		return success;
	}
	protected void fail() {
		success = false;
	}
}
