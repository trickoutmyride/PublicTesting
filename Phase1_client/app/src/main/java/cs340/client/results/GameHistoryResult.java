package cs340.client.results;

public class GameHistoryResult {
	private String type;
	private String contents;
	
	public GameHistoryResult(String type, String contents) {
		this.type = type;
		this.contents = contents;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}
}
