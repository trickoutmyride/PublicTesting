package cs340.shared.interfaces;

public interface IServer {
	public void createGame(Object request);
	public void joinGame(Object request);
	public void register(Object request);
	public void startGame(Object request);
}