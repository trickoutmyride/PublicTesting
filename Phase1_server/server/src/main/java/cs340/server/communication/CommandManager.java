package cs340.server.communication;

import cs340.shared.command.ClientCommand;
import cs340.shared.interfaces.ICommand;
import cs340.shared.message.Message;

public class CommandManager {
	private static CommandManager instance;
	
	private CommandManager() {}
	
	public static CommandManager getInstance(){
		if(instance == null){
			instance = new CommandManager();
		}
		
		return instance;
	}
	
	public static ClientCommand makeCommand(String function, Object payload){
		return new ClientCommand(function,payload);
	}

	public void sendCommand(String senderAuth, ICommand command) {
		WebSocketHandler.sendMessage(senderAuth, new Message(senderAuth, command));
	}
}