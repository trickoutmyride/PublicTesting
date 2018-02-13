package cs340.shared.message;

import cs340.shared.command.ServerCommand;

/**
 * Created by Mark on 2/13/2018.
 */

public class ServerMessage {
		private String id;
		private ServerCommand contents;

		public ServerMessage(String id, ServerCommand contents) {
			this.id = id;
			this.contents = contents;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public ServerCommand getContents() {
			return contents;
		}

		public void setContents(ServerCommand contents) {
			this.contents = contents;
		}
	}
