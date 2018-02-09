package cs340.server.services;

import cs340.shared.requests.LoginRequest;
import cs340.shared.model.ModelFacade;
import cs340.shared.model.Player;

public class LoginService {
private static LoginService instance;
	
	private LoginService (){}
	
	public static LoginService getInstance(){
		if(instance == null){
			instance = new LoginService();
		}
		
		return instance;
	}
	
	//Attemps to log in the player
	//@param request	a LoginRequest containing the username and password used in the login attempt
	//@pre 	request != null
	//
	//@post		string is empty if failed, contains auth token on success
	public static String login(LoginRequest request){
		return getInstance().loginInner(request);
	}
	
	private String loginInner(LoginRequest request){		
		Player p = new Player(request.getUsername(),request.getPassword(),""); //New player object with username and password from loginrequest		
		return ModelFacade.playerExists(p); //Returns the auth token if the player exists; otherwise, returns ""
	}
}
