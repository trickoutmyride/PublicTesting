package cs340.ui.presenters.interfaces;

public interface ILoginPresenter {
    void detach();
    void login(String username, String password);
    void register(String username, String password);
}
