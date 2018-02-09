package cs340.server.client;

import cs340.server.communication.CommandManager;
import cs340.shared.interfaces.IClient;
import cs340.shared.model.ModelFacade;
import cs340.shared.model.Player;

public class ClientProxy implements IClient {
	private static ClientProxy instance;
	
	private ClientProxy() {}
	
	public static ClientProxy getInstance(){
		if(instance == null){
			instance = new ClientProxy();
		}
		
		return instance;
	}
	
	public static void sendLoginResultCommand(String auth){
		if(!auth.equals("")){ //If login was successful
			getInstance().login(ModelFacade.getPlayer(auth));
		}else{ //If login failed
			getInstance().error(auth);
		}
	}

	@Override
	public void createGame(Object request) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void joinGame(Object request) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void login(Object request) {
		Player p = (Player)request;
		CommandManager.getInstance().sendCommand(p.getAuthToken(), CommandManager.makeCommand("login", p));
	}

	@Override
	public void register(Object request) {
		
	}

	@Override
	public void startGame(Object request) {			
		
	}

	@Override
	public void error(Object request) {
		CommandManager.getInstance().sendCommand((String) request, CommandManager.makeCommand("error", request));
	}
}
