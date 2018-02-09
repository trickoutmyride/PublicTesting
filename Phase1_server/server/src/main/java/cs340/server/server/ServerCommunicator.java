package cs340.server.server;

import org.glassfish.tyrus.server.Server;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import cs340.server.communication.WebSocketHandler;

//Stripped down server class using glassfish tyrus to interface with the websocket

//Main server communicator class
public class ServerCommunicator {
	private static final int SERVER_PORT_NUMBER = 8080;
	
	//Main
	public static void main(String[] args){
		runServer();
	}
	
	//Runs the server, stops if there's an exception on startup
	public static void runServer(){
		Server server = new Server("localhost", SERVER_PORT_NUMBER, "/", null, WebSocketHandler.class); //Creates a new server with the websockethandler as an endpoint

		try{
			server.start(); //Starts the server
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			String cmd = "";
			while(!cmd.equalsIgnoreCase("quit") && !cmd.equalsIgnoreCase("exit")){ //Have to type "quit" or "exit" to quit program
				cmd = reader.readLine();
			}
		}catch(Exception e){ 
			e.printStackTrace();
		}finally{
			server.stop(); //Stops it
		}
	}
}