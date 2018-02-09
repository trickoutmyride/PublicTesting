package cs340.shared.message;

import cs340.shared.interfaces.ICommand;

public class Message {
	private String id;
	private ICommand contents;

	public Message(String id, ICommand contents) {
		this.id = id;
		this.contents = contents;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public cs340.shared.interfaces.ICommand getContents() {
		return contents;
	}

	public void setContents(cs340.shared.interfaces.ICommand contents) {
		this.contents = contents;
	}

}
