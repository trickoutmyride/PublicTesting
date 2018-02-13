package cs340.shared.command;

import java.lang.reflect.Method;

import cs340.shared.interfaces.ICommand;
import cs340.shared.services.ServerFacade;

public class ServerCommand implements ICommand {
	private String _methodName; //Specifies the method to call within ServerFacade
	private String _param; //Object parameter makes this easy

	public ServerCommand(String method, String param) {
		this._methodName = method;
		this._param = param;
	}
	@Override
	public void execute() {
		try{
			Method method = ServerFacade.class.getMethod(_methodName, String.class);
			method.invoke(null, _param);
		}catch(Exception e){
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
	public Object getParam(){
		return this._param;
	}
}
