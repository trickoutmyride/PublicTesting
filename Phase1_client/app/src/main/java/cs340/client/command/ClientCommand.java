package cs340.client.command;

import java.lang.reflect.Method;

import cs340.client.services.ClientFacade;
import cs340.shared.interfaces.ICommand;

public class ClientCommand implements ICommand {
	private String _methodName; //Specifies the method to call within the Client Facade
	private String _param; //String parameter makes this easy
	
	//Public constructor
	public ClientCommand(String method, String param){
		this._methodName = method;
		this._param = param;
	}

	@Override
	public void execute() {
		try {
			Method method = ClientFacade.class.getMethod(_methodName, String.class);
			method.invoke(ClientFacade.getInstance(), _param);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Gets the method name
	public String getMethodName(){
		return this._methodName;
	}
	
	//Sets the methodname
	public void setMethodName(String methodName){
		this._methodName = methodName;
	}
	
	//Sets the paramvalue
	public void setParam(String param){
		this._param = param;
	}
	
	//Gets the paramvalue
	public String getParam(){
		return this._param;
	}
}
