package cs340.client.command;

import cs340.shared.command.ServerCommand;
import cs340.shared.interfaces.ICommand;
import cs340.shared.message.Message;
import cs340.client.communication.ClientCommunicator;

public class CommandManager {
	private static CommandManager instance = new CommandManager();

	private CommandManager() {
	}

	public static CommandManager getInstance() {
		return instance;
	}

	public ServerCommand makeCommand(String function, Object payload) {
		return new ServerCommand(function, payload);
	}

	public void sendCommand(String senderAuth, ICommand command) {
		ClientCommunicator.getInstance().sendMessage(new Message(senderAuth, command));
	}
}