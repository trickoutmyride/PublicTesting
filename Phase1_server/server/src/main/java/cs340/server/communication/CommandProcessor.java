package cs340.server.communication;

import cs340.shared.command.ServerCommand;
import cs340.shared.message.Message;

public class CommandProcessor {
	private static CommandProcessor instance;
	
	private CommandProcessor() {}
	
	public static CommandProcessor getInstance(){
		if(instance == null){
			instance = new CommandProcessor();
		}
		
		return instance;
	}
	
	public static void handle(Message message){
		getInstance().handleInner(message);
	}
	
	private void handleInner(Message message){
		ServerCommand cmd = (ServerCommand) message.getContents();
		cmd.execute();
	}
}
