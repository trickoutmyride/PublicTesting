package cs340.shared.message;

import cs340.client.command.ClientCommand;

public class Message {
	private String id;
	private ClientCommand contents;

	public Message(String id, ClientCommand contents) {
		this.id = id;
		this.contents = contents;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ClientCommand getContents() {
		return contents;
	}

	public void setContents(ClientCommand contents){
		this.contents = contents;
	}

}
