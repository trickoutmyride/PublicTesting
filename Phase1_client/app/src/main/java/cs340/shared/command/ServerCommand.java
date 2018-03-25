package cs340.shared.command;

import cs340.shared.interfaces.ICommand;

public class ServerCommand implements ICommand {
	private String _methodName; //Specifies the method to call within ServerFacade
	private String _param; //Object parameter makes this easy

	public ServerCommand(String method, String param) {
		this._methodName = method;
		this._param = param;
	}
	@Override
	public void execute() {}

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