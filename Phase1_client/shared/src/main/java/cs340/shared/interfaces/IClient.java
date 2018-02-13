package cs340.shared.interfaces;

public interface IClient {
	public void createGame(Object request);
	public void joinGame(Object request);
	public void login(Object request);
	public void register(Object request);
	public void startGame();
	public void error(Object request);
}