package cs340.shared.message;

import com.google.gson.Gson;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class MessageDecoder implements Decoder.Text<Message>{
	private static Gson gson = new Gson();

	@Override
	public void destroy() {


	}

	@Override
	public void init(EndpointConfig arg0) {


	}

	@Override
	//Decodes an object from a JSON inputstream
	public Message decode(String input) throws DecodeException{
		return gson.fromJson(input, Message.class);
	}

	@Override
	public boolean willDecode(String arg0) {
		return (arg0 != null);
	}

}
