package cs340.server.server;

public interface IServer {
	public void createGame(Object request);
	public void joinGame(Object request);
	public void login(Object request);
	public void register(Object request);
	public void startGame(Object request);
}
