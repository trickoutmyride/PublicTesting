package cs340.shared.message;

import com.google.gson.Gson;

import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class MessageEncoder implements Encoder.Text<Message>{
	private static Gson gson = new Gson();
	
	//Encodes an Object as a JSON string
	@Override
	public String encode(Message message){
		return gson.toJson(message);
	}
	
	//Gets a string from an InputStream
	//Used to get JSON from request bodies
	/*public String scan(InputStream inputStream)
	{
		BufferedReader bufferedReader = null;
		StringBuilder stringBuilder = new StringBuilder();
		String line;
		try{
			bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			while((line = bufferedReader.readLine()) != null)
			{
				stringBuilder.append(line + "\n");
			}
		} catch(IOException e){
		} finally {
			if(bufferedReader != null){
				try{
					bufferedReader.close();
				}
				catch(IOException e){
						
				}
			}
		}
		return stringBuilder.toString();
	}*/

	@Override
	public void destroy() { }

	@Override
	public void init(EndpointConfig arg0) {}
}
