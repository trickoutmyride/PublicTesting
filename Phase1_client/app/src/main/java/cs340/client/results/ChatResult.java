package cs340.client.results;

import java.util.ArrayList;

public class ChatResult {
	private ArrayList<String> auths;
	private String chat;
	
	public ChatResult(ArrayList<String> auths, String chat) {
		this.auths = auths;
		this.chat = chat;
	}
	public ArrayList<String> getAuths() {
		return auths;
	}
	public void setAuths(ArrayList<String> auths) {
		this.auths = auths;
	}
	public String getChat() {
		return chat;
	}
	public void setChat(String chat) {
		this.chat = chat;
	}	
}
